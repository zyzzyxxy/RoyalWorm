import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.Observable;

public class NetworkReciever extends Observable implements Runnable{

    DatagramSocket receiveSocket;
    byte[] recieveData = new byte[1024];
    int recievePort = 1233;

    public NetworkReciever() throws SocketException {
        receiveSocket = new DatagramSocket(recievePort);
    }

    public Direction recieveData()
    {
            System.out.println("In network reciever");
                DatagramPacket dp = new DatagramPacket(recieveData, recieveData.length);
                try {
                    receiveSocket.receive(dp);
                    String message = new String(dp.getData(), 0, dp.getLength());
                    System.out.println(message);
                    setChanged();
                    notifyObservers(message);

                } catch (IOException e) {
                    e.printStackTrace();
                }

        return null;
    }

    @Override
    public void run() {
        while (true)
        {
            recieveData();
        }
    }
}
