import Controller.RobotController;
import Player.KeyPlayer;
import Sequences.*;

public class Main {

    public static void main(String[] args) {
        RobotController controller = new RobotController();
        KeyPlayer player = new KeyPlayer(controller);



        Sequence sequence = new StarWars(player);
        sequence.play();

        /*
        Sequence sequence = new StarWars(player);
        sequence.play();*/


        player.goMiddle(6);
    }
}
