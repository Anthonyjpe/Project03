package Simulation;

import java.util.PriorityQueue;

public interface RouteTime {
    public int route(PriorityQueue<Address> addresses, Truck truck);
}
