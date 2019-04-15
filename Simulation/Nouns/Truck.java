/*
 * Truck
 * Author: Anthony Estephan
 * Last Updated: Sprint03
 */
package Simulation.Nouns;

import Simulation.Address.Address;
import Simulation.Enumerators.Direction;
import Simulation.AbstractAndInterfaces.RouteDirectDistance;
import Simulation.AbstractAndInterfaces.RouteDirectTime;
import Simulation.AbstractAndInterfaces.RouteDistance;
import Simulation.AbstractAndInterfaces.RouteTime;

import java.util.PriorityQueue;

public class Truck {
    private int xLocation;
    private int yLocation;
    private Direction direction;
    private Neighborhood neighborhood;
    private RouteDistance route;
    private RouteTime routeTime;

    public Truck(Neighborhood neighborhood){
        this.neighborhood = neighborhood;
        xLocation = neighborhood.getDistributionCenterStreet();
        yLocation = neighborhood.getDistributionCenterNum();
        direction = Direction.Null;
        route = new RouteDirectDistance();
        routeTime = new RouteDirectTime();
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

}
