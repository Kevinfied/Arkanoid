import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;

public class Level {

    private int totalPoints;

    private int lives;

    private static int level;
    private ArrayList<Brick> blocks;
    private ArrayList<Brick> goldBlocks;

    private Image background;

    public int pointsToClear;

    Level (int lvl) {

        blocks = new ArrayList<Brick>();
        goldBlocks = new ArrayList<Brick>();

        int var = 100;

        if (lvl == 1) {
            level = 1;
            lives = 3;
            totalPoints = 0;
            pointsToClear = 7700;
            background = Util.loadScaledImg("assets/backgrounds/background1.png", Globals.SCREEN_WIDTH, Globals.SCREEN_HEIGHT);
            // 2 * 2 * 2 * 5


            for (int i = 1; i <= 14; i++) {
                blocks.add(new Brick(i * 40 - 20, 75 + var, 40, 20, "Silver"));
                blocks.add(new Brick(i * 40 - 20, 95 + var, 40, 20, "Red"));
                blocks.add(new Brick(i * 40 - 20, 115 + var, 40, 20, "Yellow"));
                blocks.add(new Brick(i * 40 - 20, 135 + var, 40, 20, "Blue"));
                blocks.add(new Brick(i * 40 - 20, 155 + var, 40, 20, "Violet"));
                blocks.add(new Brick(i * 40 - 20, 175 + var, 40, 20, "Green"));
            }


        }
        if (lvl == 2) {
            level = 2;
            lives = 3;
            totalPoints = 0;
            pointsToClear = 7700 + 5600;
            background = Util.loadScaledImg("assets/backgrounds/background2.png", Globals.SCREEN_WIDTH, Globals.SCREEN_HEIGHT);
            // first row
            for (int i = 2; i <= 4; i++) {
                goldBlocks.add(new Brick(i * 40 - 20, 75 + var, 40, 20, "Gold"));
                blocks.add(new Brick(i * 40 - 20, 95 + var, 40, 20, "Violet"));
                blocks.add(new Brick(i * 40 - 20, 115 + var, 40, 20, "Blue"));
                blocks.add(new Brick(i * 40 - 20, 135 + var, 40, 20, "Red"));
                blocks.add(new Brick(i * 40 - 20, 155 + var, 40, 20, "Green"));
                blocks.add(new Brick(i * 40 - 20, 175 + var, 40, 20, "Cyan"));
                blocks.add(new Brick(i * 40 - 20, 195 + var, 40, 20, "Orange"));
                blocks.add(new Brick(i * 40 - 20, 215 + var, 40, 20, "Silver"));
            }

            for (int i = 6; i<=9; i++) {
                blocks.add(new Brick(i * 40 - 20, 75 + var, 40, 20, "Silver"));
                blocks.add(new Brick(i * 40 - 20, 95 + var, 40, 20, "Orange"));
                blocks.add(new Brick(i * 40 - 20, 115 + var, 40, 20, "Cyan"));
                blocks.add(new Brick(i * 40 - 20, 135 + var, 40, 20, "Green"));
                blocks.add(new Brick(i * 40 - 20, 155 + var, 40, 20, "Red"));
                blocks.add(new Brick(i * 40 - 20, 175 + var, 40, 20, "Blue"));
                blocks.add(new Brick(i * 40 - 20, 195 + var, 40, 20, "Violet"));
                goldBlocks.add(new Brick(i * 40 - 20, 215 + var, 40, 20, "Gold"));
            }

            for (int i = 11; i<=13; i++) {
                goldBlocks.add(new Brick(i * 40 - 20, 75 + var, 40, 20, "Gold"));
                blocks.add(new Brick(i * 40 - 20, 95 + var, 40, 20, "Violet"));
                blocks.add(new Brick(i * 40 - 20, 115 + var, 40, 20, "Blue"));
                blocks.add(new Brick(i * 40 - 20, 135 + var, 40, 20, "Red"));
                blocks.add(new Brick(i * 40 - 20, 155 + var, 40, 20, "Green"));
                blocks.add(new Brick(i * 40 - 20, 175 + var, 40, 20, "Cyan"));
                blocks.add(new Brick(i * 40 - 20, 195 + var, 40, 20, "Orange"));
                blocks.add(new Brick(i * 40 - 20, 215 + var, 40, 20, "Silver"));
            }

            // second row



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

    public void drawLevel(Graphics g, Ball ball, Paddle player) {

        g.setColor(new Color(0,0,0));
        g.fillRect(0,0,Globals.SCREEN_WIDTH,Globals.SCREEN_HEIGHT);
        g.drawImage(background, 0, 0, null);
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
