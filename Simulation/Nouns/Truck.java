/*
 * Truck
 * Author: Anthony Estephan & Trae Freeman
 * Last Updated: Sprint04
 */
package Simulation.Nouns;

import Simulation.Address.Address;
import Simulation.Drivers.SimulationRunner;
import Simulation.Enumerators.Direction;
import Simulation.AbstractAndInterfaces.RouteDirectDistance;
import Simulation.AbstractAndInterfaces.RouteDirectTime;
import Simulation.AbstractAndInterfaces.RouteDistance;
import Simulation.AbstractAndInterfaces.RouteTime;

import java.util.*;

public class Truck extends Subject{
    private int xLocation;
    private int yLocation;
    private Direction direction;
    private Neighborhood neighborhood;
    private RouteDistance route;
    private RouteTime routeTime;
    protected Queue<Direction> movement;
    private ArrayList<Observer> observers;
    private SimulationRunner simRunner;
    private PriorityQueue<Address> addressList;

    public Truck(Neighborhood neighborhood){
        this.neighborhood = neighborhood;
        xLocation = neighborhood.getDistributionCenterStreet();
        yLocation = neighborhood.getDistributionCenterNum();
        direction = Direction.Null;
        route = new RouteDirectDistance();
        routeTime = new RouteDirectTime();
        movement = new LinkedList<>();
        observers = new ArrayList<>();
        addressList = new PriorityQueue<>();
        try{
            simRunner = new SimulationRunner();
            registerObservers(simRunner);
        }
        catch (InterruptedException e){
            System.out.println("Sleep failed");
        }
    }

    public double route(PriorityQueue<Address> addresses){
       return Math.round(this.route.route(addresses,this) * 100.) / 100.;
    }

    public String routeTime(PriorityQueue<Address> addresses){
        //double rounded to nearest tenth
        double hours = this.routeTime.route(addresses,this);
        double minutes = hours - Math.floor(hours); minutes *= 60;
        double seconds = minutes - Math.floor(minutes); seconds *= 60;

        //casting double to int after flooring (ceiling for seconds)
        int hour = (int) Math.floor(hours); int minute = (int) Math.floor(minutes); int second = (int) Math.ceil(seconds);

        //plural for double, singular for int
        return hour + "hrs " + minute + "mins " + second + "secs";
    }

    public int getXLocation() {
        return xLocation;
    }

    public void setXLocation(int xLocation) {
        this.xLocation = xLocation;
    }

    public int getYLocation() {
        return yLocation;
    }

    public void setYLocation(int yLocation) {
        this.yLocation = yLocation;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public Neighborhood getNeighborhood(){
        return neighborhood;
    }

    public void setRoute(RouteDistance route){
        this.route = route;
    }

    public void setRouteTime(RouteTime route) { this.routeTime = route;}

    public String seeRoute(){
        return this.route.toString();
    }

    public String seeRouteTime(){ return this.routeTime.toString();}

    public void addMove(Direction direction){ movement.add(direction); }

    public void move()
    {
        if(!movement.isEmpty()) {
            if(xLocation == addressList.peek().getHouseNum() / 10 && yLocation == addressList.peek().getStreetNum() * 10){
                addressList.poll();
            }

            switch (movement.poll()) {
                case Up:
                    yLocation--;
                    notifyObservers();
                    break;
                case Down:
                    yLocation++;
                    notifyObservers();
                    break;
                case Right:
                    xLocation++;
                    notifyObservers();
                    break;
                case Left:
                    xLocation--;
                    notifyObservers();
                    break;
            }
        }

    }

    public void resetRoute(){
        while(!movement.isEmpty()){
            movement.poll();
        }
    }

    public boolean canMove(){
        return !movement.isEmpty();
    }

    @Override
    public void notifyObservers(){
        for (int i = 0; i < observers.size(); i++){
            try {
                observers.get(i).update(this.getXLocation(), this.getYLocation(),addressList.peek().getHouseNum() / 10,addressList.peek().getStreetNum() * 10 );
            }
            catch (InterruptedException ignored) { }
        }
    }

    public void registerObservers(Observer o){
        observers.add(o);
//        notifyObservers();
    }

    public void removeObservers(Observer o){
        observers.remove(o);
        notifyObservers();
    }

    public void addAddress(Address a){
        addressList.add(a);
    }
}
