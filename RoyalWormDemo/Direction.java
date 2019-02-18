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
    public static byte[] toByteArray(Direction d)
    {
        byte[] result = {((byte) d.x),((byte) d.y)};
        return result;
    }

    public static Direction toByteArray(byte[] b)
    {
        return new Direction(((int) b[0]),((int) b[1]));
    }
}
