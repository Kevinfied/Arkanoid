/*
    * Level.java
    * Kevin Xu
    *
    * Contains all the methods for the level object
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;

public class Level {

    private int totalPoints; // total points in the level. i dont think this was actually used but im scared to break something

    private int lives; // also not used

    private static int level; // level number
    private ArrayList<Brick> blocks; // arraylist of all the blocks in the level
    private ArrayList<Brick> goldBlocks; // arraylist of all the gold blocks in the level

    private Image background; // background image of the level

    private int pointsToClear; // points needed to clear the level. not used
    private Image lvl1Background = Util.loadScaledImg("assets/backgrounds/background1.png", Globals.SCREEN_WIDTH, Globals.SCREEN_HEIGHT-Globals.TOP_BORDER_HEIGHT);
    private Image lvl2Background = Util.loadScaledImg("assets/backgrounds/background2.png", Globals.SCREEN_WIDTH, Globals.SCREEN_HEIGHT-Globals.TOP_BORDER_HEIGHT);

    // constructor
    public Level (int lvl) {
        blocks = new ArrayList<Brick>();
        goldBlocks = new ArrayList<Brick>();

        // LEVEL ONE
        if (lvl == 1) {
            level = 1;
            lives = 3;
            totalPoints = 0;
            pointsToClear = 7700;
            background = lvl1Background;
            // 2 * 2 * 2 * 5

            for (int i = 1; i <= 14; i++) {
                blocks.add(new Brick(i * 40 - 20, 75 + Globals.TOP_BORDER_HEIGHT, 40, 20, "Silver"));
                blocks.add(new Brick(i * 40 - 20, 95 + Globals.TOP_BORDER_HEIGHT, 40, 20, "Red"));
                blocks.add(new Brick(i * 40 - 20, 115 + Globals.TOP_BORDER_HEIGHT, 40, 20, "Yellow"));
                blocks.add(new Brick(i * 40 - 20, 135 + Globals.TOP_BORDER_HEIGHT, 40, 20, "Blue"));
                blocks.add(new Brick(i * 40 - 20, 155 + Globals.TOP_BORDER_HEIGHT, 40, 20, "Violet"));
                blocks.add(new Brick(i * 40 - 20, 175 + Globals.TOP_BORDER_HEIGHT, 40, 20, "Green"));
            }
        }
        // LEVEL TWO
        if (lvl == 2) {
            level = 2;
            lives = 3;
            totalPoints = 0;
            pointsToClear = 7700 + 5600;
            background = lvl2Background;
            // first row
            for (int i = 2; i <= 4; i++) {
                goldBlocks.add(new Brick(i * 40 - 20, 75 + Globals.TOP_BORDER_HEIGHT, 40, 20, "Gold"));
                blocks.add(new Brick(i * 40 - 20, 95 + Globals.TOP_BORDER_HEIGHT, 40, 20, "Violet"));
                blocks.add(new Brick(i * 40 - 20, 115 + Globals.TOP_BORDER_HEIGHT, 40, 20, "Blue"));
                blocks.add(new Brick(i * 40 - 20, 135 + Globals.TOP_BORDER_HEIGHT, 40, 20, "Red"));
                blocks.add(new Brick(i * 40 - 20, 155 + Globals.TOP_BORDER_HEIGHT, 40, 20, "Green"));
                blocks.add(new Brick(i * 40 - 20, 175 + Globals.TOP_BORDER_HEIGHT, 40, 20, "Cyan"));
                blocks.add(new Brick(i * 40 - 20, 195 + Globals.TOP_BORDER_HEIGHT, 40, 20, "Orange"));
                blocks.add(new Brick(i * 40 - 20, 215 + Globals.TOP_BORDER_HEIGHT, 40, 20, "Silver"));
            }

            for (int i = 6; i<=9; i++) {
                blocks.add(new Brick(i * 40 - 20, 75 + Globals.TOP_BORDER_HEIGHT, 40, 20, "Silver"));
                blocks.add(new Brick(i * 40 - 20, 95 + Globals.TOP_BORDER_HEIGHT, 40, 20, "Orange"));
                blocks.add(new Brick(i * 40 - 20, 115 + Globals.TOP_BORDER_HEIGHT, 40, 20, "Cyan"));
                blocks.add(new Brick(i * 40 - 20, 135 + Globals.TOP_BORDER_HEIGHT, 40, 20, "Green"));
                blocks.add(new Brick(i * 40 - 20, 155 + Globals.TOP_BORDER_HEIGHT, 40, 20, "Red"));
                blocks.add(new Brick(i * 40 - 20, 175 + Globals.TOP_BORDER_HEIGHT, 40, 20, "Blue"));
                blocks.add(new Brick(i * 40 - 20, 195 + Globals.TOP_BORDER_HEIGHT, 40, 20, "Violet"));
                goldBlocks.add(new Brick(i * 40 - 20, 215 + Globals.TOP_BORDER_HEIGHT, 40, 20, "Gold"));
            }

            for (int i = 11; i<=13; i++) {
                goldBlocks.add(new Brick(i * 40 - 20, 75 + Globals.TOP_BORDER_HEIGHT, 40, 20, "Gold"));
                blocks.add(new Brick(i * 40 - 20, 95 + Globals.TOP_BORDER_HEIGHT, 40, 20, "Violet"));
                blocks.add(new Brick(i * 40 - 20, 115 + Globals.TOP_BORDER_HEIGHT, 40, 20, "Blue"));
                blocks.add(new Brick(i * 40 - 20, 135 + Globals.TOP_BORDER_HEIGHT, 40, 20, "Red"));
                blocks.add(new Brick(i * 40 - 20, 155 + Globals.TOP_BORDER_HEIGHT, 40, 20, "Green"));
                blocks.add(new Brick(i * 40 - 20, 175 + Globals.TOP_BORDER_HEIGHT, 40, 20, "Cyan"));
                blocks.add(new Brick(i * 40 - 20, 195 + Globals.TOP_BORDER_HEIGHT, 40, 20, "Orange"));
                blocks.add(new Brick(i * 40 - 20, 215 + Globals.TOP_BORDER_HEIGHT, 40, 20, "Silver"));
            }

            // second row



        }
        if (lvl == 99) { // leaving this here for future kevin to finish
            // secret level :)
            level = 99;
        }
    }

    public int getLevel() {
        return level;
    }

    public int getLives() {
        return lives;
    }

    public int getTotalPoints() {
        return totalPoints;
    }

    public int getPointsToClear() {
        return pointsToClear;
    }

    public ArrayList<Brick> getBlocks() {
        return blocks;
    }

    public ArrayList<Brick> getGoldBlocks() {
        return goldBlocks;
    }

    public Image getBackground() {
        return background;
    }


    // not used
    public void drawLevel(Graphics g, Ball ball, Paddle player) {

        g.setColor(new Color(0,0,0));
        g.fillRect(0,0,Globals.SCREEN_WIDTH,Globals.SCREEN_HEIGHT);
        g.drawImage(background, 0, Globals.TOP_BORDER_HEIGHT, null);
        for (Brick b : blocks) {
            b.draw(g);
        }

        for (Brick b : goldBlocks) {
            b.draw(g);
        }

        ball.draw(g);
        player.draw(g);

    }

}
