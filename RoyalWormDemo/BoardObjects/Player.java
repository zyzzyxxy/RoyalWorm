package BoardObjects;
/**
* Holds the information about one player. Including it´s adress which to send data to and it´s worm.
* This class is instantiated whenever we add a player to the game
* @author Johan Ericsson, Anton Eliasson Gustafsson
* @version 2019-03-07 
*/


import java.net.InetAddress;
import java.net.UnknownHostException;

import Canvas.PlayerPanel;
import Positions.Direction;
import Positions.Position;
import src.Constants;

public class Player {
    private Worm worm;
    private int lives;
    private String name;

    //Network
    public boolean host;
    public InetAddress addr;
    public int port;
    private PlayerPanel playerPanel;

    //Constructor
    public Player(String name, int pNumber, String addr, boolean host) throws InterruptedException, UnknownHostException {
        Position position = Position.getRandomPosition();
        Direction wormDir= Direction.getRandomDirection();
        worm = new Worm(position, wormDir, pNumber);
        this.name = name;
        lives = Constants.startingLives;
        this.host = host;
        this.addr = InetAddress.getByName(addr);
        this.playerPanel = new PlayerPanel(name);
    }

    /**
     *Updates players worm to a direction if necessary conditions is met. Worm cannot i.e change from right to left in an instant
     *
     * @param dir The direction to update the worm into.
     */
    public void updateDirection(Direction dir) {
        if (dir.getY() == 1 && worm.getDirection().getY() != -1) {
            worm.getNextDirection().setX(0);
            worm.getNextDirection().setY(1);
        }
        if (dir.getY() == -1 && worm.getDirection().getY() != 1) {
            worm.getNextDirection().setX(0);
            worm.getNextDirection().setY(-1);
        }
        if (dir.getX() == 1 && worm.getDirection().getX() != -1) {
            worm.getNextDirection().setX(1);
            worm.getNextDirection().setY(0);
        }
        if (dir.getX() == -1 && worm.getDirection().getX() != 1) {
            worm.getNextDirection().setX(-1);
            worm.getNextDirection().setY(0);
        }
    }

    /**
     * @return The players Worm.
     */
    public Worm getWorm() {
        return worm;
    }
    
    /**
     * @return Counter from the players Worm.
     */
    public int getWormCounter() {
        return worm.getCounter();
    }

    /**
     * Resets counter in the players Worm.
     */
    public void resetWormCounter() {
    	worm.resetCounter();
    }
    
    /**
     * Increments counter in the players Worm.
     */
    public void incWormCounter() {
    	worm.incWormCounter();
    }
    
    /**
     * @return The speed of the players worm.
     */
    public int getWormSpeed() {
        return worm.getSpeed();
    }

    /**
     * @return True if the player is host.
     */
    public boolean isHost() {
        return host;
    }

    /**
     * @return The players IP address.
     */
    public InetAddress getAddr() {
        return addr;
    }

    /**
     * @return The players port.
     */
    public int getPort() {
        return port;
    }
    
    /**
     * @return The amount of lives the players worm has left.
     */
    public int getWormLives() {
        return worm.getLives();
    }

    /**
     * @param addr The players IP address.
     */
    public void setInetAddr(String addr) throws UnknownHostException {
        this.addr = InetAddress.getByName(addr);
    }
    
    /**
     * @return The players name.
     */
    public String getName() {
    	return name;
    }

    /**
     * @return PlayerPanel.
     */
    public PlayerPanel getPlayerPanel() {
    	playerPanel.update(getWorm().getLives(), getWorm().getScore());
    	return playerPanel;
    }
}
