/*
 * Neighborhood Simulation
 * Author: Jonah Beers
 * Last Updated: Sprint03
 */
package Simulation;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SimulationRunner extends JFrame
{
    private static final int HEIGHT = 782, WIDTH = 761; // width and height of the window
    private static final String TITLE = "Neighborhood Delivery Simulation"; // title of the window
    private JFrame selectionWindow, map;

    private SimulationRunner() throws InterruptedException
    {
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
            RouteGUI neighborhoodGUI = new RightRouteGUI();
            map.add(neighborhoodGUI);

            map.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            map.setTitle(TITLE);
            map.setSize(WIDTH, HEIGHT);
            map.setResizable(false);
            map.setLocationRelativeTo(null);
            map.setVisible(true);

            neighborhoodGUI.start(); // start the simulation
        }
    }

    private class DirectRunner
    {
        private DirectRunner() throws InterruptedException
        {
            map = new JFrame();
            RouteGUI neighborhoodGUI = new DirectRouteGUI();
            map.add(neighborhoodGUI);

            map.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            map.setTitle(TITLE);
            map.setSize(WIDTH, HEIGHT);
            map.setResizable(false);
            map.setLocationRelativeTo(null);
            map.setVisible(true);

            neighborhoodGUI.start(); // start the simulation
        }
    }

    public static void main(String[] args) throws InterruptedException
    {
        new SimulationRunner();
    }

}
