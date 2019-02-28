import java.net.UnknownHostException;

public class Main {
	static GameEngine game;
	
	public static void main(String[] args) throws UnknownHostException {
		game = new GameEngine();
		game.runGame();

	}

}
