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
        xSpeed = (int)(Math.random()*25);
        ySpeed = (int)(Math.random()*25);
    }
    
    public void update()
    {
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
    
    
    
    
}
