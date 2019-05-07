package Simulation.Sandwich_Decorator;


public class Roll extends Bread {
    Bread bread;

    public Roll(){
        description = "Roll";
        this.bread = this;
    }

    public double cost() {
        return 0.75;
    }

    public int timeNeeded()
    {
        return 60;
    }


    public double getTax()
    {
        return (this.cost() * .10) + this.cost();
    }

    @Override
    public String toString(){
        return bread.getDescription() + " $:" + getTax() + " Time:" + timeNeeded();
    }
}
