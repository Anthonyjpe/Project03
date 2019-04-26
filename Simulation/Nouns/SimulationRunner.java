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
    private static final int HEIGHT = 792, WIDTH = 771; // width and height of the window
    private JFrame selectionWindow, map;
    private static final int SLEEP_TIME = 100;

    private int x, dX; // truck's current x location and x destination
    private int y, dY; // truck's current y location and y destination
    private RouteGUI gui;
    private RouteDistance route;

    public SimulationRunner() throws InterruptedException
    {
        x = 90;
        y = 90;
        popUpWindow();
    }

    private void popUpWindow() throws InterruptedException{
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

        //new RightRunner();
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

    private class Event1 implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            //selectionWindow.setVisible(false);
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
            repaint();

            map.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            map.setTitle("Neighborhood Delivery Simulation - Right Route");
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
            repaint();

            map.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            map.setTitle("Neighborhood Delivery Simulation - Direct Route");
            map.setSize(WIDTH, HEIGHT);
            map.setResizable(false);
            map.setLocationRelativeTo(null);
            map.setVisible(true);
        }
    }
}
