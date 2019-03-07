import java.util.Random;

public class Bullet extends DynamicObject {
	
	private int speed, directionCounter, changeDirection;
    private int counter;
   private char type;
   private Position position;
   private Direction direction;
   // Random rnd =  new Random();
   private boolean dead=false;
   private int dirX, dirY;
   private boolean projection;

    public Bullet(Position position, Worm w) {
        super(position, 'b',w.getDirection());
        dirX = w.getDirection().getX();
        dirY = w.getDirection().getY();
        this.speed = Constants.bulletSpeed;
        this.position = position;
        this.direction=w.getDirection();
        this.type = 'b';
        projection = true;
       // changeDirection = rnd.nextInt(5)*5;
    }
    

    public void update() throws InterruptedException {
    	if(projection) {
        GameEngine.updateGameworld((Position) position, '0');
        
        position.setX(position.getX() + dirX);
        position.setY(position.getY() + dirY);
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
    	}

    }
    
    public boolean getProjection() {
    	return projection;
    }

	public void endProjection() {
		projection = false;
	}
	
	public Bullet thisBullet() {
		return this;
	}


	public void resetProjection() {
		projection = true;
	}


}
