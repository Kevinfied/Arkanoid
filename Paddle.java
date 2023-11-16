/*
    Paddle.java
    Kevin Xu

    Contains all the methods for the paddle object
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Paddle {
    private static int x, y; // x and y are the coordinates of the top left corner of the paddle
    private static int WIDTH = 80; // width of the paddle
    private static int HEIGHT = 20; // height of the paddle
    private static int speed; // speed of the paddle moved with keyboard
    private static boolean egg; // :)
    private static String activePowerup; // the active powerup
    private static int health; // health of the paddle

    // ICONS
    private Image easterEgg = new ImageIcon("assets/Vaus.png").getImage();
    private Image lifeIcon = Util.loadScaledImg("assets/Vaus/lives.png", 15, 15);
    // all progress is hindered by a GIF 😱😱😱😱😱
    // nvm i got it 🎉
    private Image defaultIcon = new ImageIcon("assets/Vaus/default.gif").getImage();
    private Image laserIcon = new ImageIcon("assets/Vaus/laser.gif").getImage();

    // constructor
    public Paddle() {
        x = Globals.SCREEN_WIDTH/2 - (WIDTH/2);
        y = Globals.SCREEN_HEIGHT - 100;
        speed = 10;
        health = 3;
        activePowerup = "None";
    }

    // updating paddle size for powerups
    public static void powerupUpdate() {
        if (activePowerup == "Enlarge") {
            WIDTH = 120;
        }
        else if (activePowerup != "Enlarge"){
            WIDTH = 80;
        }
    }

    // draws the paddle
    public void draw(Graphics g){
        if (egg) {
            g.drawImage(easterEgg, x, y, null);
        }
        else if (activePowerup == "Laser") {
            g.drawImage(laserIcon, x, y, WIDTH, HEIGHT, null);
        }
        else {
            g.drawImage(defaultIcon, x, y, WIDTH, HEIGHT, null);
        }
    }

    // draws the lives on the screen
    public void drawLives(Graphics g) {
        for (int i=0; i<health-1; i++) {
            g.drawImage(lifeIcon, 20 + (i*15), Globals.SCREEN_HEIGHT-15, null);
        }
    }

    // resets the paddle to the center of the screen and cleans powerups
    public void deathReset() {
        x = Globals.SCREEN_WIDTH/2 - (WIDTH/2);
        y = Globals.SCREEN_HEIGHT - 100;
        clearPowerups();
    }


    public void setSpeed(int s){
        speed = s;
    }
    public void moveLeft(){
        x -= speed;
    }
    public void moveRight(){
        x += speed;
    }

    // moving the paddle with the keyboard
    public void move(boolean []keys) {

        int leftKey = KeyEvent.VK_LEFT;
        int rightKey = KeyEvent.VK_RIGHT;
        int leftA = KeyEvent.VK_A;
        int rightD = KeyEvent.VK_D;

        // keeps the paddle on the screen
        if ((keys[leftKey] || keys[leftA]) && (x - (speed/2) > 0+Globals.BORDER_WIDTH)) {
            moveLeft();
        }

        if ((keys[rightKey] || keys[rightD]) && (x + (speed/2) < Globals.SCREEN_WIDTH - WIDTH - Globals.BORDER_WIDTH-2)) {
            moveRight();
        }
    }



    public Rectangle getRect(){
        // x, y, width, height
    	return new Rectangle(x,y,WIDTH,HEIGHT);
    }

    public static int getSpeed() {
        return speed;
    }

    public static int getX() {
        return x;
    }

    public static int getY() {
        return y;
    }

    public static int getWidth() {
        return WIDTH;
    }

    public static int getHeight() {
        return HEIGHT;
    }

    public void setX(int n) {
        if (n < 0 + Globals.BORDER_WIDTH) {
            x = 0+Globals.BORDER_WIDTH;
        }
        else if (n > Globals.SCREEN_WIDTH - WIDTH - Globals.BORDER_WIDTH) {
            x = Globals.SCREEN_WIDTH - WIDTH - Globals.BORDER_WIDTH;
        }
        else {
            x = n;
        }
    }

    public void setY(int n) {
        y = n;
    }

    public int getHealth() {
    	return health;
    }

    public static void addHealth(int n) {
        health += n;
    }

    public static void loseHealth() {
    	health--;
    }

    public static void setPowerup(String s) {
    	activePowerup = s;
//        System.out.println("Powerup: " + activePowerup);
//        System.out.println("Health: " + health);
    }

    public static String getActivePowerup() {
        return activePowerup;
    }

    public static void clearPowerups() {
        Ball.clearPowerups();
    	activePowerup = "None";
        powerupUpdate();
    }

    public static void setEgg(boolean flag) {
        egg = flag;
    }

    public static boolean getEgg() {
        return egg;
    }
}



