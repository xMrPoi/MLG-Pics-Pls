/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mlgpics;

import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

/**
 * Class we use in order to play sounds
 * 
 * @author Jack
 */
public class Sound {

    private AudioStream as = null;
    
    public Sound(String path){
        setupSound(path);
    }

    /**
     * Initializes all variables needed to play music
     * 
     * @param url : Path to audio file playing
     */
    private void setupSound(String url) {
        InputStream in = getClass().getResourceAsStream(url);
        if(in == null){
            System.out.println("File not found!");
            return;
        }
        try {
            as = new AudioStream(in);
        } catch (IOException ex) {
            Logger.getLogger(Sound.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Plays sound
     */
    public void play(){
        AudioPlayer.player.start(as);
    }
    
    /**
     * Stops sound
     */
    public void stop(){
        AudioPlayer.player.stop(as);
    }
}
