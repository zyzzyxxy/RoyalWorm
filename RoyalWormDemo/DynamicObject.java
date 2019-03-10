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

    public void update() throws InterruptedException {}
}