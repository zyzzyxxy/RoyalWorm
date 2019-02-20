import javax.swing.*;
import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.net.UnknownHostException;
import java.util.*;

public class GameEngine extends Observable /*implements Observer*/ {

    public static char[][] GameWorld;
    
    private BoostManager boosts;
    List<Player> playerList = new ArrayList<>();
    List<GameObject> gameObjectList = new ArrayList<>();
    javax.swing.Timer gameTimer;
    BoostManager boostManager = new BoostManager();
    private int appleCounter = 0; //For spawning apples
    public static List<Change> changes = new ArrayList<>();//for sending changes for graphics


    //Todo this constructor shall take List<Player> when controller can provide it

    public GameEngine(String[] players) throws Exception {
        GameWorld = new char[Constants.worldWidth][Constants.worldHeight];
        resetGameworld();
        addPlayers(players);
        //startGame();
        gameTimer = new Timer(5, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameTick();
            }
        });
        //Starts the game
        gameTimer.start();
    }

    private void gameTick() {
        updateWorms();
        updateBoosts();
        tellObservers();
        //changes.clear();
    }


    private void updateBoosts() {
        if (appleCounter == Constants.APPLESPAWN) {
            boostManager.spawnApple();
            appleCounter = 0;
        } else
            appleCounter++;

    }

    private void updateWorms() {
        for (Player p : playerList) {
            if (p.worm.counter == p.worm.speed) {
                p.worm.update();
                p.worm.counter = 0;
            } else {
                p.worm.counter++;
            }
        }
    }


    //Todo this method has to be rewritten when constructor gets List<Player>
    private void addPlayers(String[] players) throws InterruptedException, UnknownHostException {
        for (String s : players) {
            addPlayer(s);
        }
        for (Player p : playerList) {
            // p.worm.addObserver(this);
        }
    }

    private void addPlayer(String name) throws InterruptedException, UnknownHostException {
        int number = playerList.size() + 1;
        boolean host = false;
        Position position;
        if (number == 1) {
            host = true;
            position = new Position(Constants.worldWidth / 4, Constants.worldHeight / 4);
            playerList.add(new Player(name, number, position, true));
        } else if (number == 2) {
            position = new Position(Constants.worldWidth / 4, Constants.worldHeight * 3 / 4);
            playerList.add(new Player(name, number, position, false));
        } else if (number == 3) {
            position = new Position(Constants.worldWidth * 3 / 4, Constants.worldHeight / 4);
            playerList.add(new Player(name, number, position, host));
        } else if (number == 4) {
            position = new Position(Constants.worldWidth * 3 / 4, Constants.worldHeight * 3 / 4);
            playerList.add(new Player(name, number, position, host));
        }
    }

    /*
    private void update() {
        for (Player p : playerList) {
           Position[] pos=  p.worm.updateBody();
           GameWorld[pos[0].x][pos[0].y] = p.worm.type;
           try {
               GameWorld[pos[0].x][pos[0].y] = p.worm.type;
           }catch (NullPointerException e)
           {e.printStackTrace();}

        }
        setChanged();
        notifyObservers();
    }
    */

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
