import java.awt.*;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Observable;
import java.util.Observer;
import java.lang.Thread;

public class Player {
    Worm worm;
    int lives;
    String name;

    //Network
    public boolean host;
    public InetAddress addr;
    public int port;

    public Player(String name, int pNumber,Position position, boolean host) throws InterruptedException, UnknownHostException {
        worm = new Worm(position, new Direction(0, -1), pNumber);
        this.name = name;
        lives = Constants.startingLives;
        this.host = host;
        this.addr = InetAddress.getByName("127.0.0.1");

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
