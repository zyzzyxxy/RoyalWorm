import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Window;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class Screen{
	
	private String type;
	private boolean host;
	private Window startFrame;
	private boolean done;
	private Controller gameController;
	private String hostAdress;
	private ClientWindow cl;
	
	public Screen(String type) {
		this.type = type;
		System.out.println("new Screen");
	}
	
	public void makeWindow() throws UnknownHostException {
		switch (type) {
			case "startScreen":
				makeStartScreen();
				break;
		}
		
	}

	private void makeStartScreen() throws UnknownHostException {
		//JFrame startFrame;
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
		StartScreen sc = new StartScreen(hostButton, clientButton);
		((Frame) startFrame).setResizable(false);
		startFrame.add(sc);
		startFrame.pack();
		startFrame.setVisible(true);
		((JFrame) startFrame).setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		System.out.println("Screen should be set");
	}
	private void buttonClicked(String actionCommand) {
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
			cl = new ClientWindow(hostAdress);
		}
		
	}

	}

