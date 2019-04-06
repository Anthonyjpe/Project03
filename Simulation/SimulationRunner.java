/*
 * Neighborhood Simulation
 * Author: Jonah Beers
 * Last Updated: Sprint03
 */
package Simulation;

import javax.swing.*;

public class SimulationRunner {

    private static final int HEIGHT = 782, WIDTH = 761; // width and height of the window
    private static final String TITLE = "Neighborhood Delivery Simulation"; // title of the window

    private SimulationRunner() throws InterruptedException {
        JFrame map = new JFrame();
        final NeighborhoodGUI neighborhoodGUI = new NeighborhoodGUI();
        map.add(neighborhoodGUI);

        map.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        map.setTitle(TITLE);
        map.setSize(WIDTH, HEIGHT);
        map.setResizable(false);
        map.setLocationRelativeTo(null);
        map.setVisible(true);

        neighborhoodGUI.start(); // start the simulation
    }

    public static void main(String[] args) throws InterruptedException
    {
        new SimulationRunner();
    }
}
