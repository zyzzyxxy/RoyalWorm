import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Observable;
import java.util.Observer;

//TODO Clarify how this will work

public class Controller implements Observer {
    GameEngine gameEngine;
    DatagramSocket datagramSocket;
    GameWindow gw;
    ClientWindow clWindow;
    JFrame startFrame;

    public Controller() throws Exception {

        showStartScreen();
        //while (startFrame.isEnabled()) { }
        startGame();

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
        for (Player p : gameEngine.playerList)
        {
            if(!p.host)
            {
                NetworkController.sendWorldData(gameEngine.GameWorld,datagramSocket,p.addr,p.port);
            }
        }

    }

    private void showStartScreen() throws Exception {
        startFrame = new JFrame("Start Screen");
        StartScreen sc = new StartScreen();
        startFrame.add(sc);
        startFrame.pack();
        startFrame.setVisible(true);
        startFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //while (sc.active){}
        //startFrame.enable(false);
    }

    public void startGame() throws Exception {
        String[] players =  {"Bob", "James"};//, "StephenHawkings", "Gulagubben"};
        this.gameEngine = new GameEngine(players);
        gameEngine.addObserver(this);
        this.datagramSocket = new DatagramSocket();
        gw = new GameWindow(gameEngine);
        clWindow = new ClientWindow();

        System.out.println("going here");
        gameEngine.resetGameworld();
    }
}
