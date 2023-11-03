import java.awt.*;

public class Ball {
	int x,y,vx,vy;
    int WIDTH = Globals.WIDTH;
    int HEIGHT = Globals.HEIGHT;
    public Ball() {
    	x = WIDTH/2;
    	y = HEIGHT/2;
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
        vx = 5;
        vy = 10000;
    }
    
    public int move(Paddle play){
		// moving
        System.our.println(vy, vx);
    	x+=vx;
    	y+=vy;
        int WIDTH = Globals.WIDTH;
        int HEIGHT = Globals.HEIGHT;

    	if(y<0 || y>HEIGHT){ // bounces off the top and bottom walls. 
    		vy*=-1;
    	}


    	Rectangle r = getRect();

		// bouncing off paddles
    	if(r.intersects(play.getRect())){
    		vy *= -1;
    	}

        // bouncing off the walls
    	if(x<0){
    		vx*=-1;
    	}
    	if(x>WIDTH){
    		vx*=-1;
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
    
}

