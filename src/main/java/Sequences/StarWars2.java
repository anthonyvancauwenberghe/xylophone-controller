package Sequences;

import Player.KeyPlayer;

public class StarWars2 implements Sequence {
    protected KeyPlayer player;

    public StarWars2(KeyPlayer player) {
        this.player = player;
    }
    public void play() {
        player.playE();
        player.playE(250);
        player.playE(250);

        player.playCLow(200);
        player.playG();
        player.playE(-150);

        player.playCLow(200);
        player.playG();
        player.playE(-150);


    }
}
