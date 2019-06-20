package Network;
/**
* Contains static methods for sending data
*@author Johan Ericsson
*@version 2019-03-06 
*/

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.UnknownHostException;

import Positions.Direction;

public class NetworkController{

    /**
     * Converts a char[][] to a byte array and sends the information to the specified address
     * @param map - the gameWorld to be sent as a char[][]
     * @param datagramSocket the DatagramSocket to use
     * @param addr the InetAdress which the data should be sent to
	 * @throws UnknownHostException UnknownHostException
	 */
    public static void sendWorldData(char[][] map, DatagramSocket datagramSocket, InetAddress addr) throws UnknownHostException {
        String result = "";
        for (char[] c:map ) {
            result+=new String(c);
        }
        byte[] data = result.getBytes();
        int port = 1234;

        try {
            datagramSocket.send(new DatagramPacket(data,data.length,addr,port));
        }
        catch (Exception e){e.printStackTrace();}
    }

    /**
     * Sends the data given to the specified address
     *
     * @param data - the data to be sent
     * @param datagramSocket the DatagramSocket to use
     * @param addr the InetAdress which the data should be sent to
     * @throws UnknownHostException UnknownHostException
     */
    public static void sendData(byte[] data, DatagramSocket datagramSocket, String addr) throws UnknownHostException {
        InetAddress inetAddress = InetAddress.getByName(addr);
        int port = 1230;

        try {
            datagramSocket.send(new DatagramPacket(data,data.length,inetAddress,port));
        }
        catch (Exception e){e.printStackTrace();}
    }
    
    /**
     * Sends the direction as a byte[] to the specified address
     *
     * @param dir - the data to be sent
     * @param datagramSocket the DatagramSocket to use
     * @param addr the InetAdress which the data should be sent to
     * @throws Exception Exception
     */
    public static void sendDirectionData(Direction dir, DatagramSocket datagramSocket, InetAddress addr) throws Exception
    {
        String result = Integer.toString(dir.getX()) + Integer.toString(dir.getY());
        byte[] data = result.getBytes();
        int port = 1230;

        try {
            datagramSocket.send(new DatagramPacket(data,data.length,addr,port));
        }
        catch (Exception e){e.printStackTrace();}
    }

}
