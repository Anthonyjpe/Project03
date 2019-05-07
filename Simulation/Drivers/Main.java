/*
 * Driver for calculating time/distance
 * Author: Anthony Estephan
 * Last Updated: Sprint04
 */
package Simulation.Drivers;

import Simulation.Address.Address;
import Simulation.Address.AddressIO;
import Simulation.Nouns.*;

import java.util.InputMismatchException;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        // Read in how many blocks are to be viewed in the Simulation
        int bound = 0;
        Scanner sc = new Scanner(System.in);
        while(bound <= 2 || bound >= 21)
        {
            System.out.println("How many streets do you want to view?");
            bound = sc.nextInt();
        }
        Address.setBound(bound);

        int addressAmount = 4;

        // Write 100 random addresses to a file
        AddressIO.writeAddresses(AddressIO.FILE, addressAmount); //second input is how many addresses to randomly create2

        // Read the addresses from the file and place them in a PriorityQueue
       /* PriorityQueue<Address> addresses = AddressIO.readAddresses(AddressIO.FILE);
        for(Address address : addresses)
            System.out.println(address);*/

        OrderOfEvents addresses = OrderOfEvents.getInstance();

        // Read the addresses from the file and place them in a PriorityQueue
        addresses.set(AddressIO.readAddresses(AddressIO.FILE));

        for(int i = 0; i < addressAmount; i++){
            addresses.addOrder(i,new Order());
        }
        // Draw the neighborhood with the addresses and distribution center shown
        Neighborhood neighborhood = new Neighborhood(bound);
        Truck truck = new Truck(neighborhood);

        try {
            truck.userInput();
        } catch (InputMismatchException e) {
            System.out.println("Letter or word was entered, Please enter a 1 or 2.");
            truck.userInput();
        }

        System.out.println(truck.route() + " " + truck.seeRoute());
        System.out.println(truck.routeTime() + " " + truck.seeRouteTime());

        // Read in how many blocks to view in the simulation
        SimulationRunner sr = new SimulationRunner(bound);
        truck.registerObservers(sr);

        //truck.route resets move queue (in case anything was in it) and adds moves to queue
        while(truck.canMove()) { //MUST RUN A truck.route BEFORE RUNNING GUI
            truck.move();
        }

    }
}

