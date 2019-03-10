package BoardObjects;

import Positions.Direction;
import Positions.Position;

/**
 * Dynamic objects are objects in motion. 
 * @author Anton Eliasson Gustafsson
 * @author Martin Hagentoft
 * @author Johan Ericsson
 * @version 2019-03-01 
 */
public class DynamicObject extends GameObject{
    Direction direction;
    int speed;
    int counter;
    char type;

/**
 * Instantiates a DynamicObject
 * @param position Position of the dynamic object.
 * @param type Type defines as char
 * @param direction  Direction of object
 */
    public DynamicObject(Position position,char type, Direction direction) {
        super(position, type);
        this.direction = direction;
    }
    /**
     * 
     * @return counter
     */
    public int getCounter() {
    	return counter;
    }
    /**
     * 
     * @param c sets counter.
     */
    public void setCounter(int c) {
    	counter = c;
    }
    /**
     * 
     * @return speed of a ghost
     */
    public int getSpeed() {
    	return speed;
    }
    //This does nothing in current version. 
    public void update() throws InterruptedException {}
}