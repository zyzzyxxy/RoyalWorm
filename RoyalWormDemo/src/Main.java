package src;

import Windows.GameStartWindow;

/**
 * Starts the program. Calls to a window where a player choose to play as host or client. 
 * @author Anton Eliasson Gustafsson
 * @author Johan Ericsson
 * @version 2019-03-10
 */

public class Main {
	private static GameStartWindow startWindow;
	public static void main(String [ ] args) throws Exception {

	startWindow = new GameStartWindow();
	}
}
