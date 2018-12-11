import Controller.InverseKinematicsController;
import Player.KeyPlayer;
import Player.SongPlayer;
import Player.SoundKeyPlayer;
import Sequences.Song;
import Songs.*;

public class Main {

    public static void main(String[] args) {

      //  RobotControllerOneSequence controller = new RobotControllerOneSequence();
        //KeyPlayer keyPlayer = new RobotKeyPlayer(controller);

        Song song = new JingleBellsSong();
       // KeyPlayer keyPlayer = new RobotKeyPlayer(controller);

        //KeyPlayer keyPlayer = new SoundKeyPlayer();

       // SongPlayer songPlayer = new SongPlayer(song, keyPlayer);
        //songPlayer.play();

        InverseKinematicsController controller = new InverseKinematicsController(song);
        controller.send();
    }
}
