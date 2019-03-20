package Simulation;

import java.util.Iterator;
import java.util.PriorityQueue;

public class Truck {
    private int xLocation;
    private int yLocation;
    private Direction direction;
    private Neighborhood neighborhood;

    Truck(Neighborhood neighborhood){
        xLocation = 90;
        yLocation = 91;
        this.neighborhood = neighborhood;
        direction = Direction.Null;
    }

    public int route(PriorityQueue<Address> addresses){// 204 tickCOunt breaks, dx - x should stop at a mutiple of 10 (Corner) but does not
        int x = xLocation;
        int y = yLocation;
        Direction d = direction;
        int tickCount = 0;

        Iterator<Address> iterator = addresses.iterator();
        while (iterator.hasNext()) {
            Address address = iterator.next();
            int dY = address.getStreetNum() * 10;
            int dX = address.getHouseNum() / 10;

            while (x != dX || y != dY) {
                System.out.println(tickCount + " " + d);
                if(d == Direction.North || d == Direction.South || d == Direction.Null) {
                    if (d == Direction.Null) {//Fresh truck
                        if (y > dY)
                            d = Direction.North;
                        else
                            d = Direction.South;
                    }

                    if (d == Direction.North && dY % 10 == 0 && canMove(x, y,d)) { //House on x-axis
                        y--;
                        tickCount++;
                    }

                    else if (d == Direction.North && Math.abs(dY - y) >= dY % 10 && canMove(x, y,d)) { // House on y - axis
                        y--;
                        tickCount++;
                    }

                    else if (isCorner(x,y,d) && y >= dY){
                        if (x > dX)
                            d = Direction.West;
                        else
                            d = Direction.East;
                    }

                    else if (d == Direction.South && dY % 10 == 0 && canMove(x, y,d)) { //House on x-axis
                        y++;
                        tickCount++;
                    }

                    else if (d == Direction.South && Math.abs(dY - y) >= dY % 10 && canMove(x, y,d)) { // House on y - axis
                        y++;
                        tickCount++;
                    }

                    else if (isCorner(x, y,d) && y <= dY) {
                        if (x > dX)
                            d = Direction.West;
                        else
                            d = Direction.East;
                    }
                }


                if(d == Direction.East || d == Direction.West) {
                    if (d == Direction.West && dX % 10 == 0 && canMove(x, y,d)) { //House on x-axis
                        x--;
                        tickCount++;
                    }

                    else if (d == Direction.West && Math.abs(dX - x) >= dX % 10 && canMove(x, y,d)) { // House on y - axis
                        x--;
                        tickCount++;
                    }

                    else if(isCorner(x, y,d) && x >= dX){
                        if (y > dY)
                            d = Direction.North;
                        else
                            d = Direction.South;
                    }

                    else if (d == Direction.East && dX % 10 == 0 && canMove(x, y,d)) { //House on x-axis
                        x++;
                        tickCount++;
                    }

                    else if (d == Direction.East && Math.abs(dX - x) >= dX % 10 && canMove(x, y,d)) { // House on y - axis
                        x++;
                        tickCount++;
                    }

                    else if (isCorner(x, y,d) && x <= dX) {
                        if (y > dY)
                            d = Direction.North;
                        else
                            d = Direction.South;
                    }
                }
            }
        }

        return tickCount;
    }

    private Boolean isCorner(int x, int y, Direction d){
        if(d == Direction.North || d == Direction.South){
            if(( x + 1 < 201 && neighborhood.getGridMarker(x + 1,y) != "  " ) || (x - 1 > -1 && neighborhood.getGridMarker(x - 1,y) != "  "))
                return true;
        }
        if(d == Direction.East || d == Direction.West){
            if ((y + 1 < 201 && neighborhood.getGridMarker(x,y + 1) != "  " ) || (y - 1 > -1 && neighborhood.getGridMarker(x ,y - 1) != "  "))
               return true;
        }
        return false;
    }

    private Boolean canMove(int x, int y, Direction d){
        if(d == Direction.North && y - 1 < -1 && neighborhood.getGridMarker(x ,y - 1) != "  ")
            return true;
        if(d == Direction.South && y + 1 < 201 && neighborhood.getGridMarker(x ,y + 1) != "  ")
            return true;
        if(d == Direction.West && x + 1 < 201 && neighborhood.getGridMarker(x + 1 ,y) != "  ")
            return true;
        if(d == Direction.East && x - 1 > -1 && neighborhood.getGridMarker(x - 1,y) != "  ")
            return true;
        return false;
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

}
