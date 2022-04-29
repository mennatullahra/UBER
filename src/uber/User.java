package uber;

import java.util.Scanner;


public class User {
	
	private String userName="";
	private String eMail="";
	private String mobileNumber="";
	private String password = "";
	private boolean loggedIn = false;
	
	User(){
		this.userName = "";
		this.eMail = "";
		this.mobileNumber = "";
		this.password = "";
	}
	
	User(String userName, String eMail, String mobileNumber, String password ){
		this.userName = userName;
		this.eMail = eMail;
		this.mobileNumber = mobileNumber;
		this.password = password;
	}
	
	
	public String getUserName () {
		return userName;
	}
	public String getPassword () {
		return password;
	}
	public boolean getState () {
		return loggedIn;
	}
	public String getEMail () {
		return eMail;
	}
	public String getMobileNumber () {
		return mobileNumber;
	}
	public void setUserName (String userName) {
		this.userName = userName;
	}
	public void setPassword (String password) {
		this.password= password;
	}
	public void setEMail (String eMail) {
		this.eMail=eMail;
	}
	public void setOnline () {
		loggedIn = true;
	}
	public void setMobileNumber (String mobileNumber) {
		this.mobileNumber=mobileNumber;
	}
	public String toString()
	{
	    return "name: "+getUserName() + ", Email: " + getEMail() + ", Mobile number: " + getMobileNumber()+ " ";
	}
	public static void main(String[] args)
    {
		User admin = new User ("mennatullaRashed", "menna@hotmail.com", "+021234567890", "M*2020");
	    Admin.getInstance(admin);
		
		@SuppressWarnings("resource")
		Scanner input = new Scanner(System.in);
		while(true){
			System.out.println(
					"1- Register as a drvier.\n"
					+ "2- Register as a passenger\n"
					+ "3- Log in as a drvier.\n"
					+ "4- Log in as a passenger.\n"
					+ "5- Request a ride as a passenger\n"
					+ "6- Add favorite area as a driver\n"
					+ "7- Apply discount as an admin\n"
					+ "8- Suspend a driver as an admin\n"
					+ "9- Suspend a passenger as an admin\n"
					+ "10- Quit\n");
			String line;
			String option;
        	option = input.nextLine();
        	if(option.contains("10")) break;
        	else if(option.contains("1")) {
        		System.out.println("Enter driver data splitted by spaces username, e-mail, mobile number"
        				+ ", password, driving license, and national id. respectively.");
        		line = input.nextLine();
        		try {
        			String[] arrOfData = line.split(" ");
        			User user = new User(arrOfData[0], arrOfData[1], arrOfData[2], arrOfData[3]);
        		    Driver driver= new Driver(user); 
        		    driver.register(user,arrOfData[4],arrOfData[5]);
            		Admin.verifyDriverRegestration(driver);
				} catch (Exception e) {
					System.err.println("Invalid input");
					continue;
				}
        	}
        	else if(option.contains("2")) {
        		System.out.println("Enter passenger data splitted by spaces username, e-mail, mobile number"
        				+ ", and password. respectively.");
        		line = input.nextLine();
        		try {
        			String[] arrOfData = line.split(" ");
            		Passenger passenger= new Passenger(arrOfData[0], arrOfData[1], arrOfData[2], arrOfData[3]);
            		passenger.register(passenger.getUser());
				} catch (Exception e) {
					System.err.println("Invalid input");
					continue;
				}
        		
        	}
        	else if(option.contains("3")) {
        		System.out.println("Enter driver data splitted by spaces username, and password. respectively.");
        		line = input.nextLine();
        		try {
        			String[] arrOfData = line.split(" ");
            		Driver.getDriverByUserName(arrOfData[0]).logIn(arrOfData[0], arrOfData[1]);
				} catch (Exception e) {
					System.err.println("Invalid input");
					continue;
				}
        		
        	}
        	else if(option.contains("4")) {
        		System.out.println("Enter passeneger data splitted by spaces username, and password. respectively.");
        		line = input.nextLine();
        		try {
        			String[] arrOfData = line.split(" ");
            		Passenger.getPassengerByUserName(arrOfData[0]).logIn(arrOfData[0], arrOfData[1]);
				} catch (Exception e) {
					System.err.println("Invalid input");
					continue;
				}
        		
        	}
        	else if(option.contains("5")) {
        		System.out.println("Enter your username, source, and destination splitted by spaces. respectively.");
        		line = input.nextLine();
        		try {
        			String[] arrOfData = line.split(" ");
        			RequestRide request=new RequestRide();
            		request.requestRide(Passenger.getPassengerByUserName(arrOfData[0]), arrOfData[1],arrOfData[2]);
				} catch (Exception e) {
					System.err.println("Invalid input");
					System.err.println(e);
					continue;
				}
        		
        	}
        	else if(option.contains("6")) {
        		System.out.println("Enter your username, and your favorite area splitted by spaces. respectively.");
        		line = input.nextLine();
        		try {
        			String[] arrOfData = line.split(" ");
            		Driver.getDriverByUserName(arrOfData[0]).addFavArea(arrOfData[1]);
				} catch (Exception e) {
					System.err.println("Invalid input");
					continue;
				}
        	}
        	else if(option.contains("7")) {
        		System.out.println("Enter the area");
        		line = input.nextLine();
        		Admin.getInstance(admin).setDiscountAreas(line);
        	}
        	else if(option.contains("8")) {
        		System.out.println("Enter username of the driver");
        		line = input.nextLine();
        		Admin.getInstance(admin).addSuspendedDrivers(Driver.getDriverByUserName(line));
        	}
        	else if(option.contains("9")) {
        		System.out.println("Enter username of the passenger");
        		line = input.nextLine();
        		Admin.getInstance(admin).addSuspendPassenger(Passenger.getPassengerByUserName(line));
        	}
        	}
    }

    }





