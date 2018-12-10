#include <kinematics.h>
const int PRINT_PRECISION = 3;

void setup() {
  Kinematics kinematics;
  Serial.begin(9600);
  Vector3 pos = kinematics.getEffectorPos(10,15,20);
  Serial.println(V3toString(pos));
}
void loop(){}

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


