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

    public int route(PriorityQueue<Address> addresses){
       return this.route.route(addresses,this);
    }

    public int routeTime(PriorityQueue<Address> addresses){
        return this.routeTime.route(addresses,this);
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
