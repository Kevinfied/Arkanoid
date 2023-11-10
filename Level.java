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

    Level (int lvl) {

        blocks = new ArrayList<Brick>();

        if (lvl == 1) {
            level = 1;
            lives = 3;
            totalPoints = 0;






            for (int i = 0; i < 8; i++) {

                for (int j=100; j<700; j+= 100) {

                    String blockColor = Brick.colors[i];

                    if (i == 7 && (j == 300 || j == 400)) {
                        blockColor = "Gold";
                    }
                    else if (i == 5 && (j == 200 || j == 500)) {
                        blockColor = "Silver";
                    }
                    else if (i == 4 && (j == 30 || j == 400)) {
                        blockColor = "Silver";
                    }

                    totalPoints += Brick.pointVals[Arrays.asList(Brick.colors).indexOf(blockColor)];

                    blocks.add(new Brick(j, (8-i)*50 + 100, 100, 50, blockColor));
                }

            }
        }
        else if (lvl == 2) {

            totalPoints = 0;
            level = 2;


            for (int k=0; k<8; k++) {
                for (int i=100; i<700; i+=100) {
                    String blockColor = Brick.colors[7-k];

                    if (k == 3 && (i == 200 || i == 500)) {
                        blockColor = "Gold";
                    }
                    else if (k == 5 && (i == 300 || i == 400)) {
//                        blockColor = "Silver";
                        continue;
                    }

                    else if (k == 4 && (i == 30 || i == 400)) {
                        blockColor = "Silver";
                    }

                    totalPoints += Brick.pointVals[Arrays.asList(Brick.colors).indexOf(blockColor)];
                    blocks.add (new Brick(i, (8-k)*50 + 50, 100, 50, blockColor));


                }
            }

            blocks.add(new Brick(350, 200, 100, 50, "Silver"));
            totalPoints += 50;

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

    public ArrayList<Brick> getBlocks() {
        return blocks;
    }



}
