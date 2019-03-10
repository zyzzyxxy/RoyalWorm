/**
 *Creates a boost that is a pickup in game, keeping track of its spawnrate
 *
 * @author Johan Ericsson, Anton Eliasson Gustavsson
 * @version 2019-03-01
 * */

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
    public void incCounter()
    {
        counter++;
    }
    public void resetCounter()
    {
        counter=0;
    }
    public boolean timeToSpawn()
    {
        return counter>=spawnRate;
    }
}

