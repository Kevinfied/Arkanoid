import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import java.util.*;


public class Powerup {

    public static final String[] names= {"Player", "Catch", "Enlarge", "Laser"};


    public int x, y;
//    public int vx, vy;
    public static int WIDTH = 30;
    public static int HEIGHT = 15;
    public String type;
    public int speed;
    public static final Image[] icons = {
            new ImageIcon("assets/powerups/Player.gif").getImage(),
            new ImageIcon("assets/powerups/Catch.gif").getImage(),
            new ImageIcon("assets/powerups/Enlarge.gif").getImage(),
            new ImageIcon("assets/powerups/Laser.gif").getImage()
    };
    public Image icon;

    public Powerup(int xx, int yy) {
        x = xx;
        y = yy;
        type = names[Util.randInt(0, 3)];
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
//        g.setColor(Color.RED);
//        g.fillRect(x, y, WIDTH, HEIGHT);

//        if (type.equals("Player")) {
//            g.setColor(Color.LIGHT_GRAY);
//        }
//        else if (type.equals("Catch")) {
//            g.setColor(Color.GREEN);
//        }
//        else if (type.equals("Enlarge")) {
//            g.setColor(Color.BLUE);
//        }
//        else if (type.equals("Laser")) {
//            g.setColor(Color.RED);
//        }
//        g.fillRect(x, y, WIDTH, HEIGHT);

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