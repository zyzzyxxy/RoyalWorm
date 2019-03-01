public class GameObject {
    private Position position;
    private char objChar;
    
    public GameObject(Position position, char objChar) {
        this.position = position;
        this.objChar = objChar;
    }

    public Position getPosition() {
        return position;
    }
    
    public char getChar() {
		return objChar;
	}
}
