/*
 * Address
 * Author: Trae Freeman
 * Last Updated: Sprint04
 */
package Simulation.Address;

import Simulation.Nouns.Time;

import java.util.Random;

public class Address implements Comparable<Address> {

    private boolean direction; //TRUE, 1 = east, FALSE, 0 = south
    private int houseNum; //house numbers are multiples of 10, starting at 100.  However, there are no houses at the multiples of 100s, as the first house on each street would be, for example, 110.
    private int streetNum; //street numbers start at 1, and go
    protected int orderTime;
    private int orderNumber;

    private static final int PM = 1200;
    private static int distribution_housenum;
    private static int distribution_streetnum;
    private static int bound;
    private static int orderNum = 0;

    protected Address() {
        Random rand = new Random();
        Time time = new Time();
        direction = false;//rand.nextBoolean();
        houseNum = getRandomHouseNum(rand.nextInt(10));
        streetNum = getRandomStreetNum();
        orderTime = Integer.parseInt(time.toString());

        orderNum++;
        orderNumber = orderNum;
    }

    public Address(int houseNum, boolean direction, int streetNum){
        if (houseNum >= 0 && houseNum < 2000)
            this.houseNum = houseNum;

        this.direction = direction;

        if (streetNum >= 0 && streetNum < 20)
            this.streetNum = streetNum;

        orderTime = 2359;

        orderNum++;
        orderNumber = orderNum;
    }

    public Address(int houseNum, boolean direction, int streetNum, int orderTime) {
        this(houseNum,direction,streetNum);

        if (orderTime >= 1000 && orderTime <= 1900)
            this.orderTime = orderTime;
    }

    private int getRandomNumberInRange(int min, int max) {
        if (min >= max) {
            throw new IllegalArgumentException("The max must be more than the min!");
        }

        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }

    /*
     * Author: Jonah Beers
     */
    public static void setBound(int newBound)
    {
        bound = newBound;
        distribution_streetnum = bound / 2;
        distribution_housenum = (bound / 2) * 10 + 10;
    }

    /*
     * Author: Jonah Beers
     */
    public int getRandomHouseNum(int rand)
    {
        int n = getRandomNumberInRange(0, bound - 1);

        n = n * 100;

        n = n + (rand * 10);

        if (n % 100 == 0 || n == 0)
            n = n + 10;
        return n;
    }

    /*
     * Author: Jonah Beers
     */
    public int getRandomStreetNum()
    {
        int n = getRandomNumberInRange(0, bound);
        return n;
    }

    public boolean isDirection() {
        return direction;
    }

    public String directionToString() {
        if (direction != false)
            return "South";
        else
            return "East";
    }

    public int getHouseNum()
    {
        return houseNum;
    }

    public int getStreetNum()
    {
        return streetNum;
    }

    public double distance()
    {
        // Based on actual line distance rather than actual time distance
        if (direction)
        {
            return Math.abs(distribution_housenum - houseNum) + Math.abs((distribution_streetnum * 100) - (streetNum * 100));
        }

        return Math.abs(distribution_housenum - (streetNum * 100)) + Math.abs((distribution_streetnum * 100) - houseNum);
    }

    @Override
    public String toString()
    {
        if(orderTime == 2359)
            return "Returning to " + Integer.toString(getHouseNum()) + " " + directionToString() + " " + Integer.toString(getStreetNum());

        if (orderTime < PM) {// am numbers 10:00 - 11:59
            String time = Integer.toString(orderTime);
            String hour = time; hour = hour.substring(0,2);
            String minute = time; minute = minute.substring(2);
            return hour + ":" + minute + "am " + Integer.toString(getHouseNum()) + " " + directionToString() + " " + Integer.toString(getStreetNum());
        }

        if (orderTime >= PM + 100) {// pm numbers 13:00 - 19:00
            int intTime = orderTime - PM;
            String time = Integer.toString(intTime);
            String hour = time; hour = hour.substring(0,1);
            String minute = time; minute = minute.substring(1);
            return hour + ":" + minute + "pm " + Integer.toString(getHouseNum()) + " " + directionToString() + " " + Integer.toString(getStreetNum());
        }

        if (orderTime != PM) {// pm numbers 12:01 - 12:59
            String time = Integer.toString(orderTime);
            String minute = time; minute = minute.substring(2);
            return "12:" + minute + "pm " + Integer.toString(getHouseNum()) + " " + directionToString() + " " + Integer.toString(getStreetNum());
        }

        //12:00 pm
        return "12:00pm " + Integer.toString(getHouseNum()) + " " + directionToString() + " " + Integer.toString(getStreetNum());
    }

    @Override
    public int compareTo(Address o) {
        return timeCompare(o);
    }
    //return: time == timeCompare(o) || distance == distanceCompare(o)

    private int distanceCompare(Address o) {
        if (distance() < o.distance())
            return -1;

        if (distance() > o.distance())
            return 1;

        return 0;
    }

    private int timeCompare(Address o) {
        if (this.orderTime < o.orderTime)
            return -1;

        if (this.orderTime > o.orderTime)
            return 1;

        if(this.orderNumber < o.orderNumber)
            return -1;

        if(this.orderNumber > o.orderNumber)
            return 1;

        return 0;
    }

    public String writeAddress() {
        return Integer.toString(getHouseNum()) + " " + directionToString() + " " + Integer.toString(getStreetNum()) + " " + orderTime;
    }

    public void resetOrderNum(){orderNum = 0;}

    public int getOrderNumber() {
        return orderNumber;
    }
}

