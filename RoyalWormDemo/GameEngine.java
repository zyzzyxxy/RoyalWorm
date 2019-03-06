import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
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
    private boolean apples,lightning,gun,ghost, royal;

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

    private void gameTick() throws InterruptedException, FileNotFoundException {
        if(!gameOver) {
            updateWorms();

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
            loadGameworld(new File(current+"/RoyalWorm/gameOver.txt"));
            setChanged();
            tellObservers();
        }
    }

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

    //What boosts will be avaliable in Game
    private void battleRoyal() {
    	shrinkWalls();
    	System.out.print("shrink that shit");
	}

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

	//What boosts will be avaliable in Game
    private void makeSpawnList() {
        if(lightning)
        spawnList.add(new Boost(Position.getRandomPosition(), 'l', 50));
        if(apples)
        spawnList.add(new Boost(Position.getRandomPosition(), 'a', 15));
    }

    //This method updates boost and spawns them if time
    private void updateBoosts() {
        for (Boost b : spawnList) {
            if (b.timeToSpawn()) {
                boostManager.spawnRandom(b.getType());
                b.resetCounter();
            } else
                b.incCounter();
        }
        if(ghostCounter==Constants.ghostSpawn&&ghost) {
            dObjectList.add(new Ghost(Position.getRandomPosition()));
            ghostCounter=0;
        }
        else {ghostCounter++;}
    }

    //Update worms, move one step
    private void updateWorms() throws InterruptedException {
        for (Player p : playerList) {
        	p.getWorm().updater();
        }
    }

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

    public static void updateGameworld(Position pos, char c) {
        GameWorld[pos.getX()][pos.getY()] = c;
        changes.add(new Change(pos.getX(), pos.getY(), c));
    }

    public void tellObservers() {
        setChanged();
        notifyObservers(changes);
    }

    public void resetGameworld() {
        for (char[] c : GameWorld) {
            Arrays.fill(c, '0');
            for (Player p :playerList) {
                p.getWorm().reset();
            }
        }
    }

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

    public static void removeFromDynamicList(GameObject o)
    {
        dObjectList.remove(o);
    }

    //Just for testing
    public void printGameWorld() {
        System.out.println("Gameworld:");
        for (char[] c : GameWorld) {
            System.out.println(new String(c));
        }

        String result = "";
        for (char[] c : GameWorld) {
            result += new String(c);
        }
        byte[] data = result.getBytes();
        String str = new String(data);
        System.out.println("result");
        System.out.println(result);
        System.out.println("str");
        System.out.println(str);

        char[][] testWorld = new char[Constants.worldWidth][Constants.worldHeight];
        int i = 0;
        while (true) {
            testWorld[i] = str.substring(0, Constants.worldHeight).toCharArray();
            str = str.substring(Constants.worldWidth);
            i++;
            if (i > Constants.worldHeight - 1)
                break;
        }
        for (char[] c : testWorld) {
            System.out.println(new String(c));
        }

        System.out.println("Same?");
        System.out.println(Testing.mapsEqual(GameWorld, testWorld));
    }
}
