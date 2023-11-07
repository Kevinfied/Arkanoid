import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Ball {
	private static int x,y,vx,vy;
	private static int width, height;

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
		this.height = 10;
		this.width = 10;
    }

    
    public int move(Paddle play){
		// moving
				this.x += vx;
				this.y += vy;

		    	if(y<0 || y>Globals.SCREEN_HEIGHT){ // bounces off the top and bottom walls.
		    		vy*=-1;
		    	}

		        if(x<0 || x>Globals.SCREEN_WIDTH){ // bounces off the left and right walls.
		    		vx*=-1;
		    	}

		    	Rectangle ball = getRect();

		// bouncing off paddles
    	if(ball.intersects(play.getRect())){
			System.out.println("BOUNCE");
			int temp = directionGet(x, y, vx, vy, play, ball);

			if (temp == 1) {
				vx*=-1;
				vy*=-1;
			}
			else if (temp == 2) {
				vy*=-1;
			}
			else if (temp == 3) {
				vx*=-1;
			}

		}


		return 0;
	}






	public int directionGet(int xx, int yy, int velX, int velY, Paddle play, Rectangle ball) {

		int prevX = xx - velX;
		int prevY = yy - velY;


		Rectangle prevBallY = new Rectangle(xx, prevY, width, height);
		Rectangle prevBallX = new Rectangle(prevX, yy, width, height);

		if (!(prevBallY.intersects(play.getRect())) && !(prevBallX.intersects(play.getRect()))) {
			System.out.println("BOUNCE");
			// 1 - both
			return 1;
		}

		else if (!(prevBallY.intersects(play.getRect()))) {
			System.out.println("BOUNCE");
			// 2 - y
			return 2;
		}

		else if (!(prevBallX.intersects(play.getRect()))) {
			System.out.println("BOUNCE");
			// 3 - x
			return 3;
		}

		else {
			System.out.println("BOUNCE");
			// 0 - none
			return 0;
		}
	}
    
    public Rectangle getRect(){
        return new Rectangle(x-(width/2),y-(height/2), width, height);
    	// return new Rectangle(x-3,y-3,6,6);
    }
    

    // draws the ball
    public void draw(Graphics g){
    	g.setColor(Color.WHITE);
    	g.fillOval(x-(width/2),y-(height/2),width,height);
//		g.drawImage(ballIcon, x, y, null);

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

}

