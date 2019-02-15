import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Arrays;

public class NetworkController {

    //Todo implement this

    public static void sendWorldData(char[][] map, DatagramSocket datagramSocket, String addr, int port) throws UnknownHostException {
        //char[][] to string to byte[]
        String result = "";
        for (char[] c:map ) {
            result+=new String(c);
        }
        byte[] data = result.getBytes();

        InetAddress inetAddress = InetAddress.getByName(addr);
        try {

            datagramSocket.send(new DatagramPacket(data,data.length,inetAddress,port));
        }
        catch (Exception e){e.printStackTrace();}


    }
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
