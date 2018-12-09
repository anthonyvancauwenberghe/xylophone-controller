//
// Created by Dudd3r on 12/8/2018.
//
#include <math.h>
#include <iostream>
using namespace std;
#ifndef KINEMATICS_KINEMATICS_H
#define KINEMATICS_KINEMATICS_H
#endif //KINEMATICS_KINEMATICS_H

class Vector2{
public:
    double x,y;

    Vector2(double x,double y){
        this->x = x;
        this->y = y;
    }

    double dst(Vector2 v){
        return sqrt(pow(v.x-x,2) + pow(v.y-y,2));
    };
    void add(Vector2 v){
        x += v.x;
        y += v.y;
    }
    void sub(Vector2 v){
        x -= v.x;
        y -= v.y;
    };
    void print(){
        cout << "Vector2 [" << x << "," << y << "]\n";
    }
};

class Vector3{
public:
    double x,y,z;

    Vector3( double x,double y,double z){
        this->x = x;
        this->y = y;
        this->z = z;
    }

    double dst(Vector3 v){
        return sqrt(pow(v.x-x,2) + pow(v.y-y,2) + pow(v.z-z,2));
    };
    void add(Vector3 v){
        x += v.x;
        y += v.y;
        z += v.z;
    }
    void sub(Vector3 v){
        x -= v.x;
        y -= v.y;
        z -= v.z;
    };
    void print(){
        cout << "Vector3 [" << x << "," << y << "," << z << "]\n";
    }
};

//Measurements

static const Vector3 FLEX_S2 = {0,1.875,-3.65};
static const double STICK_OFFSET = -0.4;
static const double L2 = 1.075;
static const double L3 = 1.8;
