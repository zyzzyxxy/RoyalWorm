public class Boost extends GameObject{

    int counter = 0;
    int spawnRate;

    public Boost(Position position,char type, int spawnRate)
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
        counter++;
    }
    public boolean timeToSpawn()
    {
        return counter>=spawnRate;
    }
}

