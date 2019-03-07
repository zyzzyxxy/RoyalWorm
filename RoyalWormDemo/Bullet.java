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
        super(position, 'b',w.getDir());
        this.counter=0;
        dirX = w.getDir().getX();
        dirY = w.getDir().getY();
        this.speed = Constants.bulletSpeed;
        this.position = position;
        this.direction=w.getDir();
        this.type = 'b';
        this.directionCounter=0;
        projection = true;
       // changeDirection = rnd.nextInt(5)*5;
    }
    

    public void update() throws InterruptedException {
    	if(projection) {
        GameEngine.updateGameworld((Position) position, '0');
        
        this.position.x+=dirX;
        this.position.y+=dirY;
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


}
