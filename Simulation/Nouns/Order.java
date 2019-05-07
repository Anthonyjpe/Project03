/*
 * Order
 * Author: Trae Freeman
 * Last Updated: Sprint04
 */
package Simulation.Nouns;

import Simulation.Enumerators.BreadTypes;
import Simulation.Enumerators.SandwichAddons;
import Simulation.Sandwich_Decorator.Bread;
import Simulation.Sandwich_Decorator.Roll;
import Simulation.Sandwich_Decorator.Wrap;

import java.util.ArrayList;
import java.util.Random;

public class Order extends Bread{

    Bread breadType;
    BreadTypes breadInitial; //roll or wrap
    ArrayList toppingsList; //includes meat

    static Random rand = new Random();

    public Order(){
        breadInitial = randomEnum(BreadTypes.class);
        switch (breadInitial){
            case Wrap:
                breadType = new Wrap();

            case Roll:
                breadType = new Roll();
        }
        for (int i = 0; i > getRandomNumOfTopping(); i++)
            toppingsList.add(getRandomTopping());
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
}
