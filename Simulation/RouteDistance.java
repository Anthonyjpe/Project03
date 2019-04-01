package Simulation;

import Simulation.Address;

import java.util.PriorityQueue;

public interface RouteDistance {
    public int route(PriorityQueue<Address> addresses, Truck truck);

}
