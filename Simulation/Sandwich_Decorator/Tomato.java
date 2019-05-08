/*
 * Truck
 * Author: Trae Freeman
 * Last Updated: Sprint04
 */
package Simulation.Sandwich_Decorator;

public class Tomato extends BreadDecorator{
    Bread bread;

    public Tomato(Bread bread){
        this.bread = bread;
    }
    public String getDescription() {
        return bread.getDescription() + ", Tomato";
    }

    @Override
    public double cost() {
        return bread.cost() + 0.75;
    }

    @Override
    public int timeNeeded() {
        return bread.timeNeeded() + 20;
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
