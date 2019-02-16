import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Main {

	public static void main(String[] args) throws Exception {
		//TODO everything
		String[] players =  {"Bob", "James"};//, "StephenHawkings", "Gulagubben"};
		GameEngine gm = new GameEngine(players);
		GameWindow gw = new GameWindow(gm);
		Controller controller = new Controller(gm);
		ClientWindow clWindow = new ClientWindow();
		gm.resetGameworld();

	}

}
