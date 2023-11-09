import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Main extends JFrame {
    GamePanel game;

    public Main() {
        super("Arkanoid");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        game = new GamePanel();
        add(game);
        pack();
        setVisible(true);
    }


    
    public static void main(String[] arguments) {
        new Main();
//        Main frame = new Main();
        System.out.println("RUNNING");
    }
}



