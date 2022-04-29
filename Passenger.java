package uber;


import java.util.*;


public class Passenger implements Observed{
	private User user;
	//A list of trips that have been requested in the application.
	ArrayList<Trip> trips= new ArrayList <>();
	
	//A list of all the passengers that use the application.
	public static ArrayList<Passenger> allPassengers = new ArrayList<Passenger>();
	    
	public Passenger(String userName, String eMail, String mobileNumber, String password ){
    	
    	user = new User(userName, eMail, mobileNumber, password );
    	
	}
	
	public boolean register(User userData) {
		try {
			for (int i = 0; i < Passenger.allPassengers.size(); i++) {
				if(getUser().getUserName().equals(Passenger.allPassengers.get(i).getUser().getUserName())) {
					System.err.println("Username has already been token");
					return false;
				}
			}
			this.user = userData;
			Passenger.allPassengers.add(this);	
			return true;
		}catch (Exception e) {
			return false;
		}
		
	}
	public boolean logIn(String userName, String password) {
		
		//checking if the passenger suspended or not.
		for (int i = 0; i < Admin.getInstance(user).getSuspendedPassengers().size(); i++) {
			if(userName.equals(Admin.getInstance(user).getSuspendedPassengers().get(i).getUser().getUserName())) {
				System.err.println("User suspended");
				return false;
			}
		}
		
		if(this.getUser().getUserName().equals(userName) && this.getUser().getPassword().equals(password)) {
			this.getUser().setOnline();
			return true;
		}
		else 
			return false;
	}
	
	public double checkRate (Driver driver) {
		if (!this.getUser().getState())
			System.err.println("User not logged in.");
		return driver.getAvgRating();
	}
	
	public static Passenger getPassengerByUserName(String userName) {
		for (int i = 0; i < allPassengers.size(); i++) {
			if(allPassengers.get(i).getUser().getUserName().equals(userName))
				return allPassengers.get(i);
		}
		return null;
	}
	
	public User getUser() {
		return user;
	}
	
	public void rateDriver (Trip trip, double rate) {
		if (!this.getUser().getState())
			System.err.println("User not logged in.");
		trip.sendRatings(rate);
	}

	public void registerObserver(Trip trip, Driver d) {
		// TODO Auto-generated method stub
		trip.setObservers(d);
	}
	
	public void removeObserver(Trip trip, Driver d) {
		// TODO Auto-generated method stub
		int observerIndex = trip.getSuggestedDrivers().indexOf(d); //Do I have this observer?
	    if (observerIndex >= 0) {
	    	trip.getSuggestedDrivers().remove(observerIndex);
	    }
	}
	
	public void notifyObserversTrip(Trip trip) {
		// TODO Auto-generated method stub
		trip.getSuggestedDrivers().forEach(o -> o.update(trip));
	}
	
	public void notifyDriverApproval(int choose, Trip trip, boolean b) {
		// TODO Auto-generated method stub
		Driver.getDrivers().get(choose).update(trip, b);
	}
	public void update(Trip trip, Driver driver, double price) {
		// TODO Auto-generated method stub
		trip.setOffers(driver,price);
	}
	
	public void updateArrival(Trip trip) {
		// TODO Auto-generated method stub
		Scanner input = new Scanner(System.in);
		
		System.out.println("(Passenger) rate the driver from 1 to 5 : ");
		double rate = input.nextDouble();
		this.notifyDriverRate(trip, rate);
		
	}
	
	public void notifyDriverRate(Trip trip, double rate) {
		// TODO Auto-generated method stub
		this.rateDriver(trip, rate);
	}
	
	
	public String toString()
	    {
	        return "	**PASSENGER**\n"+"USERNAME: "+this.getUser().getUserName() +"\nEMAIL: "+
	        		this.getUser().getEMail()+"\nMOBILE NUMBER: "+this.getUser().getMobileNumber()+
	        		"\n____________________________\n";
	    }

}