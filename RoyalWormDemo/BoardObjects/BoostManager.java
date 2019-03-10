package BoardObjects;
/**
 * This class takes care of the spawning of Boosts.
 * It can spawn a boost in a specific location or spawn it at random unoccupied location
 * @author Anton Eliasson Gustafsson
 * @author Jonathan Uhre
 * @author Johan Ericsson
 * @author Jonathan Edfeldt
 * @version 2019-03-10
 */

import java.util.Random;

import GameHandling.GameEngine;
import Positions.Change;
import Positions.Position;
import src.Constants;

public class BoostManager {
    Random rnd = new Random();
    int applesSpawned = 0;

    /**
     * Empty constructor.
     */
    public BoostManager() {
    }
    
    /**
     * Spawns a selected object on a selected position.
     * Every 20th apple spawned is a super apple.
     * 
     * @param key The Position where the object should be spawned.
     * @param value Char of the object to be spawned.
     */
    public void spawn(Position key, char value) {
        if(value=='a') {
            applesSpawned++;
	        if(applesSpawned==20) {
	            applesSpawned = 0;
	            value = 's';
	        }
        }
        GameEngine.GameWorld[key.getX()][key.getY()] = value;
        GameEngine.changes.add(new Change(key.getX(), key.getY(), value));
    }

    /**
     * Spawns a selected gameObject on a random unoccupied position.
     * 
     * @param value Char of the object to be spawned.
     */
    public void spawnRandom(char value) {
        Position tempPos;
        do {
            tempPos = new Position(rnd.nextInt(Constants.worldWidth), rnd.nextInt(Constants.worldHeight));
        }
        while (GameEngine.GameWorld[tempPos.getX()][tempPos.getY()] != '0');
        spawn(tempPos,value);
    }
}
