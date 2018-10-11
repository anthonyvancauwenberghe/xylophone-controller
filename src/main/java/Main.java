import Arduino.ArduinoSerialManager;
import Controller.RobotController;
import Player.KeyPlayer;
import Sequences.AllKeysSequence;
import Sequences.Sequence;

import static java.lang.Thread.sleep;

public class Main {

    public static void main(String[] args) {

        RobotController controller = new RobotController();
        KeyPlayer player = new KeyPlayer(controller);
        Sequence sequence = new AllKeysSequence(player);
        sequence.play();
    }
}
