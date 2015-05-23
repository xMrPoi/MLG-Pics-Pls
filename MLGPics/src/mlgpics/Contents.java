/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mlgpics;

import java.awt.Color;
import java.awt.Font;
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
/**
 *
 * @author David
 */
public class Contents extends JPanel implements ActionListener, KeyListener
{
    private Collectible[] collectables = new Collectible[21];
    private Image[] images = {new ImageIcon(this.getClass().getResource("dew.jpeg")).getImage(),
                              new ImageIcon(this.getClass().getResource("dewLogo.jpeg")).getImage(),
                              new ImageIcon(this.getClass().getResource("doritoAndDew.jpeg")).getImage(),
                              new ImageIcon(this.getClass().getResource("doritoLogo.jpeg")).getImage(),
                              new ImageIcon(this.getClass().getResource("euphorito.jpeg")).getImage(),
                              new ImageIcon(this.getClass().getResource("RV1BwzL.jpeg")).getImage()};
    private Filler[] fillers = {new Filler(),new Filler(),new Filler(),
                                new Filler(),new Filler(),new Filler(),
                                new Filler(),new Filler(),new Filler(),
                                new Filler(),new Filler(),new Filler(),
                                new Filler(),new Filler(),new Filler(),
                                new Filler(),new Filler(),new Filler(),
                                new Filler(),new Filler(),new Filler(),
                                new Filler(),new Filler(),new Filler(),
                                new Filler(),new Filler(),new Filler(),
                                new Filler(),new Filler(),new Filler(),
                                new Filler(),new Filler(),new Filler(),
                                new Filler(),new Filler(),new Filler(),
                                new Filler(),new Filler(),new Filler(),
                                new Filler(),new Filler(),new Filler(),
                                new Filler(),new Filler(),new Filler(),
                                new Filler(),new Filler(),new Filler(),
                                new Filler(),new Filler(),new Filler(),
                                new Filler(),new Filler(),new Filler(),
                                new Filler(),new Filler(),new Filler(),
                                new Filler(),new Filler(),new Filler()};//60
    
    
    private Image character;
    private Image dor,mtn, end;
    private int x = 475, y = 150;
    private int xV = 0;
    private int yV = 0;
    private int xD = 25, yD = 25;
    private boolean isWon = false;
    private boolean addSpeed = true;
    private Color rectangles = new Color(0,0,0);
    private Color course = new Color(255,255,255);
    private Timer t;
    private int score = 0, finScore = 0;
    private int amt = 20;
    private int speed = 30;
    private double startTime = System.currentTimeMillis();
    private double currentTime = 0;
    private long interval = 0;
    private int timesRun = 0;
    
    

    
    
    public Contents(){
        super.setDoubleBuffered(true);
        t = new Timer(5,this);
        t.start();
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        for(int ind = 0; ind < 7; ind++)
            collectables[ind] = (new Collectible(images[(int)(Math.random()*6)],ind * 150 + 30, 35));
        for(int ind = 7; ind < 14; ind++)
            collectables[ind] = (new Collectible(images[(int)(Math.random()*6)], (ind % 7) * 150 + 30, 350));
        for(int ind = 14; ind < 21; ind++)
            collectables[ind] = (new Collectible(images[(int)(Math.random()*6)], (ind % 7) * 150 + 30, 630));
        
        
        
        
        
        for (Filler filler : fillers) {
            filler.setImg(scaledImage(filler.getImg(), (int)(Math.random()*100 + 50) , (int)(Math.random()*100 + 50)));
        }
        
        

        
    }

    @Override
    public void paintComponent(Graphics g){ 
        super.paintComponent(g);
        Graphics g2d = (Graphics2D)g;
        g2d.setFont(new Font("TimesRoman", Font.BOLD, 25)); 
        ImageIcon ii = new ImageIcon(this.getClass().getResource("player.jpeg"));
        ImageIcon ii4 = new ImageIcon(this.getClass().getResource("images.jpeg"));
     
        character = ii.getImage();
        end = ii4.getImage();
        
        for (Filler filler : fillers) //changes coordinates of fillers
        {
            filler.update();
        }
        fillers[0].update();
  
        for (Collectible collectable : collectables) {
            collectable.setImage(scaledImage(collectable.getImage(), 40, 40));
        }

        
        character = scaledImage(character,50,50);
        end = scaledImage(end,1000,700);
        

        setBackground(rectangles); 
        g2d.setColor(course);
        g2d.fillRect(0,0,1000,100);
        g2d.fillRect(0,0,100,700);
        g2d.fillRect(900,0,100,700);
        g2d.fillRect(0,600,1000,100);
        
        g2d.fillRect(450,0,100,700);
        
        g2d.fillRect(0,300,1000,100);
        
        for (Collectible collectable : collectables) {
            if (!collectable.isCollected()) {
                g2d.drawImage(collectable.getImage(), collectable.getX(), collectable.getY(), this);
            }
        }
        
        
       
        g2d.drawImage(character, x, y, this);
        for (Filler filler : fillers) {
            if (filler.IsOn()) {
                g2d.drawImage(filler.getImg(), filler.getX(), filler.getY(), this);
            }
        }
        if(isWon)
        {
            x += speed;
            y += speed;

            g2d.drawImage(end, 0, 0, this);
            finScore += (double)(1/currentTime) * 1000000;
            
            g2d.setColor(Color.black);
            g2d.drawString("Score: "+ score + " + " + "Bonus Score: " + finScore, 50, 25);
            
            g2d.dispose();
            setVisible(false);
            try{
                Thread.sleep(3000);
            }
            catch(InterruptedException ie)
            {}
            
        }
        else
        {
        g2d.setColor(Color.black);
        g2d.drawString("Score: "+ score, 50, 25);
        }
        
    }
    public void up()
    {
   
        xV = 0;
        yV = -speed;

    }
    public void down()
    {   
        xV = 0;
        yV = speed;

    }

    public void left()
    {
        xV = -speed;
        yV = 0;

    }

    public void right()
    {

        xV = speed;
        yV = 0;

    }
    public void reset()
    {
        xV = 0;
        yV = 0;
    }
    @Override
    public void keyPressed(KeyEvent e)
    {
        int code = e.getKeyCode();
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
        //reset();
    }
    
    boolean top = true;
    boolean bottom = false;
    boolean left = false;
    boolean right = false;

    private Image scaledImage(Image img, int w, int h)
    {
        BufferedImage resizedImage = new BufferedImage(w,h,BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = resizedImage.createGraphics();
        g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
                             RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2d.drawImage(img, 0, 0, w, h, null);
        return resizedImage;
    }

    public void detectHit()
    {
        for (Collectible collectable : collectables) {
            if (collectable.getX() - 50 <= x && x <= collectable.getX() + 50 && (collectable.getY() - 50 <= y && y <= collectable.getY() + 50 && !collectable.isCollected())) {
                collectable.setCollected(true);
                score += 50;
                amt --;
            }
        }
    }
    public boolean checkWin()
    {
        for (Collectible collectable : collectables) {
            if (!collectable.isCollected()) {
                isWon = false;
                return false;
            }
        }
        isWon = true;
        return true;
    }
    public void checkEdge()
    {
        if(x >= 950 && xV == speed)//For borders
            xV = 0;
        else if(x <= 0 && xV == -speed)
            xV = 0;
        if(y >= 630 && yV == speed)
            yV = 0;
        else if(y <= 0 && yV == -speed)
            yV = 0;
        
        if(x > 50 && x < 450 && y >= 50 && y <= 50 && yV != -speed)
            yV = 0;//For Horizontal Block Edges
        if(x > 500 && x < 900 && y >= 50 && y <= 50 && yV != -speed)
            yV = 0;
        if(x > 50 && x < 450 && y >= 350 && y <= 350 && yV != -speed)
            yV = 0;
        if(x > 500 && x < 900 && y >= 350 && y <= 350 && yV != -speed)
            yV = 0;
        if(x > 50 && x < 450 && y <= 300 && y >=300 && yV != speed)
            yV = 0;
        if(x > 500 && x < 900 && y <= 300 && y >=300 && yV != speed)
            yV = 0;
        if(x > 50 && x < 450 && y <= 600 && y >=600 && yV != speed)
            yV = 0;
        if(x > 500 && x < 900 && y <= 600 && y >=600 && yV != speed)
            yV = 0;
        
        
        if(y > 50 && y < 300 && x >= 50 && x <= 50 && xV != -speed)
            xV = 0;//For Vertical Block Edges
        if(y > 350 && y < 600 && x >= 50 && x <= 50 && xV != -speed)
            xV = 0;
        if(y > 50 && y < 300 && x <= 500 && x >= 500 && xV != -speed)
            xV = 0;
        if(y > 350 && y < 600 && x <= 500 && x >= 500 && xV != -speed)
            xV = 0;
        if(y > 50 && y < 300 && x >= 450 && x <= 450 && xV != speed)
            xV = 0;
        if(y > 350 && y < 600 && x >= 450 && x <= 450 && xV != speed)
            xV = 0;
        if(y > 50 && y < 300 && x <= 900 && x >= 900 && xV != speed)
            xV = 0;
        if(y > 350 && y < 600 && x <= 900 && x >= 900 && xV != speed)
            xV = 0;

    }
    
    public void regenerate()
    {
        if(currentTime % 40 == 0)
        {
            collectables[(int)(Math.random()*20)].setCollected(false);
        }
    }
    
    public void updateSpeed()
    {
        if(score % 200 == 0 && score != 0 && addSpeed)
        {  
            speed += 5;
            addSpeed = false;
        }
        else if(timesRun == 3000)
        {
             addSpeed = true;       
        }
        timesRun++;
    }
    
    public void updateCurrent()
    {
        currentTime = System.currentTimeMillis() - startTime;
    }
    
    /*public void updateInterval()
    
    {
        interval = startTime - currentTime;
    }
    */
    
    public void addSpam()
    {
        if((int)(Math.random()*1000) > 900)
        {
            //fillers[(int)(Math.random()*20)].turnOn();
            fillers[(int)(Math.random()*20)].turnOn();
        }
        
    }
    public void updateColors()
    {
        if(score >= 2000)
        {
            rectangles = new Color((int)(Math.random()*255),(int)(Math.random()*255),(int)(Math.random()*255));
            course = new Color((int)(Math.random()*255),(int)(Math.random()*255),(int)(Math.random()*255));
        }
            
    }
    
    
    
    @Override
    public void actionPerformed(ActionEvent e)
    {
        checkEdge();//Velocity updates
        
        x += xV;
        y += yV;//Actualy movements
        
        detectHit();//Collection
        
        checkWin();//Possible end  
        
        
        updateCurrent();//Updates 
        regenerate();//Implied Doctor Who reference
           
        //updateInterval(); 
        updateSpeed();
        addSpam();
        updateColors();
        repaint();
        
    }
}
