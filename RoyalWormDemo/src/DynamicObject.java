public class DynamicObject extends GameObject implements Runnable{
    Direction direction;

    public DynamicObject(Position position,char type, Direction direction) {
        super(position, type);
        this.direction = direction;
    }

    @Override
    public void run() {

    }
}
