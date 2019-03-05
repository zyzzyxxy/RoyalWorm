import java.net.InetAddress;
import java.net.UnknownHostException;
import java.lang.Thread;

public class Player {
    Worm worm;
    int lives;
    String name;


    //Network
    private boolean host;
    private InetAddress addr;
    private int port;
    PlayerPanel playerPanel;


    public Player(String name, int pNumber, String addr, boolean host) throws InterruptedException, UnknownHostException {

        //Todo fix this to update worms in right places
        Position position = Position.getRandomPosition();
        Direction wormDir= Direction.getRandomDirection();
        worm = new Worm(position, wormDir, pNumber);
        this.name = name;
        lives = Constants.startingLives;
        this.host = host;
        this.addr = InetAddress.getByName(addr);
        this.playerPanel = new PlayerPanel(name);

    }

    public void updateDirection(Direction dir) {
        if (dir.y == 1 && worm.getDirection().y != -1) {
            worm.setNextDirection(new Direction(0,1));
        }
        if (dir.y == -1 && worm.getDirection().y != 1) {
            worm.setNextDirection(new Direction(0,-1));
        }
        if (dir.x == 1 && worm.getDirection().x != -1) {
            worm.setNextDirection(new Direction(1,0));
        }
        if (dir.x == -1 && worm.getDirection().x != 1) {
            worm.setNextDirection(new Direction(-1,0));
        }
    }


    public Worm getWorm() {
        return worm;
    }
    public int getWormCounter() {
        return worm.getCounter();
    }
    public void resetWormCounter() { worm.resetCounter(); }
    public void incWormCounter() { worm.incWormCounter(); }
    public int getWormSpeed() {
        return worm.getSpeed();
    }

    public boolean isHost() {
        return host;
    }

    public InetAddress getAddr() {
        return addr;
    }

    public int getPort() {
        return port;
    }
    public int getWormLives() {
        return worm.getLives();
    }



    public void setInetAddr(String addr) throws UnknownHostException {
        this.addr = InetAddress.getByName(addr);
    }


}
