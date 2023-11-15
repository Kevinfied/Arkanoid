import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import java.util.*;


public class Powerup {

    private static final String[] names= {"Player", "Enlarge", "Laser", "Slow"};


    private int x, y;

    private static int WIDTH = 30;
    private static int HEIGHT = 15;
    private String type;
    private int speed;
    private static final Image[] icons = {
            new ImageIcon("assets/powerups/Player.gif").getImage(),

            new ImageIcon("assets/powerups/Enlarge.gif").getImage(),
            new ImageIcon("assets/powerups/Laser.gif").getImage(),
            new ImageIcon("assets/powerups/Slow.gif").getImage()
    };
    private Image icon;

    public Powerup(int xx, int yy) {
        x = xx;
        y = yy;
        type = names[Util.randInt(0, names.length-1)];
        speed = 1;
        icon = icons[Arrays.asList(names).indexOf(type)];
    }



    public void playerPowerup() {
        Paddle.addHealth(1);
    }



    public void move() {
        y += speed;
    }

    public Rectangle getRect() {
        return new Rectangle(x, y, WIDTH, HEIGHT);
    }

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

    public void drop() {
        int a = Util.randInt(0, 100);
        if (a < 50) {

        }
    }



}