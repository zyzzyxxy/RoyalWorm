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

