/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package mlgpics;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

/**
 *
 * @author David, Jack
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
    new Frame(); 

    }
public static void music() throws MalformedURLException, LineUnavailableException, UnsupportedAudioFileException, IOException
{
        URL url = new URL("http://pscode.org/media/leftright.wav");
        Clip clip = AudioSystem.getClip();
        // getAudioInputStream() also accepts a File or InputStream
        AudioInputStream ais = AudioSystem.getAudioInputStream( url );
        clip.open(ais);
        clip.loop(Clip.LOOP_CONTINUOUSLY);
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {JOptionPane.showMessageDialog(null, "Close to exit!");
        }
        });
    }
}




