import java.awt.*;

public class Ball {
	private static int x,y,vx,vy;
//    int WIDTH = Globals.SCREEN_WIDTH;
//    int HEIGHT = Globals.SCREEN_HEIGHT;
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
        vx = 10;
        vy = 10;
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

		    	Rectangle r = getRect();

		// bouncing off paddles
    	if(r.intersects(play.getRect())){
			System.out.println("BOUNCE");

    		vy *= -1;
    	}

		
    	return 0;
    }
    
    public Rectangle getRect(){
        return new Rectangle(x,y,6,6);
    	// return new Rectangle(x-3,y-3,6,6);
    }
    

    // draws the ball
    public void draw(Graphics g){
    	g.setColor(Color.RED);
    	g.fillOval(x-3,y-3,6,6);
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

