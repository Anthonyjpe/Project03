/*
 * Driver for calculating time/distance
 * Author: Anthony Estephan
 * Last Updated: Sprint04
 */
package Simulation.Drivers;

import Simulation.Address.Address;
import Simulation.Address.AddressIO;
import Simulation.Nouns.Neighborhood;
import Simulation.Nouns.OrderOfEvents;
import Simulation.Nouns.SimulationRunner;
import Simulation.Nouns.Truck;

import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.PriorityQueue;

public class Main {


    public static void main(String[] args) throws InterruptedException {
        OrderOfEvents addresses = OrderOfEvents.getInstance();

        // Write 100 random addresses to a file
        //AddressIO.writeAddresses(AddressIO.FILE, 22); //second input is how many addresses to randomly create2

        // Read the addresses from the file and place them in a PriorityQueue
        addresses.set(AddressIO.readAddresses(AddressIO.FILE));

        // Draw the neighborhood with the addresses and distribution center shown
        Neighborhood neighborhood = new Neighborhood();
        Truck truck = new Truck(neighborhood);

        try {
            truck.userInput();
        } catch (InputMismatchException e) {
            System.out.println("Letter or word was entered, Please enter a 1 or 2.");
            truck.userInput();
        }

        System.out.println(truck.route(addresses.get()) + " " + truck.seeRoute());
        System.out.println(truck.routeTime(addresses.get()) + " " + truck.seeRouteTime());

        SimulationRunner sr = new SimulationRunner();
        truck.registerObservers(sr);

        //truck.route resets move queue (in case anything was in it) and adds moves to queue
        while(truck.canMove()) { //MUST RUN A truck.route BEFORE RUNNING GUI
            truck.move();
           // System.out.println("Truck's location: " + truck.getXLocation() + "   " + truck.getYLocation());
        }

    }
}

