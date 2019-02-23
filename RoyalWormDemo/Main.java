import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {

	static JFrame startFrame;
	static boolean host = false;
	static boolean done = false;
	static String hostAdress;
	static Controller gameController;
	static ClientWindow cl;

	public static void main(String[] args) throws Exception {
		//TODO everything
		showStartScreen();
		while(!done) {
			//System.out.println("not done");
		}
		System.out.println("hajj");
		if(host)
		{
			gameController = new Controller();
			//gameController.startGame();
		}
		else
		{
			cl = new ClientWindow(hostAdress);
		}

	}


	private static void showStartScreen() throws Exception {
		startFrame = new JFrame("Start Screen");
		JButton hostButton = new JButton("Host");
		JButton clientButton = new JButton("Client");
		StartScreen sc = new StartScreen(hostButton, clientButton);
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

		startFrame.add(sc);
		startFrame.pack();
		startFrame.setVisible(true);
		startFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

	}
	public static void buttonClicked(String actionCommand) throws Exception {
		if (actionCommand.equals("Host")) {
			System.out.println("HostButtonClicked");
			host = true;
			startFrame.dispose();
			done=true;
		}
		if (actionCommand.equals("Client")) {
			System.out.println("ClientButtonClicked");
			hostAdress = JOptionPane.showInputDialog("write host's adress");
			host = false;//test
			startFrame.dispose();
			done=true;

		}
	}

	private static void startGame() throws Exception {


	}

}
