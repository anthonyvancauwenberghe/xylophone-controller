package Sound;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import javax.sound.sampled.*;

public class MediaPlayer
{
    private boolean loop = false;
    private AudioInputStream ais = null;
    private Clip clip = null;
    //declaration of variables

    public MediaPlayer (String fileName, boolean loop)
    //Constructor for the class which fileName and accepts whether the clip needs to loop or not
    {
        this.loop = loop;
        //sets the variable within the class as constructor

        try {
            ais = AudioSystem.getAudioInputStream(new File(fileName));
            DataLine.Info info = new DataLine.Info(Clip.class, ais.getFormat());
            this.clip = (Clip) AudioSystem.getLine(info);
            this.clip.open(ais);

        } catch (IOException | UnsupportedAudioFileException | LineUnavailableException e) {
            e.printStackTrace();
        }
        //tries to load file into java's built in audio player, else prints the error to console
    }

    public void musicStart ()
    //starts music
    {
        if (loop)
        {
            clip.loop(Clip.LOOP_CONTINUOUSLY);
            //starts music on loop if loop is requested
        }
        else
        {
            Thread thread = new Thread(){
                public void run(){
                    clip.start();
                    long startTime = System.currentTimeMillis();
                    long elapsedTime = 0L;

                    while (elapsedTime < clip.getMicrosecondLength()/1000 + 1250) {
                        elapsedTime = (new Date()).getTime() - startTime;
                    }
                    musicStop();
                    this.interrupt();
                    return;
                }
            };

            thread.start();
        }
    }


    public void musicStop ()
    {
        clip.stop();
        clip.close();
    }

}
