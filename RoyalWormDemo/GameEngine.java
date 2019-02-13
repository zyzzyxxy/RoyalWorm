import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Observable;

public class GameEngine extends Observable {
    public static char[][] GameWorld;
    List<Player> playerList = new ArrayList<>();
    List<GameObject> gameObjectList = new ArrayList<>();

    public GameEngine()
    {
        GameWorld = new char[Constants.boardWidth][Constants.boardHeight];
        fillGameworld();

    }

    private void update()
    {
        setChanged();
        notifyObservers();
    }

    private void fillGameworld()
    {
        for (char[] c:GameWorld) {
            Arrays.fill(c,'0');
        }
    }

}
