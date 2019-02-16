import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.Timer;

public class GameEngine extends Observable implements Observer{
    public static char[][] GameWorld;
    List<Player> playerList = new ArrayList<>();
    List<GameObject> gameObjectList = new ArrayList<>();
    Timer gameTimer;

    public GameEngine(String[] players) throws FileNotFoundException, InterruptedException {
        GameWorld = new char[Constants.worldWidth][Constants.worldHeight];
        addPlayers(players);
    }

    private void addPlayers(String[] players) throws InterruptedException {
        for (String s : players) {
            addPlayer(s);
        }
        for (Player p:playerList) {
            p.worm.addObserver(this);
        }
    }

    private void addPlayer(String name) throws InterruptedException {
        int number = playerList.size()+1;
        boolean host = false;
        Position position;
        if (number == 1) {
            host = true;
            position = new Position(Constants.worldWidth / 4, Constants.worldHeight / 4);
            playerList.add(new Player(name, number, position, host));
        }
        else if (number == 2) {
            position = new Position(Constants.worldWidth / 4, Constants.worldHeight * 3 / 4);
            playerList.add(new Player(name, number, position, host));
        }
        else if (number == 3) {
            position = new Position(Constants.worldWidth * 3 / 4, Constants.worldHeight / 4);
            playerList.add(new Player(name, number, position, host));
        }
        else if (number == 4) {
            position = new Position(Constants.worldWidth * 3 / 4, Constants.worldHeight * 3 / 4);
            playerList.add(new Player(name, number, position, host));
        }
    }

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

    public static void updateGameworld(Position pos, char c) {
        GameWorld[pos.y][pos.x] = c;
    }


    public void resetGameworld() {
        for (char[] c : GameWorld) {
            Arrays.fill(c, '0');
        }
    }

    public void loadGameworld(File file) throws FileNotFoundException {
        Scanner sc = new Scanner(file);

        int i = 0;
        for (char[] c : GameWorld) {
            if (sc.hasNextLine()) {
                c = sc.nextLine().toCharArray();
                //System.out.println(new String(c));
                GameWorld[i++] = c;
            } else
                Arrays.fill(c, '0');
        }
    }


    //Just for testing
    public void printGameWorld() {
        for (char[] c : GameWorld) {
            System.out.println(new String(c));
        }
       /* String result = "";
        for (char[] c : GameWorld) {
            result += new String(c);
        }
        byte[] data = result.getBytes();
        String str = new String(data);
        System.out.println(result);
        System.out.println(str);

        char[][] testWorld = new char[Constants.worldWidth][Constants.worldHeight];
        int i = 0;
        while (true) {
            testWorld[i] = str.substring(0, Constants.worldWidth).toCharArray();
            str = str.substring(Constants.worldWidth);
            i++;
            if (i > Constants.worldHeight - 1)
                break;
        }
        for (char[] c : testWorld) {
            System.out.println(new String(c));
        }
*/

    }

    @Override
    public synchronized void update(Observable o, Object arg) {
        System.out.println("In update");
        Position[] changed = (Position[])arg;
        System.out.println(changed[0].x);
        System.out.println(changed[0].y);
        GameWorld[changed[0].x][changed[0].y] = ((Worm) o).type;
        System.out.println(GameWorld[changed[0].x][changed[0].y]);
        try {
            GameWorld[changed[1].x][changed[1].y] = '0';
        }
        catch (Exception e){
            System.out.println("no tail yet");
        }

        setChanged();
        notifyObservers();
        System.out.println(changed[0].toString());
        printGameWorld();
    }
}
