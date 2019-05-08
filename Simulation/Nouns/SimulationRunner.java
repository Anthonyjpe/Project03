/*
 * Neighborhood Simulation
 * Author: Jonah Beers
 * Last Updated: Sprint04
 */
package Simulation.Nouns;

import Simulation.AbstractAndInterfaces.*;
import javax.swing.*;

public class SimulationRunner extends JFrame implements Observer
{
    private int height, width; // width and height of the window
    private static final int SLEEP_TIME = 100;
    private int bound;
    private RouteGUI gui;

    public SimulationRunner(double bound) throws InterruptedException
    {
        this.bound = (int) bound;
        height = (int) (792 * bound / 20); // default height for 20x20 is 792
        width = (int) (791 * bound / 20 - 20);  // default width for 20x20 is 791
        popUpWindow();
    }

    private void popUpWindow() throws InterruptedException
    {
        new DirectRunner();
    }

    @Override
    public void update(int x, int y, int dX, int dY) throws InterruptedException {
        //thread sleep
        try {Thread.sleep(SLEEP_TIME);}
        catch (InterruptedException ignored){ }

        //repaint
        gui.repaint();
        gui.start(x, y, dX, dY);
    }

    private class DirectRunner
    {
        private DirectRunner() throws InterruptedException
        {
            JFrame map = new JFrame();
            gui = new DirectRouteGUI(bound);
            map.add(gui);
            repaint();

            map.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            map.setTitle("Neighborhood Delivery Simulation");
            map.setSize(width, height);
            map.setResizable(false);
            map.setLocationRelativeTo(null);
            map.setVisible(true);
        }
    }
}
