package Positions;
/**
 *Change class helps to keep track of changes made in the gameWorld, can be used to optimize graphics.
 * @author Johan Ericsson
 * @version 2019-03-01
 * */

public class Change extends BoardCordinates {
    private char type;
    /**
     * @param x position for the position being changed.
     * @param y position for the position being changed.
     * @param type, as in what game object is changed. 
     */
    public Change(int x, int y, char type) {
        super(x, y);
        this.type = type;
    }
    /**
     * 
     * @return type being changed
     */
    public char getType() {
    	return type;
    }
}
