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


        }

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

    public int getLevel() {
        return level;
    }


}
