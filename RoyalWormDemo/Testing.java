/**
 * This class conatins methods that can be used to test stuff in the program.
 *
 * It has been used as a convinient way of adding methods for testing diffeent things, like if a map still is the same after
 * sending it over the network and so on.
 */

public class Testing {

    public static boolean mapsEqual(char[][] w1, char[][] w2) {
        boolean eq = w1.equals(w2);
        return eq;
    }

    public static void printWorld(char[][] w1) {
        int zeros = 0;
        for (char[] c : w1) {
            System.out.println(zeros);
            for (char d : c){
                if (d == '0')
                    zeros++;
                else
                    System.out.println(new String(c));}
        }
    }

    public static byte[][] toBytes(char[][] data) {
        byte[][] toRet = new byte[data.length][data[0].length];
        for(int i = 0; i < toRet.length; i++) {
            for(int j = 0; j < toRet[0].length; j++) {

                 toRet[i][j] = (byte) data[i][j];
            }
        }
        return toRet;
    }

    public static char[][] toChars(byte[][] data) {
        char[][] toRet = new char[data.length][data[0].length];
        for(int i = 0; i < toRet.length; i++) {
            for(int j = 0; j < toRet[0].length; j++) {

                toRet[i][j] = (char) data[i][j];
            }
        }
        return toRet;
    }

    public static byte[][] charArrayToByteArray(char[][] c_array) {
        byte[][] b_array = new byte[c_array.length][c_array[0].length];
        for(int i= 0; i < c_array.length; i++)
            for(int j= 0; j < c_array[0].length; j++)
                b_array[i][j] = (byte)(0xFF & (int)c_array[i][j]);

        return b_array;
    }
}