import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import java.util.*;



public class Brick {

//    private String[] colors
    private static int x, y;
    public static final String[] colors = {"White", "Orange", "Cyan", "Green", "Red", "Blue", "Violet", "Yellow", "Gold", "Silver"};

    public static final int[] pointVals = {50, 60, 70, 80, 90, 100, 110, 120, 0, 50};
    private static int WIDTH, HEIGHT;

    private static int health;

    private Image icon;

    private int points;

    private String color;
    public Brick(int xx, int yy, int w, int h, String col) {

        this.x = xx;
        this.y = yy;
        this.WIDTH = w;
        this.HEIGHT = h;
        this.health = health;

        points = pointVals[Arrays.asList(colors).indexOf(col)];
        this.color = col;
        icon = Util.loadScaledImg("assets/blocks/Arkanoid_Brick"+color+".jpg", WIDTH, HEIGHT);
        health = 1;

        if (color.equals("Silver")) {
            health = 2; //silver blocks have 2 health
        }



    }



    public void setHealth(int h) {
        health = h;
    }


    public static int getX() {
        return x;
    }

    public static int getY() {
        return y;
    }

    public String getColor() {
        return color;
    }

    public static int getWidth() {
        return WIDTH;
    }

    public static int getHeight() {
        return HEIGHT;
    }

    public static void lowerHealth(int h) {
        health -= h;
    }


    public void draw(Graphics g) {
        g.drawImage(icon, x, y, null);
    }


}
