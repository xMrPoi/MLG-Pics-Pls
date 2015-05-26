/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mlgpics;

import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JFrame;

/**
 * Runner class made to set and execute options for the game
 * 
 * @author Jack
 */
public class Runner {

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setTitle("Frame");
        frame.setSize(1000, 700);
        frame.setPreferredSize(new Dimension(1000, 700));
        
        // Centering frame
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int)(dim.getWidth() - frame.getWidth())/2;
        int y = (int)(dim.getHeight() - frame.getHeight())/2;
        frame.setLocation(x, y);
        
        frame.setResizable(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new Contents());
        frame.setVisible(true);
    }
}