package GameHandling;
import javax.swing.*;

import BoardObjects.Player;
import Network.NetworkController;
import Network.NetworkReciever;
import Positions.Direction;
import Windows.GameWindow;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

/**
 * This class controls everything from the Host side. It also contains the GameEngine responsible for creating and running the game.
 * @author Anton Eliasson Gustafsson
 * @author Johan Ericsson
 * @version 2019-03-09
 */
public class Controller implements Observer {
    private GameEngine gameEngine;
    private DatagramSocket datagramSocket, recieveSocket;
    private GameWindow gw;
    private JFrame startFrame;
    private boolean host = false;
    private byte[] recieveData = new byte[1024];
    private Thread recieveThread;
    private NetworkReciever nwReciever;
    private List<Player> playerList = new ArrayList<>();
    private Container playerContainer = new Container();
    private TextArea textArea;
    private Checkbox royal, speed, apples,guns, ghost;
    private boolean started = false;
    private boolean royalB, speedB, applesB,gunsB, ghostB;

    /**
     * Constructor for Controller.
     * 
     * @throws Exception Exception
     */
    public Controller() throws Exception {

        nwReciever = new NetworkReciever(1230);
        nwReciever.addObserver(this::update);
        playerList.add(new Player("Host", 1, "127.0.0.1", true));
        
        showStartScreen();
        
    }

    /**
     * Manages the necessary data updates that takes place when new data is presented from GameEngine or from another player
     *
     * When GameEngine has been updated this method sends the updates to all players
     *
     * When the Controller recieves data from another player this method checks for the content and takes the action Nessecery
     * (either adding a player if game not running else updates the direction of the worm of the player whom sent the data)
     *
     * @param o The observed object
     * @param arg The argument this object gives to it´s observers
     */
    @Override
    public void update(Observable o, Object arg) {
        //For sending data to clients
        if (o instanceof GameEngine) {
            try {
                sendDataToPlayers();
            } catch (UnknownHostException e) {
                e.printStackTrace();
            }
        }

        //for getting directions
        else if (o instanceof NetworkReciever && started) {
            Direction tempDir = getDirFromString(arg.toString());
            String playerAdress = getAdressFromString(arg.toString());
            for (Player p : gameEngine.playerList) {
                try {
                    if (!p.isHost()&&p.getAddr().equals(InetAddress.getByName(playerAdress))) {
                        p.updateDirection(tempDir);
                    }
                } catch (UnknownHostException e) {
                    e.printStackTrace();
                }
            }
        }

        //For startscreen
        else if (o instanceof NetworkReciever) {
            if (arg.toString().substring(0, 5).equals("addme")) {
                int i = 6;
                StringBuilder name = new StringBuilder();
                while (!arg.toString().substring(i, i + 1).equals("/")) {
                    name.append(arg.toString().substring(i, i + 1));
                    i++;
                }
                String addr = arg.toString().substring(i + 1);
                boolean alreadyInList = false;
                for (Player p : playerList) {
                    try {
                        if (p.getAddr().equals(InetAddress.getByName(addr)))
                            alreadyInList = true;
                    } catch (UnknownHostException e) {
                        e.printStackTrace();
                    }
                }
                if (!alreadyInList) {
                    try {
                        playerList.add(new Player(name.toString(), playerList.size() + 1, addr, false));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (UnknownHostException e) {
                        e.printStackTrace();
                    }
                    textArea.setText(textArea.getText() + "\n" + name + "  Connected");
                }
            }

        } else {
            System.out.println(o.toString());
        }
    }

    /**
     * This method is used when adding players to extract the IP address.
     * 
     * @param str A String containing an IP address
     * @return The extracted IP address.
     */
    private String getAdressFromString(String str) {
        String result = "";
        int i = 0;
        boolean done = false;
        while (!done) {
            if (str.substring(i, i + 1).equals("/")) {
                result = str.substring(i + 1);
                done = true;
            }
            i++;
        }
        return result;
    }

    /**
     * This method is used when getting a direction from another player.
     * 
     * @param arg A String containing a direction.
     * @return The entered String converted to a Direction.
     */
    private Direction getDirFromString(String arg) {
        int x, y;
        if (arg.substring(0, 1).equals("0")) {
            x = 0;
            if (arg.substring(1, 2).equals("1")) {
                y = 1;
            } else //must be negative
                y = -1;
        } else {
            y = 0;
            if (arg.substring(0, 1).equals("1"))
                x = 1;
            else
                x = -1;
        }
        return new Direction(x, y);
    }

    /**
     * Sends data to all players connected as clients
     */
    private void sendDataToPlayers() throws UnknownHostException {
        for (Player p : gameEngine.playerList) {
            if (!p.isHost()) {
                NetworkController.sendWorldData(gameEngine.GameWorld, datagramSocket, p.getAddr());
            }
        }
    }

    /**
     * Shows the setupscreen before game begins
     */
    private void showStartScreen() throws Exception {
        startFrame = new JFrame("HOST-Screen");
        startFrame.getContentPane().setLayout(new FlowLayout());
        startFrame.setPreferredSize(new Dimension(600,400));
        startFrame.setResizable(false);
        startFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        JButton hostButton = new JButton("START");

        royal = new Checkbox("RoyalMode");
        speed = new Checkbox("Speedboosts");
        apples = new Checkbox("Apples");
        guns = new Checkbox("Guns");
        ghost = new Checkbox("Ghost");
        apples.setState(true);

        playerContainer.setPreferredSize(new Dimension(50, 300));

        this.datagramSocket = new DatagramSocket();
        this.recieveSocket = new DatagramSocket();

        hostButton.addActionListener(e -> {
            try {
                buttonClicked(e.getActionCommand());
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        });


        TextField textField = new TextField(InetAddress.getLocalHost().toString());
        textArea = new TextArea();
        textArea.setColumns(15);
        textArea.setRows(20);
        startFrame.add(hostButton);
        startFrame.add(textField);
        startFrame.add(textArea);
        startFrame.add(royal);
        startFrame.add(speed);
        startFrame.add(apples);
        startFrame.add(guns);
        startFrame.add(ghost);
        startFrame.pack();
        startFrame.setVisible(true);
        startFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        recieveThread = new Thread(nwReciever);
        recieveThread.start();

    }

    /**
     * Starts the game
     * 
     * @throws Exception Exception
     */
    public void startGame() throws Exception {
        boolean[] gameMode = {royalB, speedB,applesB,gunsB};
        this.gameEngine = new GameEngine(playerList,royalB,applesB,speedB,gunsB,ghostB);
        gameEngine.addObserver(this);

        started = true;
        gw = new GameWindow(gameEngine);
        gw.gameCanvas.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                hostKeyPressed(e);
            }
        });
    }

    /**
     * Checks the game modes and starts the game
     * 
     * @param actionCommand String on button.
     * @throws Exception Exception
     */
    public void buttonClicked(String actionCommand) throws Exception {
        if (actionCommand.equals("START")) {
            host = true;

            checkGameMode();

            startGame();
            startFrame.dispose();
        }
        if (actionCommand.equals("Client")) {

        }
    }

    /**
     * Checks what game modes will be activated in the game
     */
    private void checkGameMode()
    {
        if(royal.getState()==true)
            royalB=true;
        else
            royalB=false;
        if(speed.getState()==true)
            speedB=true;
        else
            speedB=false;
        if(apples.getState()==true)
            applesB=true;
        else
            applesB=false;
        if(guns.getState()==true)
            gunsB=true;
        else
            gunsB=false;
        if(ghost.getState()==true)
            ghostB=true;
        else
            ghostB=false;
    }

    /**
     * For controlling the host's worm
     */
    private void hostKeyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_DOWN && gameEngine.playerList.get(0).getWorm().getDirection().getY() != -1) {
            gameEngine.playerList.get(0).getWorm().getNextDirection().setX(0);
            gameEngine.playerList.get(0).getWorm().getNextDirection().setY(1);
        }
        if (e.getKeyCode() == KeyEvent.VK_UP && gameEngine.playerList.get(0).getWorm().getDirection().getY() != 1) {
            gameEngine.playerList.get(0).getWorm().getNextDirection().setX(0);
            gameEngine.playerList.get(0).getWorm().getNextDirection().setY(-1);
        }
        if (e.getKeyCode() == KeyEvent.VK_LEFT && gameEngine.playerList.get(0).getWorm().getDirection().getX() != 1) {
            gameEngine.playerList.get(0).getWorm().getNextDirection().setX(-1);
            gameEngine.playerList.get(0).getWorm().getNextDirection().setY(0);
        }
        if (e.getKeyCode() == KeyEvent.VK_RIGHT && gameEngine.playerList.get(0).getWorm().getDirection().getX() != -1) {
            gameEngine.playerList.get(0).getWorm().getNextDirection().setX(1);
            gameEngine.playerList.get(0).getWorm().getNextDirection().setY(0);
        }
        if(e.getKeyCode() == KeyEvent.VK_SPACE && gameEngine.playerList.get(0).getWorm().hasGun()) {
        	gameEngine.playerList.get(0).getWorm().fireGun();
        }
    }
}
