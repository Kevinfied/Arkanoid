import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import java.util.*;



public class Brick {

//    private String[] colors
    private int x, y;
    public static final String[] colors = {"White", "Orange", "Cyan", "Green", "Red", "Blue", "Violet", "Yellow", "Gold", "Silver"};

    public static final int[] pointVals = {50, 60, 70, 80, 90, 100, 110, 120, 0, 50};
    private int WIDTH, HEIGHT;

    private int health;

    private Image icon;

    private int points;

    private String color;
    public Brick(int xx, int yy, int w, int h, String col) {

        x = xx;
        y = yy;
        WIDTH = w;
        HEIGHT = h;
        health = health;

        points = pointVals[Arrays.asList(colors).indexOf(col)];
        color = col;
        icon = Util.loadScaledImg("assets/blocks/"+color+".jpg", WIDTH, HEIGHT);


        if (color.equals("Silver")) {
            health = 2;
        }
        else {
            health = 1;
        }



    }



    public void setHealth(int h) {
        health = h;
    }


    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public String getColor() {
        return color;
    }

    public int getWidth() {
        return WIDTH;
    }

    public int getHeight() {
        return HEIGHT;
    }


    public int getHealth() {
        return this.health;
    }


    public void lowerHealth(int h) {
        health -= h;
    }


    public void draw(Graphics g) {
//        System.out.println("DRAW");
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

    @Override
    public String toString() {
        return "Brick{" +
                "x=" + x +
                ", y=" + y +
                ", WIDTH=" + WIDTH +
                ", HEIGHT=" + HEIGHT +
                ", health=" + health +
                ", icon=" + icon +
                ", points=" + points +
                ", color='" + color + '\'' +
                '}';
    }



}
