import java.util.*;
import java.awt.*;

import javax.swing.*;
import java.awt.event.*;


public class Main extends JFrame{
    GamePanel game = new GamePanel();

    public Main() {
        super("Arkanoid");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        add(game);
        pack();
        setVisible(true);

    }


    public static void main(String[] arguments) {

        Main frame = new Main();


    }


    
}

