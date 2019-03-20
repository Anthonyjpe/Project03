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
        int tickCount = 0; //Number of moves
        int partial = 0; // Stores number of moves left to reach a corner

        Iterator<Address> iterator = addresses.iterator();
        while (iterator.hasNext()) {
            Address address = iterator.next();
            int dY = address.getStreetNum() * 10;
            int dX = address.getHouseNum() / 10;

            while (x != dX || y != dY) {

                if(partial != 0){ //Finish moving to corner
                    if(d == Direction.North)
                        for(;partial != 0; partial--)
                            y--;
                    else if (d == Direction.South)
                        for(;partial != 0; partial--)
                            y++;
                    else if (d == Direction.East)
                        for(;partial != 0; partial--)
                            x++;
                    else if (d == Direction.West)
                        for(;partial != 0; partial--)
                            x--;

                }


                if(d == Direction.Null){
                    d = Direction.North;
                    y--;
                    tickCount++;
                } else if(d == Direction.North){ // Y is correct (1), Y is above full (2), reverse (3), y is above partially (4)
                    if(y == dY){ //On this Y level *****1*****
                        if(x < dX) //To the right
                            d = Direction.East;
                        else if( x > dX) //To the left
                            d = Direction.West;
                    } else if( y - dY >= 10){ // Above this Y level by a full block or more *****2*****
                        for(int i = 0; i < 10; i++){ // Move a full block up (10 ticks)
                            tickCount++;
                            y--;
                        }
                    } else if( y < dY){ // Need to be facing south *****3*****
                        if(x < dX) //To the right
                            d = Direction.East;
                        else if( x > dX) //To the left
                            d = Direction.West;
                        else { //It is on the correct X level already
                            if (neighborhood.getGridMarker(x + 1,y) != "  ") // If the East block is not out of bounds
                                d = Direction.East;
                            else //If the east block is out of bounds, the right block is not
                                d = Direction.West;
                        }
                    } else if (y > dY){ // above this Y level by less than a block *****4*****
                        if(x < dX) //To the right
                            d = Direction.East;
                        else if( x > dX) //To the left
                            d = Direction.West;
                        else { // Y move partially up, logs into partial
                            for(int i = 0; y != dY; i++) {
                                y--;
                                partial = 10 - i;
                            }
                        }
                    }
                } else if(d == Direction.South){ // Y is correct (1), Y is below full (2), reverse (3), y is above partially (4)
                    if(y == dY) { //On this Y level *****1*****
                        if (x < dX) //To the right
                            d = Direction.East;
                        else if (x > dX) //To the left
                            d = Direction.West;
                    } else if (y - dY <= -10) { // Below this Y level by a full block or more *****2*****
                        for(int i = 0; i < 10; i++){ // Move a full block up (10 ticks)
                            tickCount++;
                            y++;
                        }
                    } else if (y > dY) { // Need to be facing north *****3*****
                        if(x < dX) //To the right
                            d = Direction.East;
                        else if( x > dX) //To the left
                            d = Direction.West;
                        else { //It is on the correct X level already
                            if (neighborhood.getGridMarker(x + 1,y) != "  ") // If the East block is not out of bounds
                                d = Direction.East;
                            else //If the east block is out of bounds, the right block is not
                                d = Direction.West;
                        }
                    } else { // below this Y level by less than a block *****4*****
                        if(x < dX) //To the right
                            d = Direction.East;
                        else if( x > dX) //To the left
                            d = Direction.West;
                        else { // Y move partially up, logs into partial
                            for(int i = 0; y != dY; i++) {
                                y++;
                                partial = 10 - i;
                            }
                        }
                    }
                } else if(d == Direction.East){// X is correct (1), X is below full (2), reverse (3), X is below partially (4)
                    if(x == dX) { //On this X level *****1*****
                        if (y < dY) //To the right
                            d = Direction.South;
                        else if (y > dY) //To the left
                            d = Direction.North;
                    } else if (x - dX <= -10) { // Below this X level by a full block or more *****2*****
                        for(int i = 0; i < 10; i++){ // Move a full block up (10 ticks)
                            tickCount++;
                            x++;
                        }
                    } else if (x > dX) { // Need to be facing West *****3*****
                        if(y < dY) //To the right
                            d = Direction.South;
                        else if( y > dY) //To the left
                            d = Direction.North;
                        else { //It is on the correct X level already
                            if (neighborhood.getGridMarker(x ,y + 1) != "  ") // If the East block is not out of bounds
                                d = Direction.South;
                            else //If the east block is out of bounds, the right block is not
                                d = Direction.North;
                        }
                    } else { // below this X level by less than a block *****4*****
                        if (y < dY) //To the right
                            d = Direction.South;
                        else if (y > dY) //To the left
                            d = Direction.North;
                        else { // Y move partially up, logs into partial
                            for(int i = 0; x != dX; i++) {
                                x++;
                                partial = 10 - i;
                            }
                        }
                    }
                } else if(d == Direction.West){// X is correct (1), X is above full (2), reverse (3), X is above partially (4)
                    if(x == dX) { //On this X level *****1*****
                        if (y < dY) //To the right
                            d = Direction.South;
                        else if (y > dY) //To the left
                            d = Direction.North;
                    } else if (x - dX >= 10) { // Above this X level by a full block or more *****2*****
                        for(int i = 0; i < 10; i++){ // Move a full block up (10 ticks)
                            tickCount++;
                            x--;
                        }
                    } else if (x < dX) { // Need to be facing West *****3*****
                        if(y < dY) //To the right
                            d = Direction.South;
                        else if( y > dY) //To the left
                            d = Direction.North;
                        else { //It is on the correct X level already
                            if (neighborhood.getGridMarker(x ,y + 1) != "  ") // If the East block is not out of bounds
                                d = Direction.South;
                            else //If the east block is out of bounds, the right block is not
                                d = Direction.North;
                        }
                    } else { // above this X level by less than a block *****4*****
                        if (y < dY) //To the right
                            d = Direction.South;
                        else if (y > dY) //To the left
                            d = Direction.North;
                        else { // Y move partially up, logs into partial
                            for(int i = 0; x != dX; i++) {
                                x--;
                                partial = 10 - i;
                            }
                        }
                    }
                }
            }
        }

        return tickCount;
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
