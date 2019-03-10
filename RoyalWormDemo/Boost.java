/**
 *Creates a boost that is a pickup in game, keeping track of its spawnrate
 *
 * @Param the position of the object
 * @Param the type as a char
 * @Param spawnRate as an int
 * @Return an instance of Boost
 * */

public class Boost extends GameObject {

    private int counter = 0;
    private int spawnRate;

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

