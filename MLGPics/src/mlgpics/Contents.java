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
    private Collectable[] collectables = new Collectable[21];
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
                                new Filler(),new Filler(),new Filler()};//30
                                //new Filler(),new Filler(),new Filler(),
                                //new Filler(),new Filler(),new Filler(),
                                //new Filler(),new Filler(),new Filler(),
                                //new Filler(),new Filler(),new Filler(),
                                //new Filler(),new Filler(),new Filler(),
                                //new Filler(),new Filler(),new Filler(),
                                //new Filler(),new Filler(),new Filler(),
                                //new Filler(),new Filler(),new Filler(),
                                //new Filler(),new Filler(),new Filler(),
                                //new Filler(),new Filler(),new Filler()};//60
    
    
    private Image character;
    private Image end, hit;
    
    private final LoopingColor backgroundColor = new LoopingColor(0.2F);
    private final LoopingColor boxesColor = new LoopingColor(0.4F);
    
    private int x = 475, y = 150;
    private int xV = 0;
    private int yV = 0;
    //private int xD = 25, yD = 25;
    private final int timerX = 180, timerY = 6;
    
    private boolean isWon = false;
    private boolean addSpeed = true;
    private boolean reactions = false, ep = false;
    private boolean hitmarker = false;
    
    private Color rectangles = new Color(0,0,0);
    private Color course = new Color(255,255,255);
    
    private Timer t;
    
    private int score = 0, finScore = 0;
    private int amt = 20;
    private int speed = 5;
    private int timesRun = 0;
    
    private final long startTime = System.currentTimeMillis();
    private long currentTime = 0;
    private long lastPickup = System.currentTimeMillis();
    
    private final Sound backgroundMusic, mlgReaction, darude, rekt;
    
    public Contents(){
        super.setDoubleBuffered(true);
        t = new Timer(5,this);
        t.start();
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        for(int ind = 0; ind < 7; ind++)
            collectables[ind] = (new Collectable(images[(int)(Math.random()*6)],ind * 150 + 30, 35));
        for(int ind = 7; ind < 14; ind++)
            collectables[ind] = (new Collectable(images[(int)(Math.random()*6)], (ind % 7) * 150 + 30, 350));
        for(int ind = 14; ind < 21; ind++)
            collectables[ind] = (new Collectable(images[(int)(Math.random()*6)], (ind % 7) * 150 + 30, 630));
        
        
        for (Filler filler : fillers) {
            filler.setImg(scaledImage(filler.getImg(), (int)(Math.random()*100 + 50) , (int)(Math.random()*100 + 50)));
        }
        
        // Initializing and starting playback of music
        backgroundMusic = new Sound("Music.wav");
        mlgReaction = new Sound("mlg_reaction.wav");
        darude = new Sound("darude.wav");
        rekt = new Sound("rekt.wav");
        backgroundMusic.play();
        
    }

    @Override
    public void paintComponent(Graphics g){ 
        super.paintComponent(g);
        Graphics g2d = (Graphics2D)g;
        g2d.setFont(new Font("TimesRoman", Font.BOLD, 25)); 
        ImageIcon ii = new ImageIcon(this.getClass().getResource("player.jpeg"));
        ImageIcon ii4 = new ImageIcon(this.getClass().getResource("images.jpeg"));
        ImageIcon ii5 = new ImageIcon(this.getClass().getResource("hitmarkerFiller.jpeg"));
     
        character = ii.getImage();
        end = ii4.getImage();
        hit = ii5.getImage();
        
        for (Filler filler : fillers) //changes coordinates of fillers
        {
            filler.update();
        }
        fillers[0].update();
  
        for (Collectable collectable : collectables) {
            collectable.setImage(scaledImage(collectable.getImage(), 40, 40));
        }

        
        character = scaledImage(character,50,50);
        end = scaledImage(end,1000,700);
        hit = scaledImage(end,100,100);
        

        setBackground(rectangles); 

        g2d.setColor(course);
        
        g2d.fillRect(0,0,1000,100);
        g2d.fillRect(0,0,100,700);
        g2d.fillRect(900,0,100,700);
        g2d.fillRect(0,600,1000,100);
        
        g2d.fillRect(450,0,100,700);
        
        g2d.fillRect(0,300,1000,100);
        
        for (Collectable collectable : collectables) {
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
        if(hitmarker)
        {
            g2d.drawImage(hit, x, y, this);
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
        g2d.drawString("Score: "+ score, 25, 25);
        }
        
        // 750 - (int)((System.currentTimeMillis()-lastPickup)/2.5)
        // Subtracting the time in milliseconds between last Collectable
        //    and the current time from 750 to get a new width for the countdown
        g2d.setColor(Color.blue);
        g2d.fillRect(timerX, timerY, 750 - (int)((System.currentTimeMillis()-lastPickup)/2.5), 20);
        g2d.setColor(Color.black);
        g2d.drawRect(timerX, timerY, 750 - (int)((System.currentTimeMillis()-lastPickup)/2.5), 20);
    }
    
    // Moves player 'speed' amount up
    public void up(){
        xV = 0;
        yV = -speed;
    }
    
    // Moves player 'speed' amount down
    public void down(){   
        xV = 0;
        yV = speed;
    }
    
    // Moves player 'speed' amount left
    public void left(){
        xV = -speed;
        yV = 0;
    }
    
    // Moves player 'speed' amount right
    public void right(){
        xV = speed;
        yV = 0;
    }
    
    public void reset(){
        xV = 0;
        yV = 0;
    }
    
    @Override
    public void keyPressed(KeyEvent e)
    {
        int code = e.getKeyCode();
        
        if(code == KeyEvent.VK_UP || code == KeyEvent.VK_W)
            up();
        
        
        if(code == KeyEvent.VK_DOWN || code == KeyEvent.VK_S)
            down();
        
        
        if(code == KeyEvent.VK_LEFT || code == KeyEvent.VK_A)
            left();
        
        
        if(code == KeyEvent.VK_RIGHT || code == KeyEvent.VK_D)
            right();
        
    }
    @Override
    public void keyTyped(KeyEvent e) {}
    @Override
    public void keyReleased(KeyEvent e) {}
    
    //boolean top = true;
    //boolean bottom = false;
    //boolean left = false;
    //boolean right = false;
    
    /**
     * @param img : Image to scale
     * @param w : Width to scale to
     * @param h : Height to scale to
     * @return : Returns scaled 'img'
     */
    private Image scaledImage(Image img, int w, int h){
        BufferedImage resizedImage = new BufferedImage(w,h,BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = resizedImage.createGraphics();
        g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
                             RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2d.drawImage(img, 0, 0, w, h, null);
        return resizedImage;
    }

    /**
     * Detects if you are touching a collectable
     */
    public void detectHit(){
        for (Collectable collectable : collectables) {
            
            // If player is touching collectable
            if (collectable.getX() - 50 <= x && x <= collectable.getX() + 50 && (collectable.getY() - 50 <= y && y <= collectable.getY() + 50 && !collectable.isCollected())) {
                collectable.setCollected(true);
                score += 50;
                amt --;
                
                // Sets lastPickup to currentTimeMillis
                updateCountdown();
                
                // Chance to add spam image
                addSpam();
                
                // If score is above reaction limit, plays sniper hit
                //   instead of a hitmarker sound
                if(reactions) new Sound("sniperhit.wav").play();
                else new Sound("hitmarker.wav").play();
                hitmarker = true;
            }
            else
                hitmarker = false;
        }
    }
    
    /**
     * Checks if you have collected every type of collectable
     * 
     * @return : If you have won of not
     */
    public boolean checkWin()
    {
        for (Collectable collectable : collectables) {
            if (!collectable.isCollected()) {
                isWon = false;
                return false;
            }
        }
        
        ///////////////
        //isWon = true;
        //return true;
        ///////////////
        
        isWon = false;
        return false;
    }
    
    /**
     * Checks to see if player is touching the borders or boxes
     */
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
    
    /**
     * Regenerates random Collectable
     */
    public void regenerate(){
        if(currentTime % 40 == 0)
            collectables[(int)(Math.random()*20)].setCollected(false);
    }
    
    public void updateSpeed(){
        if(score % 200 == 0 && score != 0 && addSpeed){
            speed += 5;
            addSpeed = false;
        }
        
        else if(timesRun == 3000)
             addSpeed = true;
        
        timesRun++;
    }
    
    
    public void updateCurrent(){
        currentTime = System.currentTimeMillis() - startTime;
    }
    
    /*public void updateInterval()
    
    {
        interval = startTime - currentTime;
    }
    */
    
    /**
     * Sets time since last pickup to currentTimeMillis
     */
    public void updateCountdown(){
        lastPickup = System.currentTimeMillis();
    }
    
    // 1 in 10 chance of spawning a Filler
    public void addSpam()
    {
        if((int)(Math.random()*1000) > 700)
        {
            //fillers[(int)(Math.random()*20)].turnOn();
            fillers[(int)(Math.random()*30)].turnOn();
        }
        
    }
    public void updateColors()
    {
        if(score >= 2000){
            course = new Color((int)(Math.random()*255),(int)(Math.random()*255),(int)(Math.random()*255));
            rectangles = new Color((int)(Math.random()*255),(int)(Math.random()*255),(int)(Math.random()*255));
            
        }
        else if(score >= 4000){
            if(!reactions){
                reactions = true;
                mlgReaction.play();
                darude.play();
                backgroundMusic.stop();
            }
            
            rectangles = boxesColor.nextColor();
            course = backgroundColor.nextColor();
        }
        
            
    }
    
    @Override
    public void actionPerformed(ActionEvent e)
    {
        checkEdge();//Velocity updates
        
        x += xV;
        y += yV;//Actual movements
        
        detectHit();//Collection
        
        checkWin();//Possible end  
        
        
        updateCurrent();//Updates 
        regenerate();//Implied Doctor Who reference
           
        //updateInterval(); 
        updateSpeed();
        
        //added to collectable pickup
        //addSpam();
                
                
        updateColors();
        repaint();
    }
}
