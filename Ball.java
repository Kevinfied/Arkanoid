import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class Ball {
	private static int x,y;
//	private static double vx,vy;
	public static int vx, vy;
	private static double radius;


	private static final int WIDTH = 12, HEIGHT = 12;
	private static final int RADIUS = WIDTH/2;

	public static double paddleBounce = 0;

	// border is 27 pixels wide

	Image ballIcon = new ImageIcon("vaus.png").getImage();
    public Ball() {
    	x = Globals.SCREEN_WIDTH/2;
    	y = Globals.SCREEN_HEIGHT/2;
		if(Util.randInt(0,1)==0){
			vx = Util.randInt(-5,-4);
		}
		else{
			vx = Util.randInt(4, 5);
		}

		vy = -6;

    }

    public void wallBounce() {
		int var = 3;
		if(y<=0+Globals.BORDER_WIDTH+var){ // bounces off the top wall

			y = Globals.BORDER_WIDTH+1+var;
			vy*=-1;
		}

		if (y >= Globals.SCREEN_HEIGHT-Globals.BORDER_WIDTH) { // bouncing off the bottom wall for now
			y = Globals.SCREEN_HEIGHT-Globals.BORDER_WIDTH - 1;
			vy *= -1;
		}

		if(x<=0+Globals.BORDER_WIDTH || x>=Globals.SCREEN_WIDTH-WIDTH-Globals.BORDER_WIDTH){ // bounces off the left and right walls.
			if (x <= 0 + Globals.BORDER_WIDTH) {
				x = 1+Globals.BORDER_WIDTH;
			}
			else if (x >= Globals.SCREEN_WIDTH-WIDTH - Globals.BORDER_WIDTH) {
				x = Globals.SCREEN_WIDTH-WIDTH-1-Globals.BORDER_WIDTH;
			}
			vx*=-1;
		}

	}

	public void paddleBounce(Paddle play) {
		Rectangle ball = getRect();
		// bouncing off the paddle
		if (ball.intersects(play.getRect())) {
			if (!prevPos(0, vy).intersects(play.getRect())) {
				vy *= -1;
			}

			if (!prevPos(vx, 0).intersects(play.getRect())) {
				vx *= -1;
			}

		}
	}
    public void move(){
		// moving
		x += vx;
		y += vy;

	}


//	public void onPaddle() {
//		// gotta check for the sticking powerup later
//
//		x = Paddle.getX() + Paddle.getWidth()/2 - WIDTH/2;
//		y = Paddle.getY() - WIDTH;
//	}


	public void reset() {
		x = Globals.SCREEN_WIDTH/2;
		y = Globals.SCREEN_HEIGHT/2;
		vx = 3;
		vy = 3;

	}

	public void death() {
		x = Globals.SCREEN_WIDTH/2;
		y = Globals.SCREEN_HEIGHT/2;
		vx = 0;
		vy = 0;
	}


	public int directionGet(int xx, int yy, double velX, double velY, Paddle play, Rectangle ball) {

		int prevX = (int)(xx - velX);
		int prevY = (int)(yy - velY);


		Rectangle prevBallY = new Rectangle(xx, prevY, WIDTH, HEIGHT);
		Rectangle prevBallX = new Rectangle(prevX, yy, WIDTH, HEIGHT);

		if (!(prevBallY.intersects(play.getRect())) && !(prevBallX.intersects(play.getRect()))) {
			System.out.println("DIAGONAL BOUNCE");
			// 1 - both
			return 1;
		}

		else if (!(prevBallY.intersects(play.getRect())) && (prevBallX.intersects(play.getRect()))) {
			System.out.println("VERTICAL BOUNCE");
			// 2 - y
			return 2;
		}

		else if (!(prevBallX.intersects(play.getRect())) && (prevBallY.intersects(play.getRect()))) {
			System.out.println("HORIZONTAL BOUNCE");
			// 3 - x
			return 3;
		}

		else {
			System.out.println("BOUNCE ZERO");
			// 0 - none
			return 0;
		}
	}
    

	public Rectangle prevPos(int velX, int velY) {

		return new Rectangle(x-velX-(WIDTH/2), y-velY-(HEIGHT/2), WIDTH, HEIGHT);

	}

	// get rect
	public Rectangle getRect(){
		return new Rectangle(x-(WIDTH/2),y-(HEIGHT/2), WIDTH, HEIGHT);
		// return new Rectangle(x-3,y-3,6,6);
	}


	// draws the ball
    public void draw(Graphics g){
    	g.setColor(Color.WHITE);
    	g.fillOval(x-(WIDTH/2),y-(HEIGHT/2),WIDTH,HEIGHT);

    }

	public static int getX() {
		return x;
	}

	public static int getY() {
		return y;
	}

	public static int getVelX() {
		return vx;
	}

	public static int getVelY() {
		return vy;
	}

	public static void setVelX(int n) {
		vx = n;
	}

	public static void setVelY(int n) {
		vy = n;
	}

}

