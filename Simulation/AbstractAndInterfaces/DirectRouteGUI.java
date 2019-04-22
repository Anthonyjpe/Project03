/*
 * Direct Route
 * Author: Jonah Beers
 * Last Updated: Sprint04
 */
package Simulation.AbstractAndInterfaces;

import Simulation.Address.Address;
import Simulation.Address.AddressIO;
import java.awt.*;
import java.util.PriorityQueue;

public class DirectRouteGUI extends RouteGUI{

    private static final int MARKER_SIZE = 5; // size of the houses and truck in the simulation
    private static final int BLOCK_DISTANCE = 40; // size of a block on the grid

    private static int x, dX; // truck's current x location and x destination
    private static int y, dY; // truck's current y location and y destination
    private PriorityQueue<Address> addresses = AddressIO.readAddresses(AddressIO.FILE); // read in addresses

    private Color green = new Color(50, 205, 50); // color of truck when in motion
    private Color red = new Color(205, 50, 50); // color of truck when at a stop
    private Color orange = new Color(255, 128, 0); // color of the distribution center

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
        for (int x = 0; x < 19; x++)
            for (int y = 0; y < 19; y++)
                g.drawRect(BLOCK_DISTANCE * x + 5, BLOCK_DISTANCE * y + 5, BLOCK_DISTANCE, BLOCK_DISTANCE);

        // draw deliveries
        for (Address address : addresses)
        {
            g.setColor(new Color(0,255,255));

            /*double xVal;
            double yVal;
            if(address.isDirection())
            {
                xVal = address.getHouseNum() / 100.0;
                yVal = address.getStreetNum();
            }
            else
            {
                xVal = address.getStreetNum();
                yVal = address.getHouseNum() / 100.0;
            }*/

            double y = (address.isDirection()) ? address.getHouseNum() / 100.0 : address.getStreetNum();
            double x = (!address.isDirection()) ? address.getHouseNum() / 100.0 : address.getStreetNum();
            g.fillOval(((int) x) * BLOCK_DISTANCE - 2 + (int) (40.0 * (x % 1)) + 5, ((int) y) * BLOCK_DISTANCE - 2 + (int) (40.0 * (y % 1)) + 5, MARKER_SIZE, MARKER_SIZE);
        }

        // draw distribution center
        g.setColor(orange);
        g.fillRect(9 * BLOCK_DISTANCE - 2 + 5, 9 * BLOCK_DISTANCE + 2 + 5, MARKER_SIZE, MARKER_SIZE);

        // draw truck
        if (x == dX && y == dY) // if the truck has reached its destination
        {
            g.setColor(red);
            g.fillOval(x * 4 - 2 + 5,y * 4 - 2 + 5, MARKER_SIZE, MARKER_SIZE);
        }
        else  // if the truck is still en route
        {
            g.setColor(green);
            g.fillOval(x * 4 - 2 + 5, y * 4 - 2 + 5, MARKER_SIZE, MARKER_SIZE);
        }
    }
}
