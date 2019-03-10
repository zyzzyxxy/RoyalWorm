package BoardObjects;

import Positions.Position;

/**
 * A GameObject is an object with a position and a type. 
 * A type defines what kind of GameOBject it is. For instance 'a' is an apple and 'b' is a bullet. These chars are to be placed in 
 *  a two dimensional array, gameWorld (see Class GameEngine) that makes up the entire game world.  
 *  @author Anton Eliasson Gustafsson & Martin Hagentoft
 *  @version 2019-03-01
 */
public class GameObject {
    private Position position;
    private char type;
/**
 * Instantiates a GameObject.
 * @param position for a GameObject.
 * @param type for a GameObject.
 */
    public GameObject(Position position, char type) {
        this.position = position;
        this.type = type;
    }
    /**
     * 
     * @return a GameObject's position. 
     */ 

    public Position getPosition() {
        return position;
    }
    /**
     * 
     * @return the type of GameObject.
     */
    
    public char getType() {
    	return type;
    }
}
