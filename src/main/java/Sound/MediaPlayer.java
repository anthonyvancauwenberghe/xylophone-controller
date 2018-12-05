package Sound;

import java.io.File;
import java.io.IOException;

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
            clip.start();
            //starts music as not on loop
        }

    }


    public void musicStop ()
    //stops the music
    {
        clip.stop();
    }

}
