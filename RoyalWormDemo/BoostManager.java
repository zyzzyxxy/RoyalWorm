import java.util.HashMap;
import java.util.Random;

public class BoostManager {
    Random rnd = new Random();
    //Use to spawn superApples
    int applesSpawned = 0;

    public BoostManager() {

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
        while (CollisionHandler.collisionCheck(tempPos));
        spawn(tempPos,value);
    }
}