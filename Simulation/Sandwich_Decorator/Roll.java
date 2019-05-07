package Simulation.Sandwich_Decorator;


public class Roll extends Bread {
    Bread bread;

    public Roll(){
        description = "Roll";
        this.bread = this;
    }

    public double cost() {
        return 2.00;
    }

    public int timeNeeded()
    {
        return 155;
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
