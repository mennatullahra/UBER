package uber;
import java.time.LocalTime;

public class Event {
	String eventName;
	LocalTime tripTime;
	Event(Event event){
		this.eventName = event.eventName;
		tripTime = LocalTime.now();
	}
	public Event(String eventName) {
		this.eventName = eventName;
	}
}