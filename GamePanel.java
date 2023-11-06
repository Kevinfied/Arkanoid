import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


class GamePanel extends JPanel implements KeyListener, ActionListener, MouseListener{
    Timer timer;
    Ball ball;
    private boolean []keys;
    Image back;
    Paddle player1, player2;
    Paddle player;
    int score1, score2;
    Font fontSys;
    String screen;
    
    public GamePanel(){
        // int WIDTH = 400; 
        // int HEIGHT = 800;
        fontSys = new Font("Montserat", Font.PLAIN, 32);
        screen = "intro";
        back = new ImageIcon("background.png").getImage();
        keys = new boolean[KeyEvent.KEY_LAST+1];
        ball = new Ball();
        // score1 = 0;
        // score2 = 0;


        //                  left key          right key         paddle speed
        player = new Paddle(KeyEvent.VK_LEFT, KeyEvent.VK_RIGHT, 20);
        setFocusable(true);
        requestFocus();
        addKeyListener(this);
        addMouseListener(this);
        timer = new Timer(20, this);
        timer.start();
        setPreferredSize(new Dimension(Globals.SCREEN_WIDTH, Globals.SCREEN_HEIGHT));
    }
    
    public void move(){
        if(screen == "intro"){
    
        }
        else if(screen == "main"){	
            // int res = ball.move(player1,player2);
            int res = ball.move(player);
            // if(res==1){
            //     score2++;
            //     ball = new Ball();
            // }
            // if(res==2){
            //     score1++;
            //     ball = new Ball();
            // }
            player.move(keys);
            // player1.move(keys);
            // player2.move(keys);
        }
    }
    
    public void	keyPressed(KeyEvent e){
        int code = e.getKeyCode();
        keys[code] = true;

    }
    public void	keyReleased(KeyEvent e){
        int code = e.getKeyCode();
        keys[code] = false;		
    }
    public void	keyTyped(KeyEvent e){
    }

    public void	mouseClicked(MouseEvent e){

    }

    public void	mouseEntered(MouseEvent e){

    }

    public void	mouseExited(MouseEvent e){}

    public void	mousePressed(MouseEvent e){
        System.out.println("HERE");
        if(screen == "intro"){
            screen = "main";
        }
        
    }
    public void	mouseReleased(MouseEvent e) {}
        
    // @Override
    public void	actionPerformed(ActionEvent e){
        move();
        repaint();
    }
    
    @Override
    public void paint(Graphics g){

        if(screen == "intro"){
            g.drawImage(back,0,0,null);		
        }

        else if(screen == "main"){
            g.setColor(new Color(0,0,0));
            g.fillRect(0,0,Globals.SCREEN_WIDTH,Globals.SCREEN_HEIGHT);

            ball.draw(g);
            // player1.draw(g);
            // player2.draw(g);
            player.draw(g);
            g.setFont(fontSys);
            // g.drawString(""+score1, 100,50);
            // g.drawString(""+score2, 670,50);
        }
    }
}

// import java.awt.*;
// import java.awt.event.*;
// import javax.swing.*;
// import java.util.*;

// class GamePanel extends JPanel implements KeyListener, ActionListener, MouseListener{
//     Timer timer;
//     Ball ball;
//     private boolean []keys;
//     Image back;
//     Paddle player1, player2;
//     // int score1, score2;
//     Font fontSys;
//     String screen;
    
//     public GamePanel(){
//         fontSys = new Font("Montserat", Font.PLAIN, 32);
//         screen = "intro";
//         back = new ImageIcon("intro.png").getImage();
//         keys = new boolean[KeyEvent.KEY_LAST+1];
//         ball = new Ball();
//         score1 = 0;
//         score2 = 0;
//         player1 = new Paddle(10, KeyEvent.VK_W, KeyEvent.VK_S);
//         player2 = new Paddle(770, KeyEvent.VK_UP, KeyEvent.VK_DOWN);
//         setFocusable(true);
//         requestFocus();
//         addKeyListener(this);
//         addMouseListener(this);
//         timer = new Timer(20, this);
//         timer.start();
//         setPreferredSize(new Dimension(800,600));
//     }
    
//     public void move(){
//         if(screen == "intro"){
    
//         }
//         else if(screen == "main"){	
//             int res = ball.move(player1,player2);
//             if(res==1){
//                 score2++;
//                 ball = new Ball();
//             }
//             if(res==2){
//                 score1++;
//                 ball = new Ball();
//             }
//             player1.move(keys);
//             player2.move(keys);
//         }
//     }
    
//     public void	keyPressed(KeyEvent e){
//         int code = e.getKeyCode();
//         keys[code] = true;

//     }
//     public void	keyReleased(KeyEvent e){
//         int code = e.getKeyCode();
//         keys[code] = false;		
//     }
//     public void	keyTyped(KeyEvent e){
//     }

//     public void	mouseClicked(MouseEvent e){}

//     public void	mouseEntered(MouseEvent e){}

//     public void	mouseExited(MouseEvent e){}

//     public void	mousePressed(MouseEvent e){
//         System.out.println("HERE");
//         if(screen == "intro"){
//             screen = "main";
//         }
        
//     }
//     public void	mouseReleased(MouseEvent e) {}
        
//     // @Override
//     public void	actionPerformed(ActionEvent e){
//         move();
//         repaint();
//     }
    
//     @Override
//     public void paint(Graphics g){
//         if(screen == "intro"){
//             g.drawImage(back,0,0,null);		
//         }
//         else if(screen == "main"){
//             g.setColor(new Color(0,0,0));
//             g.fillRect(0,0,800,600);		
//             ball.draw(g);
//             player1.draw(g);
//             player2.draw(g);
//             g.setFont(fontSys);
//             g.drawString(""+score1, 100,50);
//             g.drawString(""+score2, 670,50);
//         }
//     }
// }



// // import java.util.*;
// // import java.awt.*;

// // import javax.swing.*;
// // import java.awt.event.*;


// // class GamePanel extends JPanel implements KeyListener, ActionListener, MouseListener, MouseMotionListener {


// //     int WIDTH = 400;
// //     int HEIGHT = 800;

// //     Timer timer;
// //     Ball ball;

// //     private boolean []keys;
// //     Image background;
// //     Paddle player;
// //     int score;
// //     Font fontSys;


// //     String screen;
    



// //     public GamePanel() {
// //         fontSys = new Font("Montserat", Font.PLAIN, 32);
// //         screen = "intro";
// //         background = new ImageIcon("assets/background.png").getImage();
// //         keys = new boolean[KeyEvent.KEY_LAST+1];
// //         ball = new Ball();
        
// //         score = 0;


// //         // player = new Paddle(200, 350);
// //         setFocusable(true);
// //         requestFocus();
// //         addKeyListener(this);
// //         addMouseListener(this);
// //         timer = new Timer(10, this);
// //         timer.start();
// //         setPreferredSize(new Dimension(WIDTH, HEIGHT));


// //         keys = new boolean[KeyEvent.KEY_LAST+1];
// //         boxx = 100;
// //         boxy = 100;
// //         dx = 5;
// //         setFocusable(true);
// //         requestFocus();
// //         addKeyListener(this);
// //         timer = new Timer(10, this);
// //         timer.start();
// //     }

// //     public void keyPressed(KeyEvent e) {
// //         int code = e.getKeyCode();
// //         keys[code] = true;

// //     }

// //     public void keyReleased(KeyEvent e) {
// //         int code = e.getKeyCode();
// //         keys[code] = false;
// //     }

// //     public void keyTyped(KeyEvent e) {

// //     }

// //     public void actionPerformed(ActionEvent e) {
// //         move();
// //         repaint();
// //     }

// //     @Override 
// //     public void paint(Graphics g) {
// //         g.drawImage(back, 0, 0, null);
// //         g.setColor(new Color(222, 111, 111));
// //         g.fillRect(boxx, boxy, 50, 50);
// //     }

// //     // public void paintComponent(Graphics g) {
// //     //     super.paintComponent(g);
// //     //     g.drawImage(background, 0, 0, null);
// //     //     g.setFont(fontSys);
// //     //     g.setColor(Color.WHITE);
// //     //     g.drawString("Score: " + score, 10, 30);
// //     //     ball.draw(g);
// //     //     player.draw(g);
// //     //     if (screen.equals("intro")) {
// //     //         g.drawString("Click to start", 100, 400);
// //     //     }
// //     //     if (screen.equals("gameover")) {
// //     //         g.drawString("Game Over", 100, 400);
// //     //     }
// //     // }

// // }