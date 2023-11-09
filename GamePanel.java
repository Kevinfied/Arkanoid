import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


class GamePanel extends JPanel implements KeyListener, ActionListener, MouseListener{
    Timer timer;
    Ball ball;
    private boolean []keys;

    Image menu, background;

    Paddle player;
    int score1, score2;
    Font fontSys;
    String screen;
    
    public GamePanel(){
        fontSys = new Font("Montserat", Font.PLAIN, 32);
        screen = "intro";
        menu = new ImageIcon("intro1.png").getImage();
        background = new ImageIcon("background.png").getImage();
        keys = new boolean[KeyEvent.KEY_LAST+1];
        ball = new Ball();



        //                  left key          right key         paddle speed
        player = new Paddle();
        setFocusable(true);
        requestFocus();
        addKeyListener(this);
        addMouseListener(this);
        timer = new Timer(Globals.GAME_SPEED, this);
        timer.start();
        setPreferredSize(new Dimension(Globals.SCREEN_WIDTH, Globals.SCREEN_HEIGHT));
    }
    
    public void move(){
        if(screen == "intro"){
    
        }
        else if(screen == "main"){
            ball.wallBounce();
            ball.paddleBounce(player);
            player.move(keys);
            ball.move();

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
            g.setColor(new Color(0,0,0));
            g.fillRect(0,0,Globals.SCREEN_WIDTH,Globals.SCREEN_HEIGHT);
            g.drawImage(background, 0, 0, null);
            ball.draw(g);
//            ball.draw(g);
            player.draw(g);
            g.setFont(fontSys);
        }
    }
}

