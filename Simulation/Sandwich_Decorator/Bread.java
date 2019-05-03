package Simulation.Sandwich_Decorator;

public abstract class Bread {

    String description = "Nothingness";

    public String getDescription() {
        return description;
    }

    public abstract double cost();

    public abstract int timeNeeded(); // THIS IS IN SECONDS

    public abstract double getTax();

    public abstract String toString();


    //add methods to cover, bag, and tax e/ cost of a sandwich

}
