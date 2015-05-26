/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mlgpics;

import java.awt.Image;
import javax.swing.ImageIcon;

/**
 *
 * @author dgoncha7
 */
public class Filler {
    private int x, y;
    private int xSpeed, ySpeed;
    private Image img;
    private boolean isOn;
    private Image[] images = {new ImageIcon(this.getClass().getResource("3Doge5UFiller.jpeg")).getImage(),
                              new ImageIcon(this.getClass().getResource("IgnFiller.jpeg")).getImage(),
                              new ImageIcon(this.getClass().getResource("freggFiller.jpeg")).getImage(),
                              new ImageIcon(this.getClass().getResource("gnomeFiller.jpeg")).getImage(),
                              new ImageIcon(this.getClass().getResource("mlgFiller.jpeg")).getImage(),
                              new ImageIcon(this.getClass().getResource("sanicFiller.jpeg")).getImage(),
                              new ImageIcon(this.getClass().getResource("tescoFiller.jpeg")).getImage(),
                              new ImageIcon(this.getClass().getResource("rainbowFrogFiller.jpeg")).getImage()};
    public Filler()
    {
        img = images[(int)(Math.random()*8)];
        x = (int)(Math.random()*1000);
        y = (int)(Math.random()*700);
        xSpeed = (int)(Math.random()*7 + 1);
        ySpeed = (int)(Math.random()*7 + 1);
        isOn = false;
    }
    
    public void update()
    {
        if(x >= 1000 || x <= 0)
        {
            xSpeed = -xSpeed;
        }
        if(y >= 700 || y <= 0)
        {
            ySpeed = -ySpeed;
        }
        x += xSpeed;
        y += ySpeed;
    }
    
    public int getX()
    {
        return x;
    }
    public int getY()
    {
        return y;
    }
    
    
    public boolean IsOn()
    {
        return isOn;
    }
    public void turnOn()
    {
        isOn = true;
    }
    
    
    public Image getImg()
    {
        return img;
    }
    public void setImg(Image i)
    {
        img = i;
    }
    
    public void faster()
    {
        xSpeed *= 2;
        ySpeed *= 2;
    }
    
    
    
    
    
}
