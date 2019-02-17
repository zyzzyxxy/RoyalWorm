import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.Observable;
import java.util.Observer;

public class NetworkController{

    //Todo implement this

    public static void sendWorldData(char[][] map, DatagramSocket datagramSocket, InetAddress addr, int port) throws UnknownHostException {
        //char[][] to string to byte[]
        String result = "";
        for (char[] c:map ) {
            result+=new String(c);
        }
        byte[] data = result.getBytes();


        //hard coding
        InetAddress inetAddress = InetAddress.getByName("127.0.0.1");
        port = 1234;

        try {
            datagramSocket.send(new DatagramPacket(data,data.length,inetAddress,port));
        }
        catch (Exception e){e.printStackTrace();}


    }


    //Todo
    public static void sendDirectionData(){}
    public static Direction getDirectionData()
    {
        return null;
    }
    public static char[][] getWorldData()
    {
        return null;
    }


}
