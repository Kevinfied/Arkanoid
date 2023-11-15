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

    Image menu, background, gameoverScreen, winScreen;

    Paddle player;
    int score1, score2;
    Font fontSys;
    String screen;

    public ArrayList<Brick> blocks = new ArrayList<Brick>();
    public ArrayList<Brick> goldBlocks = new ArrayList<Brick>();
    public static ArrayList<Powerup> powerups;

    private ArrayList<Laser> lasers;

    private Set<Laser> lasersToDelete;

    private ArrayList<Point> explosions;
    private Set<Brick> blocksToDelete; //blocks that are broken and should be remvoed from blocks
    private Set<Powerup> powerupsToDelete; //Powerups that are caught and should be removed from powerups
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
    int laserCooldown = 30;



    public GamePanel(){
        fontSys = new Font("Montserat", Font.PLAIN, 32);
        screen = "intro";
        menu = Util.loadScaledImg("assets/intro.png", Globals.SCREEN_WIDTH, Globals.SCREEN_HEIGHT);
        background = Util.loadScaledImg("assets/backgrounds/background1.png", Globals.SCREEN_WIDTH, Globals.SCREEN_HEIGHT);
        gameoverScreen = Util.loadScaledImg("assets/screens/gameover.png", Globals.SCREEN_WIDTH, Globals.SCREEN_HEIGHT);
        winScreen = Util.loadScaledImg("assets/screens/win.png", Globals.SCREEN_WIDTH, Globals.SCREEN_HEIGHT);
        keys = new boolean[KeyEvent.KEY_LAST+1];
        player = new Paddle();
        ball = new Ball();
        powerups = new ArrayList<Powerup>();
        lasers = new ArrayList<Laser>();
        curLevel = 1;
        level = new Level(curLevel);
        for (Brick e : level.getBlocks()) {
            blocks.add(e);
        }

        for (Brick e : level.getGoldBlocks()) {
            goldBlocks.add(e);
        }
        blocksToDelete = new HashSet<>();
        powerupsToDelete = new HashSet<>();
        lasersToDelete = new HashSet<>();


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

    public void breakDebug() {
        blocks.remove(0);
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

    public void fireLaser() {
        if (keys[KeyEvent.VK_SPACE]) {
            if (ball.getOnPad()) {
//                    ball.setStart(true);
                ball.setOnPad(false);
                ball.launchBall();
            }
            else {
                if (player.getActivePowerup().equals("Laser") && laserCooldown <= 0) {
                    laserCooldown = 30;
                    lasers.add(new Laser(player.getX() + player.getWidth()/8, player.getY()));
                    lasers.add(new Laser(player.getX() + player.getWidth()/8*7, player.getY()));
                }
            }
//                ball.launchBall();
        }
    }
    public void laserCollide() {

        for (int i=0; i<lasers.size(); i++) {
            Laser l = lasers.get(i);
            for (int j = 0; j < blocks.size(); j++) {
                Brick b = blocks.get(j);

                if (l.getRect().intersects(b.getRect())) {

                    lasersToDelete.add(l);
                    blocksToDelete.add(b);
                }
            }
            for (int q=0; q<goldBlocks.size(); q++) {
                Brick gb = goldBlocks.get(q);

                if (l.getRect().intersects(gb.getRect())) {
                    lasersToDelete.add(l);
                }
            }
        }
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
                level = new Level(curLevel);
                for (Brick e : level.getBlocks()) {
                    blocks.add(e);
                }

                for (Brick e : level.getGoldBlocks()) {
                    goldBlocks.add(e);
                }
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


    public void	keyPressed(KeyEvent e){
        int code = e.getKeyCode();
        keys[code] = true;

        if (screen == "game") {
            if (code == KeyEvent.VK_8) {
                player.egg = true;
            }
            if (code == KeyEvent.VK_B) {
                breakDebug();
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

            for (Powerup pu : powerups) {
                pu.draw(g);
            }

            for (Laser l : lasers) {
                l.draw(g);
            }

            ball.draw(g);
            player.draw(g);
            player.drawLives(g);
            g.setFont(fontSys);
        }

        else if (screen == "Game Over") {
            g.drawImage(gameoverScreen, 0, 0, null);
        }

        else if (screen == "Win") {
            g.drawImage(winScreen, 0, 0,null);
        }
    }
}