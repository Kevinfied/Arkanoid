import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Ball {
	private static int x,y;
	private static double vx,vy;
	private static int width, height;

	private static final int WIDTH = 12, HEIGHT = 12;

	public static double paddleBounce = 0;

	// border is 27 pixels wide

	Image ballIcon = new ImageIcon("vaus.png").getImage();
    public Ball() {
    	x = Globals.SCREEN_WIDTH/2;
    	y = Globals.SCREEN_HEIGHT/2;
    	// if(Util.randint(0,1)==0){
    	// 	vx = Util.randint(-5, -7);
    	// }
    	// else{
    	// 	vx = Util.randint(5, 7);
    	// }


    	// if(Util.randint(0,1)==0){
    	// 	vy = Util.randint(-20, -19);
    	// }
    	// else{
    	// 	vy = Util.randint(19, 20);
    	// }
        vx = 3;
        vy = 3;
    }

    
    public int move(Paddle play){
		// moving
				this.x += vx;
				this.y += vy;

		    	if(y<=0){ // bounces off the top wall

					y = 1;
					vy*=-1;
		    	}

		        if(x<=0 || x>=Globals.SCREEN_WIDTH-WIDTH){ // bounces off the left and right walls.

					if (x <= 0) {
						x = 1;
					}
					else if (x >= Globals.SCREEN_WIDTH-WIDTH) {
						x = Globals.SCREEN_WIDTH-WIDTH-1;
					}
					vx*=-1;


		    	}

		    	Rectangle ball = getRect();

		// bouncing off paddles
//    	if(ball.intersects(play.getRect())){
//			System.out.println("BOUNCE");
//			int temp = directionGet(x, y, vx, vy, play, ball);
//
//			if (temp == 1) {
//				vx *= -1;
//				vy *= -1;
//			}
//			else if (temp == 2) {
//				vy *= -1;
//			}
//			else if (temp == 3) {
//				vx *= -1;
//			}
//
//		}


		return 0;
	}


	public void onPaddle() {
		// gotta check for the sticking powerup later

		x = Paddle.getX() + Paddle.getWidth()/2 - WIDTH/2;
		y = Paddle.getY() - WIDTH;
	}

	public void bouncePaddle() {
		paddleBounce = 0;
		if (new Rectangle((int)x, (int)y, WIDTH, HEIGHT).intersectsLine((int)Paddle.getX(),(int)Paddle.getY(),(int)Paddle.getX()+Paddle.getWidth(),Paddle.getY())) {

			paddleBounce = x-Paddle.getX(); // saving bounce x coord for catch powerup to retain bounce position

			vy = -1 * Math.abs(vy);
			int dir = (int) ((x+WIDTH/2 - Paddle.getX()) * (180.0/Paddle.getWidth()));
			vx = -1 * Math.cos(Math.toRadians(dir))*10;
		}
	}

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


		Rectangle prevBallY = new Rectangle(xx, prevY, width, height);
		Rectangle prevBallX = new Rectangle(prevX, yy, width, height);

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
    
    public Rectangle getRect(){
        return new Rectangle(x-(WIDTH/2),y-(HEIGHT/2), WIDTH, HEIGHT);
    	// return new Rectangle(x-3,y-3,6,6);
    }
    

    // draws the ball
    public void draw(Graphics g){
    	g.setColor(Color.WHITE);
    	g.fillOval(x-(width/2),y-(height/2),width,height);

    }

	public static int getX() {
		return x;
	}

	public static int getY() {
		return y;
	}

	public static double getVelX() {
		return vx;
	}

	public static double getVelY() {
		return vy;
	}

	public static void setVelX(double n) {
		vx = n;
	}

	public static void setVelY(double n) {
		vy = n;
	}

}

