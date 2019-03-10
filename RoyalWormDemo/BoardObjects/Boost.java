package BoardObjects;

import Positions.Position;

/**
 * 
 * Boosts are objects that players might pick up
 * @author Johan Ericsson
 * @author Anton Eliasson Gustafsson
 * @author Martin Hagentoft
 * @version 2019-03-01
 */

public class Boost extends GameObject {

    private int counter = 0;
    private int spawnRate;

    /**
     * Constructor
     *
     * @param position  the position of the object
     * @param type      the type as a char
     * @param spawnRate spawnRate as an int
     */
    public Boost(Position position, char type, int spawnRate)
    {
        super(position,type);
        this.spawnRate=spawnRate;
    }  
    /**
     * Increments a counter. 
     */
    public void incCounter()
    {
        counter++;
    }
    /**
     * Resets the counter
     */
    public void resetCounter()
    {
        counter=0;
    }
    /**
     * Returns true if counter is greater or equal to spawnRate, else false.
     * @return
     */
    public boolean timeToSpawn()
    {
        return counter>=spawnRate;
    }
}



