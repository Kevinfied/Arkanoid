import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import javax.sound.sampled.Clip;
import javax.sound.sampled.AudioSystem;

public class SoundEffect{
    private Clip c;
    public SoundEffect(String filename){
        setClip(filename);
    }
    public void setClip(String filename){
        try{
            File f = new File(filename);
            c = AudioSystem.getClip();
            c.open(AudioSystem.getAudioInputStream(f));
        } catch(Exception e){ System.out.println("error"); }
    }
    // play sound one time
    public void play(){
        c.setFramePosition(0);
        c.start();
    }
    // looping music instead of just playing it once
    public void loop() {
        c.setFramePosition(0);
        c.loop(Clip.LOOP_CONTINUOUSLY);
    }
    // stopping music
    public void stop(){
        c.stop();
    }
}