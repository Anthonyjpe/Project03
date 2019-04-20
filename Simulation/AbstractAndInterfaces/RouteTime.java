/*
 * RouteTime
 * Author: Anthony Estephan
 * Last Updated: Sprint04
 */
package Simulation.AbstractAndInterfaces;

import Simulation.Address.Address;
import Simulation.Nouns.Truck;

import java.util.PriorityQueue;

public interface RouteTime {
    public double route(PriorityQueue<Address> addresses, Truck truck);
}
