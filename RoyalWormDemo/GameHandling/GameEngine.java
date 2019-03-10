package GameHandling;
/**
 * @author Johan Ericsson
 * @author Anton Eliasson Gustafsson
 * @author Jonathan Uhre
 * @Version 2019-03-09
 *
 *This class takes care of the logics of the game.
 *
 * Its observable and can notify changes to i.e. a gui or a network controller.
 * It has a timer that ticks, dictating the speed of the game
 *
 * @Param Player list of the players that should be included
 * @Param booleans for every game mode available.
 * @Return an instance of GameEngine that runs.
 * */

import javax.swing.Timer;

import BoardObjects.Boost;
import BoardObjects.BoostManager;
import BoardObjects.Bullet;
import BoardObjects.DynamicObject;
import BoardObjects.GameObject;
import BoardObjects.Ghost;
import BoardObjects.Player;
import Positions.Change;
import Positions.Position;
import src.Constants;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

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
    private boolean apples,lightning,gun,ghost, royal, multiPlayer;
    
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
        if(playersList.size()>1)
            multiPlayer=true;
        else
            multiPlayer=false;

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
    
    /**
     * This method is called every time the GameTimer ticks, takes care of updating the game components.
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
            resetGameworld();
            makeGameOverMap();
            setChanged();
            tellObservers();
            gameTimer.stop();
        }
    }

    /**
     * Starts the timer
     */
    public void startTimer()
    {
        if(!gameTimer.isRunning())
        gameTimer.start();
    }

    /**
     * Makes a gameOver Screen
     */
    public void makeGameOverMap()
    {
        int[] xCordinates = {19, 19, 19, 19, 19, 20, 20, 20, 20, 20, 20, 20, 21, 21, 21, 21, 21, 21, 21, 22, 22, 22, 22, 22, 22, 22, 22, 22, 23, 23, 23, 23, 23, 24, 24, 24, 24, 24, 25, 25, 25, 25, 25, 25, 25, 25, 26, 26, 26, 26, 26, 26, 27, 27, 27, 27, 27, 27, 27, 28, 28, 28, 28, 28, 29, 30, 30, 30, 31, 31, 31, 31, 32, 32, 32, 32, 32, 33, 33, 33, 33, 33, 34, 34, 34, 34, 34, 35, 35, 35, 35, 35, 35, 35, 36, 36, 36, 36, 36, 36, 37, 37, 38, 38, 39, 39, 39, 39, 39, 39, 40, 40, 40, 40, 40, 41, 42, 42, 42, 42, 43, 43, 43, 43, 43, 43, 44, 44, 44, 44, 44, 44, 44, 44, 45, 45, 45, 45, 45, 45, 45, 46, 46, 46, 46, 47, 47, 47, 47, 48, 48, 48, 48, 48, 49, 49, 49, 49, 49, 49, 49, 49, 50, 50, 50, 50, 52, 52, 52, 52, 53, 53, 53, 53, 53, 53, 53, 53, 54, 54, 54, 54, 54, 54, 55, 55, 55, 55, 56, 56, 56, 56, 57, 57, 57, 57, 57, 58, 58, 58, 58, 59, 59, 59, 59, 60, 60, 60};
        int[] yCordinates = {9, 10, 11, 12, 13, 8, 9, 10, 11, 12, 13, 14, 8, 14, 18, 19, 20, 21, 22, 8, 14, 17, 18, 19, 20, 21, 22, 23, 8, 11, 14, 17, 23, 8, 11, 14, 17, 23, 8, 9, 11, 12, 13, 14, 17, 23, 9, 11, 12, 13, 17, 23, 17, 18, 19, 20, 21, 22, 23, 18, 19, 20, 21, 22, 13, 12, 13, 14, 10, 12, 14, 19, 10, 12, 14, 19, 20, 10, 12, 14, 20, 21, 10, 12, 14, 21, 22, 10, 11, 12, 13, 14, 22, 23, 11, 12, 13, 14, 21, 22, 20, 21, 19, 20, 10, 11, 12, 13, 14, 19, 10, 11, 12, 13, 14, 10, 10, 20, 21, 22, 10, 19, 20, 21, 22, 23, 10, 11, 12, 13, 14, 19, 21, 23, 11, 12, 13, 14, 19, 21, 23, 10, 19, 21, 23, 10, 19, 21, 23, 10, 19, 20, 21, 23, 10, 11, 12, 13, 14, 20, 21, 23, 11, 12, 13, 14, 20, 21, 22, 23, 11, 12, 13, 19, 20, 21, 22, 23, 10, 11, 12, 13, 14, 19, 10, 12, 14, 19, 10, 12, 14, 19, 10, 12, 14, 19, 20, 10, 12, 14, 20, 10, 11, 12, 14, 11, 12, 14};
        for(int i = 0; i<xCordinates.length;i++)
        {
            updateGameworld(new Position(xCordinates[i],yCordinates[i]),'w');
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
        if(multiPlayer&&playersAlive<=1)
        {
            gameOver=true;
        }
        else if(playersAlive<=0)
        {
            gameOver=true;
        }
    }

    public char[][] getGameWorld() {
        return GameWorld;
    }

    /**
     * Calls to two other methods which spawn guns and shrink walls.
     */
    private void battleRoyal() {
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
            if (dObjectList.get(i).getCounter() ==dObjectList.get(i).getSpeed()) {
                dObjectList.get(i).update();
                dObjectList.get(i).setCounter(0);
            } else {
                
            	dObjectList.get(i).setCounter(dObjectList.get(i).getCounter() +1);
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
    
    /**
     * Notifies observers.
     */
    public void tellObservers() {
        setChanged();
        notifyObservers(changes);
    }

    /**
     * Empties the world and reset worms.
     */
    public void resetGameworld() {
        for (int i = 0; i < Constants.worldWidth; i++) {
                for (int j = 0; j < Constants.worldHeight; j++) {
                    updateGameworld(new Position(i,j),'0');
                }
            }

            for (Player p :playerList) {
                p.getWorm().reset();
            }

    }
    public void resetWorms() {
        for (Player p:playerList) {
            p.resetWorm();
        }
    }

    /**
     * Resets the whole game.
     */
    public void resetGame() {
        resetGameworld();
        resetWorms();
        gameOver=false;
        dObjectList.clear();
        shrinkCOunter=0;
    }

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
                //Dont load worms and dynamics
                if(c[j]=='1'||c[j]=='2'||c[j]=='3'||c[j]=='4'||c[j]=='5'||c[j]=='b'||c[j]=='g')
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
