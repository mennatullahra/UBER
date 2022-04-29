package uber;

public interface Observed {

    public void registerObserver(Trip trip, Driver d);
    public void removeObserver(Trip trip, Driver d);
    public void notifyObserversTrip(Trip trip);
    public void notifyDriverApproval(int choose, Trip trip, boolean b);
    public void notifyDriverRate(Trip trip,double rate);
    public void update(Trip trip, Driver driver, double price);
    public void updateArrival(Trip trip);
}
