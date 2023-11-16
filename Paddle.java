/*
    Paddle.java
    Kevin Xu


 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Paddle {
    private static int x, y;
    private static int WIDTH = 80;
    private static int HEIGHT = 20;
    private static int speed;
    private static boolean egg; // :)
    private static String activePowerup;
    private static int health;


    private Image easterEgg = new ImageIcon("assets/Vaus.png").getImage();
//    Image icon = Util.loadScaledImg("assets/Vaus/Default.png", WIDTH, HEIGHT);
    private Image lifeIcon = Util.loadScaledImg("assets/Vaus/lives.png", 15, 15);
//    Image defaultIcon = Util.loadScaledImg("assets/Vaus/Arkanoid_Vaus.gif", WIDTH, HEIGHT);

    // all progress is hindered by a GIF 😱😱😱😱😱

    // nvm i got it 🎉
    private Image defaultIcon = new ImageIcon("assets/Vaus/default.gif").getImage();
    private Image laserIcon = new ImageIcon("assets/Vaus/laser.gif").getImage();
    public Paddle() {
        x = Globals.SCREEN_WIDTH/2 - (WIDTH/2);
        y = Globals.SCREEN_HEIGHT - 100;
        speed = 10;
        health = 3;
        activePowerup = "None";
    }
    public static void powerupUpdate() {
        if (activePowerup == "Enlarge") {
            WIDTH = 120;
        }
        else if (activePowerup != "Enlarge"){
            WIDTH = 80;
        }
    }
    
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

    public void drawLives(Graphics g) {
        for (int i=0; i<health-1; i++) {
            g.drawImage(lifeIcon, 20 + (i*15), Globals.SCREEN_HEIGHT-15, null);
        }
    }

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
        System.out.println("Powerup: " + activePowerup);
        System.out.println("Health: " + health);
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



