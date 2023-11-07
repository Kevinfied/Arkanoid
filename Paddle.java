import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Paddle {
	int x,y;
	int up, down;
    int left, right;
    public int width = 80;
    public int height = 20;
    int speed;
    int WIDTH = Globals.SCREEN_WIDTH;
    int HEIGHT = Globals.SCREEN_HEIGHT;

    Image vaus = new ImageIcon("vaus.png").getImage();


    public Paddle(int l, int r, int s) { 
        x = WIDTH/2 - 40;
        y = HEIGHT - 100;
        left = l;
        right = r;
        this.speed = s;
    }

    
    public void draw(Graphics g){
    	g.setColor(Color.RED);
    	g.fillRect(x,y,width,height);

//        g.drawImage(vaus, x, y, null);

    }

    
    public void moveLeft(){
        x -= speed;
    }

    public void moveRight(){
        x += speed;
    }

    public void move(boolean []keys) {

        // if in screen

                        // makes sure the paddle does not
                        // go off the screen
        if (keys[left] && (x - (speed/2) > 0)) {
            moveLeft();
        }
        if (keys[right] && (x + (speed/2) < WIDTH - this.width)) {
            moveRight();
        }


    }

    public Rectangle getRect(){
        // x, y, width, height
    	return new Rectangle(x,y,this.width,this.height);
    }

    
    
}



