/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mlgpics;


import java.awt.*;
import javax.swing.*;

/**
 *
 * @author dgoncha7
 */
public class Board extends JApplet {

   
    public void paint(Graphics g)
    {
        g.setColor(Color.black);
        g.fillRect(0,0,500,500);
        g.setColor(Color.white);
        g.fillRect(0,0,500,10);
        g.fillRect(0,0,10,500);
        g.fillRect(490,0,10,500);
        g.fillRect(0,490,500,10);
        
                
    }

    
}
