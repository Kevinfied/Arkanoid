/*
    * Powerup.java
    * Kevin Xu
    *
    * Contains all the methods for the powerup object
 */

import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import java.util.*;


public class Powerup {

    private static final String[] names= {"Player", "Enlarge", "Laser", "Slow"}; // names of the powerups


    private int x, y; // x and y are the coordinates of the top left corner of the powerup

    private static int WIDTH = 30; // width of the powerup
    private static int HEIGHT = 15; // height of the powerup
    private String type; // type of the powerup
    private int speed; // speed of the powerup
    private static final Image[] icons = { // icons
            new ImageIcon("assets/powerups/Player.gif").getImage(),
            new ImageIcon("assets/powerups/Enlarge.gif").getImage(),
            new ImageIcon("assets/powerups/Laser.gif").getImage(),
            new ImageIcon("assets/powerups/Slow.gif").getImage()
    };
    private Image icon; // icon of the powerup


    // constructor
    public Powerup(int xx, int yy) {
        x = xx;
        y = yy;
        type = names[Util.randInt(0, names.length-1)];
        speed = 1;
        icon = icons[Arrays.asList(names).indexOf(type)];
    }




    public void move() {
        y += speed;
    }

    public Rectangle getRect() {
        return new Rectangle(x, y, WIDTH, HEIGHT);
    }

    // returns the type of powerup
    public String getType() {
        return type;
    }

    public void draw(Graphics g) {
        g.drawImage(icon, x, y, WIDTH, HEIGHT,null);
    }

    public static int getWidth() {
        return WIDTH;
    }
    public static int getHeight() {
        return HEIGHT;
    }

}