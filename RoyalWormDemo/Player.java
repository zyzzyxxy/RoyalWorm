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
    Thread wormThread;

    //Network
    public boolean host;
    public InetAddress addr;
    public int port;


    public Player(String name, int pNumber, String addr, boolean host) throws InterruptedException, UnknownHostException {

        //Todo fix this to update worms in right places
        Position position = new Position(2, 3);


        worm = new Worm(position, new Direction(0, -1), pNumber);
        this.name = name;
        lives = Constants.startingLives;
        this.host = host;
        this.addr = InetAddress.getByName(addr);

    }

    public void updateDirection(Direction dir) {
        if (dir.y == 1 && worm.direction.y != -1) {
            worm.direction.x = 0;
            worm.direction.y = 1;
        }
        if (dir.y == -1 && worm.direction.y != 1) {
            worm.direction.x = 0;
            worm.direction.y = -1;
        }
        if (dir.x == 1 && worm.direction.x != -1) {
            worm.direction.x = 1;
            worm.direction.y = 0;
        }
        if (dir.x == -1 && worm.direction.x != 1) {
            worm.direction.x = -1;
            worm.direction.y = 0;
        }
    }

    public void setInetAddr(String addr) throws UnknownHostException {
        this.addr = InetAddress.getByName(addr);
    }


}
