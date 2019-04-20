/*
 * Route Interface
 * Author: Jonah Beers
 * Last Updated: Sprint03
 */
package Simulation.AbstractAndInterfaces;

import javax.swing.*;

public abstract class RouteGUI extends JPanel{

    public abstract void start() throws InterruptedException;

    public abstract void start(int x, int y, int dX, int dY) throws InterruptedException;

}
