import java.util.List;
import java.util.Random;

public class Ghost extends DynamicObject {
    private int speed, directionCounter, changeDirection;
    private int counter;
    private char type;
    private Position position;
    private Direction direction;
    private Random rnd =  new Random();
    private boolean dead=false;

    public Ghost(Position position) {
        super(position, 'g',Direction.getRandomDirection());
        this.counter=0;
        this.speed = Constants.ghostSpeed;
        this.position = position;
        this.direction=Direction.getRandomDirection();
        this.type = 'g';
        this.directionCounter=0;
        changeDirection = rnd.nextInt(5) * 5;
    }

    public void update() throws InterruptedException {
        GameEngine.updateGameworld((Position) position, '0');

        position.setX(position.getX() + direction.getX());
        position.setY(position.getY() + direction.getY());
        
        if (position.getX() >= Constants.worldWidth)
            position.setX(0);
        if (position.getY() >= Constants.worldHeight)
            position.setY(0);
        if (position.getX() < 0)
            position.setX(Constants.worldWidth - 1);
        if (position.getY() < 0)
            position.setY(Constants.worldHeight - 1);

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

    public char getType() {
    	return type;
    }
    
	public boolean isDead() {
		return dead;
	}

	public void setDead(boolean b) {
		dead = b;
	}
}
