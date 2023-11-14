import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;


public class Laser {

    public int x, y; // x and y are the coordinates of the center of the laser ball
    public static int RADIUS;
    public int speed;

    public Laser(int xx, int yy) {
        x = xx;
        y = yy;
        RADIUS = 2;
        speed = 5;

    }


    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
    public void move() {
        y -= speed;
    }

    public void draw(Graphics g) {
        g.setColor(Color.RED);
        g.fillOval(x-RADIUS, y-RADIUS, RADIUS*2, RADIUS*2);
    }
    public Rectangle getRect() {
        return new Rectangle(x-RADIUS, y-RADIUS, RADIUS*2, RADIUS*2);
    }


    public static int getRadius() {
        return RADIUS;
    }
}
