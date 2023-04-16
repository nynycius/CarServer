import java.io.*;

public class Cars implements Serializable {

	private static final long serialVersionUID = 1L;

	
	private String registration;
	private String make;
	private int price;
	private int milage;
	private boolean forSale;

	public Cars(String r, String mk, int p, int ml, boolean f) {

		registration = r;
		make = mk;
		price = p;
		milage = ml;
		forSale = f;
	}
	
	
	public Cars() {

		registration = null;
		make = null;
		price = 0;
		milage = 0;
		forSale = false;
	}
	
	public String getRegis() {
		return registration;
	}
	
	public boolean getSale() {
		
		return forSale; 
	}
	
	// set car to sold
	public void sold() {
		forSale = false;
	}
	
	// return price of the car
	public int price() {
		
		return price;		
		
	}
	
	
	@Override
	public String toString() {
		return "[registration=" + registration + ", make=" + make + ", price=" + price + ", milage=" + milage
				+ ", forSale=" + forSale + "]";
	}
	
	public boolean equalMake(Object ob) { // equality based on full make only
		if (!(ob instanceof Cars))
			return false;
		Cars c = (Cars) ob;
		return make.equals(c.make);
	}
	
	@Override 
	public boolean equals(Object ob) { //  equality based on full make only
		if (!(ob instanceof Cars))
			return false;
		Cars c = (Cars) ob;
		return registration.equals(c.registration);
		
	}


	// ======================================================
	// Methods used to read and write to streams over sockets
	// read whole object
	public void writeOutputStream(DataOutputStream out) {
		try {
			
			out.writeUTF(registration);
			out.writeUTF(make);
			out.writeInt(price);
			out.writeInt(milage);
			out.writeBoolean(forSale);
			

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// read whole object
	public void readInputStream(DataInputStream in) {
		try {

			registration = in.readUTF();
			make = in.readUTF();
			price = in.readInt();
			milage = in.readInt();
			forSale = in.readBoolean();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
