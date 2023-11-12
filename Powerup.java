import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import java.util.*;


public class Powerup {

    public static final String[] names= {"Player","Catch", "Enlarge", "Laser"};


    public int x, y;
//    public int vx, vy;
    public int WIDTH = 20;
    public int HEIGHT = 20;
    public String type;
    public int speed;

    public Powerup(int xx, int yy, String t) {
        x = xx;
        y = yy;
        type = t;
        speed = 3;


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

    public void draw() {

    }


}