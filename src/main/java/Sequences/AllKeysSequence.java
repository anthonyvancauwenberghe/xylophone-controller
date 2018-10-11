package Sequences;

import Player.KeyPlayer;

public class AllKeysSequence implements Sequence {

    protected KeyPlayer player;

    public AllKeysSequence(KeyPlayer player) {
        this.player = player;
    }

    public void play(){
        player.playCLow();
        player.playD();
        player.playE();
        player.playF();
        player.playG();
        player.playA();
        player.playB();
        player.playCHigh();
    }
}
