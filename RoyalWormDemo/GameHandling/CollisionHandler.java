package GameHandling;

import BoardObjects.Bullet;
import BoardObjects.GameObject;
import BoardObjects.Ghost;
import BoardObjects.Player;
import BoardObjects.Worm;
import Positions.Position;

/**
 * Checks and Handles collisions between GameObjects
 * @author Anton Eliasson Gustafsson
 * @author Jonathan Uhre
 * @author Johan Ericsson
 * @author Jonathan Edfeldt
 * @version 2019-03-09
 */

public class CollisionHandler {

    /**
     * Checks if there is an object on the selected position.
     *
     * @param pos The position to check for collision.
     * @return True if there is a collision, false if it isn't.
     */
	public static boolean collisionCheck(Position pos) 
	{
		return (GameEngine.GameWorld[pos.getX()][pos.getY()]!='0');
	}
	
    /**
     * Handles the collision of two GameObjects. Should be used when an object changes position.
     * 
     * @param object The GameObject which collision should be handled.
     * @param pos The Position of the collision.
     */
	public static void collisionHandle(GameObject object, Position pos) throws InterruptedException {
		//Handles collision for Worm
		if(object instanceof Worm){
			if (object.getType() == '1' || object.getType() == '2' || object.getType() == '3' || object.getType() == '4' || object.getType() == '5') {
				switch (GameEngine.GameWorld[pos.getX()][pos.getY()]) {
					case '0': //empty spot
						break;
					case 'a': //apple
						((Worm) object).grow(1);
						break;
					case 's': //super apple
						((Worm) object).grow(10);
						break;
					case 'w': // wall
						((Worm) object).reset();
						break;
					case '1': // player 1
						((Worm) object).reset();
						break;
					case '2': // player 2
						((Worm) object).reset();
						break;
					case '3':// player 3
						((Worm) object).reset();
						break;
					case '4':// player 4
						((Worm) object).reset();
						break;
					case '5':// player 5
						((Worm) object).reset();
						break;
					case 'l': // lightning
						((Worm) object).lightningMode();
						break;
					case 'p': //pistol (gun)
						((Worm) object).pickUpGun();
						break; 
					case 'b': //bullet
						((Worm) object).reset();
						break;
				}
			}
		}
		//Handles collision for Ghost.
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
		//Handles collision for Bullet.
		else if(object instanceof Bullet) {
			Worm tmp = ((Bullet)object).getWorm();
			char comp = GameEngine.GameWorld[pos.getX()][pos.getY()];
			if(comp =='w' || comp == 'b'|| comp == '1' || comp == '2' || comp == '3' || comp == '4' || comp == '5' || comp == 'g' || comp == 'a' || comp == 's'){
				if(comp == 'w')
				((Bullet)object).endProjection();
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
