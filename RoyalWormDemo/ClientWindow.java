import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.Arrays;


/**
This window works also as a controller for clients recieving and sending data
 */
public class ClientWindow extends Thread {
    private JFrame frame;
    int recievePort = 1234;
    DatagramSocket dSocket = new DatagramSocket(recievePort);
    byte[] data = new byte[4661];
    ClientCanvas clientCanvas;
    char[][] recievedWorld;
    KeyListener keyListener;
    InetAddress hostAddr;
    int portNr = 1233;

    public ClientWindow() throws Exception, InterruptedException {

        makeFrame();
        recievedWorld = new char[Constants.worldWidth][Constants.worldHeight];
        for (char[] c : recievedWorld) {
            Arrays.fill(c, '0');
        }
        System.out.println("Got this far");
        clientCanvas = new ClientCanvas(recievedWorld);
        frame.add(clientCanvas);
        clientCanvas.addKeyListener(keyListener = new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                //keyHandler(e.getKeyCode());
            }

            @Override
            public void keyPressed(KeyEvent e) {
                try {
                    keyHandler(e.getKeyCode());
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });
        clientCanvas.grabFocus();
        clientCanvas.repaint();
        //dSocket = new DatagramSocket();
       // hostAddr = InetAddress.getByName("127.0.0.1");
        hostAddr = InetAddress.getByName("192.168.43.88");
        recieveMessages();
    }

    private void makeFrame() throws IOException {
        frame = new JFrame("Client Window");
        frame.setPreferredSize(new Dimension(Constants.boardWidth,Constants.boardHeight));
        frame.setLocationRelativeTo(null);
        frame.pack();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setResizable(false);
    }

    public void recieveMessages() throws IOException, InterruptedException {
        while (true && !interrupted()) {
            DatagramPacket dp = new DatagramPacket(data,data.length);
        try{
            dSocket.receive(dp);
            String message = new String(dp.getData(),0,dp.getLength());

            stringToWorld(message);
           // clientCanvas.updateClientworld(GameEngine.GameWorld);
            clientCanvas.updateClientworld(recievedWorld);
            clientCanvas.repaint();
            //sleep(10);
            }
        catch (Exception e){e.printStackTrace();}
        }
    }
    private void stringToWorld(String s)
    {
        int i = 0;
        while (true) {
            recievedWorld[i] = s.substring(0, Constants.worldHeight).toCharArray();
            s = s.substring(Constants.worldHeight);
            i++;
            if (i > Constants.worldWidth - 1)
                break;
        }

    }
    private void keyHandler(int keycode) throws Exception {
        switch (keycode)
        {
            case KeyEvent.VK_UP:
                System.out.println("Up");
                NetworkController.sendDirectionData(new Direction(0,-1),dSocket,hostAddr,portNr);
                break;
            case KeyEvent.VK_DOWN:
                System.out.println("Down");
                NetworkController.sendDirectionData(new Direction(0,1),dSocket,hostAddr,portNr);
                break;
            case KeyEvent.VK_LEFT:
                System.out.println("Left");
                NetworkController.sendDirectionData(new Direction(-1,0),dSocket,hostAddr,portNr);
                break;
            case KeyEvent.VK_RIGHT:
                System.out.println("Right");
                NetworkController.sendDirectionData(new Direction(1,0),dSocket,hostAddr,portNr);
                break;
        }
    }

}
