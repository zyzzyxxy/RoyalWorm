import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.Arrays;

public class ClientWindow extends Thread {
    private JFrame frame;
    int recievePort = 1234;
    DatagramSocket dSocket = new DatagramSocket(recievePort);
    byte[] data = new byte[4661];
    ClientCanvas clientCanvas;
    char[][] recievedWorld;

    public ClientWindow() throws IOException {

        makeFrame();
        recievedWorld = new char[Constants.worldWidth][Constants.worldHeight];
        for (char[] c : recievedWorld) {
            Arrays.fill(c, '0');
        }
        System.out.println("Got this far");
        clientCanvas = new ClientCanvas(recievedWorld);
        frame.add(clientCanvas);
        recieveMessages();
    }

    private void makeFrame() throws IOException {
        frame = new JFrame("Client Window");
        frame.setPreferredSize(new Dimension(Constants.boardWidth,Constants.boardHeight));
        frame.setLocationRelativeTo(null);
        frame.pack();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    private void recieveMessages() throws IOException {
        while (true && !interrupted()) {
            DatagramPacket dp = new DatagramPacket(data,data.length);
            dSocket.receive(dp);
            String message = new String(dp.getData(),0,dp.getLength());

            stringToWorld(message);
            clientCanvas.updateClientworld(GameEngine.GameWorld);
            clientCanvas.updateClientworld(recievedWorld);
            clientCanvas.repaint();
        }
    }
    private void stringToWorld(String s)
    {
        int i = 0;
        while (true) {
            recievedWorld[i] = s.substring(0, Constants.worldWidth).toCharArray();
            s = s.substring(Constants.worldHeight);
            i++;
            if (i > Constants.worldHeight - 1)
                break;
        }

    }

}
