import Player.SongPlayer;
import Player.SoundKeyPlayer;
import Songs.StarWars2Song;
import Songs.StarWarsSong;

public class Main {

    public static void main(String[] args) {
       /* RobotController controller = new RobotController();
        RobotKeyPlayer player = new RobotKeyPlayer(controller);*/

        SongPlayer player = new SongPlayer(new StarWars2Song(),new SoundKeyPlayer());
        player.play();
    }
}
