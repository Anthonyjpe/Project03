package Simulation.Sandwich_Decorator;

public class SandwichMaker {

    public static void main(String[] args) {
        Bread sand1 = new Roll();
        System.out.println(sand1.getDescription() + " $" + sand1.cost() + " Time:" + sand1.timeNeeded());

        Bread sand2 = new Roll();
        sand2 = new Ham(sand2);
        sand2 = new Turkey(sand2);
        sand2 = new Cheese(sand2);
        sand2 = new Tomato(sand2);
        System.out.println(sand2.getDescription() + " $" + sand2.cost() + " Time:" + sand2.timeNeeded());
    }
}
