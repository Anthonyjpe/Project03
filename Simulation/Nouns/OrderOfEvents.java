package Simulation.Nouns;

import Simulation.Address.Address;

import java.util.LinkedList;
import java.util.PriorityQueue;

public class OrderOfEvents {

    private static OrderOfEvents uniqueInstance;
    private LinkedList<Address> addresses;

    private OrderOfEvents(){
        addresses = new LinkedList<>();
    };

    public static OrderOfEvents getInstance(){
        if(uniqueInstance == null) {
            uniqueInstance = new OrderOfEvents();
        }

        return uniqueInstance;
    }

    public void set(PriorityQueue<Address> addresses) {
        while (!addresses.isEmpty()) {
            this.addresses.add(addresses.poll());
        }
    }

    public LinkedList<Address> get(){
        return addresses;
    }
}
