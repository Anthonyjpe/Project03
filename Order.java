import java.util.Random;

public class Order {

    Sandwich sandwich;
    Chips chips;
    Drinks drink;

    static Random rand = new Random();

    protected Order(){
        sandwich = randomEnum(Sandwich.class);
        chips = randomEnum(Chips.class);
        drink = randomEnum(Drinks.class);
    }

    public Sandwich getSandwich() {
        return sandwich;
    }

    public void setSandwich(Sandwich sandwich) {
        this.sandwich = sandwich;
    }

    public void setChips(Chips chips) {
        this.chips = chips;
    }

    public Drinks getDrink() {
        return drink;
    }

    public void setDrink(Drinks drink) {
        this.drink = drink;
    }

    public Chips getChips() {
        return chips;
    }

    public static <T extends Enum<?>> T randomEnum(Class<T> clazz){
        int x = rand.nextInt(clazz.getEnumConstants().length);
        return clazz.getEnumConstants() [x];
    }

    @Override
    public String toString() {
        return sandwich + " " + chips + " " + drink;
    }
}
