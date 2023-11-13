import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Paddle {
    public static int x, y;
    private static int WIDTH = 80;
    private static int HEIGHT = 20;
    private static int speed;

//    private Image easterEgg;
    public static boolean egg;
    public static String activePowerup;
    public static int health;

//    private Image icon;
    int w = 80;
    int h = 20;

    Image easterEgg = new ImageIcon("assets/Vaus.png").getImage();
    Image icon = Util.loadScaledImg("assets/Vaus/Default.png", WIDTH, HEIGHT);
    public Paddle() {
        x = Globals.SCREEN_WIDTH/2 - (WIDTH/2);
        y = Globals.SCREEN_HEIGHT - 100;

        speed = 10;
        health = 3;
        activePowerup = "None";
    }

    
    public void draw(Graphics g){
//    	g.setColor(Color.RED);
//    	g.fillRect(x,y,w,h);
        if (egg) {
            g.drawImage(easterEgg, x, y, null);
        }
        else {
            g.drawImage(icon, x, y, null);
        }

    }

    public void deathReset() {
        x = Globals.SCREEN_WIDTH/2 - (WIDTH/2);
        y = Globals.SCREEN_HEIGHT - 100;
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
//        x = n;
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


    public String getRandomPowerup() {
        if (Util.randInt(0, 2) == 1) {
            int a = Util.randInt(0, 100);
            if (a < 50) {
                return "Player";
            }
            else if (a < 75) {
                return "Catch";
            }
            else if (a < 90) {
                return "Enlarge";
            }
            else {
                return "Laser";
            }
        }
        return "None";
    }
    
}



