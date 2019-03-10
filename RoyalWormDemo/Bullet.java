/** 
 * Bullets can be used when a player picks up a gun. A player fires bullets from a gun with space bar. 
 * @author Anton Eliasson Gustafsson & Martin Hagentoft
 * @version 2019-03-01
 */

public class Bullet extends DynamicObject {
	private int speed;
    private Position position;
    private int dirX, dirY;
    private boolean projection;
    private Worm w;
/**
 * Instantiates a bullet. 
 * @param position from where the bullet is being projected. 
 * @param w as in Worm, from which the bullet is coming from. 
 */
    public Bullet(Position position, Worm w) {
        super(position, 'b',w.getDirection());
        dirX = w.getDirection().getX();
        dirY = w.getDirection().getY();
        this.speed = Constants.bulletSpeed;
        this.position = position;
        projection = true;
        this.w = w;
    }
    
   /**
    * Updates a bullet's position if projected. 
    */
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
    
    /**
     * 
     * @return projection.
     */
    
    public boolean getProjection() {
    	return projection;
    }
    /**
     * Ends the projection of a bullet.
     */
	public void endProjection() {
		projection = false;
	}
	/**
	 * 
	 * @return speed. 
	 */
	public int getSpeed() {
		return speed;
	}
	/** 
	 * @return the worm that be bullet is being projected from. 
	 */
	public Worm getWorm(){
		return w;
	}


}
