
import javax.swing.*;
import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.*;

public class GameEngine extends Observable {
	
    private GameObject[][] gameWorld;
    
    private BoostManager boosts;
    static List<Player> playerList = new ArrayList<>();
    private List<Boost> spawnList = new ArrayList<>();
    javax.swing.Timer gameTimer;
    static BoostManager boostManager = new BoostManager();
    private List<Change> changes = new ArrayList<>();//for sending changes for graphics
    int gameCOunter=0;

    //TODO this constructor shall take List<Player> when controller can provide it

    public GameEngine(List<Player> playersList) throws Exception {
        GameWorld = new GameObject[Constants.worldWidth][Constants.worldHeight];
        resetGameworld();
        playerList = playersList;
        makeSpawnList();

        gameTimer = new Timer(Constants.GAMESPEED, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    gameTick();
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
            }
        });
        //Starts the game
        gameTimer.start();
    }

    private void gameTick() throws InterruptedException {
        updateWorms();

        //TOdo fix this
        if((gameCOunter%Constants.GENERALSPAWNRATE)==0)
            updateBoosts();

        tellObservers();
        //changes.clear();
        gameCOunter++;
        if(gameCOunter==100)
            gameCOunter = 0;
    }

    //What boosts will be avaliable
    private void makeSpawnList()
    {
        spawnList.add(new Speed(Position.getRandomPosition()));
        spawnList.add(new Apple(Position.getRandomPosition()));
    }

    //Todo make use of spawnList
    private void updateBoosts() {
        for (Boost b:spawnList) {
            if(b.timeToSpawn()){
                boostManager.spawnRandom(b.type);
                b.resetCounter();
            }
            else
                b.incCounter();
        }

    }

    private void updateWorms() throws InterruptedException {
        for (Player p : playerList) {
            if (p.worm.counter == p.worm.speed) {
                p.worm.update();
                p.worm.counter = 1;
            } else {
                p.worm.counter++;
            }
        }
    }


    public void updateGameworld(Position pos, GameObject o) {
        GameWorld[pos.x][pos.y] = o;
        changes.add(new Change(pos.x,pos.y,o));
    }

    public void tellObservers() {
        setChanged();
        notifyObservers(changes);
        //changes.clear();
    }

    //TODO this does not reset worms
    public void resetGameworld() {
    	int y = 0;
        for (GameObject[] oa : GameWorld) {
        	int x = 0;
            for(GameObject o : oa) {
            	o = new EmptyObject(new Position(x, y));
            	x++;
            }
            y++;
        }
    }

    //TODO fix loading, now it loads wrong by 90 degrees
    public void loadGameworld(File file) throws FileNotFoundException {
        Scanner sc = new Scanner(file);

        int i = 0;
        for (GameObject[] o : GameWorld) {
            if (sc.hasNextLine()) {
                o = sc.nextLine().toCharArray();
                GameWorld[i++] = o;
            } else
                Arrays.fill(o, '0');
        }
    }
    
    public GameObject getFromGameWorld(int x, int y) {
    	return gameWorld[x][y];
    }
    
    public static List<Player> getPlayerList() {
    	return playerList;
    }
}
