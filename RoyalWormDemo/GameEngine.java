/**
 *This class takes care of the logics of the game.
 *
 * It´s observable and can notify changes to i.e. a gui or a network controller.
 * It has a timer that ticks, dictating the speed of the game
 *
 * @Param Player list of the players that should be included
 * @Param booleans for every game mode avaliable.
 * @Return an instance of GameEngine that runs.
 * */


import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;
/**
 * This class is responsible for the game logic and makes the game tick. Each tick, (or gametick) the game updates. 
 * @author anton
 *
 */
public class GameEngine extends Observable {
    public static char[][] GameWorld;
    public static List<Player> playerList = new ArrayList<>();
    public static List<DynamicObject> dObjectList = new ArrayList<>();
    public static List<Change> changes = new ArrayList<>();//for sending changes for graphics
    
    private List<Boost> spawnList = new ArrayList<>();
    private javax.swing.Timer gameTimer;
    private BoostManager boostManager = new BoostManager();
    private int gameCOunter = 0;
    private int ghostCounter = 0;
    private int shrinkCOunter = 0;
    private boolean gameOver=false;
    private boolean apples,lightning,gun,ghost, royal;
    
    /**
     * Instantiates a GameEngine. 
     * Whenever a GameEngine is created, it sets up the game world's size and a timer which with an actionlistener becomes a gametick.
     * @param playersList creates a public list of players
     * @param royal is a boolean determining if the game mode Battle Royal is active or not. 
     * @param apples is a boolean determining if apples are in the game or not.
     * @param lightning is a boolean determining if lightnings are in the game or not.
     * @param gun is a boolean determining if guns are in the game or not. NOTE: Does not work in current version.
     * @param ghost is a boolean determining if ghosts are in the game or not.
     * @throws Exception 
     */

    public GameEngine(List<Player> playersList,boolean royal, boolean apples,boolean lightning, boolean gun, boolean ghost) throws Exception {
        this.apples = apples;
        this.lightning = lightning;
        this.gun = gun;
        this.ghost = ghost;
        this.royal = royal;

        GameWorld = new char[Constants.worldWidth][Constants.worldHeight];
        resetGameworld();
        playerList = playersList;
        makeSpawnList();

        gameTimer = new Timer(Constants.GAMESPEED, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    gameTick();
                } catch (InterruptedException | FileNotFoundException e1) {
                    e1.printStackTrace();
                }
            }
        });
        //Starts the game
        gameTimer.start();
    }

    //This method is called every time the GameTimer ticks, takes care of updating the gamecomponents.
    
    /**
     * Marks whenever a gametick has passed, updating worms, bullets, boosts, ghosts and walls.
     * @throws InterruptedException
     * @throws FileNotFoundException
     */
    private void gameTick() throws InterruptedException, FileNotFoundException {
        if(!gameOver) {
            updateWorms();
            updateBullets();
            if(gameCOunter % Constants.allowedFire == 0) {
            	for(Player p : playerList) {
            		p.getWorm().toggleFireAllowed();
            	}
            }

            //no need to inc every counter every time
            if ((gameCOunter % Constants.GENERALSPAWNRATE) == 0) {
                updateBoosts();
                updateDynamicObjects();
                checkForGameOver();

            }
            
            if((gameCOunter %  Constants.WALL_SPAWN_SPEED == 0) && royal) {
            	battleRoyal();
            }

            tellObservers();
            gameCOunter++;
            if (gameCOunter == 1000)
                gameCOunter = 0;
        }
        else {
            String current="";
            try {
                current = new File( "." ).getCanonicalPath();
                System.out.println(current);
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("Game over");
            loadGameworld(new File("C:/Users/anton/Documents/Gitz/RoyalWormgameOver.txt"));
            setChanged();
            tellObservers();
        }
    }

    /**
     * Updates bullets' positions. 
     * @throws InterruptedException
     */
	public void updateBullets() throws InterruptedException {
		for(Player p : playerList) {
			//System.out.println(p.getWorm().getBullets().size());
			for(Bullet b : p.getWorm().getBullets()) {
				//p.getWorm().getBullets().remove(b); This should be here but crashes the game. Makes no significant differense though.
				if(b.getProjection()) {
				if(gameCOunter % Constants.bulletSpeed == 0)
						b.update();
						updateGameworld(b.getPosition(), 'b');
						
				}
			}
		}
	}
	/**
	 * Checks if game over.
	 */
	public void checkForGameOver()
    {
        int playersAlive=0;
        for (Player p:playerList) {
            if(p.getWorm().getLives() > 0)
                playersAlive++;
        }
        if(playersAlive<=0)
        {
            gameOver=true;
        }
    }

    public char[][] getGameWorld() {
        return GameWorld;
    }

    /*
     * Calls to two other methods which spawn guns and shrink walls.
     */
    private void battleRoyal() {
    	spawnGun();
    	shrinkWalls();
    }
    /**
     * Spawns a gun on a random position with no collisions.  
     */
	private void spawnGun() {
		Position tmp;
		do {
			tmp = randomPos();
		}while(GameWorld[tmp.getX()][tmp.getY()] != '0');
			
		updateGameworld(tmp, 'p');
	}
	/**
	 * Generates a random position. 
	 * @return the generated position. 
	 */
	public Position randomPos() {
		Random xr = new Random();
		Random yr = new Random();
		int x = xr.nextInt(79);
		int y = yr.nextInt(59);
		Position tmp = new Position(x, y);
		return tmp;
	}

	/**
	 * Shrink walls in Battle Royal mode. 
	 */
	private void shrinkWalls() {
		for(int i = 0; i<80; i++) {
			updateGameworld(new Position(i,0+shrinkCOunter), 'w');
			updateGameworld(new Position(i,59-shrinkCOunter), 'w');
		}
		
		for(int j = 0; j<60; j++) {
			updateGameworld(new Position(0+shrinkCOunter,j), 'w');
			updateGameworld(new Position(79-shrinkCOunter,j), 'w');
		}
		shrinkCOunter++;
	}

	/**
	 * Adds lightnings and apples to a spawnlist.
	 */
    private void makeSpawnList() {
        if(lightning)
        spawnList.add(new Boost(Position.getRandomPosition(), 'l', 50));
        if(apples)
        spawnList.add(new Boost(Position.getRandomPosition(), 'a', 15));
    }

/**
 * Updates boosts' positions and calls for random boosts spawns.
 */
    private void updateBoosts() {
        for (Boost b : spawnList) {
            if (b.timeToSpawn()) {
                boostManager.spawnRandom(b.getType());
                b.resetCounter();
            } else
                b.incCounter();
        }
        if(ghostCounter==Constants.ghostSpawn&&ghost) {
            dObjectList.add(new Ghost(randomPos()));
            ghostCounter=0;
        }
        else {ghostCounter++;}
    }

    /**
     * Updates worms.
     * @throws InterruptedException
     */
    private void updateWorms() throws InterruptedException {
        for (Player p : playerList) {
        	p.getWorm().updater();
        }
    }

    /**
     * Updates dynamic objects (except worms). NOTE: Only ghosts in current version.
     * @throws InterruptedException
     */
    private void updateDynamicObjects() throws InterruptedException {
        for (int i = 0; i < dObjectList.size();i++) {
            if(dObjectList.get(i) instanceof Ghost)
                if(((Ghost)dObjectList.get(i)).isDead())
                    dObjectList.remove(dObjectList.get(i));
        }
        for (int i = 0; i < dObjectList.size();i++) {
            if (dObjectList.get(i).counter ==dObjectList.get(i).speed) {
                dObjectList.get(i).update();
                dObjectList.get(i).counter = 0;
            } else {
                
            	dObjectList.get(i).counter++;
            }
        }
    }

    /**
     * Updates gameWorld. 
     * @param pos for a position in gameWorld.
     * @param c corresponds what type of object that is stored in the position.
     */
    public static void updateGameworld(Position pos, char c) {
        GameWorld[pos.getX()][pos.getY()] = c;
        changes.add(new Change(pos.getX(), pos.getY(), c));
    }
    
    //notifies observers
    /**
     * Notifies observers.
     */
    public void tellObservers() {
        setChanged();
        notifyObservers(changes);
    }

    //empties the world and reset worms
    /**
     * Resets gameWorld with an empty space. 
     */
    public void resetGameworld() {
        for (char[] c : GameWorld) {
            Arrays.fill(c, '0');
            for (Player p :playerList) {
                p.getWorm().reset();
            }
        }
    }

    //Loads a world file, must be right format
    /**
     * Loads a game map. 
     * @param file of the map.
     * @throws FileNotFoundException
     */
    public void loadGameworld(File file) throws FileNotFoundException {
        Scanner sc = new Scanner(file);
        for (int i = 0; i < Constants.worldHeight; i++) {
            if (sc.hasNextLine()) {
                char[] c = sc.nextLine().toCharArray();
            for (int j = 0; j < Constants.worldWidth; j++) {
                //Dont load worms
                if(c[j]=='1'||c[j]=='2'||c[j]=='3'||c[j]=='4'||c[j]=='5')
                    c[j]='0';
                updateGameworld(new Position(j,i),c[j]);
            }
        }
        }
    }

    /**
     * Removes a GameObject from the dynamic list
     * @param o is the GameObject being removed. 
     */
    public static void removeFromDynamicList(GameObject o)
    {
        dObjectList.remove(o);
    }

}
