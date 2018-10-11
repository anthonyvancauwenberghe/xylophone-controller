package Player;

import Controller.RobotController;

public class KeyPlayer {

    protected RobotController controller;

    public KeyPlayer(RobotController controller) {
        this.controller = controller;
    }

    public void playCLow() {
        this.goMiddle(-13);
        this.controller.move(-13,hitKeyMotor2Coordinate(),hitKeyMotor3Coordinate());
    }

    public void playCHigh() {
        this.goMiddle(30);
        this.controller.move(30,hitKeyMotor2Coordinate(),hitKeyMotor3Coordinate()-8);
    }

    public void playA() {
        this.goMiddle(17);
        this.controller.move(17,hitKeyMotor2Coordinate(),hitKeyMotor3Coordinate());
    }

    public void playG() {
        this.goMiddle(12);
        this.controller.move(12,hitKeyMotor2Coordinate(),hitKeyMotor3Coordinate());
    }

    public void playB() {
        this.goMiddle(25);
        this.controller.move(25,hitKeyMotor2Coordinate(),hitKeyMotor3Coordinate());
    }

    public void playE() {
        this.goMiddle(-1);
        this.controller.move(-1,hitKeyMotor2Coordinate(),hitKeyMotor3Coordinate());
    }

    public void playD() {
        this.goMiddle(-7);
        this.controller.move(-7,hitKeyMotor2Coordinate(),hitKeyMotor3Coordinate());
    }


    public void playF() {
        this.goMiddle(6);
        this.controller.move(6,hitKeyMotor2Coordinate(),hitKeyMotor3Coordinate());
    }

    public void goMiddle(int position){
        this.controller.move(position,-75,0);
    }

    private void wait(int milliseconds){
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private int hitKeyMotor2Coordinate(){
        return -80;
    }

    private int hitKeyMotor3Coordinate(){
        return 20;
    }
}
