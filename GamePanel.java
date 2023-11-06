import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


class GamePanel extends JPanel implements KeyListener, ActionListener, MouseListener{
    Timer timer;
    Ball ball;
    private boolean []keys;
//    Image back;
    Image menu, background;
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
        menu = new ImageIcon("intro.png").getImage();
        background = new ImageIcon("background.png").getImage();
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
        timer = new Timer(5, this);
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
            g.drawImage(menu,0,0,null);
        }



        else if(screen == "main"){
//            g.setColor(new Color(0,0,0));
//            g.fillRect(0,0,Globals.SCREEN_WIDTH,Globals.SCREEN_HEIGHT);
            g.drawImage(background, 0, 0, null);
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

