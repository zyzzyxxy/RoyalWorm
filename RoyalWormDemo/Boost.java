/**
 * 
 * Boosts are objects that players might pick up
 * @author Johan Ericsson & Anton Eliasson Gustafsson & Martin Hagentoft
 * @version 2019-03-01
 */

public class Boost extends GameObject {

    private int counter = 0;
    private int spawnRate;
    
/**
 * Instantiates a boost.
 * @param position to locate a spawn position
 * @param type as a char 
 * @param spawnRate is an integer user for determining for how frequent a boost spawns.   
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



