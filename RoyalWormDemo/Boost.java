public class Boost extends GameObject{

    private int counter = 0;

    public Boost(Position position, char objChar)
    {
        super(position, objChar);
    }
    public void incCounter()
    {
        counter++;
    }
    public void resetCounter()
    {
        counter=0;
    }
    
}

