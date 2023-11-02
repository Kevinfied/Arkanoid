import java.util.*;
import java.awt.*;

import javax.swing.*;
import java.awt.event.*;


class GamePanel extends JPanel implements KeyListener, ActionListener, MouseListener, MouseMotionListener {


    int WIDTH = 400;
    int HEIGHT = 800;

    Timer timer;
    Ball ball;

    private boolean []keys;
    Image background;
    Paddle player;
    int score;
    Font fontSys;


    String screen;
    



    public GamePanel() {
        fontSys = new Font("Montserat", Font.PLAIN, 32);
        screen = "intro";
        background = new ImageIcon("assets/background.png").getImage();
        keys = new boolean[KeyEvent.KEY_LAST+1];
        ball = new Ball();
        
        score = 0;


        // player = new Paddle(200, 350);
        setFocusable(true);
        requestFocus();
        addKeyListener(this);
        addMouseListener(this);
        timer = new Timer(10, this);
        timer.start();
        setPreferredSize(new Dimension(WIDTH, HEIGHT));


        keys = new boolean[KeyEvent.KEY_LAST+1];
        boxx = 100;
        boxy = 100;
        dx = 5;
        setFocusable(true);
        requestFocus();
        addKeyListener(this);
        timer = new Timer(10, this);
        timer.start();
    }

    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        keys[code] = true;

    }

    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();
        keys[code] = false;
    }

    public void keyTyped(KeyEvent e) {

    }

    public void actionPerformed(ActionEvent e) {
        move();
        repaint();
    }

    @Override 
    public void paint(Graphics g) {
        g.drawImage(back, 0, 0, null);
        g.setColor(new Color(222, 111, 111));
        g.fillRect(boxx, boxy, 50, 50);
    }

    // public void paintComponent(Graphics g) {
    //     super.paintComponent(g);
    //     g.drawImage(background, 0, 0, null);
    //     g.setFont(fontSys);
    //     g.setColor(Color.WHITE);
    //     g.drawString("Score: " + score, 10, 30);
    //     ball.draw(g);
    //     player.draw(g);
    //     if (screen.equals("intro")) {
    //         g.drawString("Click to start", 100, 400);
    //     }
    //     if (screen.equals("gameover")) {
    //         g.drawString("Game Over", 100, 400);
    //     }
    // }

}