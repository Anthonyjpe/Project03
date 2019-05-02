package Simulation.Sandwich_Decorator;

public class Ham extends BreadDecorator {
    Bread bread;

    public Ham(Bread bread){
        this.bread = bread;
    }

    public String getDescription() {
        return bread.getDescription() + ", Ham";
    }

    public double cost() {
        return bread.cost() + 1.50;
    }

    public int timeNeeded() {
        return bread.timeNeeded() + 30;
    }

}
