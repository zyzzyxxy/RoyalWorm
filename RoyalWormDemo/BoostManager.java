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

    //TODO
    public void spawnWall(){}
    public void spawn(Position key, GameObject object) {
       // boosts.put(key, value);
        if(object instanceof Apple)
            applesSpawned++;
        if(applesSpawned==20)
        {
            applesSpawned = 0;
            spawnSuperApple();
        }

        Main.gameController.gameEngine.updateGameWorld(new Position(key.x, key.y), object);

    }

    public void spawnRandom(char value) {

        Position tempPos;
        do {
            tempPos = new Position(rnd.nextInt(Constants.worldWidth), rnd.nextInt(Constants.worldHeight));
        }
        while (CollisionHandler.collisionCheck(tempPos));
        spawn(tempPos,value);
    }
}