import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import javax.swing.Timer;
import java.util.*;
import java.util.ArrayList;

import java.util.Set;


class GamePanel extends JPanel implements KeyListener, ActionListener, MouseListener, MouseMotionListener {
    Timer timer;
    Ball ball;
    private boolean []keys;

    Image menu, background;

    Paddle player;
    int score1, score2;
    Font fontSys;
    String screen;

    public ArrayList<Brick> blocks = new ArrayList<Brick>();
    public ArrayList<Brick> goldBlocks = new ArrayList<Brick>();
    public static ArrayList<Powerup> powerups;
    private ArrayList<Laser> lasers;

    private ArrayList<Point> explosions;
    private Set<Brick> blocksToDelete; //blocks that are broken and should be remvoed from blocks

    private LoadedImages images = new LoadedImages(); //images that are to be used

    private Paddle paddle; //the current paddle
    private Point mouse, offset;
    private int points, lives, totalPoints, highScore, startFrame, curLevel, loadFrame;

    private String curScreen; //which screen the player is currently on
    private Level [] levels; //levels[i] stores starting info for the ith level
    private Font fontLocal; //Font used for text to be drawn on screen
    private Sound music;

    private boolean start, catching, firstEver, laserActive;
    Level one, two;
    Level level;

//    public void startLevel(int lvl) {
//
//
//        blocks = new ArrayList<Brick>();
//        powerups = new ArrayList<Powerup>();
//        lasers = new ArrayList<Laser>();
//        explosions = new ArrayList<Point>();
//
//        if (lvl == 2){
//            totalPoints = levels[1].getTotalPoints() + levels[2].getTotalPoints(); //the total points to clear the second level is the sum of the points to clear the 1st and 2nd levels
//        }
//
//        if (lvl == 1){ //resets the lives and points if starting the first level
//            points = 0;
//            lives = levels[lvl].getLives();
//            totalPoints = levels[lvl].getTotalPoints();
//        }
//
//        for (Brick b : levels[lvl].getBlocks()){ //adds Blocks into the ArrayList<Block> blocks
//            blocks.add(b);
//        }
//
////        ball = new Ball(400, 600, 5);
//        ball = new Ball();
//
//        //resets paddle and any activated Powerups on the last level's paddle
////        paddle = new Paddle(400, 750);
//        paddle = new Paddle();
//        laserActive = false;
//        catching = false;
//
//        start = true;
//        curScreen = "LEVEL_"+lvl;
//
//
//
//
//
//    }



    public GamePanel(){
        fontSys = new Font("Montserat", Font.PLAIN, 32);
        screen = "intro";
        menu = Util.loadScaledImg("assets/intro.png", Globals.SCREEN_WIDTH, Globals.SCREEN_HEIGHT);
        background = Util.loadScaledImg("assets/backgrounds/background1.png", Globals.SCREEN_WIDTH, Globals.SCREEN_HEIGHT);
        keys = new boolean[KeyEvent.KEY_LAST+1];
        player = new Paddle();
        ball = new Ball();
        powerups = new ArrayList<Powerup>();

        level = new Level(2);
        for (Brick e : level.getBlocks()) {
            blocks.add(e);
        }

        for (Brick e : level.getGoldBlocks()) {
            goldBlocks.add(e);
        }
        blocksToDelete = new HashSet<>();

//        for (Brick FR: blocks) {
//            System.out.println(FR.toString());
//        }

        setFocusable(true);
        requestFocus();
        addKeyListener(this);
        addMouseListener(this);

        addMouseMotionListener(this);
        addMouseListener(this);


        timer = new Timer(Globals.GAME_SPEED, this);
        timer.start();
        setPreferredSize(new Dimension(Globals.SCREEN_WIDTH, Globals.SCREEN_HEIGHT));
    }

    public void brickCollide() {
        for (int i=0; i<blocks.size(); i++) {
            Brick b = blocks.get(i);
            if (ball.getRect().intersects(b.getRect())) {
                if (!ball.prevPos(0, ball.vy).intersects(b.getRect())) {
                    ball.vy *= -1;
                }
                if (!ball.prevPos(ball.vx, 0).intersects(b.getRect())) {
                    ball.vx *= -1;
                }
                b.lowerHealth(1);
                if (blocks.get(i).getHealth() <= 0){ //if the Block is broken
                    blocksToDelete.add(blocks.get(i));
                }

            }
        }
        for (int i=0; i<goldBlocks.size(); i++) {
            Brick b = goldBlocks.get(i);
            if (ball.getRect().intersects(b.getRect())) {
                if (!ball.prevPos(0, ball.vy).intersects(b.getRect())) {
                    ball.vy *= -1;
                }
                if (!ball.prevPos(ball.vx, 0).intersects(b.getRect())) {
                    ball.vx *= -1;
                }
            }
        }
    }


    public void deleteBlocks(){
        for (Brick b : blocksToDelete){
            points += b.getPoints();
            blocks.remove(b);
        }
        blocksToDelete.clear();
    }


    public void move(){
        if(screen == "intro"){
        }

        else if(screen == "game"){


            player.move(keys);
            ball.move();
            ball.wallBounce();
            ball.deathCheck(player);
            ball.paddleBounce(player);
            for (Powerup pu : powerups) {
                pu.move();
            }
        }
    }
    
    public void	keyPressed(KeyEvent e){
        int code = e.getKeyCode();
        keys[code] = true;

        if (screen == "game") {
            if (code == KeyEvent.VK_SPACE) {
                if (ball.getOnPad()) {
//                    ball.setStart(true);
                    ball.setOnPad(false);
                    ball.launchBall();
                }
//                ball.launchBall();
            }
            if (code == KeyEvent.VK_8) {
                player.egg = true;
            }
        }

    }
    public void	keyReleased(KeyEvent e){
        int code = e.getKeyCode();
        keys[code] = false;		
    }
    public void	keyTyped(KeyEvent e){}

    public void	mouseClicked(MouseEvent e){}

    public void	mouseEntered(MouseEvent e){}

    public void	mouseExited(MouseEvent e){}

    public void	mousePressed(MouseEvent e){
        System.out.println("HERE");
        System.out.println(points);
        if(screen == "intro") {
            screen = "game";
        }


    }
    public void	mouseReleased(MouseEvent e) {}

    @Override
    public void mouseMoved(MouseEvent e) {}

    public void mouseDragged(MouseEvent e) {
        if (screen == "game") {
            player.setX(e.getX()-player.getWidth()/2);
        }
    }

    public void	actionPerformed(ActionEvent e){
        move();
        brickCollide();
        deleteBlocks();
        repaint();
    }


    @Override
    public void paint(Graphics g){

        if(screen == "intro"){
            g.drawImage(menu,0,0,null);


        }




        else if(screen == "game"){
            g.setColor(new Color(0,0,0));
            g.fillRect(0,0,Globals.SCREEN_WIDTH,Globals.SCREEN_HEIGHT);
            Image background = level.getBackground();
            g.drawImage(background, 0, 0, null);

            for (Brick FR: blocks) {
                FR.draw(g);
            }

            for (Brick FR: goldBlocks) {
                FR.draw(g);
            }

            ball.draw(g);
            player.draw(g);
            g.setFont(fontSys);
//            two.drawLevel(g, ball, player);
        }
    }
}

