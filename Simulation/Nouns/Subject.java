/*
 * Order
 * Author: Trae Freeman
 * Last Updated: Sprint04
 */
package Simulation.Nouns;

import java.util.ArrayList;

abstract class Subject {

    //array of observers; neighborhood into observer
    private ArrayList<Observer> observers;

    public void notifyObservers() {
        //for each in array call update
    }

    public void registerObservers(Observer o) //Observer put into the array of observers
    {

    }

    public void removeObservers(Observer o) {

    }
}
