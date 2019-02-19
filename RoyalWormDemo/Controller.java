import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.net.DatagramSocket;
import java.net.UnknownHostException;
import java.util.Observable;
import java.util.Observer;

//TODO Clarify how this will work

/**
 * This class controls everything
 */
public class Controller implements Observer {
    GameEngine gameEngine;
    DatagramSocket datagramSocket;
    GameWindow gw;
    ClientWindow clWindow;
    JFrame startFrame;
    boolean host = false;

    public Controller() throws Exception {

        showStartScreen();
        //showStartScreen();
        //while (startFrame.isEnabled()) { }
        //startGame();

    }

    @Override
    public void update(Observable o, Object arg) {
        try {
            sendDataToPlayers();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

    private void sendDataToPlayers() throws UnknownHostException {
        for (Player p : gameEngine.playerList) {
            if (!p.host) {
                NetworkController.sendWorldData(gameEngine.GameWorld, datagramSocket, p.addr, p.port);
            }
        }

    }

    //Todo
    private void recieveDataFromPlayers() throws UnknownHostException {
        for (Player p : gameEngine.playerList) {
            if (!p.host) {

            }
        }
    }

    private void showStartScreen() throws Exception {
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

    public void startGame() throws Exception {
        String[] players = {"Bob", "James"};//, "StephenHawkings", "Gulagubben"};
        this.gameEngine = new GameEngine(players);
        gameEngine.addObserver(this);
        this.datagramSocket = new DatagramSocket();
        if (host) {
            gw = new GameWindow(gameEngine);
            gw.gameCanvas.addKeyListener(new KeyAdapter() {
                @Override
                public void keyPressed(KeyEvent e) {
                    hostButtonPressed(e);
                    System.out.println("Key pressed" + e.getKeyCode());
                }
            });
        } else
            clWindow = new ClientWindow();

        System.out.println("going here");
        gameEngine.resetGameworld();
    }

    public void buttonClicked(String actionCommand) throws Exception {
        if (actionCommand.equals("Host")) {
            System.out.println("HostButtonClicked");
            host = true;
            startGame();
            startFrame.dispose();
        }
        if (actionCommand.equals("Client")) {
            System.out.println("ClientButtonClicked");
            String hostAdress = JOptionPane.showInputDialog("write host's adress");
            host = false;
        }
    }

    private void hostButtonPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            gameEngine.playerList.get(0).worm.direction.x = 0;
            gameEngine.playerList.get(0).worm.direction.y = 1;
        }
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            gameEngine.playerList.get(0).worm.direction.x = 0;
            gameEngine.playerList.get(0).worm.direction.y = -1;
        }
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            gameEngine.playerList.get(0).worm.direction.x = -1;
            gameEngine.playerList.get(0).worm.direction.y = 0;
        }
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            gameEngine.playerList.get(0).worm.direction.x = 1;
            gameEngine.playerList.get(0).worm.direction.y = 0;
        }
    }

    public void makeStartScreen() {

    }


}
