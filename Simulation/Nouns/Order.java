/*
 * Order
 * Author: Trae Freeman
 * Last Updated: Sprint04
 */
package Simulation.Nouns;

import Simulation.Enumerators.BreadTypes;
import Simulation.Enumerators.SandwichAddons;
import Simulation.Sandwich_Decorator.*;

import java.util.ArrayList;
import java.util.Random;

public class Order{

    Bread breadType;
    BreadTypes breadInitial; //roll or wrap
    ArrayList toppingsList; //includes meat

    static Random rand = new Random();

    public Order(){
        toppingsList = new ArrayList();
        breadInitial = randomEnum(BreadTypes.class);
        switch (breadInitial){
            case Wrap:
                breadType = new Wrap();
                break;
            case Roll:
                breadType = new Roll();
                break;
        }
        for (int i = 0; i < getRandomNumOfTopping(); i++) {
            SandwichAddons sa = getRandomTopping();
            switch (sa){
                case Ham:
                    breadType = new Ham(breadType);
                    break;
                case Cheese:
                    breadType = new Cheese(breadType);
                    break;
                case Tomato:
                    breadType = new Tomato(breadType);
                    break;
                case Turkey:
                    breadType = new Turkey(breadType);
                    break;
                case Lettuce:
                    breadType = new Lettuce(breadType);
                    break;
                case Mustard:
                    breadType = new Mustard(breadType);
                    break;
                case Mayonnaise:
                    breadType = new Mayonnaise(breadType);
                    break;

            }

        }

    }

    public Bread getBreadType() {
        return breadType;
    }

    public void Bread(Bread bread) {
        this.breadType = bread;
    }


    public int getRandomNumOfTopping(){
        return rand.nextInt(20);
    }

    public SandwichAddons getRandomTopping(){
        return randomEnum(SandwichAddons.class);
    }


    public static <T extends Enum<?>> T randomEnum(Class<T> clazz){
        int x = rand.nextInt(clazz.getEnumConstants().length);
        return clazz.getEnumConstants() [x];
    }

    public String toString() {
        return breadType.toString();
    }
}
