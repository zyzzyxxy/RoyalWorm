public class DynamicObject extends GameObject implements Runnable{
    Direction direction;

    public DynamicObject(Position position, Direction direction) {
        super(position);
        this.direction = direction;
    }

    @Override
    public void run() {

    }
}
