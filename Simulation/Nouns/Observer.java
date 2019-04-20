/*
 * Order
 * Author: Trae Freeman
 * Last Updated: Sprint04
 */
package Simulation.Nouns;

public interface Observer {

    void update(int x, int y, int dX, int dY) throws InterruptedException; //all information that neighborhood needs
}
