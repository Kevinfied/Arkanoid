/*
	Ball.java
	Kevin Xu

	Contains all the methods for the ball object (i think)
	Has bounce, move, and draw methods
	and a bunch of others that took me way too long to figure out
 */


import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class Ball {
	// forgot about access protection 😧
	private static int x,y; // x and y are the coordinates of the center of the ball
	private static int vx, vy; // vx and vy are the velocities of the ball

	private static final int WIDTH = 12, HEIGHT = 12; // width and height of the ball
	private static final int RADIUS = WIDTH/2; // radius of the ball
	private static boolean start; // start is true when the ball is on the paddle
	private static boolean onPad; // if the ball is on the paddle
	private static boolean slowing; // if the ball is slowing down (slow powerup)
	private static int slowTimer = 0; // timer for slow powerup
	private static int slowCounter = 0; // number of slow powerups used

	// fixed them :)

	// border is 27 pixels wide - Past kevin

	Image r = new ImageIcon("assets/Vaus.png").getImage(); // egg

	// constructor
    public Ball() {
		startPos();
		onPad = true;
    }

	// launches the ball from paddle with a random velocity
	public void launchBall() {
		vx = Util.randInt(-5, 5);
		vy = -5;
		start = false;
		onPad = false;
	}

	// wall bounces
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

	// checking if the ball goes below the paddle.
	public boolean deathCheck(Paddle player) {
		if (y>=Globals.SCREEN_HEIGHT) {
			// resetting ball and making it stick to paddle
			startPos();
			Paddle.loseHealth();
			onPad = true;
			player.deathReset(); // also resets the player position and powerups
			return true;
		}
		return false;
	}

	// starting position
	public void startPos() {
		x = Globals.SCREEN_WIDTH / 2;
		y = Paddle.getY() - RADIUS;
		vx = 0;
		vy = 0;
	}

	// paddle bounce
	public void paddleBounce(Paddle play) {
		// this took way too long ;-;
		// i think i lost half of my hair doing this
		Rectangle ball = getRect();
		// bouncing off the paddle
		if (ball.intersects(play.getRect())) {
			if(!prevBallY().intersects(play.getRect())){
				// bunch of math that i already forget
				// just kidding. just taking the x distance of ball to center and doing some
				// magic with it
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
					vx *= -1; //switching x velocity just in case ball wants to be wacky
				}
			}
		}
	}

	// MOVE
    public void move(){
		// moving
		if (onPad) {
			onPaddle();
		}
		else {
			x += vx;
			y += vy;
		}
//		System.out.printf("vx: %d, vy: %d\n", vx, vy);
	}

	// STICK TO PADDLE
	public void onPaddle() {
		x = Paddle.getX() + Paddle.getWidth()/2;
		y = Paddle.getY() - RADIUS;
	}



	// jerry told me to put this here so im going to leave it here
	public double derivatives(double num) {
		return 0.0;
	}

	// returns the previous position of the ball
	public Rectangle prevPos(int velX, int velY) {
		return new Rectangle(x-velX-(WIDTH/2), y-velY-(HEIGHT/2), WIDTH, HEIGHT);
	}
	public Rectangle prevBallX(){
		return new Rectangle(x-RADIUS-vx,y-RADIUS,WIDTH,HEIGHT);
	}
	public Rectangle prevBallY(){
		return new Rectangle(x-RADIUS,y-RADIUS-vy,WIDTH,HEIGHT);
	}


	// get rect
	public Rectangle getRect(){
		return new Rectangle(x-(WIDTH/2),y-(HEIGHT/2), WIDTH, HEIGHT);
	}

	// draws the ball. nothing to see here
    public void draw(Graphics g){
		if (Paddle.getEgg()) {
			g.drawImage(r, x, y, null);
		}
		else {
			g.setColor(Color.WHITE);
			g.fillOval(x-(WIDTH/2),y-(HEIGHT/2),WIDTH,HEIGHT);
		}
    }

	// Slow powerup
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

	// slow powerup timer
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

	// resets speed after slow powerup
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
//		System.out.printf("vx: %d, vy: %d\n", vx, vy);
	}

	// clears all powerups
	public static void clearPowerups() {
		resetSpeed(slowCounter); // resets speed if slow powerup was active
		slowing = false;
		slowTimer = 0;
		slowCounter = 0;
	}


	// static methods.
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

