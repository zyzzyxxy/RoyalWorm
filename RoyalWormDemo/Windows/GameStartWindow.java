package Windows;
/**
 * Starts the program and lets a user choose between Host or Client -mode
 * @author Anton Eliasson Gustafsson
 * @author Johan Ericsson
 * @version 2019-03-10
 */

import javax.swing.*;

import GameHandling.Controller;
import Network.NetworkController;

import java.net.DatagramSocket;

public class GameStartWindow {
	
	private static JFrame startFrame;
	private static boolean host = false;
	private static boolean done = false;
	private static String hostAdress;
	private static Controller gameController;
	private static ClientWindow cl;
	private static DatagramSocket sendSocket;
	private static StartScreen sc;
	
	/**
	 * Instantiates a GameStartWindow. Calls to the method showStartScreen. 
	 * @throws Exception Exception
	 */
	public GameStartWindow() throws Exception {
		showStartScreen();
	}
	
	/**
	 * This method is used for a player to chose to play as host or client. See more comments inside method. 
	 * @throws Exception
	 */
	private static void showStartScreen() throws Exception {
		sendSocket = new DatagramSocket();
		startFrame = new JFrame("Start Screen");
		JButton hostButton = new JButton("Host");
		JButton clientButton = new JButton("Client");
		hostButton.addActionListener(e -> {
			try {
				buttonClicked(e.getActionCommand());
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		});
		clientButton.addActionListener(e -> {
			try {
				buttonClicked(e.getActionCommand());
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		});
		sc = new StartScreen(hostButton, clientButton);
		startFrame.setResizable(false);
		startFrame.add(sc);
		startFrame.pack();
		startFrame.setVisible(true);
		startFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	/**
	 * This method takes a button (Host or Client) input from showStartScreen.
	 * @param actionCommand is the pressed button. 
	 * @throws Exception Exception
	 */
	public static void buttonClicked(String actionCommand) throws Exception {
		//If a player choosees to play as host, a Controller is instantiated. 
		if (actionCommand.equals("Host")) {
			host = true;
			startFrame.dispose();
			done=true;
			gameController = new Controller();
		}
		
		if (actionCommand.equals("Client")) {
			//If a player chooses to play as a client -> send data to a host so that the host knows a player has joined 
			//and creates a ClientWindow. 
			hostAdress = JOptionPane.showInputDialog("write host's adress");
			host = false;
			startFrame.dispose();
			done=true;
			String name = sc.connectToHostTextfield.getText();
			byte[] data = ("addme:" + name).getBytes();
			NetworkController.sendData(data,sendSocket, hostAdress);
			cl = new ClientWindow(hostAdress);
		}
	}
}
