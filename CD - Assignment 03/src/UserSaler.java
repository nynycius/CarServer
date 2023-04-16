import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class UserSaler {

	final static int portPerson = 1236;
	private static Socket socket;

	private String name;
	private int id;
	static int counter = 0;

	UserSaler(String n) {

		name = n;
		id = counter++;
	}

	// Write the car in the system
	public void write(Cars c) {
		try {
			socket = new Socket(InetAddress.getLocalHost(), portPerson);
			DataInputStream in = new DataInputStream(socket.getInputStream());
			DataOutputStream out = new DataOutputStream(socket.getOutputStream());
			out.writeInt(0); // send upload option
			c.writeOutputStream(out);
			boolean ok = in.readBoolean();
			if (!ok) {
				System.out.println(c.getRegis());
				System.out.println("Car already exist");
			} else {
				System.out.println(c.getRegis() + " added successfully");
			}
			socket.close();
		} catch (IOException e) {
			System.out.println(e);
		}
	}

	// retrieve all from server
	public void allSales() {

		// Create a list to store all results
		List<Cars> sales;

		try {
			socket = new Socket(InetAddress.getLocalHost(), portPerson);
			DataInputStream in = new DataInputStream(socket.getInputStream());
			DataOutputStream out = new DataOutputStream(socket.getOutputStream());

			out.writeInt(1);

			ObjectInputStream objIn = new ObjectInputStream(in); // get the object from DataInput in
			Object obj = objIn.readObject();
			sales = (List<Cars>) obj;

			System.out.println("\nSearch all cars for Sale: \n");

			if (sales.size() == 0) {
				System.out.println("No Cars are for Sale");
			} else {
				sales.forEach(c -> System.out.println(c));
			}

		} catch (IOException | ClassNotFoundException e) {
			System.out.println(e);
		}
	}

	public void searchMake(String make) {

		List<Cars> makeList;

		try {
			socket = new Socket(InetAddress.getLocalHost(), portPerson);
			DataInputStream in = new DataInputStream(socket.getInputStream());
			DataOutputStream out = new DataOutputStream(socket.getOutputStream());

			out.writeInt(2);
			out.writeUTF(make); // retrieve data based on Make

			ObjectInputStream objIn = new ObjectInputStream(in); // get the object from DataInput in
			Object obj = objIn.readObject(); // retrieve full list of Objects

			makeList = (List<Cars>) obj;

			System.out.println("\nSearch by make: \n");

			if (makeList.size() == 0) {
				System.out.println("There is no car with this make");
			} else {
				makeList.forEach(c -> System.out.println(c));
			}

		} catch (IOException | ClassNotFoundException e) {
			System.out.println(e);
		}
	}

	public void totalSell() {

		try {
			socket = new Socket(InetAddress.getLocalHost(), portPerson);
			DataInputStream in = new DataInputStream(socket.getInputStream());
			DataOutputStream out = new DataOutputStream(socket.getOutputStream());

			out.writeInt(3);
			int total = in.readInt();

			System.out.println("\nTotal value");
			System.out.println(total);

		} catch (Exception e) {
			System.out.println(e);
		}

	}

	public void sell(String register) {

		try {
			socket = new Socket(InetAddress.getLocalHost(), portPerson);
			DataInputStream in = new DataInputStream(socket.getInputStream());
			DataOutputStream out = new DataOutputStream(socket.getOutputStream());

			out.writeInt(4);
			out.writeUTF(register);

			boolean ok = in.readBoolean();
			if (!ok) {
				System.out.println("Error");
			} else {
				System.out.println("\n" + register + " sold");
			}
			socket.close();

		} catch (Exception e) {
			System.out.println(e);
		}
	}

	// retrieve all from server
	public void allSold() {

		// Create a list to store all results
		List<Cars> sales;

		try {
			socket = new Socket(InetAddress.getLocalHost(), portPerson);
			DataInputStream in = new DataInputStream(socket.getInputStream());
			DataOutputStream out = new DataOutputStream(socket.getOutputStream());

			out.writeInt(5);

			ObjectInputStream objIn = new ObjectInputStream(in); // get the object from DataInput in
			Object obj = objIn.readObject();
			sales = (List<Cars>) obj;

			System.out.println("\nSearch all cars Sold: \n");

			if (sales.size() == 0) {
				System.out.println("No Cars Sold yet");
			} else {
				sales.forEach(c -> System.out.println(c)); // uses stream to print all 
			}

		} catch (IOException | ClassNotFoundException e) {
			System.out.println(e);
		}
	}
}
