import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import java.util.*;
import java.io.*;
import javax.sound.sampled.Clip;
import javax.sound.sampled.AudioSystem;

public class Sound {


    Clip clip;
    public void play() {

    }

    public void setClip(String name) {
        try {
            File file = new File(name);
            clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(file));
        }
        catch (Exception exc) {
            exc.printStackTrace(System.out);
        }

    }

    public void loop() {

    }


}
