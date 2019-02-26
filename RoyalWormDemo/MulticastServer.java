import java.net.*;
import java.io.*;

public class MulticastServer {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		new MulticastServerThread().start();
		
	}

}
