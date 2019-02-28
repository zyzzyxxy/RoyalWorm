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
    
    public void spawnApple(){
        if(applesSpawned==20)
        {
            applesSpawned = 0;
            spawnSuperApple();
        }
        else {
        	spawnRandom('a');
        	applesSpawned++;
        }
    }
    
    public void eraseApple(Position pos){
    	
    	//System.out.println("\n" + posX + " "  + posY);
    	//GameEngine test = new GameEngine();
    	//int ds = GameEngine.GameWorld.length;
    	//System.out.println("\n" + ds);
    	
    	
    	GameEngine.GameWorld[pos.getX()][pos.getY()] = '0';

    	
    	
    	GameEngine.updateGameworld(pos, '0');
		
    	for(int i = 0; i < GameEngine.GameWorld.length; i++){
    		for(int j = 0; j < GameEngine.GameWorld[i].length; j++){
    			/*
    			if("a".equals(String.valueOf(GameEngine.GameWorld[i][j]))) {
    				System.out.println("\n" + "x: " + i + " y: " + j + " value: " + String.valueOf(GameEngine.GameWorld[i][j]));
    			}
    			*/
    			if(i == pos.getX() && j == pos.getY()){
    				System.out.println("\n" + "x: " + i + " y: " + j + " value: " + String.valueOf(GameEngine.GameWorld[i][j]));
    			}
    		}
    	}
    	
    	
    }
    
    public void spawnSuperApple(){
            spawnRandom('s');
    }
    //Todo
    public void spawnWall(){}
    public void spawn(Position key, char value) {
       // boosts.put(key, value);
        if(value=='a')
            applesSpawned++;
        if(applesSpawned==20)
        {
            applesSpawned = 0;
            spawnSuperApple();
        }

        GameEngine.GameWorld[key.x][key.y] = value;
        GameEngine.changes.add(new Change(key.x, key.y, value));

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