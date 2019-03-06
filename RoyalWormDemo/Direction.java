import java.util.Random;

public class Direction extends BoardCordinates {
	
    public Direction(int x, int y) {
        super(x, y);
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
}
