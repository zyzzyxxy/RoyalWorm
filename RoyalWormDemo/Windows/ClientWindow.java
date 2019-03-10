package Windows;
/**
 * @author Johan Ericsson & Jonathan Uhre
 * @version 2019-03-10
 *
 * The game window for the client.
 * It also contains some networking related to receiving and sending data.
 */

import javax.swing.*;

import Canvas.ClientCanvas;
import Network.NetworkController;
import Positions.Change;
import Positions.Direction;
import src.Constants;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ClientWindow{
    private JFrame frame;
    private int recievePort = 1234;
    private DatagramSocket dSocket = new DatagramSocket(recievePort);
    private byte[] data = new byte[4800];
    private ClientCanvas clientCanvas;
    private char[][] receivedWorld;
    private InetAddress hostAddr;
    private Thread rThread;

    public static List<Change> changes = new ArrayList<>();
    
    /**
     * Constructor for ClientWindow. Initiates the JFrame and networking for receiving and sending data.
     * 
     * @param hostAddress The IP address of the host computer.
     */
    public ClientWindow(String hostAddress) throws Exception {
        hostAddr = InetAddress.getByName(hostAddress);
        makeFrame();
        receivedWorld = new char[Constants.worldWidth][Constants.worldHeight];
        for (char[] c : receivedWorld) {
            Arrays.fill(c, '0');
        }
        System.out.println("Got this far");
        clientCanvas = new ClientCanvas(receivedWorld);
        frame.add(clientCanvas);
        clientCanvas.addKeyListener(new KeyListener() {
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
                    receiveMessages();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        rThread.start();
    }

    /**
     * Initiates the JFrame.
     */
    private void makeFrame() throws IOException {
        frame = new JFrame("Client Window");
        frame.setPreferredSize(new Dimension(Constants.boardWidth+20,Constants.boardHeight+40)); //clientWindow isn't an exact copy of gameWindow (A bit smaller). Thus the addition to dimensions.
        frame.setLocationRelativeTo(null);
        frame.pack();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setResizable(false);
    }

    /**
     * Network receiver used to receive gameWorld from host.
     */
    public void receiveMessages() throws IOException, InterruptedException {
        while (true) {
            DatagramPacket dp = new DatagramPacket(data,data.length);
        try{
            dSocket.receive(dp);

            String message = new String(dp.getData(),0,dp.getLength());

            stringToWorld(message);
            clientCanvas.updateClientWorld(receivedWorld);
            clientCanvas.repaint();
            }
        catch (Exception e){e.printStackTrace();}
        }
    }
    
    /**
     * Converts string to receiverWorld.
     * 
     * @param s String containing a gameWorld.
     */
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
    
    /**
     * Sends a Direction to the host according to the key pressed.
     */
    private void keyHandler(int keycode) throws Exception {
        switch (keycode)
        {
            case KeyEvent.VK_UP:
                NetworkController.sendDirectionData(new Direction(0,-1),dSocket,hostAddr);
                break;
            case KeyEvent.VK_DOWN:
                NetworkController.sendDirectionData(new Direction(0,1),dSocket,hostAddr);
                break;
            case KeyEvent.VK_LEFT:
                NetworkController.sendDirectionData(new Direction(-1,0),dSocket,hostAddr);
                break;
            case KeyEvent.VK_RIGHT:
                NetworkController.sendDirectionData(new Direction(1,0),dSocket,hostAddr);
                break;
        }
    }
}
