package Player;

import Configs.Config;
import Sequences.Note;
import Sequences.Song;

public class SongPlayer {
    protected Song song;

    protected final KeyPlayer player;

    public SongPlayer(Song song, KeyPlayer player) {
        this.song = song;
        this.player = player;
        if (this.player instanceof RobotKeyPlayer)
            this.adjustTempoToRobotLimits();
    }

    public final void play() {
        for (Note note : this.song.getNotes()) {
            this.playKey(note);
            try {

                Thread.sleep(this.calculateNoteDelay(note));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    protected long calculateNoteDelay(Note note) {
        return (long) ((60000 / song.getTempo()) * 4 * note.length.length);
    }

    protected final void playKey(Note note) {
        switch (note.key.type) {
            case "A":
                this.player.playA();
                break;
            case "B":
                this.player.playB();
                break;
            case "C0":
                this.player.playCLow();
                break;
            case "C1":
                this.player.playCHigh();
                break;
            case "D":
                this.player.playD();
                break;
            case "E":
                this.player.playE();
                break;
            case "F":
                this.player.playF();
                break;
            case "G":
                this.player.playG();
                break;
            default:
                throw new RuntimeException("key does not exist on player");

        }
    }

    public void adjustTempoToRobotLimits() {
        long delay = -1;
        for (Note note : this.song.getNotes()) {
            if (delay == -1 || this.calculateNoteDelay(note) < delay) {
                delay = this.calculateNoteDelay(note);
            }
        }

        if (delay < Config.ROBOT_MOVE_SPEED_LIMIT) {
            double ratio = (double) delay / (double) Config.ROBOT_MOVE_SPEED_LIMIT;
            int originalTempo = this.song.getTempo();
            this.song.setTempo((int) (this.song.getTempo() * ratio));
            System.out.println("Tempo decreased from " + originalTempo + " to " + this.song.getTempo() + " to adjust for robot movement limits");
        }
    }
}
