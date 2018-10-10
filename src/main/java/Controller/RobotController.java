package Controller;

import Arduino.ArduinoSerialManager;

public class RobotController {
    protected ArduinoSerialManager serialManager;

    public int angleMotor1 = 0;

    public int angleMotor2 = 0;


    public RobotController() {
        this.serialManager = ArduinoSerialManager.getInstance();
    }

    public void move(int angleMotor1, int angleMotor2) {
        this.serialManager.write(String.valueOf(angleMotor1) + ',' + String.valueOf(angleMotor2));
        this.angleMotor1 = angleMotor1;
        this.angleMotor2 = angleMotor2;
    }
}
