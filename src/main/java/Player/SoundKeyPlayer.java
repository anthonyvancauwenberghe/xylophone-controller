package Player;

import Sound.MediaPlayer;

public class SoundKeyPlayer implements KeyPlayer {

    public void playCLow() {
        this.playSound("c");
    }

    public void playCHigh() {
        this.playSound("c1");
    }

    public void playA() {
        this.playSound("a");
    }

    public void playG() {
        this.playSound("g");
    }

    public void playB() {
        this.playSound("b");
    }

    public void playE() {
        this.playSound("e");
    }

    public void playD() {
        this.playSound("d");
    }

    public void playF() {
        this.playSound("f");
    }


    protected void playSound(String key) {
        (new MediaPlayer("sounds/" + key + ".wav", false)).musicStart();
    }


}
