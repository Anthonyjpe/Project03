/*
 * Time
 * Author: Trae Freeman
 * Last Updated: Sprint04
 */
package Simulation.Nouns;

import java.util.Random;

public class Time { //REMEMBER: This is Military time; meaning deliveries are from 1000 - 1900
    private int hour; //make toString give AM/PM depending on hour
    private int minute;

    private int getRandomNumberInRange(int min, int max){
        if (min >= max){
            throw new IllegalArgumentException("The max must be more than the min!");
        }

        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }

    public Time(){
        hour = getRandomHour();
        if(hour < 900)
            minute = getRandomMinute();
    }

    private int getRandomHour(){
        int x;
        x = getRandomNumberInRange(0, 9);
        return x * 100;
    }

    private int getRandomMinute(){
        int x;
        x = getRandomNumberInRange(0, 59);
        return x;
    }

    private int getHour() {
        return hour;
    }

    private int getMinute() {
        return minute;
    }

    @Override
    public String toString(){
        int milTime = 1000;
        milTime = milTime + getHour() + getMinute();
        return (Integer.toString(milTime));
    }
}