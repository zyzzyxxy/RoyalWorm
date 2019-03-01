import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
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
    Thread recieveThread, tempThread;
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
        //playerReciever = new NetworkReciever(1230);
        //playerReciever.addObserver(this::update);
        playerList.add(new Player("Host", 1, "127.0.0.1", true));

        showStartScreen();
        //showStartScreen();
        //while (startFrame.isEnabled()) { }
        //startGame();

    }

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
            System.out.println("NetworkUpdate from controller");
            System.out.println(arg);

            Direction tempDir = getDirFromString(arg.toString());
            String playerAdress = getAdressFromString(arg.toString());
            System.out.println(arg);
            System.out.println(playerAdress);
            //Todo fix this to be dynamic
            for (Player p : gameEngine.playerList) {
                try {
                    if (!p.host&&p.addr.equals(InetAddress.getByName(playerAdress))) {
                        p.updateDirection(tempDir);
                    }
                } catch (UnknownHostException e) {
                    e.printStackTrace();
                }
            }
        }

        //For startscreen
        else if (o instanceof NetworkReciever) {
            System.out.println("We have contact" + arg.toString());
            if (arg.toString().substring(0, 5).equals("addme")) {
                int i = 6;
                StringBuilder name = new StringBuilder();
                while (!arg.toString().substring(i, i + 1).equals("/")) {
                    name.append(arg.toString().substring(i, i + 1));
                    i++;
                }
                String addr = arg.toString().substring(i + 1);
                System.out.println(name + " : " + addr);
                boolean alreadyInList = false;
                for (Player p : playerList) {
                    try {
                        if (p.addr.equals(InetAddress.getByName(addr)))
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

    private void sendDataToPlayers() throws UnknownHostException {
        for (Player p : gameEngine.playerList) {
            if (!p.host) {
                NetworkController.sendWorldData(gameEngine.GameWorld, datagramSocket, p.addr, p.port);
            }
        }
    }

    //TODO
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
        textArea = new TextArea("No players connected");
        textArea.setColumns(15);
        textArea.setRows(20);
        startFrame.add(hostButton);
        startFrame.add(textField);
        startFrame.add(textArea);
        startFrame.pack();
        startFrame.setVisible(true);
        startFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        recieveThread = new Thread(nwReciever);
        recieveThread.start();

    }

    public void startGame() throws Exception {
        String[] players = {"Bob", "James"};//, "StephenHawkings", "Gulagubben"};
        this.gameEngine = new GameEngine(playerList);
        gameEngine.addObserver(this);


        started = true;
        gw = new GameWindow(gameEngine);
        gw.gameCanvas.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                hostButtonPressed(e);
                System.out.println("Key pressed" + e.getKeyCode());
            }
        });
        //  recieveThread = new Thread(nwReciever);
        // recieveThread.start();


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
