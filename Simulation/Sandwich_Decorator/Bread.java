package Simulation.Sandwich_Decorator;

public abstract class Bread {

    String description = "Nothingness";

    public String getDescription() {
        return description;
    }

    public abstract double cost();

    public abstract int timeNeeded(); // THIS IS IN SECONDS


    //add methods to cover, bag, and tax e/ cost of a sandwich

}
