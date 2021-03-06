/*
 * Driver for calculating time/distance
 * Author: Anthony Estephan
 * Last Updated: Sprint04
 */
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

    public void addOrder(int i,Order order){
        addresses.get(i).addOrder(order);
    }

    public PriorityQueue<Address> getPQ(){
        return new PriorityQueue<>(addresses);
    }
}
