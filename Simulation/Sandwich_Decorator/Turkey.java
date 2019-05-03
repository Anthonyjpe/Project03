package Simulation.Sandwich_Decorator;

public class Turkey extends BreadDecorator {

    Bread bread;

    public Turkey(Bread bread){
        this.bread = bread;
    }

    public String getDescription() {
        return bread.getDescription() + ", Turkey";
    }

    public double cost() {
        return bread.cost() + 1.25;
    }

    public int timeNeeded() {
        return bread.timeNeeded() + 30;
    }
}
