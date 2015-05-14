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
public class Contents extends JPanel implements ActionListener
{
    private Image character;
    private Image dor,mtn, end;
    private int x = 10, y = 0;
    private int xD = 0, yD = 0;
    private int xM = 800, yM = 500;
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
        ImageIcon ii4 = new ImageIcon(this.getClass().getResource("images.jpeg"));
        
        //haracter = character.getScaledInstance(100,50,Image.SCALE_AREA_AVERAGING);
        character = ii.getImage();
        dor = ii3.getImage();
        mtn = ii2.getImage();
        end = ii4.getImage();
        
        //I don't even know what this is supposed to do
        ////Internet people said it would work .-.
        /*ImageIcon ii = new ImageIcon("/Users/David/Desktop/MLG-Pics-Pls/MLGPics/src/mlgpics/player.jpeg");
        BufferedImage bi = new BufferedImage(100, 100, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d2 = (Graphics2D)bi.createGraphics();
        g2d2.addRenderingHints(new RenderingHints(RenderingHints.KEY_RENDERING,
                                   RenderingHints.VALUE_RENDER_QUALITY));*/
            //boolean b = g2d2.drawImage(ii.getImage(), 0, 0, 50, 50, null);
            //System.out.println(b);
        character = scaledImage(character,100,100);
        dor = scaledImage(dor,100,100);
        mtn = scaledImage(mtn,100,100);
        end = scaledImage(end,1000,600);
            //ImageIO.write(bi, "jpeg", new File("dew.jpeg"));
        
 
        
     
        setBackground(Color.black);
        g2d.setColor(Color.white);
        g2d.fillRect(0,0,900,100);
        g2d.fillRect(0,0,100,600);
        g2d.fillRect(800,0,100,600);
        g2d.fillRect(0,500,900,100);
        g2d.drawImage(dor, xD, yD, this);
        g2d.drawImage(mtn, xM, yM, this);
        g2d.drawImage(character, x, y, this);
        //try{
        //checkWin();
        //}
        //catch( MalformedURLException mue)
        //{}
        if(checkWin())
        {
            /*g2d.setColor(Color.white);
            g2d.fillRect(0,0,900,600);*/
            try
            {Thread.sleep(3000);
            }
            catch(InterruptedException ie)
            {}
            /*g2d.setColor(Color.black);
            g2d.drawString("You won m8", 250, 250);}*/
            g2d.drawImage(end, 0, 0, this);
            
        
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
        /*if(bD && bM)
        {
            xV = 0;
            yV = 0;
            URL url = new URL("http://cdn.yourepeat.com/media/gif/000/627/681/63322bf6e355e19a0746313e7ab18387.gif");
            Icon icon = new ImageIcon(url);
            JLabel label = new JLabel(icon);

            JFrame f = new JFrame("Animation");
            f.getContentPane().add(label);
            f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            f.pack();
            f.setLocationRelativeTo(null);
            f.setVisible(true);
        }*/
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
    public void actionPerformed(ActionEvent e) {
        
        move();
        detectMtnCol();
        detectDorCol();
        repaint();
    }

}
