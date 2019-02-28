import java.net.UnknownHostException;

public class GameEngine {
	
	public GameEngine() throws UnknownHostException{
		//runGame();
	}
	
	void runGame() throws UnknownHostException {
		startWindow();
		
	}

	public void startWindow() throws UnknownHostException {
		Screen startScreen = new Screen("startScreen");
		startScreen.makeWindow();
	}
	
}

	
