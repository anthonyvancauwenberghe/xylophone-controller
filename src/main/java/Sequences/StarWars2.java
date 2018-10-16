package Sequences;

import Player.KeyPlayer;

public class StarWars2 implements Sequence {
    protected KeyPlayer player;

    public StarWars2(KeyPlayer player) {
        this.player = player;
    }
    public void play() {
        player.playE();
        player.playE();
        player.playE();

        player.playCLow();
        player.playG();
        player.playE();

        player.playCLow();
        player.playG();
        player.playE();


    }
}
