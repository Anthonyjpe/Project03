package Simulation.Sandwich_Decorator;

public class Mayonnaise extends BreadDecorator {

    Bread bread;

    public String getDescription() {
        return bread.getDescription() + ", Mayonnaise";
    }

    public double cost() {
        return bread.cost() + 1.25;
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
