package uber;

public interface Observer {
	public void notifySubject(Trip trip, double price);
	public void notifyArrival(Trip trip);
	public void update(Trip t);
    public void update(Trip trip, boolean b);
}
