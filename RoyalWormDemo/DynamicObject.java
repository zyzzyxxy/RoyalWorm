public class DynamicObject extends GameObject{
    Direction direction;
    int speed;
    int counter;
    char type;

    public DynamicObject(Position position,char type, Direction direction) {
        super(position, type);
        this.direction = direction;
    }

    public void update() throws InterruptedException {}
}
