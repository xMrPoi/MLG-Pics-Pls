/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mlgpics;

import javax.swing.JFrame;

/**
 *
 * @author David
 */
public class Frame extends JFrame{
    public Frame(){
        super.setTitle("Frame");
        super.setSize(900,600);
        super.setLocation(100,100);
        super.setResizable(false);
        super.add(new Contents());
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        super.setVisible(true);
    }
public static void main(String [] args){
    new Frame();
    }       
}

