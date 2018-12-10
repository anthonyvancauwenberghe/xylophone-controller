#include <math.h>
#include "kinematics.h"
//#include <iostream>
//using namespace std;

const double RADIAN = 0.0174533;

double Kinematics::toRadians(double d){
    return d * RADIAN;
}
double Kinematics::toDegrees(double r){
    return r/RADIAN;
}

double Kinematics::law_of_cosines(double a, double b, double c){
    return toDegrees(acos( ( pow(a,2) + pow(b,2) - pow(c,2) ) / (2*a*b) ) );
}

double Kinematics::tangent_law(double a, double b, double alpha){
    return atan(b*sin(toRadians(-alpha))/(a+b*cos(toRadians(-alpha))));
}

Vector2 Kinematics::getPointOnCircle(Vector2 origin, double radius, double degrees){

    double x = cos(toRadians(degrees)) * radius;
    double y = -sin(toRadians(degrees)) * radius;
    Vector2 vector(x,y);
    vector.add(origin);
    return vector;
}

double Kinematics::getCircleArc(double radius, double x){

    return toDegrees(acos(x/radius));
}

Vector3 Kinematics::getModTop(Vector3 orig, double radius, double degrees){

    Vector2 in(orig.x,orig.z);
    Vector2 out = getPointOnCircle( in ,radius,-90-degrees);
    return {out.x, orig.y, out.y};
}

Vector3 Kinematics::getModSide(Vector3 orig, double radius, double degrees){
    Vector2 in ( orig.z,orig.y);
    Vector2 out = getPointOnCircle( in ,radius,-90+degrees);
    return {orig.x,out.y,out.x};
}

double Kinematics::getDegTop(Vector3 orig, double radius, Vector3 pos){
    return 90-getCircleArc(radius,-(pos.x-orig.x));
}

Vector3 Kinematics::getOffsetModTop(Vector3 effect, double d){
    double dst = Vector2(effect.x,effect.z).dst(Vector2(FLEX_S2.x,FLEX_S2.z));
    double arc_offset = getDegTop(FLEX_S2,dst, effect);
    return getModTop(Vector3(FLEX_S2.x,effect.y,FLEX_S2.z),dst,-d+arc_offset);
}

double Kinematics::getDegSide(Vector3 orig, double radius, Vector3 pos){
    return getCircleArc(radius,pos.z-orig.z);
}

//Forward kinematics

Vector3 Kinematics::getEffectorPos(double servo1, double servo2, double servo3){

    Vector3 flex_s3 = getModSide(FLEX_S2,L2,servo2);
    Vector3 reach = getModSide({STICK_OFFSET,flex_s3.y,flex_s3.z},L3,servo2+servo3);
    return getOffsetModTop(reach,servo1);
}

//Inverse kinematics

Vector3 Kinematics::getDegs(Vector3 pos){

    //d1
    double dst = Vector2(pos.x,pos.z).dst(Vector2(FLEX_S2.x,FLEX_S2.z));
    double arc_offset = getDegTop(FLEX_S2,dst, Vector3(STICK_OFFSET,pos.y,pos.z));
    double d1 = -(getDegTop(FLEX_S2,dst,pos)-arc_offset);

    //d3
    Vector3 effect_norm = getOffsetModTop(pos,-d1);
    double c = Vector3(effect_norm.x-STICK_OFFSET,effect_norm.y,effect_norm.z).dst(FLEX_S2);
    double d3 = 180-law_of_cosines(L3,L2,c);

    //d2
    double d2 = 90 - toDegrees(atan((-(pos.y-FLEX_S2.y))/(-pos.z+FLEX_S2.z)) - tangent_law(L2,L3,d3));
    return {d1,d2,d3};
}

//int main()
//{
//    Kinematics kinematics;
//    kinematics.getEffectorPos(10,15,30).print();
//    return 0;
//}





