import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class Ball {
	private static int x,y;
//	private static double vx,vy;
	public static int vx, vy;

	private static final int WIDTH = 12, HEIGHT = 12;
	private static final int RADIUS = WIDTH/2;

	public static double paddleBounce = 0;

	public static boolean start;
	public static boolean onPad, stick;
	public static boolean slowing;
	public static int slowTimer = 0;
	public static int slowCounter = 0;
	public int stickDist;

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
		start = false;
		onPad = false;
	}

    public void wallBounce() {
		int var = 3;
		if(y<=0+Globals.BORDER_WIDTH+var){ // bounces off the top wall
			y = Globals.BORDER_WIDTH+1+var;
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
		if (Paddle.getActivePowerup() == "Catch") {
			stick = true;
			stickDist = x - Paddle.getX();
		}
		else {
			stick = false;
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
	}


	public void onPaddle() {
		// gotta check for the sticking powerup later


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

	// get rect
	public Rectangle getRect(){
		return new Rectangle(x-(WIDTH/2),y-(HEIGHT/2), WIDTH, HEIGHT);
		// return new Rectangle(x-3,y-3,6,6);
	}


	// draws the ball
    public void draw(Graphics g){

		if (Paddle.egg) {
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
		vx *= 0.7;
		vy *= 0.7;
		if (vx < 1 && vx > -1) {
			vx = 1;
		}
		if (vy < 1 && vy > -1) {
			vy = 1;
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
		else {
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

