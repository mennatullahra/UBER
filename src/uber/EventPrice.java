package uber;
public class EventPrice extends Event{
	String driverName;
	double price;
	public EventPrice(Event event,Driver driver, double price) {
		super(event);
		this.driverName = driver.getUser().getUserName();
		this.price = price;
	}
	public String toString() {
		return "EVENT NAME: "+eventName+"\nTRAVIL TIME: ~"+tripTime+"\nDRIVER NAME:"
				+ " "+driverName+"\nPRICE: "+price+"\n_______________________________";
	}
}
