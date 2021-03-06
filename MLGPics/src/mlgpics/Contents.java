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
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 *
 * @author David, Jack
 */
public class Contents extends JPanel implements ActionListener, KeyListener {
    
    private Collectable[] collectables = new Collectable[21];
    private final Image[] images = {new ImageIcon(this.getClass().getResource("dew.jpeg")).getImage(), //putting images used into images[]
        new ImageIcon(this.getClass().getResource("dewLogo.jpeg")).getImage(),
        new ImageIcon(this.getClass().getResource("doritoAndDew.jpeg")).getImage(),
        new ImageIcon(this.getClass().getResource("doritoLogo.jpeg")).getImage(),
        new ImageIcon(this.getClass().getResource("euphorito.jpeg")).getImage(),
        new ImageIcon(this.getClass().getResource("RV1BwzL.jpeg")).getImage()};
    private final Filler[] fillers = {new Filler(), new Filler(), new Filler(), //putting fillers into fillers[]
        new Filler(), new Filler(), new Filler(),
        new Filler(), new Filler(), new Filler(),
        new Filler(), new Filler(), new Filler(),
        new Filler(), new Filler(), new Filler(),
        new Filler(), new Filler(), new Filler(),
        new Filler(), new Filler(), new Filler(),
        new Filler(), new Filler(), new Filler(),
        new Filler(), new Filler(), new Filler(),
        new Filler(), new Filler(), new Filler()};//30
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
    private Image ggYouLost;
    private Image start;
    
    private final LoopingColor backgroundColor = new LoopingColor(0.2F, .01F);
    private final LoopingColor boxesColor = new LoopingColor(0.4F, .01F);
    
    private final int firstChange = 1500, secondChange = 3000;
    
    private int x = 475, y = 150;
    private int xV = 0;
    private int yV = 0;
    private final int timerX = 180, timerY = 6;
    
    private boolean reactions = false;
    private boolean lost = false;
    private boolean speedsUpdated = false, speedsUpdatedAgain = false;//lelz
    private boolean space = false;
    
    private Color rectangles = new Color(0, 0, 0);
    private Color course = new Color(255, 255, 255);
    
    private Timer t;
    
    private int score = 0;
    private final int speed = 5;
    
    private long startTime;
    private long lastPickup;
    
    private final Sound backgroundMusic, mlgReaction, darude, rekt, sadHorn;
    
    public Contents() {
        super.setDoubleBuffered(true);
        t = new Timer(5, this);//Checks for an event every 5 milliseconds
        t.start();
        setFocusable(true);
        addKeyListener(this);
        setFocusTraversalKeysEnabled(false);
        for (int ind = 0; ind < 7; ind++) {//Spawns collectables on board
            collectables[ind] = (new Collectable(images[(int) (Math.random() * 6)], ind * 150 + 30, 35));
        }
        for (int ind = 7; ind < 14; ind++) {
            collectables[ind] = (new Collectable(images[(int) (Math.random() * 6)], (ind % 7) * 150 + 30, 350));
        }
        for (int ind = 14; ind < 21; ind++) {
            collectables[ind] = (new Collectable(images[(int) (Math.random() * 6)], (ind % 7) * 150 + 30, 630));
        }
        
        for (Filler filler : fillers) {//Puts fillers aka spam on board
            filler.setImg(scaledImage(filler.getImg(), (int) (Math.random() * 100 + 50), (int) (Math.random() * 100 + 50)));
        }
        
        // Initializing and starting playback of music
        backgroundMusic = new Sound("Music.wav");
        mlgReaction = new Sound("mlg_reaction.wav");
        darude = new Sound("darude.wav");
        rekt = new Sound("rekt.wav");
        sadHorn = new Sound("sadHorn.wav");
        backgroundMusic.play();
        
        ImageIcon ii = new ImageIcon(this.getClass().getResource("player.jpeg"));
        ImageIcon ii2 = new ImageIcon(this.getClass().getResource("images.jpeg"));
        ImageIcon ii3 = new ImageIcon(this.getClass().getResource("hitmarkerFiller.jpeg"));
        ImageIcon ii4 = new ImageIcon(this.getClass().getResource("gitGud.jpeg"));
        ImageIcon ii5 = new ImageIcon(this.getClass().getResource("start.jpeg"));
        
        character = ii.getImage();
        end = ii2.getImage();
        hit = ii3.getImage();
        ggYouLost = ii4.getImage();
        start = ii5.getImage();
        
        character = scaledImage(character, 50, 50);
        end = scaledImage(end, 1000, 700);
        hit = scaledImage(end, 100, 100);
        ggYouLost = scaledImage(ggYouLost, 1000, 700);
        start = scaledImage(start, 1000, 700);
    }
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        Graphics g2d = (Graphics2D) g;
        g2d.setFont(new Font("TimesRoman", Font.BOLD, 25));
        
        for (Filler filler : fillers) //changes coordinates of fillers
        {
            filler.update();
        }
        fillers[0].update();
        
        for (Collectable collectable : collectables) {
            collectable.setImage(scaledImage(collectable.getImage(), 40, 40));
        }
        
        if (lost) {
            g2d.setFont(new Font("TimesRoman", Font.BOLD, 40));
            g2d.drawImage(ggYouLost, 0, 0, this);
            g2d.drawString("Score: " + score, 250, 190);
            backgroundMusic.stop();
            mlgReaction.stop();
            darude.stop();
            rekt.stop();
            sadHorn.play();
            return;
        }
        
        setBackground(rectangles);
        
        g2d.setColor(course);
        
        g2d.fillRect(0, 0, 1000, 100);
        g2d.fillRect(0, 0, 100, 700);
        g2d.fillRect(900, 0, 100, 700);//Draws track
        g2d.fillRect(0, 600, 1000, 100);
        
        g2d.fillRect(450, 0, 100, 700);
        
        g2d.fillRect(0, 300, 1000, 100);
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
        
        g2d.setColor(Color.black);
        g2d.drawString("Score: " + score, 25, 25);
        
        // 750 - (int)((System.currentTimeMillis()-lastPickup)/2)
        // Subtracting the time in milliseconds between last Collectable
        //    and the current time from 750 to get a new width for the countdown
        g2d.setColor(Color.blue);
        g2d.fillRect(timerX, timerY, 750 - (int) ((System.currentTimeMillis() - lastPickup) / 2), 20);
        g2d.setColor(Color.black);
        
        g2d.drawRect(timerX, timerY, 750 - (int) ((System.currentTimeMillis() - lastPickup) / 2), 20);
        if(!space) g2d.drawImage(start, 0, 0, this);
        
    }
    
    // Moves player 'speed' amount up
    public void up() {
        if (space) {
            xV = 0;
            yV = -speed;
        }
    }
    
    // Moves player 'speed' amount down
    public void down() {
        if (space) {
            xV = 0;
            yV = speed;
        }
    }
    
    // Moves player 'speed' amount left
    public void left() {
        if (space) {
            xV = -speed;
            yV = 0;
        }
    }
    
    // Moves player 'speed' amount right
    public void right() {
        if (space) {
            xV = speed;
            yV = 0;
        }
    }
    
    public void reset() {
        if (space) {
            xV = 0;
            yV = 0;
        }
    }
    
    public void space() {
        if(space) return;
        space = true;
        lastPickup = System.currentTimeMillis();
    }
    
    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        
        if (code == KeyEvent.VK_UP || code == KeyEvent.VK_W) {
            up();
        }
        if (code == KeyEvent.VK_DOWN || code == KeyEvent.VK_S) {
            down();
        }
        
        if (code == KeyEvent.VK_LEFT || code == KeyEvent.VK_A) {
            left();
        }
        
        if (code == KeyEvent.VK_RIGHT || code == KeyEvent.VK_D) {
            right();
        }
        
        if (code == KeyEvent.VK_SPACE) {
            space();
        }
        
    }
    
    @Override
    public void keyTyped(KeyEvent e) {
    }
    
    @Override
    public void keyReleased(KeyEvent e) {
    }
    
    /**
     * @param img : Image to scale
     * @param w : Width to scale to
     * @param h : Height to scale to
     * @return : Returns scaled 'img'
     */
    private Image scaledImage(Image img, int w, int h) {
        BufferedImage resizedImage = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = resizedImage.createGraphics();
        g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
                RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2d.drawImage(img, 0, 0, w, h, null);
        return resizedImage;
    }
    
    /**
     * Detects if you are touching a collectable
     */
    public void detectHit() {
        if(lost) return;
        for (Collectable collectable : collectables) {
            
            // If player is touching collectable
            if (collectable.getX() - 50 <= x && x <= collectable.getX() + 50 && (collectable.getY() - 50 <= y && y <= collectable.getY() + 50 && !collectable.isCollected())) {
                collectable.setCollected(true);
                score += 50;
                // Sets lastPickup to currentTimeMillis
                updateCountdown();
                
                // Chance to add spam image
                addSpam();
                
                // If score is above reaction limit, plays sniper hit
                //   instead of a hitmarker sound
                if (reactions) {
                    new Sound("sniperhit.wav").play();
                }
                new Sound("hitmarker.wav").play();
            }
        }
    }
    
    /**
     * Returns true and sets 'lost' to true if player has ran out of time
     *
     * @return : True if player has ran out of time
     */
    public boolean checkLose() {
        
        if ((750 - (int) ((System.currentTimeMillis() - lastPickup) / 2)) <= 0 && space) {
            
            lost = true;
            return true;
        }
        return false;
        
    }
    
    /**
     * Checks to see if player is touching the borders or boxes
     */
    public void checkEdge() {
        if (x >= 950 && xV == speed)//For borders
        {
            xV = 0;
        } else if (x <= 0 && xV == -speed) {
            xV = 0;
        }
        if (y >= 630 && yV == speed) {
            yV = 0;
        } else if (y <= 0 && yV == -speed) {
            yV = 0;
        }
        
        if (x > 50 && x < 450 && y >= 50 && y <= 50 && yV != -speed) {
            yV = 0;//For Horizontal Block Edges
        }
        if (x > 500 && x < 900 && y >= 50 && y <= 50 && yV != -speed) {
            yV = 0;
        }
        if (x > 50 && x < 450 && y >= 350 && y <= 350 && yV != -speed) {
            yV = 0;
        }
        if (x > 500 && x < 900 && y >= 350 && y <= 350 && yV != -speed) {
            yV = 0;
        }
        if (x > 50 && x < 450 && y <= 300 && y >= 300 && yV != speed) {
            yV = 0;
        }
        if (x > 500 && x < 900 && y <= 300 && y >= 300 && yV != speed) {
            yV = 0;
        }
        if (x > 50 && x < 450 && y <= 600 && y >= 600 && yV != speed) {
            yV = 0;
        }
        if (x > 500 && x < 900 && y <= 600 && y >= 600 && yV != speed) {
            yV = 0;
        }
        
        if (y > 50 && y < 300 && x >= 50 && x <= 50 && xV != -speed) {
            xV = 0;//For Vertical Block Edges
        }
        if (y > 350 && y < 600 && x >= 50 && x <= 50 && xV != -speed) {
            xV = 0;
        }
        if (y > 50 && y < 300 && x <= 500 && x >= 500 && xV != -speed) {
            xV = 0;
        }
        if (y > 350 && y < 600 && x <= 500 && x >= 500 && xV != -speed) {
            xV = 0;
        }
        if (y > 50 && y < 300 && x >= 450 && x <= 450 && xV != speed) {
            xV = 0;
        }
        if (y > 350 && y < 600 && x >= 450 && x <= 450 && xV != speed) {
            xV = 0;
        }
        if (y > 50 && y < 300 && x <= 900 && x >= 900 && xV != speed) {
            xV = 0;
        }
        if (y > 350 && y < 600 && x <= 900 && x >= 900 && xV != speed) {
            xV = 0;
        }
        
    }
    
    /**
     * Regenerates random Collectable
     */
    public void regenerate() {
        if ((System.currentTimeMillis() - startTime) % 30 == 0) {
            collectables[(int) (Math.random() * 20)].setCollected(false);
        }
    }
    
    /**
     * Sets time since last pickup to currentTimeMillis
     */
    public void updateCountdown() {
        lastPickup = System.currentTimeMillis();
    }
    
    // 1 in 10 chance of spawning a Filler
    public void addSpam() {
        if (space) {
            if ((int) (Math.random() * 1000) > 600) {
                //fillers[(int)(Math.random()*20)].turnOn();
                fillers[(int) (Math.random() * 30)].turnOn();
            }
        }
        
    }
    
    public void updateColors() {
        if (score >= secondChange) {
            if((Math.random()*1000) >= 900){
                course = new Color((int)(Math.random()*255),(int)(Math.random()*255),(int)(Math.random()*255));
                rectangles = new Color((int)(Math.random()*255),(int)(Math.random()*255),(int)(Math.random()*255));
            }
            rekt.play();
            if (!speedsUpdated) {
                for (Filler filler : fillers) {
                    filler.faster();
                }
                speedsUpdated = true;
            }
            
        } else if (score >= firstChange) {
            if (!reactions) {
                reactions = true;
                mlgReaction.play();
                darude.play();
                backgroundMusic.stop();
                if (!speedsUpdatedAgain) {
                    for (int ind = 0; ind < fillers.length; ind++) {
                        fillers[ind].faster();
                    }
                }
            }
            
            rectangles = boxesColor.nextColor();
            course = backgroundColor.nextColor();
        }
        
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        checkEdge();//Velocity updates
        
        x += xV;
        y += yV;//Actual movements
        
        detectHit();//Collection
        
        checkLose();//Possible end
        
        regenerate();//Implied Doctor Who reference
        
        updateColors();
        repaint();
    }
}
