package Windows;
/**
 * A container filled with  host and clientbuttons and a textfield for writing clientName.
 * @author Johan Ericsson
 * @version 2019-03-01
 */

import javax.swing.*;

import src.Constants;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class StartScreen extends Container {

    public JTextField infoTextfield = new JTextField();
    public JTextField connectToHostTextfield = new JTextField("Write your name here");
    public JButton hostButton;
    public JButton clientButton;

    /**
     * Constructor
     *
     * @param hostButton    the Button to use
     * @param clientButton  the Button to use
     * @throws UnknownHostException UnknownHostException
     */
    public StartScreen(JButton hostButton, JButton clientButton) throws UnknownHostException {
        this.hostButton = hostButton;
        this.clientButton = clientButton;
        makeStartScreen();

    }

    /**
     * Populates the  screen
     *
     * @throws UnknownHostException
     */
    private void makeStartScreen() throws UnknownHostException {
        this.setPreferredSize(new Dimension(350,250));
        this.setLocation(0,0);

        setLayout(new BorderLayout());
        Container middleContainer = new Container();
        Container rightContainer = new Container();

        middleContainer.setLayout(new GridLayout(4, 4));

        rightContainer.setLayout(new FlowLayout());
        rightContainer.setPreferredSize(new Dimension(100, Constants.boardHeight));

        Container clientConnectInfo = new Container();
        clientConnectInfo.setLayout(new FlowLayout());

        infoTextfield.setText(InetAddress.getLocalHost().toString());

        add(infoTextfield, BorderLayout.NORTH);
        add(middleContainer, BorderLayout.CENTER);

        middleContainer.add(hostButton);
        middleContainer.add(clientButton);

        connectToHostTextfield.setColumns(50);
        connectToHostTextfield.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                connectToHostTextfield.setText("");
            }
        });
        middleContainer.add(connectToHostTextfield);
    }
}
