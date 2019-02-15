public class Direction extends BoardCordinates {
    public int x,y;

    public Direction(int x, int y) {
        super(x,y);
        this.x=x;
        this.y=y;
    }

    public void setDirection(int x, int y)
    {
        this.x=x;
        this.y=x;
    }
}
