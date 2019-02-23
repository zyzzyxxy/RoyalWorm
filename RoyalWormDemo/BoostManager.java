import java.util.HashMap;
import java.util.Random;

public class BoostManager {
    //private HashMap<Position, Boost> boosts;
    Random rnd = new Random();
    //Use to spawn superApples
    int applesSpawned = 0;

    public BoostManager() {

        //boosts = new HashMap<Position, Boost>();
    }
    public void spawnApple()
    {
        if(applesSpawned==20)
        {
            applesSpawned = 0;
            spawnSuperApple();
        }
        else {
        spawnRandom('a');
        applesSpawned++;}
    }
    public void spawnSuperApple()
    {
            spawnRandom('s');
    }
    //Todo
    public void spawnWall(){}
    public void spawn(Position key, char value) {
       // boosts.put(key, value);
        GameEngine.GameWorld[key.x][key.y] = value;
        GameEngine.changes.add(new Change(key.x,key.y,value));
    }
    public void spawnRandom(char value) {

        Position tempPos;
        do {
            tempPos = new Position(rnd.nextInt(Constants.worldWidth), rnd.nextInt(Constants.worldHeight));
        }
        while (CollisionHandler.collisionCheck(tempPos));
        spawn(tempPos,value);
    }
    /*
    public void delete(Position key) {
        boosts.remove(key);
    }

    public void clearAllBoosts() {
        boosts.clear();
    }*/

}