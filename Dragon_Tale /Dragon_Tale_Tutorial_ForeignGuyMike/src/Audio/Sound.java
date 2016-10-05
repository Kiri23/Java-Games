package Audio;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Sound {

private Clip clip;
public Sound(String fileName)
{
    try
    {
        File file = new File(fileName);
        if (file.exists())
        {
            AudioInputStream sound = AudioSystem.getAudioInputStream(file);
            try {
				clip = AudioSystem.getClip();
			} catch (LineUnavailableException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            try {
				clip.open(sound);
			} catch (LineUnavailableException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
        else
        {
            throw new RuntimeException("Sound: file not found: " + fileName);
        }
    }
    catch (UnsupportedAudioFileException e)
    {
        e.printStackTrace();
        throw new RuntimeException("Sound: Unsupported Audio File: " + e);
    }
    catch (IOException e)
    {
        e.printStackTrace();
        throw new RuntimeException("Sound: Input/Output Error: " + e);
    }
}

public void play()
{
    clip.setFramePosition(0);
    clip.start();
}
public void loop()
{
    clip.loop(Clip.LOOP_CONTINUOUSLY);
}
public void stop()
{
    clip.stop();
}
}