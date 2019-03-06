public class GameObject {
    private Position position;
    private char type;

    public GameObject(Position position, char type) {
        this.position = position;
        this.type = type;
    }

    public Position getPosition() {
        return position;
    }
    
    public char getType() {
    	return type;
    }
}
