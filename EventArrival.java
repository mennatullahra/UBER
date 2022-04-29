package uber;

public class EventArrival extends Event{
	String passengerName;
	String DriverName;
	
	EventArrival(Event event, Trip trip) {
		super(event);
		this.passengerName = trip.getPassenger().getUser().getUserName();
		this.DriverName = trip.getDriver().getUser().getUserName();
	}

	public String toString() {
		return eventName+" "+tripTime+" "+passengerName+" "+DriverName+"\n";
	}
}
