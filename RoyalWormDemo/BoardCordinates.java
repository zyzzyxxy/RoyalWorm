/**
 *This class gives us a tuple of ints that can be used for positioning and direction".
 *
 * @Param two ints, x,y
 * @Return an instance of BoardCordinates.
 * */

public class BoardCordinates {
    private int x, y;

    public BoardCordinates(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }
    
    public int getY() {
        return y;
    }
    
    public void setY(int y) {
        this.y = y;
    }
}
