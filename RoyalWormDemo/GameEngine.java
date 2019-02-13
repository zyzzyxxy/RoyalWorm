import javax.swing.*;
import java.util.Observable;

public class GameEngine extends Observable {
    public static char[][] GameWorld;

    public GameEngine()
    {
        GameWorld = new char[Constants.boardWidth][Constants.boardHeight];
    }

    private void update()
    {
        setChanged();
        notifyObservers();
    }

}
