import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Main {

	Player[] playerList;

	public static void main(String[] args) throws Exception {
		//TODO everything
		JFrame startFrame = new JFrame("Setup game");
		startFrame.add(new StartScreen());
		startFrame.setDefaultCloseOperation(3);
		startFrame.setVisible(true);
		startGame();

	}
	private static void startGame() throws Exception {
		String[] players =  {"Bob", "James"};//, "StephenHawkings", "Gulagubben"};
		GameEngine gm = new GameEngine(players);
		gm.printGameWorld();
		GameWindow gw = new GameWindow(gm);
		Controller controller = new Controller(gm);
		ClientWindow clWindow = new ClientWindow();
		gm.resetGameworld();
	}

}
