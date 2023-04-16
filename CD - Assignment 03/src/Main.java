
/***
 * 
 * @author Vinicius Pepe Bellomo, SN.: 3052662 
 * 	
 */


public class Main {

	public static void main(String[] args) {

		UserSaler emp = new UserSaler("Employee");
		
		// Populating the server with cars, buy UserSaler request
		emp.write(new Cars("16L1234", "Ferrari", 120000, 1000, true));
		emp.write(new Cars("01LH1234", "Ford Fiesta", 1000, 1000, true));
		emp.write(new Cars("02D1234", "Ford Focus", 11000, 2000, true));
		emp.write(new Cars("03WW1234", "Ford Mondeo", 12000, 3000, true));
		emp.write(new Cars("05KK1234", "Ford Mustang", 14000, 5000, true));
		emp.write(new Cars("06CW1234", "Ford B-Max", 15000, 6000, true));
		emp.write(new Cars("07LS1234", "Ford C-Max", 16000, 7000, true));
		emp.write(new Cars("08KE1234", "Ford S-Max", 17000, 8000, true));
		emp.write(new Cars("10WM1234", "Toyota Starlet", 19000,  10000, true));
		emp.write(new Cars("11M1234", "Toyota Avensis", 20000, 11000, true));
		emp.write(new Cars("85LS9876", "Ferrari", 120000, 2000, true));
		emp.write(new Cars("15CE1234", "Mitsubishi Lancer", 24000, 15000, true));
		emp.write(new Cars("01LH9876", "Ford Fiesta", 1200, 800, true));
		emp.write(new Cars("07KJ8526", "Ford Fiesta", 800, 1200, true));
		emp.write(new Cars("08LP7319", "Ford Focus", 8000, 5000, true));
		
		// adding some repetition to test control
		
		emp.write(new Cars("05KK1234", "Ford Mustang", 14000, 5000, true));
		emp.write(new Cars("06CW1234", "Ford B-Max", 15000, 6000, true));
		emp.write(new Cars("07LS1234", "Ford C-Max", 16000, 7000, true));
		
		//Retrieve all cars for sale
		emp.allSales();
		
		// Retrieve by make
		emp.searchMake("Ford Fiesta");
		
		// print all value
		emp.totalSell();
		
		// Sell car
		emp.sell("16L1234");
		emp.sell("07LS1234");
		emp.sell("10WM1234");
		emp.sell("03WW1234");
		
		// Check if it sold
		emp.allSold();
		
		
	
	}

}
