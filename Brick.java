/*
    Brick.java
    Kevin Xu

    Contains all the methods for the brick object.
    The ball bounces off of it and the player has to break it.
 */

import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import java.util.*;


public class Brick {

    private int x, y; // x and y are the coordinates of the top left corner of the brick

    // colors
    private static final String[] colors = {"White", "Orange", "Cyan", "Green", "Red", "Blue", "Violet", "Yellow", "Gold", "Silver"};

    // point values
    private static final int[] pointVals = {50,      60,        70,     80,      90,    100,     110,      120,      0,      50};
    private int WIDTH, HEIGHT; // width and height of the brick

    private int health; // health of the brick

    private Image icon; // icon of the brick

    private int points; // points of the brick

    private String color; // color of the brick

    // constructor
    public Brick(int xx, int yy, int w, int h, String col) {
        x = xx;
        y = yy;
        WIDTH = w;
        HEIGHT = h;
        health = health;

        points = pointVals[Arrays.asList(colors).indexOf(col)];
        color = col;
        icon = Util.loadScaledImg("assets/blocks/"+color+".jpg", WIDTH, HEIGHT);

        if (color.equals("Silver")) { // silver blocks takes 2 hits
            health = 2;
        }
        else { // all others take only 1 hit
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
        g.drawImage(icon, x, y, null);
    }
    public Rectangle getRect() {
        return new Rectangle(x, y, WIDTH, HEIGHT);
    }
    public int getPoints() {
        return points;
    }


    // for debug
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
