import java.util.List;
import java.util.Random;

public class Ghost extends DynamicObject {
    int speed, directionCounter, changeDirection;
    public int counter;
    char type;
    Position position;
    Direction direction;
    Random rnd =  new Random();
    boolean dead=false;

    public Ghost(Position position) {
        super(position, 'g',Direction.getRandomDirection());
        this.counter=0;
        this.speed = Constants.ghostSpeed;
        this.position = position;
        this.direction=Direction.getRandomDirection();
        this.type = 'g';
        this.directionCounter=0;
        changeDirection = rnd.nextInt(5)*5;

    }


    public void update() throws InterruptedException {
        GameEngine.updateGameworld((Position) position, '0');

        this.position.x+=direction.x;
        this.position.y+=direction.y;
        if (position.x >= Constants.worldWidth)
            position.x = 0;
        if (position.y >= Constants.worldHeight)
            position.y = 0;
        if (position.x < 0)
            position.x = Constants.worldWidth - 1;
        if (position.y < 0)
            position.y = Constants.worldHeight - 1;

        if (CollisionHandler.collisionCheck((Position) position))
            CollisionHandler.collisionHandle(this, (Position)position);

        if(directionCounter==changeDirection) {
            direction = Direction.getRandomDirection();
            changeDirection = (rnd.nextInt(5)+1) * 5;
            directionCounter=0;
        }
        else
            directionCounter++;
        GameEngine.updateGameworld((Position)this.position, 'g');

    }


}
