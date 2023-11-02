class Ball {


    private int x, y;
    private int vx, vy;

    public Ball(int x, int y, int vx, int vy) {
        this.x = x;
        this.y = y;
        this.vx = vx;
        this.vy = vy;

    }

    public void move() {
        x += vx;
        y += vy;
    }


    public boolean collidePaddle(Paddle paddle) {


        if (x > paddle.getX() && x < paddle.getX() + paddle.getWidth() && y > paddle.getY() && y < paddle.getY() + paddle.getHeight()) {
            vy = -vy;
            return true;
        }
        return false;


    }
    

    public boolean collideWall() {
        if (x < 0 || x > 400) {
            vx = -vx;
            return true;
        }
        if (y < 0 || y > 400) {
            vy = -vy;
            return true;
        }
        return false;
    }

    public void draw(Graphics g) {
        g.setColor(Color.RED);
        g.fillOval(x, y, 20, 20);
    }







}