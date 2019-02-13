import java.awt.*;

public class Player {
    Worm worm;
    int lives;
    String name;

    public Player(String name, Color color, Position position)
    {
        worm = new Worm(position, new Direction(0,-1),color);
        this.name = name;
        lives = Constants.startingLives;
        Thread t1 = new Thread(worm);
        t1.start();

    }

    public Worm getWorm()
    {
        return worm;
    }
}
