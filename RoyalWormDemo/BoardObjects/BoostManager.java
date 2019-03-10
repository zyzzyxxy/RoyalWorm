package BoardObjects;
/**
 * This class takes care of the spawning of Boosts. It can spawn a boost in a specific location
 * or spawn it at random (free) location
 *
 * @Return an instance of BoostManager
 * */

import java.util.Random;

import GameHandling.GameEngine;
import Positions.Change;
import Positions.Position;
import src.Constants;

public class BoostManager {
    Random rnd = new Random();
    //Use to spawn superApples
    int applesSpawned = 0;

    public BoostManager() {
    }

    public void spawnSuperApple()
    {
            spawnRandom('s');
    }

    //TODO
    public void spawnWall(){}

    public void spawn(Position key, char value) {

        if(value=='a')
            applesSpawned++;
        
        if(applesSpawned==20)
        {
            applesSpawned = 0;
            spawnSuperApple();
        }

        GameEngine.GameWorld[key.getX()][key.getY()] = value;
        GameEngine.changes.add(new Change(key.getX(), key.getY(), value));
    }

    public void spawnRandom(char value) {

        Position tempPos;
        do {
            tempPos = new Position(rnd.nextInt(Constants.worldWidth), rnd.nextInt(Constants.worldHeight));
        }
        while (GameEngine.GameWorld[tempPos.getX()][tempPos.getY()] != '0');
        spawn(tempPos,value);
    }
}