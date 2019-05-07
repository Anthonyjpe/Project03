package Simulation.Sandwich_Decorator;

public class Lettuce extends BreadDecorator {
    Bread bread;

    public Lettuce(Bread bread){
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

    @Override
    public double getTax()
    {
        return (this.cost() * .10) + this.cost();
    }

    public String toString(){
        return bread.getDescription() + " $" + getTax() + " Time:" + timeNeeded();
    }
}
