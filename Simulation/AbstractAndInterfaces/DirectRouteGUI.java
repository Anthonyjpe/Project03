/*
 * Direct Route
 * Author: Jonah Beers
 * Last Updated: Sprint04
 */
package Simulation.AbstractAndInterfaces;

import Simulation.Address.Address;
import Simulation.Address.AddressIO;
import Simulation.Nouns.OrderOfEvents;

import java.awt.*;
import java.util.PriorityQueue;

public class DirectRouteGUI extends RouteGUI{

    private static final int MARKER_SIZE = 5; // size of the houses and truck in the simulation
    private static final int BLOCK_DISTANCE = 40; // size of a block on the grid

    private int bound; // how many streets the neighborhood has
    private static int x, dX; // truck's current x location and x destination
    private static int y, dY; // truck's current y location and y destination
    private PriorityQueue<Address> addresses = AddressIO.readAddresses(AddressIO.FILE); // read in addresses

    public DirectRouteGUI(int bound)
    {
        this.bound = bound;
    }

    @Override
    // Author: Trae Freeman
    public void start(int x, int y, int dX, int dY)
    {
        DirectRouteGUI.x = x;
        DirectRouteGUI.y = y;
        DirectRouteGUI.dX = dX;
        DirectRouteGUI.dY = dY;
        repaint();
    }

    public void paint(Graphics g)
    {
        // set backdrop
        g.setColor(new Color(0,0,0));
        g.fillRect(0,0,800,800);

       // draw streets
        g.setColor(new Color(255,255,255));
        for (int x = 0; x < bound - 1; x++)
            for (int y = 0; y < bound - 1; y++)
                g.drawRect(BLOCK_DISTANCE * x + 5, BLOCK_DISTANCE * y + 5, BLOCK_DISTANCE, BLOCK_DISTANCE);

        // draw deliveries
        for (Address address : addresses)
        {
            g.setColor(new Color(0,255,255));
            double y = (address.isDirection()) ? address.getHouseNum() / 100.0 : address.getStreetNum();
            double x = (!address.isDirection()) ? address.getHouseNum() / 100.0 : address.getStreetNum();
            g.fillOval(((int) x) * BLOCK_DISTANCE - 2 + (int) (40.0 * (x % 1)) + 5, ((int) y) * BLOCK_DISTANCE - 2 + (int) (40.0 * (y % 1)) + 5, MARKER_SIZE, MARKER_SIZE);
        }

        // draw distribution center
        g.setColor(new Color(255, 128, 0));
        g.fillRect((int) (Math.floor((bound - 1.) / 2.)) * BLOCK_DISTANCE +3, (int) (Math.floor((bound - 1.)/ 2.) + 1) * BLOCK_DISTANCE - 33, MARKER_SIZE, MARKER_SIZE);

        // draw truck
        if ((x == dX && y == dY) || (x == dY && y == dX)) // if the truck has reached its destination
        {
            g.setColor(new Color(205, 50, 50));
            g.fillOval(x * 4 - 2 + 5,y * 4 - 2 + 5, MARKER_SIZE, MARKER_SIZE);
        }
        else  // if the truck is still en route
        {
            g.setColor(new Color(255,255,0));
            g.fillOval(x * 4 - 2 + 5, y * 4 - 2 + 5, MARKER_SIZE, MARKER_SIZE);
        }
    }
}
