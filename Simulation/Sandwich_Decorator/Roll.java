package Simulation.Sandwich_Decorator;


public class Roll extends Bread {
    Bread bread;

    public Roll(){
        description = "Roll";
        this.bread = bread;
    }

    public double cost() {
        return 0.75;
    }

    public int timeNeeded()
    {
        return 60;
    }
}
