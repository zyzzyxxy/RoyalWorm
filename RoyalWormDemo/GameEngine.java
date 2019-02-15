import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class GameEngine extends Observable {
    public static char[][] GameWorld;
    List<Player> playerList = new ArrayList<>();
    List<GameObject> gameObjectList = new ArrayList<>();

    public GameEngine() throws FileNotFoundException {
        GameWorld = new char[Constants.worldHeight][Constants.worldWidth];
    }

    private void update() {
        setChanged();
        notifyObservers();
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
            if(sc.hasNextLine()) {
                c=sc.nextLine().toCharArray();
                System.out.println(new String(c));
                GameWorld[i++] = c;
            }
            else
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

}
