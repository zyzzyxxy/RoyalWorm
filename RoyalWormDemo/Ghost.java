/**
 * An enemy, this is a gameobject with a very simple ai that changes direction at random inside a timespan.
 * @author 
 * @version 
 */

import java.util.Random;

public class Ghost extends DynamicObject {
    private int directionCounter, changeDirection;
    private Position position;
    private Direction direction;
    private Random rnd =  new Random();
    private boolean dead=false;

 /**
  * Instantiates a Ghost.
  * @param position of the ghost. 
  */
    public Ghost(Position position) {
        super(position, 'g',Direction.getRandomDirection());
        this.counter=0;

        this.position = position;
        this.direction=Direction.getRandomDirection();
        this.directionCounter=0;
        changeDirection = rnd.nextInt(5) * 5;
    }
   /**
    * Updates the position for a Ghost. 
    */
    public void update() throws InterruptedException {
        //Current position gets empty.
    	GameEngine.updateGameworld((Position) position, '0');
    	//New position for a ghost.
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

    /**
     * This method checks if the ghost is dead.
     * @return 
     */
    
	public boolean isDead() {
		return dead;
	}
	/**
	 * Sets ghost to dead or alive. 
	 * @param b is set to true if it is dead, false if alive. 
	 */
	public void setDead(boolean b) {
		dead = b;
	}
}
