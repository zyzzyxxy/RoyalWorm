/**
 * Class used by GameObjects tha have positions
 */

import java.util.Random;

public class Position extends BoardCordinates {
	
    public Position(int x, int y) {
        super(x, y);
    }

    /**
     * Method for getting a random position
     *
     * @return a random position
     */
    public static Position getRandomPosition()
    {
        Random rnd = new Random();
        return new Position(rnd.nextInt(Constants.worldWidth-1),rnd.nextInt(Constants.worldHeight-1));
    }
}
