/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mlgpics;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;
/**
 *
 * @author David
 */
public class Contents extends JPanel implements ActionListener
{
    private Image character;
    private int x = 0, y = 0;
    private Timer t;
    
    
    public Contents(){
        super.setDoubleBuffered(true);
        t = new Timer(10,this);
        t.start();
        
    }
    @Override
    public void paintComponent(Graphics g){ 
        super.paintComponent(g);
        ImageIcon ii = new ImageIcon(this.getClass().getResource("player.jpeg"));
        character = ii.getImage();
        

        Graphics g2d = (Graphics2D)g;//cast
        setBackground(Color.black);
        g2d.setColor(Color.white);
        g2d.fillRect(0,0,900,100);
        g2d.fillRect(0,0,100,600);
        g2d.fillRect(800,0,100,600);
        g2d.fillRect(0,500,900,100);
        g2d.drawImage(character, x, y, this);
    }
    
    int xV = 5;
    int yV = 5;
    boolean top = true;
    boolean bottom = false;
    boolean left = false;
    boolean right = false;
    
    public void move()
    {
        if(top)
        {
            x += xV;
        }
        else if(right)
        {
            y += yV;
        }
        else if(bottom)
        {
            x -= xV;
        }
        else if(left)
        {
            y -= yV;
        }
        if(x == 800 && y == 0)
        {
            top = false;
            right = true;
        }
        else if(x == 800 && y == 510)
        {
            right = false;
            bottom = true;
        }
        else if(x == -20 && y == 510)
        {
            bottom = false;
            left = true;
        }
        else if(x == -20 && y == 0)
        {
            left = false;
            top = true;
        }
        
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        
        move();
        repaint();
    }

}
