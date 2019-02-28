import java.util.Random;

public class Direction extends BoardCordinates {
    public double x,y;

    public Direction(double x, double y) {
        super(x,y);
        this.x=x;
        this.y=y;
    }

    public static Direction getRandomDirection()
    {
        Random rnd = new Random();
        switch (rnd.nextInt(4))
        {
            case 0: return new Direction(0,1);
            case 1: return new Direction(0,-1);
            case 2: return new Direction(1,0);
            case 3: return new Direction(-1,0);
        }
        return new Direction(1,1);
    }
    //Todo Maby this is not needed
    /*
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
    }*/
}
