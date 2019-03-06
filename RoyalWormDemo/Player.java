import java.net.InetAddress;
import java.net.UnknownHostException;
import java.lang.Thread;

public class Player {
    private Worm worm;
    private int lives;
    private String name;

    //Network
    public boolean host;
    public InetAddress addr;
    public int port;
    private PlayerPanel playerPanel;

    public Player(String name, int pNumber, String addr, boolean host) throws InterruptedException, UnknownHostException {
        //TODO fix this to update worms in right places
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
        if (dir.getY() == 1 && worm.getDirection().getY() != -1) {
            worm.getNextDirection().setX(0);
            worm.getNextDirection().setY(1);
        }
        if (dir.getY() == -1 && worm.getDirection().y != 1) {
            worm.getNextDirection().setX(0);
            worm.getNextDirection().setY(-1);
        }
        if (dir.getX() == 1 && worm.getDirection().x != -1) {
            worm.getNextDirection().setX(1);
            worm.getNextDirection().setY(0);
        }
        if (dir.getX() == -1 && worm.getDirection().getX() != 1) {
            worm.getNextDirection().setX(-1);
            worm.getNextDirection().setY(0);
        }
    }

    public void setInetAddr(String addr) throws UnknownHostException {
        this.addr = InetAddress.getByName(addr);
    }
    
    public String getName() {
    	return name;
    }
    
    public Worm getWorm() {
    	return worm;
    }
    
    public PlayerPanel getPlayerPanel() {
    	return playerPanel;
    }
}
