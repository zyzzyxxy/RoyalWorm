package Positions;
/**
 * This class gives us a tuple of ints that can be used for positioning and direction".
 * @author Anton Eliasson Gustafsson
 * @author Martin Hagentoft
 * @author Johan Ericsson
 * @version 2019-03-01
 * */

public class BoardCordinates {
    private int x, y;
	/**
	 * Instantiates BoardCordinates
	 * @param x-coordinate 
	 * @param y-coordinate
	 */
	    public BoardCordinates(int x, int y) {
	        this.x = x;
	        this.y = y;
	    }

	/**
	 * 
	 * @return x position
	 */
	    public int getX() {
	        return x;
	    }

	/**
	 * 
	 * @param x sets the x-position
	 */
	public void setX(int x) {
	    this.x = x;
	}
	
	/**
	 * 
	 * @return y-coordinate
	 */
	public int getY() {
	    return y;
	}
	
	/**
	 * Sets y-coordinate
	 * @param y  y-coordinate
	 */
	public void setY(int y) {
	    this.y = y;
	}
}
