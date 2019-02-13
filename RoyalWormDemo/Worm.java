import java.awt.*;

public class Worm extends DynamicObject{
    Color color;
    int speed;

    public Worm(Position position, Direction direction, Color color) {
        super(position, direction);
        this.color = color;
        this.speed = Constants.wormspeed;
    }
    @Override
    public void run() {
            move();
            try {
                this.wait(1000/speed);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    private void move(){

    }



    }



