import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Main {

	public static void main(String[] args) throws IOException, InterruptedException {
		//TODO everything
		String[] players =  {"Bob", "James", "StephenHawkings", "Gulagubben"};
		//String[] players =  {"Bob"};
		GameEngine gm = new GameEngine(players);
		//gm.printGameWorld();
		GameWindow gw = new GameWindow(gm);
		gm.resetGameworld();
		gm.playerList.get(0).worm.addToSpeed(5);
		//gw.loadFile(new File("/Users/johanericsson/Documents/GitHub/RoyalWorm/RoyalWorm/Maps/testMap2"));
	}

}
