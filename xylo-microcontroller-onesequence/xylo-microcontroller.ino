#include <LiquidCrystal.h>

#define METAL 1

#if defined PLASTIC
#define SERVOMIN  140// this is the 'minimum' pulse length count (out of 4096)
#define SERVOMAX  540// this is the 'maximum' pulse length count (out of 4096)
#elif defined METAL
#define SERVOMIN  112// this is the 'minimum' pulse length count (out of 4096)
#define SERVOMAX  560// this is the 'maximum' pulse length count (out of 4096)
#endif

#define NUM_MOTORS 3 // for now we only use two joints simultaneously
#define LEFTEND -90 // for calibration?
#define RIGHTEND 90 // for calibration?

#include <string.h>
#include <stdlib.h>
#include <Servo.h> 
#include <Wire.h>
#include <Adafruit_PWMServoDriver.h>

float incoming[NUM_MOTORS]; //buffer
float pwmvalue[NUM_MOTORS]; //buffer
float calib[NUM_MOTORS]; // used to calibrate offsets
byte i = 0;
char record[100];
char recvchar;
byte indx = 0;
Adafruit_PWMServoDriver pwm = Adafruit_PWMServoDriver();

Servo servo0;
Servo servo1;
Servo servo2;

void setup() 
{
  Serial.begin(9600);
  pwm.begin();
  pwm.setPWMFreq(60); 
  delay(10);
  zeroCalib();
  // offsets of angular positions to be determined by students for each motor
  setCalib(0,0); // offset for motor 0
  setCalib(1,0); // offset for motor 1
  setCalib(2,0); // offset for motor 2
  servo0.attach(0);
  servo1.attach(1);
  servo2.attach(2);
  while(!Serial);
}

// main function
// receives bytes from Serial communication
// If full data packages is received, values are extracted
// If data package is correct, motor positions are being updated
void loop() 
{
  
    if (Serial.available())
    {
        recvchar = Serial.read();
        if (recvchar != '\n')
        { 
            record[indx++] = recvchar;
        }
        else if (recvchar == '\n')
        {
          record[indx] = '\0';
          indx = 0;
          Serial.println(record);
          getData(record); // extract motor positions from data package
          writeToMotor(); // write pwm values to motor
          printData(pwmvalue); // for bebugging send pwm values to monitor
        }          
    }

}

// extract data from data packages
// expected format: VALUE KOMMA VALUE \n
void getData(char record[])
{
    i = 0;
    char *index = strtok(record, ",");
    while(index != NULL)
    {
        incoming[i++] = atof(index); 
        index = strtok(NULL, ",");
    }
}

// update servo motor positions
void writeToMotor()
{
  int lastHitValue = 0;
    if(i == NUM_MOTORS)
    {
for (byte j = 0 ; j < NUM_MOTORS ; j++)
        {     
          incoming[j] += calib[j];
          pwmvalue[j] = map(incoming[j],LEFTEND,RIGHTEND,SERVOMIN,SERVOMAX);
          // do not remove this safety function to avoid hardware damages
          pwmvalue[j] = constrain(pwmvalue[j],SERVOMIN,SERVOMAX);
          
          if(j==2){
            pwm.setPWM(j, 0, pwmvalue[j]-100); // function by Adafruit library
          }
          else{
            pwm.setPWM(j, 0, pwmvalue[j]); // function by Adafruit library
          }
        }

        delay(250);
      
        for (byte j = 0 ; j < NUM_MOTORS ; j++)
        {     
          incoming[j] += calib[j];
          pwmvalue[j] = map(incoming[j],LEFTEND,RIGHTEND,SERVOMIN,SERVOMAX);
          // do not remove this safety function to avoid hardware damages
          pwmvalue[j] = constrain(pwmvalue[j],SERVOMIN,SERVOMAX);
          if(j==1){
           pwm.setPWM(j, 0, pwmvalue[j]+3); // function by Adafruit library 
          }
          else{
           pwm.setPWM(j, 0, pwmvalue[j]); // function by Adafruit library 
          }
          
          if(j==2){
            delay(70);
            lastHitValue = pwmvalue[j];
            pwm.setPWM(2, 0, lastHitValue - 100);
          }
        }
        
    }
    else
    {
        Serial.println("Enter correct number of values separated by commas!");
    }
   
}

void writeToMotor(int j,double pwmvalue)
{  
          pwmvalue += calib[j];
          pwmvalue = map(pwmvalue,-90,90,SERVOMIN,SERVOMAX);
          // do not remove this safety function to avoid hardware damages
          pwmvalue = constrain(pwmvalue,SERVOMIN,SERVOMAX);
          pwm.setPWM(j, 0, pwmvalue); // function by Adafruit library
          delay(10);
}

// Print data
void printData(float data[])
{
    for (byte j = 0 ; j < NUM_MOTORS ; j++)
    {
      Serial.print(data[j]);
      Serial.print('\t');
    }
    Serial.println(); 
}

// Initialize all calibration values to zero
void zeroCalib()
{
    for (byte j = 0 ; j < NUM_MOTORS ; j++)
      calib[j] = 0;
}

// Update calibration value
void setCalib(int motor,int val)
{
    if(motor < NUM_MOTORS)
        calib[motor] = val;
    else
       Serial.println("Enter a valid motor number"); 
}
