/**
 *Change class helps to keep track of changes made in the gameWorld, can be used to optimize graphics.
 *
 * @Param the position that has been changed, two ints
 * @Param the type that it has been changed into, char
 * @Return an instance of Change.
 * */

public class Change extends BoardCordinates {
    private char type;
    public Change(int x, int y, char type) {
        super(x, y);
        this.type = type;
    }
    
    public char getType() {
    	return type;
    }
}
