/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mlgpics;

import java.awt.Image;

/**
 *
 * @author David
 */
public class Collectible {
    private Image image;
    private int atX;
    private int atY;
    private boolean isCollected = false;

    public Collectible(Image img, int x, int y)
    {
    image = img;
    atX = x;
    atY = y;
    }
    public boolean isCollected()
    {
        return isCollected;
    }
    public int getX()
    {
        return atX;
    }
    public int getY()
    {
        return atY;
    }
    
    /*public void tryCollect(int x, int y)
    {
        if(x >= atX - 50 && x <= atX + 50 && y >= atY - 50 && y <= atY + 50)
            isCollected = true;
    }*/
    
    public Image getImage()
    {
        return image;
    }
    
    public void setImage(Image img)
    {
        image = img;
    }
    public void setX(int x)
    {
        atX = x;
    }
    public void setY(int y)
    {
        atY = y;
    }
    public void setCollected(boolean b)
    {
        isCollected = b;
    }    
        

}
