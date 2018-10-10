import Arduino.ArduinoSerialManager;
import Controller.RobotController;

import static java.lang.Thread.sleep;

public class Main {

    public static void main(String[] args) {

        RobotController controller = new RobotController();

        try {
            while(true){
                controller.move(20,0);
                sleep(50);
                controller.move(45,0);
                sleep(50);
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
