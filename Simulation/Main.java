package Simulation;

import java.util.PriorityQueue;

public class Main {


    public static void main(String[] args)
    {
        // Write 100 random addresses to a file
        AddressIO.writeAddresses(AddressIO.FILE, 100);

        // Read the addresses from the file and place them in a PriorityQueue
        PriorityQueue<Address> addresses = AddressIO.readAddresses(AddressIO.FILE);

        // Draw the neighborhood with the addresses and distribution center shown
        /*Neighborhood.drawNeighborhood(addresses);
        Neighborhood neighborhood = new Neighborhood();
        neighborhood.generateNeighborhood(addresses);
        neighborhood.printNeighborhood();*/
        Neighborhood neighborhood = new Neighborhood();
        NeighborhoodGUI simulation = new NeighborhoodGUI(neighborhood);
        //simulation.start();

        Truck truck = new Truck(neighborhood);
        System.out.println(truck.route(addresses) + " " + truck.seeRoute());
        System.out.println(truck.routeTime(addresses) + " " + truck.seeRouteTime());

        truck.setRoute(new RouteRightDistance());
        truck.setRouteTime(new RouteRightTime());
        System.out.println(truck.route(addresses) + " " + truck.seeRoute());
        System.out.println(truck.routeTime(addresses) + " " + truck.seeRouteTime());

    }
}
