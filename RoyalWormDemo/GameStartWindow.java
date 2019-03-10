/**
 * Starts the program and let´s user choose between Host or Client -mode
 */

import javax.swing.*;
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
	
	public GameStartWindow() throws Exception {
		showStartScreen();
	}
	
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
	
	public static void buttonClicked(String actionCommand) throws Exception {

		if (actionCommand.equals("Host")) {
			System.out.println("HostButtonClicked");
			host = true;
			startFrame.dispose();
			done=true;
			gameController = new Controller();
		}

		if (actionCommand.equals("Client")) {
			System.out.println("ClientButtonClicked");
			hostAdress = JOptionPane.showInputDialog("write host's adress");
			host = false;//test
			startFrame.dispose();
			done=true;
			String name = sc.connectToHostTextfield.getText();
			byte[] data = ("addme:" + name).getBytes();
			NetworkController.sendData(data,sendSocket, hostAdress);
			cl = new ClientWindow(hostAdress);
		}
	}
}
