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
        int bottomMotorPosition = this.CLowMotor3Position() + 0 * keyOffset();
        this.controller.move(bottomMotorPosition, hitKeyMotor2Coordinate(), hitKeyMotor3Coordinate(), this.controller.defaultDelay + extraDelay);
      //  this.goMiddle(bottomMotorPosition);
    }

    public void playD(int extraDelay) {
        int bottomMotorPosition = this.CLowMotor3Position() + 1 * keyOffset();
        this.controller.move(bottomMotorPosition, hitKeyMotor2Coordinate(), hitKeyMotor3Coordinate(), this.controller.defaultDelay + extraDelay);
       // this.goMiddle(bottomMotorPosition);
    }

    public void playE(int extraDelay) {
        int bottomMotorPosition = this.CLowMotor3Position() + 2 * keyOffset();
        this.controller.move(bottomMotorPosition, hitKeyMotor2Coordinate(), hitKeyMotor3Coordinate(), this.controller.defaultDelay + extraDelay);
        //this.goMiddle(bottomMotorPosition);
    }

    public void playF(int extraDelay) {
        int bottomMotorPosition = this.CLowMotor3Position() + 3 * keyOffset();
        this.controller.move(bottomMotorPosition, hitKeyMotor2Coordinate(), hitKeyMotor3Coordinate(), this.controller.defaultDelay + extraDelay);
      //  this.goMiddle(bottomMotorPosition);
    }

    public void playG(int extraDelay) {
        int bottomMotorPosition = this.CLowMotor3Position() + 4 * keyOffset();
        this.controller.move(bottomMotorPosition, hitKeyMotor2Coordinate(), hitKeyMotor3Coordinate(), this.controller.defaultDelay + extraDelay);
       // this.goMiddle(bottomMotorPosition);
    }

    public void playA(int extraDelay) {
        int bottomMotorPosition = this.CLowMotor3Position() + 5 * keyOffset();
        this.controller.move(bottomMotorPosition, hitKeyMotor2Coordinate(), hitKeyMotor3Coordinate(), this.controller.defaultDelay + extraDelay);
       // this.goMiddle(bottomMotorPosition);
    }

    public void playB(int extraDelay) {
        int bottomMotorPosition = this.CLowMotor3Position() + 6 * keyOffset() +1;
        this.controller.move(bottomMotorPosition, hitKeyMotor2Coordinate(), hitKeyMotor3Coordinate(), this.controller.defaultDelay + extraDelay);
       // this.goMiddle(bottomMotorPosition);
    }

    public void playCHigh(int extraDelay) {
        int bottomMotorPosition = this.CLowMotor3Position() + 7 * keyOffset() + 1;
        this.controller.move(bottomMotorPosition, hitKeyMotor2Coordinate(), hitKeyMotor3Coordinate(), this.controller.defaultDelay + extraDelay);
      //  this.goMiddle(bottomMotorPosition);
    }

    public void goMiddle(int position, int delay) {
        this.controller.move(position, hitKeyMotor2CoordinateMiddle(), 3, delay);
    }

    public void goMiddle(int position) {
        this.goMiddle(position, 70);
    }

    private int hitKeyMotor2Coordinate(){
        return -75;
    }

    private int hitKeyMotor2CoordinateMiddle(){
        return -75;
    }

    private int hitKeyMotor3Coordinate(){
        return 42;
    }

    private int CLowMotor3Position() {
        return -20;
    }

    private int keyOffset() {
        return 6;
    }
}
