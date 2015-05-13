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
import java.awt.image.BufferedImage;
/**
 *
 * @author David
 */
public class Contents extends JPanel implements ActionListener
{
    private Image character;
    private Image dor,mtn;
    private int x = 10, y = 0;
    private int xD = -20, yD = 0;
    private int xM = 800, yM = 510;
    private boolean bM = false, bD = false;
    private Timer t;
    
    
    public Contents(){
        super.setDoubleBuffered(true);
        t = new Timer(10,this);
        t.start();
        
    }
    @Override
    public void paintComponent(Graphics g){ 
        super.paintComponent(g);
        Graphics g2d = (Graphics2D)g;
        ImageIcon ii = new ImageIcon(this.getClass().getResource("player.jpeg"));
        ImageIcon ii2 = new ImageIcon(this.getClass().getResource("dew.jpeg"));
        ImageIcon ii3 = new ImageIcon(this.getClass().getResource("doritoLogo.jpeg"));
        character = ii.getImage();
        character = character.getScaledInstance(100,50,Image.SCALE_AREA_AVERAGING);
        
        dor = ii3.getImage();
        mtn = ii2.getImage();
 
        
        

        ;//cast
        setBackground(Color.black);
        g2d.setColor(Color.white);
        g2d.fillRect(0,0,900,100);
        g2d.fillRect(0,0,100,600);
        g2d.fillRect(800,0,100,600);
        g2d.fillRect(0,500,900,100);
        g2d.drawImage(dor, xD, yD, this);
        g2d.drawImage(mtn, xM, yM, this);
        g2d.drawImage(character, x, y, this);
        if(checkWin())
        {
            g2d.setColor(Color.white);
            g2d.fillRect(0,0,900,600);
            try
            {Thread.sleep(3000);
            }
            catch(InterruptedException ie)
            {}
            g2d.setColor(Color.black);
            g2d.drawString("You won m8", 250, 250);
        }
        
        
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
    public void detectMtnCol()
    {
        if(xM == x && yM == y)
        {
            xM = 250;
            yM = 250;
            bM = true;
        }
    }
    public void detectDorCol()
    {
        if(xD == x && yD == y)
        {
            xD = 400;
            yD = 250;
            bD = true;
        }
    }
    public boolean checkWin()
    {
        return bD && bM;
        
    }
            
    @Override
    public void actionPerformed(ActionEvent e) {
        
        move();
        detectMtnCol();
        detectDorCol();
        repaint();
    }

}
