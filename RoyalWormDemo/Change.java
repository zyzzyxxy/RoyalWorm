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
