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

    public Controller(GameEngine gameEngine) throws SocketException {
        this.gameEngine = gameEngine;
        this.datagramSocket = new DatagramSocket();
        gameEngine.addObserver(this);
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
}
