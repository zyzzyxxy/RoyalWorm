import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class NetworkController{

    public static void sendWorldData(char[][] map, DatagramSocket datagramSocket, InetAddress addr, int port) throws UnknownHostException {
        String result = "";
        for (char[] c:map ) {
            result+=new String(c);
        }
        byte[] data = result.getBytes();
        port = 1234;

        try {
            datagramSocket.send(new DatagramPacket(data,data.length,addr,port));
        }
        catch (Exception e){e.printStackTrace();}
    }

    public static void sendData(byte[] data, DatagramSocket datagramSocket, String addr, int port) throws UnknownHostException {
        InetAddress inetAddress = InetAddress.getByName(addr);
        port = 1230;

        try {
            datagramSocket.send(new DatagramPacket(data,data.length,inetAddress,port));
        }
        catch (Exception e){e.printStackTrace();}
    }

    public static void sendDirectionData(Direction dir, DatagramSocket datagramSocket, InetAddress addr, int port) throws Exception
    {
        String result = Integer.toString(dir.getX()) + Integer.toString(dir.getY());
        byte[] data = result.getBytes();
        port = 1230;

        try {
            datagramSocket.send(new DatagramPacket(data,data.length,addr,port));
        }
        catch (Exception e){e.printStackTrace();}
    }

}
