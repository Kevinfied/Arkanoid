public class Paddle {

    private int x, y;
    private int width, height;


    public Paddle(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void moveLeft() {
        x -= 5;
    }

    public void moveRight() {
        x += 5;
    }

    public void moveUp() {
        y -= 5;
    }


    public void move(boolean []keys) {
        if (keys[KeyEvent.VK_LEFT] || keys[KeyEvent.VK_A]) {
            moveLeft();
        }

        if (keys[KeyEvent.])


    }


}