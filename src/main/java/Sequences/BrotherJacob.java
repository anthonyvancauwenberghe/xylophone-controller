package Sequences;

import Player.KeyPlayer;

public class BrotherJacob implements Sequence
{
    protected KeyPlayer player;

    public BrotherJacob(KeyPlayer player) {
        this.player = player;
    }

    public void play(){
        player.playCLow();
        player.playD();
        player.playE();
        player.playCLow(50);

        player.playCLow();
        player.playD();
        player.playE();

        player.playCLow(50);

        player.playE();
        player.playF();
        player.playG();

        player.playE(400);
        player.playF();
        player.playG();

        player.playG(150);
        player.playA(-200);
        player.playG(-200);
        player.playF(-200);
        player.playE(-200);
        player.playCLow(120);

        player.playG(70);
        player.playA(-200);
        player.playG(-200);
        player.playF(-200);
        player.playE(-200);
        player.playCLow(120);

        player.playCLow(70);
        player.playD(70);
        player.playCLow(70);

        player.playCLow(300);
        player.playD(70);
        player.playCLow(70);


    }
}
