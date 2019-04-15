/*
 * RouteRightTime
 * Author: Anthony Estephan
 * Last Updated: Sprint03
 */
package Simulation.AbstractAndInterfaces;

import Simulation.Address.Address;
import Simulation.Enumerators.Direction;
import Simulation.Nouns.Truck;

import java.util.Iterator;
import java.util.PriorityQueue;

public class RouteRightTime implements RouteTime {

    @Override
    public double route(PriorityQueue<Address> addresses, Truck truck) {
        int x = truck.getXLocation();
        int y = truck.getYLocation();
        Direction d = truck.getDirection();
        double tickCount = 0; //Number of moves
        int partial = 0; // Stores number of moves left to reach a corner

        Iterator<Address> iterator = addresses.iterator();
        while (iterator.hasNext()) {
            Address address = iterator.next();
            int dY = address.getStreetNum() * 10;
            int dX = address.getHouseNum() / 10;

            while (x != dX || y != dY) {

                if(partial != 0){ //Finish moving to corner
                    if(d == Direction.North)
                        for(;partial > 0; partial--) {
                            tickCount++;
                            y--;
                        }
                    else if (d == Direction.South)
                        for(;partial > 0; partial--) {
                            tickCount++;
                            y++;
                        }
                    else if (d == Direction.East)
                        for(;partial > 0; partial--) {
                            tickCount++;
                            x++;
                        }
                    else if (d == Direction.West)
                        for(;partial > 0; partial--) {
                            tickCount++;
                            x--;
                        }

                }


                if(d == Direction.Null){
                    d = Direction.North;
                    y--;
                    tickCount++;
                } else if(d == Direction.North){ // Y is correct (1), Y is above full (2), reverse (3), y is above partially (4)
                    if(y == dY){ //On this Y level *****1*****
                        d = Direction.East;
                        tickCount += 2;
                        if(x > dX) { //To the Left
                            for(int i = 0; i < 10; i++){
                                tickCount++;
                                x++;
                            }
                            d = Direction.South;
                            tickCount += 2;
                            for(int i = 0; i < 10; i++){
                                tickCount++;
                                y++;
                            }
                            d = Direction.West;
                            tickCount += 2;
                        }
                    } else if( y - dY >= 10){ // Above this Y level by a full block or more *****2*****
                        for(int i = 0; i < 10; i++){ // Move a full block up (10 ticks)
                            tickCount++;
                            y--;
                        }
                    } else if( y < dY){ // Need to be facing south *****3*****
                        //To the right
                        d = Direction.East;
                        tickCount += 2;
                        for (int i = 0; i < 10; i++) {
                            tickCount++;
                            x++;
                        }
                        d = Direction.South;
                        tickCount += 2;

                    } else if (y > dY){ // above this Y level by less than a block *****4*****
                        if(x < dX) { //To the right
                            for(int i = 0; i < 10; i++){ // Move a full block up (10 ticks)
                                tickCount++;
                                y--;
                            }
                            d = Direction.East;
                            tickCount +=2;
                        }
                        else if( x > dX) { //To the left
                            d = Direction.East;
                            tickCount += 2;
                            for (int i = 0; i < 10; i++) {
                                tickCount++;
                                x++;
                            }
                            d = Direction.South;
                            tickCount += 2;
                            for (int i = 0; i < 10; i++) {
                                tickCount++;
                                y++;
                            }
                            d = Direction.West;
                            tickCount += 2;
                        }
                        else { // Y move partially up, logs into partial
                            for(int i = 0; y != dY; i++) {
                                y--;
                                partial = 9 - i;
                            }
                        }
                    }
                } else if(d == Direction.South){ // Y is correct (1), Y is below full (2), reverse (3), y is above partially (4)
                    if(y == dY){ //On this Y level *****1*****
                        d = Direction.West;
                        tickCount += 2;
                        if(x < dX) { //To the right
                            for(int i = 0; i < 10; i++){
                                tickCount++;
                                x--;
                            }
                            d = Direction.South;
                            tickCount += 2;
                            for(int i = 0; i < 10; i++){
                                tickCount++;
                                y++;
                            }
                            d = Direction.East;
                            tickCount += 2;
                        }
                    } else if (y - dY <= -10) { // Below this Y level by a full block or more *****2*****
                        for(int i = 0; i < 10; i++){ // Move a full block up (10 ticks)
                            tickCount++;
                            y++;
                        }
                    } else if (y > dY) { // Need to be facing north *****3*****
                        d = Direction.West;
                        tickCount += 2;
                        for (int i = 0; i < 10; i++) {
                            tickCount++;
                            x--;
                        }
                        d = Direction.North;
                        tickCount += 2;
                    } else { // below this Y level by less than a block *****4*****
                        if(x < dX) {//To the right
                            d = Direction.West;
                            tickCount += 2;
                            for (int i = 0; i < 10; i++) {
                                tickCount++;
                                x--;
                            }
                            d = Direction.North;
                            tickCount += 2;
                            for (int i = 0; i < 10; i++) {
                                tickCount++;
                                y--;
                            }
                            d = Direction.East;
                            tickCount += 2;
                        }
                        else if( x > dX) { //To the left
                            for(int i = 0; i < 10; i++){ // Move a full block up (10 ticks)
                                tickCount++;
                                y++;
                            }
                            d = Direction.West;
                            tickCount += 2;
                        }
                        else { // Y move partially up, logs into partial
                            for(int i = 0; y != dY; i++) {
                                y++;
                                partial = 9 - i;
                            }
                        }
                    }
                } else if(d == Direction.East){// X is correct (1), X is below full (2), reverse (3), X is below partially (4)
                    if(x == dX) { //On this X level *****1*****
                        d = Direction.South;
                        tickCount += 2;
                        if(y > dY) { //To the Left
                            for(int i = 0; i < 10; i++){
                                tickCount++;
                                y++;
                            }
                            d = Direction.West;
                            tickCount += 2;
                            for(int i = 0; i < 10; i++){
                                tickCount++;
                                x--;
                            }
                            d = Direction.North;
                            tickCount += 2;
                        }
                    } else if (x - dX <= -10) { // Below this X level by a full block or more *****2*****
                        for(int i = 0; i < 10; i++){ // Move a full block up (10 ticks)
                            tickCount++;
                            x++;
                        }
                    } else if (x > dX) { // Need to be facing West *****3*****
                        d = Direction.South;
                        tickCount += 2;
                        for (int i = 0; i < 10; i++) {
                            tickCount++;
                            y++;
                        }
                        d = Direction.West;
                        tickCount += 2;
                    } else { // below this X level by less than a block *****4*****
                        if (y < dY) { //To the right
                            for(int i = 0; i < 10; i++){ // Move a full block up (10 ticks)
                                tickCount++;
                                x++;
                            }
                            d = Direction.South;
                            tickCount += 2;
                        }
                        else if (y > dY) { //To the left
                            d = Direction.South;
                            tickCount += 2;
                            for(int i = 0; i < 10; i++){ // Move a full block up (10 ticks)
                                tickCount++;
                                y++;
                            }
                            d= Direction.West;
                            tickCount += 2;
                            for(int i = 0; i < 10; i++){ // Move a full block up (10 ticks)
                                tickCount++;
                                x--;
                            }
                            d = Direction.North;
                            tickCount += 2;
                        }
                        else { // Y move partially up, logs into partial
                            for(int i = 0; x != dX; i++) {
                                x++;
                                partial = 9 - i;
                            }
                        }
                    }
                } else if(d == Direction.West){// X is correct (1), X is above full (2), reverse (3), X is above partially (4)
                    if(x == dX) { //On this X level *****1*****
                        d = Direction.North;
                        tickCount += 2;
                        if(y < dY) { //To the Left
                            for(int i = 0; i < 10; i++){
                                tickCount++;
                                y--;
                            }
                            d = Direction.East;
                            tickCount += 2;
                            for(int i = 0; i < 10; i++){
                                tickCount++;
                                x++;
                            }
                            d = Direction.South;
                            tickCount += 2;
                        }
                    } else if (x - dX >= 10) { // Above this X level by a full block or more *****2*****
                        for(int i = 0; i < 10; i++){ // Move a full block up (10 ticks)
                            tickCount++;
                            x--;
                        }
                    } else if (x < dX) { // Need to be facing East *****3*****
                        d = Direction.North;
                        tickCount += 2;
                        for (int i = 0; i < 10; i++) {
                            tickCount++;
                            y--;
                        }
                        d = Direction.East;
                        tickCount += 2;
                    } else { // above this X level by less than a block *****4*****
                        if (y < dY) { //To the right
                            d = Direction.North;
                            tickCount += 2;
                            for(int i = 0; i < 10; i++){ // Move a full block up (10 ticks)
                                tickCount++;
                                y--;
                            }
                            d = Direction.East;
                            tickCount += 2;
                            for(int i = 0; i < 10; i++){ // Move a full block up (10 ticks)
                                tickCount++;
                                x++;
                            }
                            d = Direction.South;
                            tickCount += 2;
                        }
                        else if (y > dY) { //To the left
                            for(int i = 0; i < 10; i++){ // Move a full block up (10 ticks)
                                tickCount++;
                                x--;
                            }
                            d = Direction.North;
                            tickCount += 2;
                        }
                        else { // Y move partially up, logs into partial
                            for(int i = 0; x != dX; i++) {
                                x--;
                                partial = 9 - i;
                            }
                        }
                    }
                }

                if(x == dX && y == dY){
                    tickCount += 5;
                }
            }
        }

        return tickCount / 1000;

    }

    public String toString() {
        return "hours with Right Turn route";
    }
}
