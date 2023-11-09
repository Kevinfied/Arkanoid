import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Paddle {
    private static int x, y;
    private static int WIDTH = 80;
    private static int HEIGHT = 20;
    private static int speed;

    int w = 80;
    int h = 20;

    Image vaus = new ImageIcon("vaus.png").getImage();

    public Paddle() {
        x = Globals.SCREEN_WIDTH/2 - (WIDTH/2);
        y = Globals.SCREEN_HEIGHT - 100;

        speed = 10;
    }

    
    public void draw(Graphics g){
    	g.setColor(Color.RED);
    	g.fillRect(x,y,w,h);

//        g.drawImage(vaus, x, y, null);

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
        if ((keys[leftKey] || keys[leftA]) && (x - (speed/2) > 0)) {
            moveLeft();
        }
        if ((keys[rightKey] || keys[rightD])&& (x + (speed/2) < Globals.SCREEN_WIDTH - WIDTH)) {
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




    
}



