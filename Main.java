import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Main extends JFrame {
    GamePanel game= new GamePanel();

    public Main() {
        super("Arkanoid");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        add(game);
        pack();
        setVisible(true);
    }


    
    public static void main(String[] arguments) {
        Main frame = new Main();
        System.out.println("RUNNING");
    }
}



