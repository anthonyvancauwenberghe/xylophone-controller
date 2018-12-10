#include <kinematics.h>
#include <Servo.h>

const int PRINT_PRECISION = 3;
const int MAX_NUM_NOTES = 50;

const int SERVO1_BOUNDS[2] = {-60,60};
const int SERVO2_BOUNDS[2] = {0,90};
const int SERVO3_BOUNDS[2] = {-45,45};

const boolean DEBUG = 1;

boolean busy_playing = 0;
Vector3 pos_sequence[MAX_NUM_NOTES];
double dly_sequence[MAX_NUM_NOTES];

Servo servo1;
Servo servo2;
Servo servo3;

Kinematics kinematics;

void setup() {
  
//  servo1.attach();
//  servo2.attach();
//  servo3.attach();
  Serial.begin(9600);
  Serial.println("Xylophone controller");
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

void setServo(int servoid, double degrees){
   if(DEBUG){Serial.print("Setting servo ");Serial.print(servoid);Serial.print(" ");Serial.println(degrees);}
   switch(servoid){
    case 1:
      if(degrees < SERVO1_BOUNDS[0] || degrees > SERVO1_BOUNDS[1]){
        Serial.println("Warning: Outbound servo1 setting!");
        break;
      }else{
        servo1.write(degrees);
        break;
      }
    case 2:
      if(degrees < SERVO2_BOUNDS[0] || degrees > SERVO2_BOUNDS[1]){
        Serial.println("Warning: Outbound servo2 setting!");
        break;
      }else{
        servo2.write(degrees);
        break;
      }
    case 3:
      if(degrees < SERVO3_BOUNDS[0] || degrees > SERVO3_BOUNDS[1]){
        Serial.println("Warning: Outbound servo3 setting!");
        break;
      }else{
        servo3.write(degrees);
        break;
      }
    default:
      Serial.println("Warning: Invalid servoID!");
  }
}

double readServo(int servoid){
  switch(servoid){
    case 1:
      return servo1.read();
    case 2:
      return servo2.read();
    case 3:
      return servo3.read();
    default:
      Serial.println("Warning: Invalid servoID!");
      return 0;
  }
}

boolean readCMD(){
  
  if(Serial.available()){
    Serial.println("Reading.");
    String cmdstr = Serial.readStringUntil("\n");
    int i = 0;
    while(cmdstr.indexOf(";") != -1){
        String seqpart = cmdstr.substring(0,cmdstr.indexOf(";"));
        String x = seqpart.substring(0,seqpart.indexOf(","));
        seqpart = seqpart.substring(seqpart.indexOf(",")+1,seqpart.length());
        String y = seqpart.substring(0,seqpart.indexOf(","));
        seqpart = seqpart.substring(seqpart.indexOf(",")+1,seqpart.length());
        String z = seqpart.substring(0,seqpart.indexOf(","));
        seqpart = seqpart.substring(seqpart.indexOf(",")+1,seqpart.length());
        String d = seqpart.substring(0,seqpart.indexOf(","));
        cmdstr = cmdstr.substring(cmdstr.indexOf(";")+1,cmdstr.length());
        pos_sequence[i] = Vector3(x.toFloat(),y.toFloat(),z.toFloat());
        dly_sequence[i] = d.toFloat();
        i++;
    }
    Serial.flush();
    return 1;
  }else{
    return 0;
  }
}

void executeSequence(){
  busy_playing = 0;
  for(int i=0; i<MAX_NUM_NOTES; i++){
    if(dly_sequence[i]!=0){
      double s1 = kinematics.getDegs(pos_sequence[i]).x;
      double s2 = kinematics.getDegs(pos_sequence[i]).y;
      double s3 = kinematics.getDegs(pos_sequence[i]).z;
      setServo(1,s1);
      setServo(2,s2);
      setServo(3,s3);
      delay(dly_sequence[i]);
    }
  }
  Serial.println("Finished playing.");
}

void printSequence(){

  for(int i=0; i<MAX_NUM_NOTES; i++){
    if(dly_sequence[i]!=0){
      Serial.print(V3toString(pos_sequence[i]));
      Serial.print(" delay: ");
      Serial.println(dly_sequence[i]);
    }
  }
}

char* V3toString(Vector3 v){
  char str[50];
  const char h[] = "Vector3 (";
  const char c[2] = ",";
  const char t[2] = ")";
  char x[10],y[10],z[10];
  strcpy(str, h);
  dtostrf(v.x,PRINT_PRECISION+3,PRINT_PRECISION,x);
  strcat(str, x);
  strcat(str, c);
  dtostrf(v.y,PRINT_PRECISION+3,PRINT_PRECISION,y);
  strcat(str, y);
  strcat(str, c);
  dtostrf(v.z,PRINT_PRECISION+3,PRINT_PRECISION,z);
  strcat(str, z);
  strcat(str, t);
  return str;
}


