/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mlgpics;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
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
     * @param url : The file name you are accessing
     * @return : Full path name for file in the jar
     */
    private static String getURL(String url) {
        return Runner.class.getResource(url).toString().replace("file:", "");
    }
    
    /**
     * Initializes all variables needed to play music
     * 
     * @param url : Path to audio file playing
     */
    private void setupSound(String url) {
        FileInputStream in = null;
        try {
            in = new FileInputStream(getURL(url));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(url + " not found");
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
