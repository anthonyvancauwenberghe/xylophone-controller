package Controller;

import Arduino.ArduinoSerialManager;

public class RobotController {
    protected ArduinoSerialManager serialManager;

    public int angleMotor1;

    public int angleMotor2;

    public int angleMotor3;

    public int defaultDelay = 400;


    public RobotController() {
        this.serialManager = ArduinoSerialManager.getInstance();
    }

    public void move(int angleMotor1, int angleMotor2, int angleMotor3) {
       this.move(angleMotor1,angleMotor2,angleMotor3,this.defaultDelay );
    }

    public void move(int angleMotor1, int angleMotor2, int angleMotor3,int delay) {
        try {Thread.sleep(delay);} catch(Exception e){}
        this.processSafetyControls(angleMotor1, angleMotor2, angleMotor3);
        this.serialManager.write(String.valueOf(angleMotor1) + ',' + String.valueOf(angleMotor2) + ',' + String.valueOf(angleMotor3));
        this.angleMotor1 = angleMotor1;
        this.angleMotor2 = angleMotor2;
        this.angleMotor3 = angleMotor3;
    }

    private void processSafetyControls(int angleMotor1, int angleMotor2, int angleMotor3) {
        if (angleMotor1 < -20 || angleMotor1 > 35) {
            throw new RuntimeException("Safety Values bottom motor Exceeded");
        }
        if (angleMotor2 < -90 || angleMotor2 > -40) {
            throw new RuntimeException("Safety Values middle motor Exceeded");
        }
        if (angleMotor3 < -10 || angleMotor3 > 60) {
            throw new RuntimeException("Safety Values top motor Exceeded");
        }
    }
}
