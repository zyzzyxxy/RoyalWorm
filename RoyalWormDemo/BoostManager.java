import java.util.HashMap;
import java.util.Random;

public class BoostManager {
    private HashMap<Position, Boost> boosts;
    Random rnd = new Random();
    //Use to spawn superApples
    int applesSpawned = 0;

    public BoostManager() {
        boosts = new HashMap<Position, Boost>();
    }
    public void spawnApple()
    {
        if(applesSpawned==20)
        {
            applesSpawned = 0;
            spawnSuperApple();
        }
        else {
        Position tempPos;
        do {
            tempPos = new Position(rnd.nextInt(Constants.worldWidth), rnd.nextInt(Constants.worldHeight));
        }
        while (CollisionHandler.collisionCheck(tempPos));
        spawn(tempPos,new Boost(tempPos,'a'));
        applesSpawned++;}
    }
    public void spawnSuperApple()
    {
            Position tempPos;
            do {
                tempPos = new Position(rnd.nextInt(Constants.worldWidth), rnd.nextInt(Constants.worldHeight));
            }
            while (CollisionHandler.collisionCheck(tempPos));
            spawn(tempPos,new Boost(tempPos,'s'));

    }

    public void spawn(Position key, Boost value) {
        boosts.put(key, value);
        GameEngine.GameWorld[key.x][key.y] = value.type;
        GameEngine.changes.add(new Change(key.x,key.y,value.type));
    }

    public void delete(Position key) {
        boosts.remove(key);
    }

    public void clearAllBoosts() {
        boosts.clear();
    }

}