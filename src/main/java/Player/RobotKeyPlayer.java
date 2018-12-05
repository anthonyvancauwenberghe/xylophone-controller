package Player;

import Controller.RobotController;

public class RobotKeyPlayer implements KeyPlayer {

    protected RobotController controller;

    public RobotKeyPlayer(RobotController controller) {
        this.controller = controller;
    }

    public void playCLow() {
        this.playCLow(0);
    }

    public void playCHigh() {
        this.playCHigh(0);
    }

    public void playA() {
        this.playA(0);
    }

    public void playG() {
        this.playG(0);
    }

    public void playB() {
        this.playB(0);
    }

    public void playE() {
        this.playE(0);
    }

    public void playD() {
        this.playD(0);
    }

    public void playF() {
        this.playF(0);
    }

    public void playCLow(int extraDelay) {
        this.goMiddle(-13);
        this.controller.move(-13,hitKeyMotor2Coordinate(),hitKeyMotor3Coordinate(),this.controller.defaultDelay+ extraDelay);
    }

    public void playCHigh(int extraDelay) {
        this.goMiddle(30);
        this.controller.move(31,hitKeyMotor2Coordinate(),hitKeyMotor3Coordinate()-8,this.controller.defaultDelay+ extraDelay);
    }

    public void playA(int extraDelay) {
        this.goMiddle(17);
        this.controller.move(17,hitKeyMotor2Coordinate(),hitKeyMotor3Coordinate(),this.controller.defaultDelay+ extraDelay);
    }

    public void playG(int extraDelay) {
        this.goMiddle(12);
        this.controller.move(12,hitKeyMotor2Coordinate(),hitKeyMotor3Coordinate(),this.controller.defaultDelay+ extraDelay);
    }

    public void playB(int extraDelay) {
        this.goMiddle(25);
        this.controller.move(25,hitKeyMotor2Coordinate(),hitKeyMotor3Coordinate(),this.controller.defaultDelay+ extraDelay);
    }

    public void playE(int extraDelay) {
        this.goMiddle(-1);
        this.controller.move(-1,hitKeyMotor2Coordinate(),hitKeyMotor3Coordinate(),this.controller.defaultDelay+ extraDelay);
    }

    public void playD(int extraDelay) {
        this.goMiddle(-7);
        this.controller.move(-7,hitKeyMotor2Coordinate(),hitKeyMotor3Coordinate(),this.controller.defaultDelay+ extraDelay);
    }

    public void playF(int extraDelay) {
        this.goMiddle(6);
        this.controller.move(6,hitKeyMotor2Coordinate(),hitKeyMotor3Coordinate(),this.controller.defaultDelay+ extraDelay);
    }

    public void goMiddle(int position){
        this.controller.move(position,-75,1,80);
        try{Thread.sleep(40);} catch(Exception e){}
    }

    private int hitKeyMotor2Coordinate(){
        return -77;
    }

    private int hitKeyMotor3Coordinate(){
        return 37;
    }
}
