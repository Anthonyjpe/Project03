/*
 * Neighborhood Simulation
 * Author: Jonah Beers
 * Last Updated: Sprint04
 */
package Simulation.Nouns;

import Simulation.AbstractAndInterfaces.*;
import Simulation.Nouns.Observer;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SimulationRunner extends JFrame implements Observer
{
    private int height, width; // width and height of the window
    private static final int SLEEP_TIME = 100;

    private int x, dX; // truck's current x location and x destination
    private int y, dY; // truck's current y location and y destination
    private int bound;
    private RouteGUI gui;

    public SimulationRunner(double bound) throws InterruptedException
    {
        this.bound = (int) bound;
        height = (int) (792 * ((bound / 20) + ((bound % 20) * 0.01))); // default height for 20x20 is 792
        width = (int) (791 * ((bound / 20) + ((bound % 20) * 0.01)));  // default width for 20x20 is 791
        x = (int) (Math.floor((bound - 1.) / 2.) * 10);
        y = (int) (Math.floor((bound - 1.)/ 2.) * 10);
        popUpWindow();
    }

    private void popUpWindow() throws InterruptedException
    {
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
