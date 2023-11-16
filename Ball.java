/*
	Ball.java
	Kevin Xu


 */


import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class Ball {
	private static int x,y;
//	private static double vx,vy;
	private static int vx, vy;

	private static final int WIDTH = 12, HEIGHT = 12;
	private static final int RADIUS = WIDTH/2;

	private static double paddleBounce = 0;

	private static boolean start;
	private static boolean onPad, stick;
	private static boolean slowing;
	private static int slowTimer = 0;
	private static int slowCounter = 0;
	private int stickDist;

	// border is 27 pixels wide

	Image r = new ImageIcon("assets/Vaus.png").getImage();
    public Ball() {
		startPos();
		onPad = true;
    }

	public void startBall() {

		onPaddle();

		if (start) {
			vx = Util.randInt(-5, 5);
			vy = -5;
			start = false;
		}
	}



	public void launchBall() {
		vx = Util.randInt(-5, 5);
		vy = -5;
//		vx = 0;
		start = false;
		onPad = false;
	}

    public void wallBounce() {
		int var = 3; // small offset from the top border
		if(y<=0+Globals.BORDER_WIDTH+var+Globals.TOP_BORDER_HEIGHT){ // bounces off the top wall
			y = Globals.BORDER_WIDTH+1+var+Globals.TOP_BORDER_HEIGHT;
			vy*=-1;
		}

		// bounces off side walls
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

	public boolean deathCheck(Paddle player) {
		if (y>=Globals.SCREEN_HEIGHT) {
			startPos();
			Paddle.loseHealth();
			onPad = true;
			player.deathReset();
			return true;
		}
		return false;
	}

	public void startPos() {
		x = Globals.SCREEN_WIDTH / 2;
		y = Paddle.getY() - RADIUS;

		System.out.println(y);
		vx = 0;
		vy = 0;
	}

	public int ballDir(){
		if(vx<0){
			return 0;
		}
		if(vx>0){
			return 1;
		}
		return 2;
	}


	public void paddleBounce(Paddle play) {
		Rectangle ball = getRect();
		// bouncing off the paddle
		if (ball.intersects(play.getRect())) {
			if(!prevBallY().intersects(play.getRect())){

				double distFromCenter = (x - (play.getX()+play.getWidth()/2));
				double speed = Math.pow(vy, 2) + Math.pow(vx, 2);


				double newVx = vx+(distFromCenter/15);
				System.out.println("dist" + distFromCenter);
				System.out.println(newVx);
				double newVy = Math.sqrt(speed - Math.pow(newVx, 2)) * -1;

				vx = (int)Math.round(newVx);
				if ((int)Math.round(newVy) != 0) {
					vy = (int)Math.round(newVy);
				}
				else {
					vy = -1;
				}

				if (vx > 10) {
					vx = 10;
				}
				else if (vx < -10) {
					vx = -10;
				}
				if (vy > 10) {
					vy = 10;
				}
				else if (vy < -10) {
					vy = -10;
				}

			}
			else {
				if(!prevBallX().intersects(play.getRect())) {
					vx *= -1; //switching x velocity just in case
				}
			}
		}
	}
    public void move(){
		// moving
		if (onPad) {
			onPaddle();
		}
		else if (stick) {
			stick(stickDist);
		}
		else {
			x += vx;
			y += vy;
		}
		System.out.printf("vx: %d, vy: %d\n", vx, vy);

	}


	public void onPaddle() {
		x = Paddle.getX() + Paddle.getWidth()/2;
		y = Paddle.getY() - RADIUS;
	}

	public void stick(int dist) {
		x = Paddle.getX() + dist;
		y = Paddle.getY() - RADIUS;
	}


	public void reset() {
		x = Globals.SCREEN_WIDTH/2;
		y = Globals.SCREEN_HEIGHT/2;
		vx = 3;
		vy = 3;

	}

	public double derivatives(double num) {

		return 0.0;
	}

	public Rectangle prevPos(int velX, int velY) {

		return new Rectangle(x-velX-(WIDTH/2), y-velY-(HEIGHT/2), WIDTH, HEIGHT);

	}
	public Rectangle prevBallX(){//ball if you minus ball x velocity
		return new Rectangle(x-RADIUS-vx,y-RADIUS,WIDTH,HEIGHT);
	}


	public Rectangle prevBallY(){//ball if you minus ball y velocity
		return new Rectangle(x-RADIUS,y-RADIUS-vy,WIDTH,HEIGHT);
	}

	// get rect
	public Rectangle getRect(){
		return new Rectangle(x-(WIDTH/2),y-(HEIGHT/2), WIDTH, HEIGHT);
		// return new Rectangle(x-3,y-3,6,6);
	}


	// draws the ball
    public void draw(Graphics g){

		if (Paddle.getEgg()) {
			g.drawImage(r, x, y, null);
		}
		else {
			g.setColor(Color.WHITE);
			g.fillOval(x-(WIDTH/2),y-(HEIGHT/2),WIDTH,HEIGHT);
		}
    }

	public void slowPowerup() {
		slowing = true;
		slowTimer += 1000;
		slowCounter++;
		if (vx == 0) {
			vx = vx;
		}
		else {
			double newVx = vx * 0.7;

			if (newVx < 1 && newVx > -1) {
				vx = (int)Math.round(newVx);
			}
		}
		double newVy = vy * 0.7;

		if ((int)Math.round(newVy) == 0) {
			vy = vy;
		}
		else {
			vy = (int)Math.round(newVy);
		}
	}

	public void slowTimer() {
		if (slowTimer > 0) {
			slowTimer--;
			System.out.println(slowTimer);
		}
		else if (slowTimer <= 0 && slowing) {
			resetSpeed(slowCounter);
			slowing = false;
			slowTimer = 0;
			slowCounter = 0;
		}
	}

	public static void resetSpeed(int counter) {
		vx /= Math.pow(0.7, counter);
		vy /= Math.pow(0.7, counter);
		if (vx < 0) {
			vx--;
		}
		else if (vx > 0) {
			vx++;
		}
		if (vy < 0) {
			vy--;
		}
		else {
			vy++;
		}
		System.out.printf("vx: %d, vy: %d\n", vx, vy);
	}

	public static void clearPowerups() {
		resetSpeed(slowCounter);
		stick = false;
		slowing = false;
		slowTimer = 0;
		slowCounter = 0;

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

	public boolean getOnPad() {
		return onPad;
	}

	public void setOnPad(boolean n) {
		onPad = n;
	}

}

