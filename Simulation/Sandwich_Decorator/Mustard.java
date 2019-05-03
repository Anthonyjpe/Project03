package Simulation.Sandwich_Decorator;

public class Mustard extends BreadDecorator {
    Bread bread;

    public String getDescription() {
        return bread.getDescription() + ", Mustard";
    }

    public double cost() {
        return bread.cost() + 0.25;
    }

    public int timeNeeded() {
        return bread.timeNeeded() + 30;
    }
}
