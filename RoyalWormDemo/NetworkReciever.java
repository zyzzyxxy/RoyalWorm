/**
 * @author Johan Ericsson
 * @Version 2019-03-06
 *
 * A class for that can be instantiated and run in a thread. Recieves data and
 * notifies it´s observers sending the data as an argument.
 */


import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.Observable;

public class NetworkReciever extends Observable implements Runnable{

    private DatagramSocket receiveSocket;
    private byte[] recieveData = new byte[1024];
    private int recievePort;

    /**
     * Constructor
     *
     * @param port what port to use
     * @throws SocketException
     */
    public NetworkReciever(int port) throws SocketException {
        recievePort = port;
        receiveSocket = new DatagramSocket(recievePort);
    }

    /**
     * Recieves the data and notifies it´s observers
     *
     * @throws IOException
     */
    public void recieveData() throws IOException {
            System.out.println("In network reciever");
                DatagramPacket dp = new DatagramPacket(recieveData, recieveData.length);

                receiveSocket.receive(dp);
                String message = new String(dp.getData(), 0, dp.getLength());
                dp.getAddress();
                setChanged();
                notifyObservers(message+dp.getAddress());
    }

    @Override
    public void run() {
        while (true)
        {
            try {
                recieveData();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
