package Simulation.Sandwich_Decorator;


import java.io.BufferedReader;

public class Wrap extends Bread {
    Bread bread;

    public Wrap(){
        description = "Wrap";
        this.bread = bread;
    }

    public double cost() { return 0.50; }

    @Override
    public int timeNeeded() {
        return 75;
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
