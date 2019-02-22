import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.Observable;

public class NetworkReciever extends Observable{

    DatagramSocket receiveSocket;

    public NetworkReciever()
    {

    }
    /*
    public Direction recieveData()
    {
            System.out.println("In network reciever");
                DatagramPacket dp = new DatagramPacket(recieveData, recieveData.length);
                try {
                    recieveSocket.receive(dp);
                    String message = new String(dp.getData(), 0, dp.getLength());
                    System.out.println(message);


                } catch (IOException e) {
                    e.printStackTrace();
                }

        return null;
    }*/
}
