import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.Observable;

public class NetworkReciever extends Observable implements Runnable{

    private DatagramSocket receiveSocket;
    private byte[] recieveData = new byte[1024];
    private int recievePort = 1233;

    public NetworkReciever(int port) throws SocketException {
        recievePort = port;
        receiveSocket = new DatagramSocket(recievePort);
    }

    public Direction recieveData() throws IOException {
            System.out.println("In network reciever");
                DatagramPacket dp = new DatagramPacket(recieveData, recieveData.length);

                receiveSocket.receive(dp);
                String message = new String(dp.getData(), 0, dp.getLength());
                //System.out.println(message);

                dp.getAddress();
                setChanged();
                notifyObservers(message+dp.getAddress());

        return null;
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
