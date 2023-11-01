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


    public void collidePaddle() {


    }
    


}