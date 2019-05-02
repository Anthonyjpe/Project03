package Simulation.Sandwich_Decorator;

public class Cheese extends BreadDecorator {
    Bread bread;

    public Cheese(Bread bread){
        this.bread = bread;
    }

    public String getDescription() {
        return bread.getDescription() + ", Cheese";
    }

    public double cost() {
        return bread.cost() + 0.75;
    }

    @Override
    public int timeNeeded() {
        return bread.timeNeeded() + 40;
    }
}
