/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cs4200_Nqueens;

/**
 *
 * @author John
 */
public class Pair {
    public final Coordinate first;
    public final Coordinate second;
    
    Pair(Coordinate a, Coordinate b) {
        first = a;
        second = b;
    }
    
    public static class Coordinate {
        public final int x;
        public final int y;
        
        Coordinate(int x, int y) {
            this.x = x;
            this.y = y;
        }
        
    }
    
}
