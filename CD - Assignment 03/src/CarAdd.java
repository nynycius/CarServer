import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.util.*;

public class CarAdd {
	
	final private static int portPerson = 1236;
	
	// Write the car in the system
	public static void write(Cars c) {
		try {
			Socket socket;
			socket = new Socket(InetAddress.getLocalHost(), portPerson);
			DataInputStream in = new DataInputStream(socket.getInputStream());
			DataOutputStream out = new DataOutputStream(socket.getOutputStream());
			out.writeInt(0); // send upload option
			c.writeOutputStream(out);
			boolean ok = in.readBoolean();
			if (!ok)
				System.out.println("Error");
			socket.close();
		} catch (IOException e) {
			System.out.println(e);
		}
	}

}
