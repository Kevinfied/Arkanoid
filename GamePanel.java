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

    private ArrayList<Brick> blocks;
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

    Brick b;
    public GamePanel(){
        fontSys = new Font("Montserat", Font.PLAIN, 32);
        screen = "intro";
        menu = new ImageIcon("intro1.png").getImage();
//        background = new ImageIcon("background.png").getImage();
        background = Util.loadScaledImg("background.png", Globals.SCREEN_WIDTH, Globals.SCREEN_HEIGHT);
        keys = new boolean[KeyEvent.KEY_LAST+1];
        ball = new Ball();

        b = new Brick(20, 20, 50, 20, "Silver");

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


    public void deleteBlocks(){ //method to remove broken blocks
        for (Brick b : blocksToDelete){
            points += b.getPoints(); //increases points
            blocks.remove(b); //removes Block
        }
        blocksToDelete.clear(); //resets the set of broken blocks
    }


    public void collideBlocks(){//method to check collisions with Blocks and respond accordingly
        int newVX = ball.getVelX(), newVY = ball.getVelY(); //the velocity in x and y directions after hitting zero or more blocks

        for (int i=0; i<blocks.size(); i++){
            Brick b = blocks.get(i);
            if (ball.getRect().intersects(b.getRect())){ //checks if ball intersects with a Block
//                ball.playBounce(); //makes sound effect for bouncing ball
                if (!b.getColor().equals("Gold")){

                    blocks.get(i).lowerHealth(1);
                    if (blocks.get(i).getHealth() <= 0){ //if the Block is broken
                        blocksToDelete.add(blocks.get(i));

                        if (!b.getColor().equals("Silver")){ //no Powerups from Silver blocks
                            //adds random Powerup or no Powerup for each Block that broke
                            int chance = Util.randInt(1, 30);
//                            if (chance <= 4){
//                                powerups.add(new Powerup(b.getX(), b.getY(),Powerup.powerNames[chance-1]));
//                            }
                        }
                    }
                }

                //checks which side of the Block, b, the ball hit and changes the velocity accordingly
                if (ball.getRect().intersects(b.getBottom()) || ball.getRect().intersects(b.getTop())){
                    newVY = -ball.getVelY();
                }
                else if (ball.getRect().intersects(b.getLeft()) || ball.getRect().intersects(b.getRight())){
                    newVX = -ball.getVelX();
                }
            }
        }

        //sets new velocity for ball
        ball.setVelX(newVX);
        ball.setVelY(newVY);


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
    public void	keyTyped(KeyEvent e){
    }

    public void	mouseClicked(MouseEvent e){

    }

    public void	mouseEntered(MouseEvent e){

    }

    public void	mouseExited(MouseEvent e){}

    public void	mousePressed(MouseEvent e){
        System.out.println("HERE");
        if(screen == "intro"){
            screen = "game";
        }
        
    }
    public void	mouseReleased(MouseEvent e) {}
        
    // @Override
    public void	actionPerformed(ActionEvent e){


        move();
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

//            for (Brick b: blocks) {
//                b.draw(g);
//            }


            b.draw(g);

            ball.draw(g);
//            ball.draw(g);
            player.draw(g);
            g.setFont(fontSys);




        }
    }
}

