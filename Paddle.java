import java.awt.*;

public class Paddle {
	int x,y;
	int up, down;
    int left, right;
    int speed;
    int WIDTH = Globals.WIDTH;
    int HEIGHT = Globals.HEIGHT;

    int paddleWidth = 80;
    int paddleHeight = 20;

    public Paddle(int l, int r, int s) { 
        x = WIDTH/2 - 40;
        y = HEIGHT - 100;
        left = l;
        right = r;
        this.speed = s;
    }

    
    public void draw(Graphics g){
    	g.setColor(Color.RED);
    	g.fillRect(x,y,paddleWidth,paddleHeight);
    }
    
    public void moveLeft(){
        x -= speed;
    }

    public void moveRight(){
        x += speed;
    }

    public void move(boolean []keys) {
        if (keys[left]) {
            moveLeft();
        }
        if (keys[right]) {
            moveRight();
        }
    }

    public Rectangle getRect(){
        // x, y, width, height
    	return new Rectangle(x,y,paddleWidth,paddleHeight);
    }
    
    
}



