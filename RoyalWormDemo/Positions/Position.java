package Positions;
/**
* Class used by GameObjects tha have positions
* @author Anton Eliasson Gustafsson
* @author Johan Ericsson
* @version 2019-03-03 
*/

import java.util.Random;

import src.Constants;

public class Position extends BoardCordinates {
	
	/**
	 * Constructor to position.
	 * 
	 * @param x The X value of the coordinate.
	 * @param y The Y value of the coordinate.
	 */
    public Position(int x, int y) {
        super(x, y);
    }

    /**
     * Method for getting a random position.
     *
     * @return a random position
     */
    public static Position getRandomPosition()
    {
        Random rnd = new Random();
        return new Position(rnd.nextInt(Constants.worldWidth-1),rnd.nextInt(Constants.worldHeight-1));
    }
}
