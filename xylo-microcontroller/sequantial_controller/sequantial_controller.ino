#include <kinematics.h>
#include <Adafruit_PWMServoDriver.h>

#define SERVOMIN  112// this is the 'minimum' pulse length count (out of 4096)
#define SERVOMAX  560// this is the 'maximum' pulse length count (out of 4096)

//Connect servos to correct pins
#define SERVO1 0 //bottom
#define SERVO2 1 //mid
#define SERVO3 2 //top

//Misc const
const boolean DEBUG = 1;
const int MAX_NUM_NOTES = 50;

//Offsets
const int SERVO1_OFFSET = 5; // bottom servo
const int SERVO3_OFFSET = -15; // small top servo

//Movement Configs
const int STABILITY_DELAY = 10;
const float HOVER_HEIGHT = 1.8; // cm / 10
const float HIT_DROP = 0.3;
const float LIFT_DELAY = 40;
const Vector3 DEFAULT_POS = Vector3(0,HOVER_HEIGHT,0);

//Dyn
boolean busy_playing = 0;
Vector3 current_pos;
int packeti = 0;

//Sequence (song)
Vector3 pos_sequence[MAX_NUM_NOTES];
int dly_sequence[MAX_NUM_NOTES];

Adafruit_PWMServoDriver pwm = Adafruit_PWMServoDriver();

Kinematics kinematics;

#ifdef __arm__
// should use uinstd.h to define sbrk but Due causes a conflict
extern "C" char* sbrk(int incr);
#else  // __ARM__
extern char *__brkval;
#endif  // __arm__
 
int freeMemory() {
  char top;
#ifdef __arm__
  return &top - reinterpret_cast<char*>(sbrk(0));
#elif defined(CORE_TEENSY) || (ARDUINO > 103 && ARDUINO != 151)
  return &top - __brkval;
#else  // __arm__
  return __brkval ? &top - __brkval : &top - __malloc_heap_start;
#endif  // __arm__
}

void setup() {
  Serial.begin(115200);
  Serial.println("Xylophone controller");
  pwm.begin();
  pwm.setPWMFreq(60);
  setServos(kinematics.getDegs(DEFAULT_POS));
}

void loop(){
  if(!busy_playing){
    if(readCMD()){
      Serial.println("Sequence successfully read, playing...");
      if(DEBUG){printSequence();}
      busy_playing = 1;
      executeSequence();
    }
  }
}

void setServos(Vector3 angle_vector){
  float s1 = -angle_vector.x+SERVO1_OFFSET;
  float s2 = -angle_vector.y;
  float s3 = angle_vector.z+SERVO3_OFFSET;
  Serial.print("Setting servos: ");
  Serial.print(s1);Serial.print(" ");
  Serial.print(s2);Serial.print(" ");
  Serial.println(s3);
  writeToMotor(SERVO1,s1);
  writeToMotor(SERVO2,s2);
  writeToMotor(SERVO3,s3);
}

void writeToMotor(int j,double pwmvalue)
{  
  pwmvalue = map(pwmvalue,-90,90,SERVOMIN,SERVOMAX);
  // do not remove this safety function to avoid hardware damages
  pwmvalue = constrain(pwmvalue,SERVOMIN,SERVOMAX);
  pwm.setPWM(j, 0, pwmvalue); // function by Adafruit library
  delay(STABILITY_DELAY);
}

boolean readCMD(){
  
  if(Serial.available()){
    String cmd = Serial.readStringUntil('\n');
    Serial.println(cmd);
    while(cmd.indexOf(';')!=-1){
      recordPacket(cmd.substring(0,cmd.indexOf(';')));
      packeti++;
      cmd = cmd.substring(cmd.indexOf(';')+1,cmd.length());
    }
    return 1;
  }
  return 0;
}

void recordPacket(String packet){
  String x = packet.substring(0,packet.indexOf(","));
  packet = packet.substring(packet.indexOf(",")+1,packet.length());
  String z = packet.substring(0,packet.indexOf(","));
  packet = packet.substring(packet.indexOf(",")+1,packet.length());
  String d = packet.substring(0,packet.indexOf(","));
  pos_sequence[packeti] = Vector3(x.toFloat(),HOVER_HEIGHT,z.toFloat());
  dly_sequence[packeti] = d.toInt();
  //Serial.println(freeMemory());
}

void executeSequence(){
  Serial.println("-------------SONG--------------");
  for(int i=0; i<packeti; i++){
    if(dly_sequence[i]!=0){
      //move
      setServos(kinematics.getDegs(pos_sequence[i]));
      current_pos = pos_sequence[i];
      //wait
      if(i!=0){
        delay(dly_sequence[i-1]);
      }else{
        delay(1000);
      }
      //hit
      hitKey();
    }
  }
  Serial.println("-------------------------------");
  Serial.println("Finished playing.");
  Serial.flush();
  busy_playing = 0;
  packeti = 0;
  setServos(kinematics.getDegs(DEFAULT_POS));
}

void hitKey(){
  Vector3 target = Vector3(current_pos.x,current_pos.y-HIT_DROP,current_pos.z);
  setServos(kinematics.getDegs(target));
  //delay(LIFT_DELAY);
  setServos(kinematics.getDegs(current_pos));
  delay(LIFT_DELAY);
}

void printSequence(){
  Serial.println("-------------SEQUENCE--------------");
  for(int i=0; i<MAX_NUM_NOTES; i++){
    if(dly_sequence[i]!=0){
      
      Serial.print(V3toString(pos_sequence[i]));
      Serial.print(" delay: ");
      Serial.println(dly_sequence[i]);
    }
  }
  Serial.println("-----------------------------------");
}

char* V3toString(Vector3 v){
  char str[50];
  const char h[] = "Vector3 (";
  const char c[2] = ",";
  const char t[2] = ")";
  char x[10],y[10],z[10];
  strcpy(str, h);
  String(v.x).toCharArray(x,10);
  strcat(str, x);
  strcat(str, c);
  String(v.y).toCharArray(y,10);
  strcat(str, y);
  strcat(str, c);
  String(v.z).toCharArray(z,10);
  strcat(str, z);
  strcat(str, t);
  return str;
}
