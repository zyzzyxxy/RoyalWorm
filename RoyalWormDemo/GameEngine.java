
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

    public static char[][] GameWorld;

    List<Player> playerList = new ArrayList<>();
    List<Boost> spawnList = new ArrayList<>();
    javax.swing.Timer gameTimer;
    BoostManager boostManager = new BoostManager();
    public static List<Change> changes = new ArrayList<>();//for sending changes for graphics
    int gameCOunter=0;

    //Todo this constructor shall take List<Player> when controller can provide it

    public GameEngine(List<Player> playersList) throws Exception {
        GameWorld = new char[Constants.worldWidth][Constants.worldHeight];
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
        spawnList.add(new Boost(Position.getRandomPosition(),'l',50));
        spawnList.add(new Boost(Position.getRandomPosition(),'a',20));
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
                p.worm.counter = 0;
            } else {
                p.worm.counter++;
            }
        }
    }


    public static void updateGameworld(Position pos, char c) {
        GameWorld[pos.x][pos.y] = c;
        changes.add(new Change(pos.x,pos.y,c));
    }

    public void tellObservers() {
        setChanged();
        notifyObservers(changes);
        changes.clear();
    }

    //Todo this does not reset worms
    public void resetGameworld() {
        for (char[] c : GameWorld) {
            Arrays.fill(c, '0');
        }
    }

    //Todo fix loading, now it loads wrong by 90 degrees
    public void loadGameworld(File file) throws FileNotFoundException {
        Scanner sc = new Scanner(file);

        int i = 0;
        for (char[] c : GameWorld) {
            if (sc.hasNextLine()) {
                c = sc.nextLine().toCharArray();
                GameWorld[i++] = c;
            } else
                Arrays.fill(c, '0');
        }
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

    /*
    @Override
    public synchronized void update(Observable o, Object arg) {
        Position[] changed = (Position[])arg;
        GameWorld[changed[0].x][changed[0].y] = ((Worm) o).type;
        try {
            GameWorld[changed[1].x][changed[1].y] = '0';
        }
        catch (Exception e){
            System.out.println("no tail yet");
        }

        setChanged();
        notifyObservers();
    }
    */
}
