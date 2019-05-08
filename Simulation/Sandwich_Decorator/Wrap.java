package Simulation.Sandwich_Decorator;

public class Wrap extends Bread {
    Bread bread;

    public Wrap(){
        description = "Wrap";
        this.bread = this;
    }

    public double cost() { return 1.75; }

    @Override
    public int timeNeeded() {
        return 150;
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
