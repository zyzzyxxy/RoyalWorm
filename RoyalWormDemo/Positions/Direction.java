package Positions;
import java.util.Random;
/**
 *  
 * Direction 
 * @author Anton Eliasson Gustafsson 
 * @version 2019-03-09
 * 
 */
public class Direction extends BoardCordinates {
	/**
	 * Instantiates a direction.
	 * @param x direction x-axis
	 * @param y direction y-axis
	 */
    public Direction(int x, int y) {
        super(x, y);
    }
/**
 * Generates a random direction. 
 * @return Direction
 */
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
