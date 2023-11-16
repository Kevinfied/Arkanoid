/*
    * GamePanel.java
    * Kevin Xu
    *
    * This class is the main class that runs the game. All the action happens here!
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.Timer;
import java.util.*;
import java.util.ArrayList;
import java.io.*;
import java.util.Set;


class GamePanel extends JPanel implements KeyListener, ActionListener, MouseListener, MouseMotionListener {
    private Timer timer; // timer that runs the game
    private Ball ball; // ball
    private boolean []keys; // array of key states

    private Image gameoverScreen, winScreen, introScreen; // screen backgrounds

    private Paddle player; // paddle

    private String screen; // current screen

    private ArrayList<Brick> blocks = new ArrayList<Brick>(); // contains all non gold blocks in the current level
    private ArrayList<Brick> goldBlocks = new ArrayList<Brick>(); // contains all gold blocks in the current level
    private static ArrayList<Powerup> powerups; // all falling powerup objects

    private ArrayList<Laser> lasers; // all laser objects on screen

    private Set<Laser> lasersToDelete; // lasers to remove

    private Set<Brick> blocksToDelete; //blocks that are broken and should be removed from blocks
    private Set<Powerup> powerupsToDelete; //Powerups that are caught and should be removed from powerups

    private int points, curLevel; // amount of points the player has, and the level the player is on

    private Font fontLocal, fontSys, fontScores; //Font used for text to be drawn on screen

    private Level level; // the active level
    private int laserCooldown = 30; // laser cooldown


    // loading all the stuff
    public GamePanel(){
        // FONTS
        fontSys = new Font("Montserat", Font.PLAIN, 32);
        try{
            File fntFile = new File("assets/Fonts/pixela-extreme.ttf");
            fontLocal = Font.createFont(Font.TRUETYPE_FONT, fntFile).deriveFont(32f);
            fontScores = Font.createFont(Font.TRUETYPE_FONT, fntFile).deriveFont(60f);
        }
        catch(IOException ex){
            System.out.println(ex);
        }
        catch(FontFormatException ex){
            System.out.println(ex);
        }


        // restarting game
        gameReset();

        // mumbo jumbo that i still dont understand
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


    // the "cheat"
    public void breakDebug() {
        blocks.remove(0);
    } // press B to remove a block. for debugging!

    // checking collisions between ball and blocks and adding bricks to be removed accordingly
    public void brickCollide() {
        for (int i=0; i<blocks.size(); i++) {
            Brick b = blocks.get(i);
            if (ball.getRect().intersects(b.getRect())) {
                int bx = ball.getVelX();
                int by = ball.getVelY();
                // Mr.McKenzie collide strategy!
                if (!ball.prevPos(0, by).intersects(b.getRect())) {
                    ball.setVelY(ball.getVelY() * -1);
                }
                if (!ball.prevPos(ball.getVelX(), 0).intersects(b.getRect())) {
                    ball.setVelX(ball.getVelX() * -1);
                }

                b.lowerHealth(1); // lowers health
                if (blocks.get(i).getHealth() <= 0){ //if the Block is broken
                    blocksToDelete.add(blocks.get(i)); // add to blocks to be removed
                }
            }
        }
        // collisions for gold blocks but dont remove
        for (int i=0; i<goldBlocks.size(); i++) {
            Brick b = goldBlocks.get(i);
            if (ball.getRect().intersects(b.getRect())) {
                if (!ball.prevPos(0, ball.getVelY()).intersects(b.getRect())) {
                    ball.setVelY(ball.getVelY() * -1);
                }
                if (!ball.prevPos(ball.getVelX(), 0).intersects(b.getRect())) {
                    ball.setVelX(ball.getVelX() * -1);
                }
            }
        }
    }

    // pew pew!
    public void fireLaser() {
        // fires lasers if player has laser powerup
        if (keys[KeyEvent.VK_SPACE]) {
            if (ball.getOnPad()) { // launch ball if ball is on pad
                ball.setOnPad(false);
                ball.launchBall();
            }
            // else fires two lasers
            else {
                if (player.getActivePowerup().equals("Laser") && laserCooldown <= 0) {
                    laserCooldown = 30;
                    lasers.add(new Laser(player.getX() + player.getWidth()/8, player.getY()));
                    lasers.add(new Laser(player.getX() + player.getWidth()/8*7, player.getY()));
                }
            }
        }
    }

    // checking collisions of lasers
    public void laserCollide() {
        // my very very efficient code!

        // checking all lasers and all blocks
        for (int i=0; i<lasers.size(); i++) {
            Laser l = lasers.get(i);
            for (int j = 0; j < blocks.size(); j++) {
                Brick b = blocks.get(j);
                // delete delete
                if (l.getRect().intersects(b.getRect())) {
                    lasersToDelete.add(l);
                    blocksToDelete.add(b);
                }
            }
            // delete laser but no delete gold block
            for (int q=0; q<goldBlocks.size(); q++) {
                Brick gb = goldBlocks.get(q);
                if (l.getRect().intersects(gb.getRect())) {
                    lasersToDelete.add(l);
                }
            }
            // hits top of screen
            if (l.getY() < 0+Globals.TOP_BORDER_HEIGHT + Globals.BORDER_WIDTH) {
                lasersToDelete.add(l);
            }
        }
        // removing lasers
        for (Laser l:lasersToDelete) {
            lasers.remove(l);
        }
    }

    
    public void powerupCollide() {
        for (int i=0; i<powerups.size(); i++) {
            Powerup p = powerups.get(i);
            if (p.getRect().intersects(player.getRect())) {
                powerupsToDelete.add(powerups.get(i));
            }
        }
    }


    public void deleteBlocks(){
        for (Brick b : blocksToDelete){
            points += b.getPoints();
            if (Util.randInt(0, 2) == 1) {
                powerups.add(new Powerup(b.getX() + ((b.getWidth() - Powerup.getWidth())/2), b.getY()+b.getHeight()));
            }
            blocks.remove(b);
        }
        blocksToDelete.clear();
    }

    public void deletePowerups() {
        for (Powerup p : powerupsToDelete) {
            if (p.getType().equals("Player")) {
                player.addHealth(1);
            }
            else if (p.getType().equals("Slow")) {
                ball.slowPowerup();
            }
            else {
                player.setPowerup(p.getType());
                player.powerupUpdate();
            }
            powerups.remove(p);
        }
        powerupsToDelete.clear();
    }

    public void progression() {
        if (blocks.size() == 0) {
            if (curLevel+1 <= 2) {
                curLevel++;
                player.deathReset();
                ball.setOnPad(true);
                ball.startPos();
                level = new Level(curLevel);
                for (Brick e : level.getBlocks()) {
                    blocks.add(e);
                }

                for (Brick e : level.getGoldBlocks()) {
                    goldBlocks.add(e);
                }

                powerups.clear();
                lasers.clear();
            }
            else {
                curLevel = 3;
            }

        }
    }


    public void move(){
        if(screen == "intro"){
        }

        else if(screen == "game"){
            player.move(keys);
            ball.move();
            ball.wallBounce();
            if (ball.deathCheck(player)) {
                powerups.clear();
            }
//            ball.deathCheck(player);
            ball.paddleBounce(player);
            for (Powerup pu : powerups) {
                pu.move();
            }
            for (Laser l:lasers) {
                l.move();
            }
        }
    }

    public void checkGameOver() {
        if (player.getHealth() <= 0) {
            screen = "Game Over";
        }
        if (curLevel == 3) {
//            System.out.println("win");
            screen = "Win";
        }
    }

    public void gameReset() {
        screen = "intro";
        introScreen = Util.loadScaledImg("assets/screens/intro.png", Globals.SCREEN_WIDTH, Globals.SCREEN_HEIGHT);
        gameoverScreen = Util.loadScaledImg("assets/screens/gameover.png", Globals.SCREEN_WIDTH, Globals.SCREEN_HEIGHT);
        winScreen = Util.loadScaledImg("assets/screens/win.png", Globals.SCREEN_WIDTH, Globals.SCREEN_HEIGHT);
        keys = new boolean[KeyEvent.KEY_LAST+1];
        player = new Paddle();
        ball = new Ball();
        powerups = new ArrayList<Powerup>();
        lasers = new ArrayList<Laser>();
        curLevel = 1;
        level = new Level(curLevel);
        points = 0;
        blocks.clear();
        powerups.clear();
        goldBlocks.clear();
        lasers.clear();
        for (Brick e : level.getBlocks()) {
            blocks.add(e);
        }

        for (Brick e : level.getGoldBlocks()) {
            goldBlocks.add(e);
        }
        blocksToDelete = new HashSet<>();
        powerupsToDelete = new HashSet<>();
        lasersToDelete = new HashSet<>();
//        curLevel = 1;
//        player.deathReset();
//        ball.setOnPad(true);
//        ball.startPos();
//        level = new Level(curLevel);
//        for (Brick e : level.getBlocks()) {
//            blocks.add(e);
//        }
//
//        for (Brick e : level.getGoldBlocks()) {
//            goldBlocks.add(e);
//        }
//
//        powerups.clear();
//        lasers.clear();
//        screen = "intro";
//        points = 0;


    }


    public void	keyPressed(KeyEvent e){
        int code = e.getKeyCode();
        keys[code] = true;

        if (screen == "game") {
            if (code == KeyEvent.VK_8) {
                player.setEgg(true);
            }
            if (code == KeyEvent.VK_B) {
                breakDebug();
            }
            if (code == KeyEvent.VK_L) {
                Paddle.setPowerup("Laser");
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

        if (screen == "Game Over") {
            gameReset();
        }
        if (screen == "Win") {
            gameReset();
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
        powerupCollide();
        deletePowerups();
        fireLaser();
        laserCollide();
        laserCooldown--;
        ball.slowTimer();
        progression();
        checkGameOver();
        repaint();
    }


    @Override
    public void paint(Graphics g){

        if(screen == "intro"){
            g.drawImage(introScreen,0,0,null);
        }


        else if(screen == "game"){
            g.setColor(new Color(0,0,0));
            g.fillRect(0,0,Globals.SCREEN_WIDTH,Globals.SCREEN_HEIGHT);
            Image background = level.getBackground();
            g.drawImage(background, 0, Globals.TOP_BORDER_HEIGHT, null);

            for (Brick FR: blocks) {
                FR.draw(g);
            }

            for (Brick FR: goldBlocks) {
                FR.draw(g);
            }

            for (Powerup pu : powerups) {
                pu.draw(g);
            }

            for (Laser l : lasers) {
                l.draw(g);
            }

            ball.draw(g);
            player.draw(g);
            player.drawLives(g);


            g.setFont(fontLocal);
            g.drawString("Score: " + points, 20, 60);
            g.drawString("Level: " + curLevel, 450, 60);
        }

        else if (screen == "Game Over") {
            g.drawImage(gameoverScreen, 0, 0, null);
            g.setColor(Color.WHITE);
            g.setFont(fontScores);
            g.drawString(""+points, Globals.SCREEN_WIDTH/2-75, 400);
        }

        else if (screen == "Win") {
            g.drawImage(winScreen, 0, 0,null);
            g.setColor(Color.WHITE);
            g.setFont(fontScores);
            g.drawString(""+points, Globals.SCREEN_WIDTH/2-75, 450);
        }
    }
}