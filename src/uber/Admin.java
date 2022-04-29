package uber;

import java.util.ArrayList;


public class Admin extends User {

	static private Admin instance;
	private User user;
    private ArrayList<String> discountAreas = new ArrayList<String>();
    private ArrayList<Event> events = new ArrayList<>();
	private static ArrayList<Driver> driversPending = new ArrayList<Driver>();
    private ArrayList<Passenger> suspendedPassengers = new ArrayList<Passenger>();
    private ArrayList<Driver> suspendedDrivers = new ArrayList<Driver>();
    
    public Admin() {
    	this.setUserName("");
		this.setEMail("");
		this.setPassword("");
		this.setMobileNumber("");
    }
    
    Admin(User user) {
    	this.user = user;
    }
    
    public ArrayList<String> getDiscountAreas(){
   		return discountAreas;
   	}
   	
   	public void setDiscountAreas(String area){
   		discountAreas.add(area);
   	}
   
    static public Admin getInstance(User user) {
    	if (instance == null) {
    		instance = new Admin(user);
    		return instance;
    	}
    	else
    		return instance;
    }
	

    public double haveDiscount(Trip trip) {
    	double discount=0.9;
    	double nonDiscount=1;
    	for (int i = 0; i < getDiscountAreas().size(); i++) {
    		if(trip.getDestination().equals(getDiscountAreas().get(i))) {
        		return discount;
        	}
		}
    	return nonDiscount;
    	
    }
    
    public void setUser(User user) {
    	this.user = user;
    }
    
    public User getUser() {
    	return this.user;
    }
    
	public ArrayList<Driver>  getDriversPending() {
		return driversPending;
	}
	
	public ArrayList<Passenger>  getSuspendedPassengers() {
		return suspendedPassengers;
    }
	
    public ArrayList<Driver>  getSuspendedDrivers() {
		return suspendedDrivers;
	}
    
    public void addPendingDriver(Driver driver){
        driversPending.add(driver);
    }
    
    public void addSuspendPassenger(Passenger passenger){
            suspendedPassengers.add(passenger);
    }
    
    public void addSuspendedDrivers(Driver driver){
        suspendedDrivers.add(driver);
    }
    
    public boolean validAccount(Passenger passenger){
    	
        boolean valid=false;
        for(int i=0;i<suspendedPassengers.size();i++){
            if(passenger==suspendedPassengers.get(i)){
               valid=true;
            }
        }
        return valid;
        
    }
     public boolean validAccount(Driver driver){
    	 boolean valid=false;
        for(int i=0;i<suspendedDrivers.size();i++){
            if(driver==suspendedDrivers.get(i)||driver==driversPending.get(i)){
            	valid=true;
            }
        }
        return valid;
    }
    public static String verifyDriverRegestration (Driver driver){
    	
    	boolean valid=false;
    	//variable hold index of the driver in the pending list "if it's pended".
        int idxDriver = 0;
        for(int i=0;i<driversPending.size();i++){
            if(driver==driversPending.get(i)){
            	valid=true;
            	idxDriver=i;
               
            }
        }
        if(valid==true){
        	
           driversPending.remove(idxDriver);
           Driver.setDriver(driver);
           driver.setID(Driver.getDrivers().size());
           return " The Driver has been verified";
        }
        else{ 
           return "The Driver doesn't exist in the pending list" ;
        }
    }

	public ArrayList<Event> getEvents() {
		return events;
	}

	public void setEvents(ArrayList<Event> events) {
		this.events = events;
	} 
    
    }
    
