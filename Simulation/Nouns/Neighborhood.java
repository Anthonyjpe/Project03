/*
 * Neighborhood
 * Author: Jonah Beers & Anthony Estephan
 * Last Updated: Sprint04
 */
package Simulation.Nouns;

import Simulation.Address.Address;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Neighborhood
{
    private int distribution_center_street;
    private int distribution_center_num;
    private int neighborhood_dimensions;
    private String[][] grid;
    private Truck truck[]; // multiple trucks can be owned

    public Neighborhood(double bound)
    {
        neighborhood_dimensions = (int) (bound * 10. + 1);
        distribution_center_street = (int) (Math.floor((bound - 1.) / 2.) * 10);
        distribution_center_num = (int) (Math.floor((bound - 1.)/ 2.) * 10 + 1);
        grid = new String[neighborhood_dimensions][neighborhood_dimensions];
    }

    private void generateNeighborhood()
    {
        // Location of houses, represented as "o"; crossroads as "-"
        for (int x = 0; x < neighborhood_dimensions; x++)
        {
            for (int y = 0; y < neighborhood_dimensions; y++)
            {
                if (x % 10 == 0)
                {
                    if (y % 10 == 0)
                        grid[x][y] = "- ";
                    else
                        grid[x][y] = "o ";
                }
                if (x % 10 != 0)
                {
                    if (y % 10 == 0)
                        grid[x][y] = "o ";
                    else
                        grid[x][y] = "  ";
                }
            }
        }

        // Location of the distribution center, represented as "&"
        grid[distribution_center_num][distribution_center_street] = "& ";
    }

    public String getGridMarker(int x,int y) {
        return grid[x][y];
    }

    public void generateNeighborhood(String filename)
    {
        generateNeighborhood();

        // Read file, create new address and add location to neighborhood map
        try {
            File file = new File(filename);
            Scanner scanner = new Scanner(file);
            while(scanner.hasNextLine())
            {
                String line = scanner.nextLine();
                String address[] = line.split(" ");
                int houseNum = Integer.parseInt(address[0]);
                int streetNum = Integer.parseInt(address[1]);
                int orderTime = Integer.parseInt(address[2]);
                add(new Address(houseNum,address[1].compareTo("East") == 0, streetNum, orderTime));
            }
        }
        catch (IOException e)
        {
            System.out.println("IOException encountered: " + e);
        }

        // Location of the distribution center, represented as "&"
        grid[distribution_center_num][distribution_center_street] = "& ";

    }

    public void generateNeighborhood(PriorityQueue<Address> addresses)
    {
        generateNeighborhood();

        // Add locations to neighborhood map
        Iterator<Address> iterator = addresses.iterator();
        while (iterator.hasNext())
            add(iterator.next());

        // Location of the distribution center, represented as "&"
        grid[distribution_center_num][distribution_center_street] = "& ";

    }

    public void add(Address ad)
    {
        // Location of an address, represented as "x"
        if (!ad.isDirection())
            grid[ad.getHouseNum()/10][ad.getStreetNum()*10] = "x ";
        else
            grid[ad.getStreetNum()*10][ad.getHouseNum()/10] = "x ";
    }

    public void addTruck(Truck truck)
    {
        this.truck[0] = truck;
    }

    public Address getDistributionCenter()
    {
        return new Address(distribution_center_num * 10,true,distribution_center_street / 10);
    }

    public int getDistributionCenterNum()
    {
        return distribution_center_num;
    }

    public int getDistributionCenterStreet()
    {
        return distribution_center_street;
    }

}
