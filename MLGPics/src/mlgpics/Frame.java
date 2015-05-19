/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mlgpics;

import java.io.*;
import java.io.FileInputStream;
import java.io.IOException;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JFrame;
import sun.applet.Main;
import sun.audio.AudioData;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;
import sun.audio.ContinuousAudioDataStream;


/**
 *
 * @author David
 */
public class Frame extends JFrame{
    public Frame(){
        super.setTitle("Frame");
        super.setSize(1000,700);
        super.setLocation(100,100);
        super.setResizable(true);
        super.add(new Contents());
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        super.setVisible(true); 
      

        
    }
public static void main(String [] args){
     // music();
    new Frame(); 

    }
public static void music()
{
    /*AudioPlayer AP = AudioPlayer.player;
    File file = new File("BackgroundMusic.wav");
    AudioData AD;
    ContinuousAudioDataStream loop = null;
    try{
    AS = new AudioStream(new FileInputStream("/Users/David/Desktop/MLG-Pics-Pls/MLGPics/src/mlgpics/BackgroundMusic.wav"));
    AD = AS.getData();
    loop = new ContinuousAudioDataStream(AD);
    }
    catch(IOException error){}
    AP.start(loop);*/
    /*try{
        Clip clip = (Clip) AudioSystem.getClip();
        clip.open(AudioSystem.getAudioInputStream(new File("BackgroundMusic.wav")));
        clip.start();
        Thread.sleep(1000);
    }
    catch(Exception e){}
            */
    }
   
}


