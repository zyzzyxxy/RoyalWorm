import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Arrays;


/**
This window works also as a controller for clients recieving and sending data
 */
public class ClientWindow{
    private JFrame frame;
    int recievePort = 1234;
    DatagramSocket dSocket = new DatagramSocket(recievePort);
    byte[] data = new byte[4900];
    ClientCanvas clientCanvas;
    char[][] receivedWorld;
    KeyListener keyListener;
    InetAddress hostAddr;
    int portNr = 1233;
    Thread rThread;

    public ClientWindow(String host) throws Exception {
        //Jonathans gamla
        hostAddr = InetAddress.getByName(host);
        makeFrame();
        receivedWorld = new char[Constants.worldWidth][Constants.worldHeight];
        for (char[] c : receivedWorld) {
            Arrays.fill(c, '0');
        }
        System.out.println("Got this far");
        clientCanvas = new ClientCanvas(receivedWorld);
        frame.add(clientCanvas);
        clientCanvas.addKeyListener(keyListener = new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                
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
        rThread=new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    recieveMessages();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        rThread.start();

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
        while (true) {
            DatagramPacket dp = new DatagramPacket(data,data.length);
        try{
            dSocket.receive(dp);

            String message = new String(dp.getData(),0,dp.getLength());

            stringToWorld(message);
            clientCanvas.updateClientworld(receivedWorld);
            clientCanvas.repaint();
            }
        catch (Exception e){e.printStackTrace();}
        }
    }
    private void stringToWorld(String s)
    {
        int i = 0;
        while (true) {
            receivedWorld[i] = s.substring(0, Constants.worldHeight).toCharArray();
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
