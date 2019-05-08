/*
 * Truck
 * Author: Trae Freeman
 * Last Updated: Sprint04
 */
package Simulation.Sandwich_Decorator;

public abstract class Bread {

    String description = "Nothingness";

    public String getDescription() {
        return description;
    }

    public abstract double cost();

    public abstract int timeNeeded(); // THIS IS IN SECONDS

    public abstract double getTax();

    public abstract String toString();

}
