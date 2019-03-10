/**
 * Checks and Handles collisions between GameObjects
 */

public class CollisionHandler {

    /**
     * Checks if there is an object on the selected position.
     *
     * @param pos The position to be checked for collision.
     * @return True if there is a collision, false if it isn't.
     */
	public static boolean collisionCheck(Position pos) 
	{
		if(GameEngine.GameWorld[pos.getX()][pos.getY()]!='0') {
			System.out.println("Collision!!");
			return true;
		}
		return false;
	}
	
    /**
     * Handles the collision of two GameObjects. Should be used when an object changes position.
     * 
     * @param object The GameObject which collision should be handled.
     * @param pos The Position of the collision.
     */
	public static void collisionHandle(GameObject object, Position pos) throws InterruptedException {

		if(object instanceof Worm){
			if (object.getType() == '1' || object.getType() == '2' || object.getType() == '3' || object.getType() == '4' || object.getType() == '5') {
				System.out.print("in if");
				switch (GameEngine.GameWorld[pos.getX()][pos.getY()]) {
					case '0':
						break;
					case 'a':
						((Worm) object).grow(1);
						break;
					case 's':
						((Worm) object).grow(10);
						break;
					case 'w':
						((Worm) object).reset();
						break;
					case '1':
						((Worm) object).reset();
						break;
					case '2':
						((Worm) object).reset();
						break;
					case '3':
						((Worm) object).reset();
						break;
					case '4':
						((Worm) object).reset();
						break;
					case '5':
						((Worm) object).reset();
						break;
					case 'l':
						((Worm) object).lightningMode();
						break;
					case 'p':
						((Worm) object).pickUpGun();
						break;
					case 'b':
						((Worm) object).reset();
						break;
				}
			}
		}
		else if (object instanceof Ghost)
		{	
			char comp = GameEngine.GameWorld[pos.getX()][pos.getY()];
			if(comp == 'w') {
				((Ghost)object).setDead(true);
			}
			for (Player p:GameEngine.playerList) {
				if(p.getWorm().isInBody(pos)!=-1) {
					p.getWorm().cut(pos);
					((Ghost)object).setDead(true);
				}
			}
		}
		else if(w instanceof Bullet) {
			Worm tmp = ((Bullet)w).getWorm();
			char comp = GameEngine.GameWorld[pos.getX()][pos.getY()];
			if(comp =='w' || comp == 'b'|| comp == '1' || comp == '2' || comp == '3' || comp == '4' || comp == '5' || comp == 'g' || comp == 'a' || comp == 's'){
				if(comp == 'w')
				((Bullet)w).endProjection();
				if(comp == 's')
					tmp.setScore(tmp.getScore() + 10);
				if(comp == 'a')
					tmp.setScore(tmp.getScore() + 5);
				if(comp == 'g')
					tmp.setScore(tmp.getScore() + 50);
				if(comp == 'p')
					tmp.setScore(tmp.getScore() + 3);
			}
			else 
			for (Player p:GameEngine.playerList) {
				if(p.getWorm().isInBody(pos)!=-1) {
					p.getWorm().cut(pos);
				}
			}
		}
	}
}
