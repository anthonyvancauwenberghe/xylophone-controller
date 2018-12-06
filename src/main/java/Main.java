import Controller.RobotController;
import Player.KeyPlayer;
import Player.RobotKeyPlayer;
import Player.SongPlayer;
import Player.SoundKeyPlayer;
import Sequences.Song;
import Songs.*;

public class Main {

    public static void main(String[] args) {

        RobotController controller = new RobotController();
        //KeyPlayer keyPlayer = new RobotKeyPlayer(controller);

        Song song = new OdeToJoySong();
        KeyPlayer keyPlayer = new RobotKeyPlayer(controller);

        SongPlayer songPlayer = new SongPlayer(song, keyPlayer);
        songPlayer.play();
    }
}
