
import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class GameEngine extends Observable {

    public static char[][] GameWorld;

    List<Player> playerList = new ArrayList<>();
    List<Boost> spawnList = new ArrayList<>();
    javax.swing.Timer gameTimer;
    BoostManager boostManager = new BoostManager();
    public static List<Change> changes = new ArrayList<>();//for sending changes for graphics
    int gameCOunter = 0;

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
        if ((gameCOunter % Constants.GENERALSPAWNRATE) == 0)
            updateBoosts();

        tellObservers();
        //changes.clear();
        gameCOunter++;
        if (gameCOunter == 100)
            gameCOunter = 0;
    }

    //What boosts will be avaliable in Game
    private void makeSpawnList() {
        spawnList.add(new Boost(Position.getRandomPosition(), 'l', 10));
        spawnList.add(new Boost(Position.getRandomPosition(), 'a', 20));
    }

    //Todo make use of spawnList
    private void updateBoosts() {
        for (Boost b : spawnList) {
            if (b.timeToSpawn()) {
                boostManager.spawnRandom(b.type);
                b.resetCounter();
            } else
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
        changes.add(new Change(pos.x, pos.y, c));
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

        for (int i = 0; i < Constants.worldHeight; i++) {
            if (sc.hasNextLine()) {
                char[] c = sc.nextLine().toCharArray();
            for (int j = 0; j < Constants.worldWidth; j++) {
                //Dont load worms
                if(c[i]=='1'||c[i]=='2'||c[i]=='3'||c[i]=='4'||c[i]=='5')
                    c[j]='0';
                GameWorld[j][i] = c[j];
            }
        }
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

}
