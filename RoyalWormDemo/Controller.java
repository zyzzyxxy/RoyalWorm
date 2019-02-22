import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

//TODO Clarify how this will work

/**
 * This class controls everything
 */
public class Controller implements Observer {
    GameEngine gameEngine;
    DatagramSocket datagramSocket, recieveSocket;
    GameWindow gw;
    ClientWindow clWindow;
    JFrame startFrame;
    boolean host = false;
    byte[] recieveData = new byte[1024];
    Thread recieveThread;
    NetworkReciever nwReciever;
    NetworkReciever playerReciever;
    String hostAdress;
    List<Player> playerList = new ArrayList<>();
    Container playerContainer = new Container();
    TextArea textArea;
    boolean started = false;


    //Constructor
    public Controller() throws Exception {

        nwReciever = new NetworkReciever(1230);
        nwReciever.addObserver(this::update);
        playerReciever = new NetworkReciever(1231);
        playerReciever.addObserver(this::update);

        showStartScreen();
        //showStartScreen();
        //while (startFrame.isEnabled()) { }
        //startGame();

    }

    @Override
    public void update(Observable o, Object arg) {
        //System.out.println(o.toString());
        if(o instanceof GameEngine) {
            try {
                sendDataToPlayers();
                //recieveDataFromPlayers();
            } catch (UnknownHostException e) {
                e.printStackTrace();
            }
        }
        else if(o instanceof NetworkReciever && o.equals(nwReciever))
        {
            System.out.println("NetworkUpdate from controller");
            System.out.println(arg);

            Direction tempDir = getDirFromString(arg.toString());
            //Todo fix this to be dynamic
            for (Player p : gameEngine.playerList) {
                if (!p.host) {
                    p.worm.direction = tempDir;
                }
            }
        }
    }

    private Direction getDirFromString(String arg)
    {
        int x,y;
        if(arg.substring(0,1).equals("0")) {
            x = 0;
            if(arg.substring(1,2).equals("1")){
                y=1;
            }
            else //must be negative
                y=-1;
            }
        else {
            y=0;
            if (arg.substring(0,1).equals("1"))
                x = 1;
            else
                x=-1;
            }

        return new Direction(x,y);
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
            System.out.println("asddd");
            if (!p.host) {
                DatagramPacket dp = new DatagramPacket(recieveData, recieveData.length);
                try {
                    recieveSocket.receive(dp);
                    String message = new String(dp.getData(), 0, dp.getLength());
                    System.out.println(message);


                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void showStartScreen() throws Exception {
        startFrame = new JFrame("HOST-Screen");
        startFrame.getContentPane().setLayout(new FlowLayout());
        startFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        JButton hostButton = new JButton("START");
        playerContainer.setPreferredSize(new Dimension(50,300));
        hostButton.addActionListener(e -> {
            try {
                buttonClicked(e.getActionCommand());
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        });

        textArea = new TextArea("No players connected");
        textArea.setColumns(15);
        textArea.setRows(20);
        startFrame.add(hostButton);
        startFrame.add(textArea);
        startFrame.pack();
        startFrame.setVisible(true);
        startFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

    }

    public void startGame() throws Exception {
        String[] players = {"Bob", "James"};//, "StephenHawkings", "Gulagubben"};
        this.gameEngine = new GameEngine(players);
        gameEngine.addObserver(this);
        this.datagramSocket = new DatagramSocket();
        this.recieveSocket = new DatagramSocket();

        started = true;
        gw = new GameWindow(gameEngine);
        gw.gameCanvas.addKeyListener(new KeyAdapter() {
                @Override
                public void keyPressed(KeyEvent e) {
                    hostButtonPressed(e);
                    System.out.println("Key pressed" + e.getKeyCode());
                }
            });
            recieveThread = new Thread(nwReciever);
            recieveThread.start();


    }

    public void buttonClicked(String actionCommand) throws Exception {
        if (actionCommand.equals("START")) {
            System.out.println("HostButtonClicked");
            host = true;
            startGame();
            startFrame.dispose();
        }
        if (actionCommand.equals("Client")) {

        }
    }

    private void hostButtonPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_DOWN && gameEngine.playerList.get(0).worm.direction.y != -1) {
            gameEngine.playerList.get(0).worm.direction.x = 0;
            gameEngine.playerList.get(0).worm.direction.y = 1;
        }
        if (e.getKeyCode() == KeyEvent.VK_UP && gameEngine.playerList.get(0).worm.direction.y != 1) {
            gameEngine.playerList.get(0).worm.direction.x = 0;
            gameEngine.playerList.get(0).worm.direction.y = -1;
        }
        if (e.getKeyCode() == KeyEvent.VK_LEFT && gameEngine.playerList.get(0).worm.direction.x != 1) {
            gameEngine.playerList.get(0).worm.direction.x = -1;
            gameEngine.playerList.get(0).worm.direction.y = 0;
        }
        if (e.getKeyCode() == KeyEvent.VK_RIGHT && gameEngine.playerList.get(0).worm.direction.x != -1) {
            gameEngine.playerList.get(0).worm.direction.x = 1;
            gameEngine.playerList.get(0).worm.direction.y = 0;
        }
    }


}
