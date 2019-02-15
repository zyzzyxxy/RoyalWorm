import java.awt.*;
import java.util.Observable;
import java.util.Observer;
import java.lang.Thread;

public class Player {
    Worm worm;
    int lives;
    String name;
    boolean host;

    public Player(String name, int pNumber,Position position, boolean host) throws InterruptedException {
        worm = new Worm(position, new Direction(0, -1), pNumber);
        this.name = name;
        lives = Constants.startingLives;
        this.host = host;

        Thread t1 = new Thread(worm);
        t1.start();
    }

    public void update()
    {
        worm.update();
    }

    public Worm getWorm() {
        return worm;
    }

}
