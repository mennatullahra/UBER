package uber;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;

public class Trip {

	private Passenger passenger;
	private Driver driver;
	private String source, destination;
	private double Price=0.0;
	private int ID;
	private LocalTime tripTime = LocalTime.now();
	private static ArrayList<Trip> allTrips = new ArrayList<Trip>();
	private ArrayList<Driver> suggestedDrivers = new ArrayList<>();
	private ArrayList<Driver> observers = new ArrayList<>();
    private HashMap<Driver, Double> offers= new HashMap<Driver, Double>();
    //temporary variables
    Passenger subject;
	double suggestionPrice=0;

	Trip(){
		this.passenger=null;
	    this.source="";
	    this.destination="";
	    this.sendNotification();
	    this.Price = Double.POSITIVE_INFINITY;
	}
	Trip(Passenger passenger,String source,String destination){
	    this.passenger=passenger;
	    this.source=source;
	    this.destination=destination;
	    this.ID = allTrips.size()+1;
	}
	
	public void sendRatings(double rate) {
	    driver.setDriverRating(rate);
		this.driver.calcAvgRating(rate);
	}
	
	public void setPriceSuggestion(double price) {
		suggestionPrice=price;
	}
	
	public Passenger getPassenger () {
		return passenger;
	}
	public void setPassenger (Passenger passenger) {
		this.passenger = passenger;
	}
	
	public Driver getDriver () {
		return driver;
	}
	
	public void setDriver (Driver driver) {
		this.driver = driver ;
	}
	public void setSource (String source) {
		this.source = source;
	}

	public String getSource () {
		return source;
	}
	public void setDestination (String destination) {
		this.destination = destination;
	}
	
	public String getDestination () {
		return destination;
	}
	
	public void setPrice (double Price) {
		this.Price = Price;
	}
	
	public void setOfferPrice(Driver driver, double price) {
		offers.put(driver, price);
	}
	
	public double getPrice () {
		return Price;
	}
	
	public LocalTime getTime () {
		return tripTime;
	}
	
	public ArrayList<Driver> getSuggestedDrivers(){
		return suggestedDrivers;
	}
	
	public void setSuggestedDrivers(Driver driver){
		suggestedDrivers.add(driver);
	}
	
	public void setOffers(Driver driver, Double price){
		offers.put(driver, price);
	}
	
	public HashMap<Driver, Double> getOffers(){
		return offers;
	}
	
	public void setObservers(Driver driver){
		observers.add(driver);
	}
	
	public static void setTrip(Trip trip) {
		allTrips.add(trip);
	}
	
	
	public void setData (Driver driver, Passenger passenger, String source, String destination, double price) {
		this.passenger = passenger;
		this.driver = driver ;
		this.source=source;
	    this.destination=destination;
	    this.Price = price;
	}
	
	
	public void sendNotification(){
            for(int j=0;j<Driver.getAllDrivers().size();j++)
          for(int i=0;i<Driver.getAllDrivers().get(j).getFavouriteAreas().size();i++)
          {
              
              if(source==Driver.getAllDrivers().get(j).getFavouriteAreas().get(i)){
                 Driver.getAllDrivers().get(j).addAreasTrips(this);
               
              }
          }        
                
          }


	public String toString()
    {
		
        return "__________________TRIP__________________\n"+getPassenger() +""+ getDriver() + "\nSOURCE: "+this.getSource()
        		+"\nDESTENATION: "+getDestination()+"\nPRICE: "+getPrice()+"\n__________________TRIP__________________";
    }
        }
  
