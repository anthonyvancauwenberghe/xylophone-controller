package Sequences;

import Player.KeyPlayer;

public class StarWars implements Sequence {
    protected KeyPlayer player;

    public StarWars(KeyPlayer player) {
        this.player = player;
    }
    public void play() {
        player.playCLow();
        player.playG(400);
        player.playF(400);
        player.playE(-200);
        player.playD(-200);
        player.playCHigh(-100);
        player.playG(400);
        player.playF(400);
        player.playE(-200);
        player.playD(-200);
        player.playCHigh(-100);
        player.playG(400);


        player.playF(100);
        player.playE(-100);
        player.playF(-200);
        player.playD();
    }
}
