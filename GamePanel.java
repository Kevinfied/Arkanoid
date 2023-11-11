import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import javax.swing.Timer;
import java.util.*;
import java.util.ArrayList;

import java.util.Set;


class GamePanel extends JPanel implements KeyListener, ActionListener, MouseListener{
    Timer timer;
    Ball ball;
    private boolean []keys;

    Image menu, background;

    Paddle player;
    int score1, score2;
    Font fontSys;
    String screen;

    public ArrayList<Brick> blocks = new ArrayList<Brick>();;
    private ArrayList<Powerup> powerups;
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

    Brick b, c, d;
    Level one;

    public GamePanel(){
        fontSys = new Font("Montserat", Font.PLAIN, 32);
        screen = "intro";
        menu = new ImageIcon("intro1.png").getImage();
//        background = new ImageIcon("background.png").getImage();
        background = Util.loadScaledImg("background.png", Globals.SCREEN_WIDTH, Globals.SCREEN_HEIGHT);
        keys = new boolean[KeyEvent.KEY_LAST+1];
        ball = new Ball();

//        b = new Brick(20, 20, 50, 20, "Silver");
//        c = new Brick(20, 200, 70, 50, "Silver");
//        blocks.add(new Brick(90, 200, 70, 50, "Silver"));
//        blocks.add(new Brick(160, 200, 70, 50, "Silver"));
//        blocks.add(new Brick(230, 200, 70, 50, "Silver"));
//        blocks.add(new Brick(300, 200, 70, 50, "Silver"));
//        blocks.add(new Brick(370, 200, 70, 50, "Silver"));
//        blocks.add(new Brick(440, 200, 70, 50, "Silver"));
//        blocks.add(new Brick(510, 200, 70, 50, "Silver"));
//        blocks.add(new Brick(580, 200, 70, 50, "Silver"));
//        blocks.add(b);
//        blocks.add(c);

        one = new Level(1);
        for (Brick e : one.getBlocks()){
            blocks.add(e);
        }
        for (Brick FR: blocks) {
            System.out.println(FR.toString());
        }
//        System.out.println(blocks);

        //                  left key          right key         paddle speed
        player = new Paddle();
        setFocusable(true);
        requestFocus();
        addKeyListener(this);
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
                if (blocks.get(i).getHealth() <= 0){ //if the Block is broken
                    blocksToDelete.add(blocks.get(i));

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
            ball.move();
            player.move(keys);
            ball.wallBounce();
            ball.paddleBounce(player);
        }
    }
    
    public void	keyPressed(KeyEvent e){
        int code = e.getKeyCode();
        keys[code] = true;
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
        if(screen == "intro"){
            screen = "game";
        }
//        if (screen == "game") {
//            player.x = mouse.x;
//            player.y = mouse.y;
//        }
        
    }
    public void	mouseReleased(MouseEvent e) {}
        

    public void	actionPerformed(ActionEvent e){
        move();
        brickCollide();
//        deleteBlocks();
        repaint();
    }
    
    @Override
    public void paint(Graphics g){

        if(screen == "intro"){
            g.drawImage(menu,0,0,null);


        }




        else if(screen == "game"){

//            g.setColor(new Color(0,0,0));
//            g.fillRect(0,0,Globals.SCREEN_WIDTH,Globals.SCREEN_HEIGHT);
            g.setColor(new Color(0,0,0));
            g.fillRect(0,0,Globals.SCREEN_WIDTH,Globals.SCREEN_HEIGHT);
            g.drawImage(background, 0, 0, null);

            for (Brick FR: blocks) {
                FR.draw(g);
            }

//            blocks.get(0).draw(g);


//            b.draw(g);

            ball.draw(g);
//            ball.draw(g);
            player.draw(g);
            g.setFont(fontSys);




        }
    }
}

