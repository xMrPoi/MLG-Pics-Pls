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
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import javax.imageio.ImageIO;
import java.lang.Throwable;
import java.net.MalformedURLException;
import java.net.URL;
import javax.swing.Icon;
import javax.swing.JFrame;
import javax.swing.JLabel;
/**
 *
 * @author David
 */
public class Contents extends JPanel implements ActionListener, KeyListener
{
    private Image character;
    private Image dor,mtn, end;
    private int x = 300, y = 10;
    private int xV = 0;
    private int yV = 0;
    private int xD = 25, yD = 25;
    private int xM = 800, yM = 500;
    private boolean bM = false, bD = false;
    private Timer t;
    
    
    public Contents(){
        super.setDoubleBuffered(true);
        t = new Timer(5,this);
        t.start();
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        
    }
    @Override
    public void paintComponent(Graphics g){ 
        super.paintComponent(g);
        Graphics g2d = (Graphics2D)g;
        ImageIcon ii = new ImageIcon(this.getClass().getResource("player.jpeg"));
        ImageIcon ii2 = new ImageIcon(this.getClass().getResource("dew.jpeg"));
        ImageIcon ii3 = new ImageIcon(this.getClass().getResource("doritoLogo.jpeg"));
        ImageIcon ii4 = new ImageIcon(this.getClass().getResource("images.jpeg"));
     
        character = ii.getImage();
        dor = ii3.getImage();
        mtn = ii2.getImage();
        end = ii4.getImage();
  
        character = scaledImage(character,50,50);
        dor = scaledImage(dor,30,30);
        mtn = scaledImage(mtn,30,30);
        end = scaledImage(end,1000,700);
        

        setBackground(Color.black);
        g2d.setColor(Color.white);
        g2d.fillRect(0,0,1000,100);
        g2d.fillRect(0,0,100,700);
        g2d.fillRect(900,0,100,700);
        g2d.fillRect(0,600,1000,100);
        g2d.fillRect(200,0,100,700);
        g2d.fillRect(450,0,100,700);
        g2d.fillRect(700,0,100,700);
        g2d.fillRect(0,300,1000,100);

        g2d.drawImage(dor, xD, yD, this);
        g2d.drawImage(mtn, xM, yM, this);
        g2d.drawImage(character, x, y, this);

        if(checkWin())
        {

            try
            {Thread.sleep(3000);
            }
            catch(InterruptedException ie)
            {}

            g2d.drawImage(end, 0, 0, this);
            
            
        
        }
        
        
    }
    /*if(x >= 950 || x <= 0)
            xV = 0;
        else if(x < 950 && x > 0)
            xV = 5;
     
        if(y >= 625 || y <= 0)
            yV = 0;
        else if(y < 625 && y > 0)
            xV = 5;*/
    public void up()
    {
   
        xV = 0;
        yV = -5;

    }
    public void down()
    {

        
        xV = 0;
        yV = 5;

    }

    public void left()
    {
        xV = -5;
        yV = 0;

    }

    public void right()
    {

        xV = 5;
        yV = 0;

    }
    public void reset()
    {
        xV = 0;
        yV = 0;
    }
    /*public void rightDown()
    {
        xV = 5;
        yV = 5;
    }
    public void rightUp()
    {
        xV = 5;
        yV = -5;
    }
    public void leftDown()
    {
        xV = -5;
        yV = 5;
    }
    public void leftUp()
    {
        xV = -5;
        yV = -5;
    }*/
    @Override
    public void keyPressed(KeyEvent e)
    {
        int code = e.getKeyCode();
        
        /*if((code == KeyEvent.VK_UP && code == KeyEvent.VK_RIGHT) || (code == KeyEvent.VK_W && code == KeyEvent.VK_D))
        {
            rightUp();
        }
        else if(code == KeyEvent.VK_UP || code == KeyEvent.VK_W)
        {
            up();
        }
        else if(code == KeyEvent.VK_RIGHT || code == KeyEvent.VK_D)
        {
            right();
        }
            
        
        
        
        if((code == KeyEvent.VK_UP && code == KeyEvent.VK_LEFT) || (code == KeyEvent.VK_W && code == KeyEvent.VK_A))
        {
            leftUp();
        }
        else if(code == KeyEvent.VK_UP || code == KeyEvent.VK_W)
        {
            up();
        }
        else if(code == KeyEvent.VK_LEFT || code == KeyEvent.VK_A)
        {
            left();
        }
        
        
        
        
        if((code == KeyEvent.VK_DOWN && code == KeyEvent.VK_RIGHT) || (code == KeyEvent.VK_S && code == KeyEvent.VK_D))
        {
            rightDown();
        }
        else if(code == KeyEvent.VK_DOWN || code == KeyEvent.VK_S)
        {
            down();
        }
        else if(code == KeyEvent.VK_RIGHT || code == KeyEvent.VK_D)
        {
            right();
        }
        
        
        if((code == KeyEvent.VK_DOWN && code == KeyEvent.VK_LEFT) || (code == KeyEvent.VK_S && code == KeyEvent.VK_A))
        {
            leftDown();
        }
        else if(code == KeyEvent.VK_DOWN || code == KeyEvent.VK_S)
        {
            down();
        }
        else if(code == KeyEvent.VK_LEFT || code == KeyEvent.VK_A)
        {
            left();
        }*/
        
        if(code == KeyEvent.VK_UP || code == KeyEvent.VK_W)
        {
            up();
        }
        if(code == KeyEvent.VK_DOWN || code == KeyEvent.VK_S)
        {
            down();
        }
        if(code == KeyEvent.VK_LEFT || code == KeyEvent.VK_A)
        {
            left();
        }
        if(code == KeyEvent.VK_RIGHT || code == KeyEvent.VK_D)
        {
            right();
        }
        
    }
    @Override
    public void keyTyped(KeyEvent e) {}
    @Override
    public void keyReleased(KeyEvent e) 
    {

        
        
    }
    
    boolean top = true;
    boolean bottom = false;
    boolean left = false;
    boolean right = false;
    
    public void move()
    {
        /*if(top)
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
        else if(x == 800 && y == 500)
        {
            right = false;
            bottom = true;
        }
        else if(x == 0 && y == 500)
        {
            bottom = false;
            left = true;
        }
        else if(x == 0 && y == 0)
        {
            left = false;
            top = true;
        }*/
        
        
        
    }
   
   
    public void detectMtnCol()
    {
        if((xM-50 <= x && x <= xM+50) && (yM-50 <= y && y <= yM+50))
        {
            xM = 250;
            yM = 250;
            bM = true;
        }
    }
    public void detectDorCol()
    {
        if((xD-50 <= x && x <= xD+50) && (yD-50 <= y && y <= yD+50))
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
    private Image scaledImage(Image img, int w, int h)
    {
        BufferedImage resizedImage = new BufferedImage(w,h,BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = resizedImage.createGraphics();
        g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
                             RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2d.drawImage(img, 0, 0, w, h, null);
        return resizedImage;
    }
            
    @Override
    public void actionPerformed(ActionEvent e)
    {
       
        move(); 
        x += xV;
        y += yV;
        detectMtnCol();
        detectDorCol();
        repaint();
    }

}
