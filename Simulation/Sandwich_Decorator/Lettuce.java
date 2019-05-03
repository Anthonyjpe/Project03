package Simulation.Sandwich_Decorator;

public class Lettuce extends BreadDecorator {
    Bread bread;

    public Lettuce(){
        this.bread = bread;
    }

    public String getDescription() {
        return bread.getDescription() + ", Lettuce";
    }

    public double cost() {
        return bread.cost() + 0.50;
    }

    public int timeNeeded() {
        return bread.timeNeeded() + 20;
    }
}
