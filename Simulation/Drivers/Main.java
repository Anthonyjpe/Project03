/*
 * Driver for calculating time/distance
 * Author: Anthony Estephan
 * Last Updated: Sprint04
 */
package Simulation.Drivers;

import Simulation.Address.Address;
import Simulation.Address.AddressIO;
import Simulation.Nouns.Neighborhood;
import Simulation.Nouns.SimulationRunner;
import Simulation.Nouns.Truck;

import java.util.PriorityQueue;

public class Main {


    public static void main(String[] args) throws InterruptedException {
        // Write 100 random addresses to a file
        //AddressIO.writeAddresses(AddressIO.FILE, 100);

        // Read the addresses from the file and place them in a PriorityQueue
        PriorityQueue<Address> addresses = AddressIO.readAddresses(AddressIO.FILE);

        // Draw the neighborhood with the addresses and distribution center shown
        Neighborhood neighborhood = new Neighborhood();
        Truck truck = new Truck(neighborhood);
        System.out.println(truck.route(addresses) + " " + truck.seeRoute());
        System.out.println(truck.routeTime(addresses) + " " + truck.seeRouteTime());

        truck.userInput();

        SimulationRunner sr = new SimulationRunner();
        truck.registerObservers(sr);
        /*
        truck.setRoute(new RouteRightDistance());
        truck.setRouteTime(new RouteRightTime());
        System.out.println(truck.route(addresses) + " " + truck.seeRoute());
        System.out.println(truck.routeTime(addresses) + " " + truck.seeRouteTime());
        */

        //truck.route resets move queue (in case anything was in it) and adds moves to queue
        while(truck.canMove()) { //MUST RUN A truck.route BEFORE RUNNING GUI
            truck.move();
            System.out.println("Truck's location: " + truck.getXLocation() + "   " + truck.getYLocation());
        }
    }
}

