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
        InetAddress inetAddress = InetAddress.getByName("192.168.0.136");
        port = 1234;

        try {
            datagramSocket.send(new DatagramPacket(data,data.length,inetAddress,port));
        }
        catch (Exception e){e.printStackTrace();}
    }


    //Todo
    public static void sendDirectionData(Direction dir,DatagramSocket datagramSocket, InetAddress addr, int port) throws Exception
    {
        String result = Integer.toString(dir.x) + Integer.toString(dir.y);
        byte[] data = result.getBytes();


        //hard coding
        InetAddress inetAddress = InetAddress.getByName("192.168.43.88");
        port = 1230;

        try {
            datagramSocket.send(new DatagramPacket(data,data.length,inetAddress,port));
        }
        catch (Exception e){e.printStackTrace();}
    }
    public static Direction getDirectionData()
    {
        return null;
    }
    public static char[][] getWorldData()
    {
        return null;
    }


}
