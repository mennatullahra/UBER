package uber;

public class EventApproval  extends Event  {
	String passengerName;
	
	EventApproval(Event event, Trip trip) {
		super(event);
		passengerName = trip.getPassenger().getUser().getUserName();
	}
	public String toString() {
		return eventName+" "+tripTime+" "+passengerName+"\n";
	}
}

