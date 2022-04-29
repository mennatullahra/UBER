package uber;


import java.util.ArrayList;


public  class Driver extends User implements Observer{
	private double avgRating=0;
	private String drivingLicense="";
	private String nationalID="";
	private User user;
	private int ID;
	private boolean busy=false;
	private static ArrayList<Driver> allDrivers = new ArrayList<Driver>();
    private ArrayList<Trip> favouriteAreasTrips = new ArrayList<>();
	private ArrayList<String> favouriteAreas = new ArrayList<>();
    private ArrayList <Double> usersRating = new ArrayList <>();
    private ArrayList<Trip> offers = new ArrayList<>();

    Driver(User user){
    	this.setUser(user);
    	Admin.getInstance(user).addPendingDriver(this);
    	busy = false;
    }
    
    private void setUser(User user) {
		this.user = user;
	}

	public boolean register(User userData, String drivingLicense, String nationalID) {
		try {
			
		    for (int i = 0; i < Driver.allDrivers.size(); i++) {
				if(getUserName().equals(Driver.allDrivers.get(i).getUser().getUserName())) {
					System.err.println("Username has already been token");
					return false;
				}
			}
		    this.user= userData;
    	    this.drivingLicense = drivingLicense;
        	this.nationalID = nationalID;
			return true;
		}catch (Exception e) {
			return false;
		}
    }
    
    public boolean logIn(String userName, String password) {
		for (int i = 0; i < Admin.getInstance(user).getSuspendedDrivers().size(); i++) {
			if(userName.equals(Admin.getInstance(user).getSuspendedDrivers().get(i).getUserName())) {
				System.out.println("User suspended");
				return false;
			}
		}
		if(this.getUserName().equals(userName) && this.getPassword().equals(password)) {
			this.setOnline();
			return true;
		}
		else 
			return false;
	}
	
    public String  getDriverLicense() {
		return drivingLicense;
	}

    public User getUser() {
		return user;
	}

    public int  getID() {
		return ID;
	}

    public boolean isBusy() {
    	return busy;
    }

    public ArrayList<Double>  getDriverRating() {
		return usersRating;
	} 

    
    public static ArrayList<Driver> getAllDrivers() {
		return allDrivers;
	}
    
    public static ArrayList<Driver>  getDrivers() {
		return allDrivers;
	}
    
    public static Driver getDriverByUserName(String userName) {
		for (int i = 0; i < allDrivers.size(); i++) {
			if(allDrivers.get(i).getUser().getUserName().equals(userName))
				return allDrivers.get(i);
		}
		return null;
	}

    public String  getDrivingLicense() {
		return drivingLicense;
	}
    
    public String  getNationalID() {
		return nationalID;
	}
    
    public ArrayList<String>  getFavouriteAreas() {
		return favouriteAreas;
	}
    
    public ArrayList<String>  getUsersRating() {
		return favouriteAreas;
	}
    
	public double  getAvgRating() {
		return avgRating;
	}
	
    
    public void isBusy(boolean state) {
    	busy = state ;
    }
    
    public void setID(int id) {
		this.ID = id;
	}
    
    public static void  setDriver(Driver driver) {
		allDrivers.add(driver);
	}
    
    public void  setDriverRating(Double rate) {
		usersRating.add(rate);
	}
    
    public void  setFavouriteAreas(String area) {
		favouriteAreas.add(area);
	}
        
    public void  calcAvgRating(double rate) {
        int n=usersRating.size();
		avgRating=(avgRating+rate)/n;
	}
	
	public void addFavArea(String area){
        favouriteAreas.add(area);
    }
	
	public void addUserRatings(double rate){
        usersRating.add(rate);
    }
        
	public void addAreasTrips(Trip trip){
        favouriteAreasTrips.add(trip);
    }
	
    
    public void arrivedLocation(Trip trip) {
    	System.out.println("Driver arrived to the location.");
    	Event newEvent = new Event("arrived location");
    	Event newEventArrival = new EventArrival(newEvent, trip);
    	Admin.getInstance(user).getEvents().add(newEventArrival);
    	
    }
    
    public void arrivedDestination(Trip trip) {
    	System.out.println("Driver arrived to the destination.");
    	Event newEvent = new Event("arrived destination");
    	Event newEventArrival = new EventArrival(newEvent, trip);
    	Admin.getInstance(user).getEvents().add(newEventArrival);
    	this.notifyArrival(trip);
    	this.busy = false;
    }

	public void notifySubject(Trip trip, double price) {
		// TODO Auto-generated method stub
		trip.subject.update(trip, this, trip.suggestionPrice);
		Event newEvent = new Event("driver puting a price");
    	Event newEventArrival = new EventPrice(newEvent, this, price);
    	Admin.getInstance(user).getEvents().add(newEventArrival);
	}

	public void update(Trip t) {
		this.offers.add(t);
	}

	 
	public void update(Trip trip, boolean b) {
		if (b = true) {
			System.out.println("the driver in the way.");
			this.busy = true;
			Trip.setTrip(trip);
		}
		else
			System.out.println("the trip cancelled");
	}

	 
	public void notifyArrival(Trip trip) {
		trip.subject.updateArrival(trip);
	}
	   
    public String toString()
    {
        return "	**DRIVER**\n"+"USERNAME: "+this.getUser().getUserName() +"\nEMAIL: "+
        		this.getUser().getEMail()+"\nMOBILE NUMBER: "+this.getUser().getMobileNumber() +
        		"\nAVERAGE RATE: "+this.getAvgRating() +"\nDRIVING LICENSE: "+
        		this.getDrivingLicense() +"\nNATIONAL ID: "+
                this.getNationalID() +"\nID: "+
                this.getID() + "\n____________________________\n";
    }
        
}

    
