import java.io.*;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.net.*;

public class CarServer {

	final static int portPerson = 1236;
	final static int numThreads = 50;
	static ExecutorService pool = Executors.newFixedThreadPool(numThreads);
	static Semaphore sem = new Semaphore(50);

	public static void main(String[] args) {

		System.out.println("Server running...");
		Data data = new Data();
		try {
			ServerSocket servesock = new ServerSocket(portPerson);
			while (true) {
				try {
					sem.acquire();

				} catch (InterruptedException e) {
				}
				// wait for a service request on port portSqrt
				Socket socket = servesock.accept();
				// Submit request pool
				pool.submit(new CarRequests(socket, data, sem));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

class CarRequests implements Runnable {
	Socket socket;
	Data data;
	Semaphore semaphore;

	// Constructor for CarRequests
	CarRequests(Socket s, Data d, Semaphore sem) {

		socket = s;
		data = d;
		semaphore = sem;
	}

	public void run() {

		try {
			// opt 0 add car
			DataInputStream in = new DataInputStream(socket.getInputStream());
			DataOutputStream out = new DataOutputStream(socket.getOutputStream());
			int opt = in.readInt();
			if (opt == 0) { // upload
				System.out.println(Thread.currentThread().getName());
				Cars c = new Cars();
				c.readInputStream(in);
				// if method return true it was added, else, already exist
				out.writeBoolean(data.add(c));
				socket.close();
				semaphore.release();

			} else if (opt == 1) { // retrieve all
				System.out.println(Thread.currentThread().getName());
				ObjectOutputStream objOut = new ObjectOutputStream(out); // get the object from DataInput in

				List<Cars> dt = data.allSales();

				objOut.writeObject(dt);
				socket.close();
				semaphore.release();
			}

			else if (opt == 2) { // retrieve by make
				System.out.println(Thread.currentThread().getName());

				String make = in.readUTF();

				ObjectOutputStream objOut = new ObjectOutputStream(out); // get the object from DataInput in

				List<Cars> dt = data.make(make);

				objOut.writeObject(dt); // write out the whole list object
				socket.close();
				semaphore.release();
			}

			else if (opt == 3) { // retrieve total of cars to be sold

				int total = data.totalSell();
				System.out.println(total);

				out.writeInt(total); // send total to client
				socket.close();
				semaphore.release();

			} else if (opt == 4) { // set car to sold based on register

				String registration = in.readUTF();

				System.out.println("Selling Car");
				data.sell(registration);
				out.writeBoolean(true);
				socket.close();
				semaphore.release();

			} else if (opt == 5) { // retrieve all sold
				ObjectOutputStream objOut = new ObjectOutputStream(out); // get the object from DataInput in

				List<Cars> dt = data.allSold();

				objOut.writeObject(dt);
				socket.close();
				semaphore.release();

			} else {
				System.out.println("Request not found");
			}

		} catch (Exception e) {

		}
	}

}

class Data {
	private ArrayList<Cars> data = new ArrayList<Cars>();
	private Lock lock = new ReentrantLock();

	public boolean add(Cars c) {
		lock.lock();
		try {
			if (!data.contains(c)) {
				data.add(c);
				return true;
			} else {
				return false;
			}
		} finally {
			lock.unlock();
		}
	}

	public List<Cars> allSales() {
		// nothing will be changed, no lock is necessary

		lock.lock();
		try {
			List<Cars> dt = new ArrayList<Cars>();
			Cars c;
			for (int i = 0; i < data.size(); i++) {

				c = data.get(i);
				if (c.getSale()) {
					dt.add(c);
				}
			}

			return dt;
		} finally {
			lock.unlock();
		}
	}

	// retrieve by make
	public List<Cars> make(String make) {

		lock.lock();
		try {
			List<Cars> dt = new ArrayList<Cars>();
			Cars c;
			for (int i = 0; i < data.size(); i++) {

				c = new Cars("", make, 0, 0, true);
				// add to list when the make is equal
				if (c.equalMake(data.get(i))) {
					dt.add(data.get(i));
				}

			}
			// return list with results
			return dt;

		} finally {
			lock.unlock();
		}
	}

	public int totalSell() {

		lock.lock();
		try {
			int sum = 0;

			for (int i = 0; i < data.size(); i++) {

				if (data.get(i).getSale()) {

					sum += data.get(i).price();
				}
			}

			return sum;

		} finally {
			lock.unlock();
		}
	}

	public void sell(String registration) {

		lock.lock();
		try {
			Cars c;

			for (int i = 0; i < data.size(); i++) {

				c = new Cars(registration, "", 0, 0, true);
				// add to list when the make is equal
				if (c.equals(data.get(i))) {
					data.get(i).sold(); // set forSale to false
				}

			}
		} finally {
			lock.unlock();
		}

	}

	public List<Cars> allSold() {

		lock.lock();
		try {
			List<Cars> dt = new ArrayList<Cars>();
			Cars c;
			for (int i = 0; i < data.size(); i++) {

				c = data.get(i);
				if (!c.getSale()) { // if For sale is false
					dt.add(c);
				}
			}

			return dt;
		} finally {
			lock.unlock();
		}
	}

}
