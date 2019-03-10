package BoardObjects;

import Positions.Direction;
import Positions.Position;

/**
 * Dynamic objects are objects in motion. 
 * @author 
 * @version 2019-03-01 
 */
public class DynamicObject extends GameObject{
    Direction direction;
    int speed;
    int counter;
    char type;
/**
 * Instantiates a DynamicObject
 * @param position of the dynamic object.
 * @param type is defines as a char
 * @param direction 
 */
    public DynamicObject(Position position,char type, Direction direction) {
        super(position, type);
        this.direction = direction;
    }
    public int getCounter() {
    	return counter;
    }
    
    public void setCounter(int c) {
    	counter = c;
    }
    
    public int getSpeed() {
    	return speed;
    }

    public void update() throws InterruptedException {}
}