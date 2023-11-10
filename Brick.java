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
        icon = Util.loadScaledImg("assets/blocks/"+color+".jpg", WIDTH, HEIGHT);
        health = 1;

        if (color.equals("Silver")) {
            health = 2;
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


    public static int getHealth() {
        return health;
    }


    public static void lowerHealth(int h) {
        health -= h;
    }


    public void draw(Graphics g) {
        System.out.println("DRAW");
        g.drawImage(icon, x, y, null);

    }

    public Rectangle getRect() {

        return new Rectangle(x, y, WIDTH, HEIGHT);
    }

    public Rectangle getBottom(){ //returns the bottom side of the block
        return new Rectangle(x, y+HEIGHT-1, WIDTH, 1);
    }
    public Rectangle getTop(){ //returns the top side of the block
        return new Rectangle(x, y, WIDTH, 1);
    }
    public Rectangle getLeft(){ //returns the left side of the block
        return new Rectangle(x, y, 1, HEIGHT);
    }
    public Rectangle getRight(){//returns the right side of the block
        return new Rectangle(x+WIDTH-1, y, 1, HEIGHT);
    }

    public int getPoints() {
        return points;
    }

}
