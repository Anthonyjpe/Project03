/*
 * Neighborhood Simulation
 * Author: Jonah Beers
 * Last Updated: Sprint04
 */
package Simulation.Drivers;

import Simulation.AbstractAndInterfaces.*;
import Simulation.Address.Address;
import Simulation.Address.AddressIO;
import Simulation.Nouns.Observer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.PriorityQueue;

public class SimulationRunner extends JFrame implements Observer
{
    private static final int HEIGHT = 792, WIDTH = 771; // width and height of the window
    private static final String TITLE = "Neighborhood Delivery Simulation"; // title of the window
    private JFrame selectionWindow, map;

    private static final int MARKER_SIZE = 5; // size of the houses and truck in the simulation
    private static final int BLOCK_DISTANCE = 40; // size of a block on the grid
    private static final int SLEEP_TIME = 100;

    private int x, dX; // truck's current x location and x destination
    private int y, dY; // truck's current y location and y destination
    private Address currentAddress; // stores the address for the current destination
    private PriorityQueue<Address> addresses = AddressIO.readAddresses(AddressIO.FILE); // read in addresses
    private RouteGUI gui;
    private RouteDistance route;

    private Color green = new Color(50, 205, 50); // color of truck when in motion
    private Color red = new Color(205, 50, 50); // color of truck when at a stop
    private Color orange = new Color(255, 128, 0); // color of the distribution center
    private Color purple = new Color(255,0,255); // color of the current destination

    public SimulationRunner() throws InterruptedException
    {
        x = 90;
        y = 90;
        popUpWindow();
    }

    private void popUpWindow() throws InterruptedException {
        selectionWindow = new JFrame();
        selectionWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        selectionWindow.setTitle("Route Selection");
        selectionWindow.setSize(300, 65);

        JPanel panel = new JPanel();

        JLabel choose = new JLabel("Choose a Route:");
        panel.add(choose);

        JButton directOption = new JButton("Direct");
        directOption.addActionListener(new Event1());
        panel.add(directOption);

        JButton rightOption = new JButton("Right");
        rightOption.addActionListener(new Event2());
        panel.add(rightOption);

        selectionWindow.add(panel);
        selectionWindow.setResizable(false);
        selectionWindow.setLocationRelativeTo(null);
        //selectionWindow.setVisible(true);

        new DirectRunner();
    }

    @Override
    // Author: Trae Freeman
    public void update(int x, int y, int dX, int dY) throws InterruptedException {
        //thread sleep  1
        try {Thread.sleep(SLEEP_TIME);}
        catch (InterruptedException ignored){ }

        //replace truck x & y   2
        this.x = x;
        this.y = y;
        this.dX = dX;
        this.dY = dY;

        //repaint   3
        gui.repaint();
        //start(x, y, dX, dY);
    }

    private class Event1 implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            selectionWindow.setVisible(false);
            try {
                new DirectRunner();
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }
        }
    }

    private class Event2 implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            selectionWindow.setVisible(false);
            try {
                new RightRunner();
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }
        }
    }

    private class RightRunner
    {
        private RightRunner() throws InterruptedException
        {
            map = new JFrame();
            route = new RouteRightDistance();
            gui = new RightRouteGUI();
            map.add(gui);
            gui.repaint();

            map.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            map.setTitle(TITLE);
            map.setSize(WIDTH, HEIGHT);
            map.setResizable(false);
            map.setLocationRelativeTo(null);
            map.setVisible(true);
        }
    }

    private class DirectRunner
    {
        private DirectRunner() throws InterruptedException
        {
            map = new JFrame();
            route = new RouteDirectDistance();
            gui = new DirectRouteGUI();
            map.add(gui);
            gui.repaint();

            map.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            map.setTitle(TITLE);
            map.setSize(WIDTH, HEIGHT);
            map.setResizable(false);
            map.setLocationRelativeTo(null);
            map.setVisible(true);
        }
    }

    public void paint(Graphics g)
    {
        // draw backdrop
        g.setColor(new Color(0,0,0));
        g.fillRect(0,0, WIDTH, HEIGHT);

        // draw streets
        g.setColor(new Color(255,255,255));
        for (int x = 0; x < 19; x++)
            for (int y = 0; y < 19; y++)
                g.drawRect(BLOCK_DISTANCE * x, BLOCK_DISTANCE * y, BLOCK_DISTANCE, BLOCK_DISTANCE);

        // draw deliveries
        for (Address address : addresses)
        {
            g.setColor(Color.BLUE);
            double y = (address.isDirection()) ? address.getHouseNum() / 100.0 : address.getStreetNum();
            double x = (!address.isDirection()) ? address.getHouseNum() / 100.0 : address.getStreetNum();
            if(address == currentAddress) // if the address is the current destination
                g.setColor(purple);
            g.fillOval(((int) x) * BLOCK_DISTANCE - 2 + (int) (40.0 * (x % 1)), ((int) y) * BLOCK_DISTANCE - 2 + (int) (40.0 * (y % 1)), MARKER_SIZE, MARKER_SIZE);
        }

        // draw distribution center
        g.setColor(orange);
        g.fillRect(9 * BLOCK_DISTANCE - 2, 9 * BLOCK_DISTANCE + 2, MARKER_SIZE, MARKER_SIZE);

        // draw truck
        if (x == dX && y == dY) // if the truck has reached its destination
        {
            g.setColor(red);
            g.fillOval(x * 4 - 2,y * 4 - 2, MARKER_SIZE, MARKER_SIZE);
        }
        else  // if the truck is still en route
        {
            g.setColor(green);
            g.fillOval(x * 4 - 2, y * 4 - 2, MARKER_SIZE, MARKER_SIZE);
        }
    }

   // public static void main(String[] args) throws InterruptedException { new SimulationRunner(); }

}
