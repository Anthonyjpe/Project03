/*
 * Truck
 * Author: Trae Freeman
 * Last Updated: Sprint04
 */
package Simulation.Sandwich_Decorator;

public class Mustard extends BreadDecorator {
    Bread bread;

    public Mustard(Bread bread){
        this.bread = bread;
    }

    public String getDescription() {
        return bread.getDescription() + ", Mustard";
    }

    public double cost() {
        return bread.cost() + 0.25;
    }

    public int timeNeeded() {
        return bread.timeNeeded() + 30;
    }

    @Override
    public double getTax()
    {
        return (this.cost() * .10) + this.cost();
    }

    public String toString(){
        return bread.getDescription() + " $" + getTax() + " Time:" + timeNeeded();
    }
}
