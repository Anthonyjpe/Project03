import java.util.PriorityQueue;

public interface Route {
    public int route(PriorityQueue<Address> addresses, Truck truck);

}
