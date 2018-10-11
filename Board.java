/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cs4200_Nqueens;

//import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Set;

/**
 *
 * @author John
 */
public class Board {
    private int[][] gameBoard;
    private int heuristic;
    ArrayList<Point> listOfQueens;
    
    
    
    Board() {
        gameBoard = generateBoard();
        heuristic = -1;
        listOfQueens = new ArrayList<Point>();
    }
    
    Board(Board b) {
        gameBoard = new int[21][21];
        
        for (int i = 0; i < gameBoard.length; i++)
            for (int j = 0; j < gameBoard[i].length; j++) {
                gameBoard[i][j] = b.gameBoard[i][j];
            }
        
        heuristic = b.heuristic;
        
        listOfQueens = b.listOfQueens;
    }
    
    
    //One queen per column!
    public int[][] generateBoard() {
        int[][] board = new int[21][21];
        Random randomObj = new Random();
        
        for (int i = 0; i < board.length; i++) {
            int randomSpot = randomObj.nextInt(21);
            board[randomSpot][i] = 1;
        }
   
        return board;
    }
    
    public ArrayList<Point> calculateQueens() {
        int[][] board = this.gameBoard;
        ArrayList<Point> listOfQueens = new ArrayList<>();
        
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == 1) {
                    
                    listOfQueens.add(new Point(i,j));
                }
            }
        }
        
        return listOfQueens;
    }
    
    public int calculateConflictingPairs() {
        ArrayList<Point> queensList = this.listOfQueens;
        int numOfPairs = 0;
        
        Map<Point, ArrayList<Point>> pairMap = new HashMap<>();
        ArrayList<Point> attackingPairs = new ArrayList<>();

        for (Point queen : queensList) {
            attackingPairs = new ArrayList<>();
            pairMap.put(queen, attackingPairs);
        }
        
            // Find horizontal pairs
            for (Point p : queensList) {
               
                for (Point r : queensList) {
                    
                    //Point class stores (x,y) pairs as coordinate system, it's flipped for 2d arrays.
                    //2D arrays go by rows & columns, so, a horizontal pair in 2D arrays means same y, different x.
                    // 
                    if (r.y != p.y && r.x == p.x) {
                        System.out.print("Horizontal Pair: ");
                        System.out.println("Row: " + p.x + " Column: " + p.y + ", R.Row: " + r.x + " R.Column: " + r.y);

                        //The magnum opus.
                        if (!pairMap.get(p).contains(r) && !pairMap.get(r).contains(p)) {

                            pairMap.get(p).add(r);

                        }
                    }

                }
            }
            
            //Find up->right diagonal pairs
            for (Point p : queensList) {
     
                for (Point r : queensList) {
                    int rowpivot = p.x-1;
                    int columnpivot = p.y+1;
                    for (Point s : queensList) {
                        if (r.x == rowpivot && r.y == columnpivot) {
                            System.out.print("Up->Right Diagonal: ");
                            System.out.println("P: " + p.x + " " + p.y + "R: " + r.x + " " + r.y);
                            
                            if (!pairMap.get(p).contains(r) && !pairMap.get(r).contains(p)) {
                                pairMap.get(p).add(r);
                            }

                        }
                        rowpivot--;
                        columnpivot++;
                    }
                    /*if (r.x == (p.x-1) && r.y == (p.y+1)) {
                        System.out.print("Up->Right Diagonal: ");
                        System.out.println("P: " + p.x + " " + p.y + "R: " + r.x + " " + r.y);
                    }*/
                    
                    
                }
            }
            //Find up->left diagonal pairs
            for (Point p : queensList) {
     
                for (Point r : queensList) {
                    int rowpivot = p.x-1;
                    int columnpivot = p.y-1;
                    for (Point s : queensList) {
                        if (r.x == rowpivot && r.y == columnpivot) {
                            System.out.print("Up->Left Diagonal: ");
                            System.out.println("P: " + p.x + " " + p.y + "R: " + r.x + " " + r.y);
                            
                            if (!pairMap.get(p).contains(r) && !pairMap.get(r).contains(p)) {
                                pairMap.get(p).add(r);
                            }

                        }
                        rowpivot--;
                        columnpivot--;
                    }
                    /*if (r.x == (p.x-1) && r.y == (p.y+1)) {
                        System.out.print("Up->Right Diagonal: ");
                        System.out.println("P: " + p.x + " " + p.y + "R: " + r.x + " " + r.y);
                    }*/
                    
                    
                }
            }
            //Find down->right diagonal pairs
            for (Point p : queensList) {
     
                for (Point r : queensList) {
                    int rowpivot = p.x+1;
                    int columnpivot = p.y+1;
                    for (Point s : queensList) {
                        if (r.x == rowpivot && r.y == columnpivot) {
                            System.out.print("Down->Right Diagonal: ");
                            System.out.println("P: " + p.x + " " + p.y + "R: " + r.x + " " + r.y);
                            
                            if (!pairMap.get(p).contains(r) && !pairMap.get(r).contains(p)) {
                                pairMap.get(p).add(r);
                            }

                        }
                        rowpivot++;
                        columnpivot++;
                    }
                    /*if (r.x == (p.x-1) && r.y == (p.y+1)) {
                        System.out.print("Up->Right Diagonal: ");
                        System.out.println("P: " + p.x + " " + p.y + "R: " + r.x + " " + r.y);
                    }*/
                    
                    
                }
            }
            //Find down->left diagonal pairs
            for (Point p : queensList) {
     
                for (Point r : queensList) {
                    int rowpivot = p.x+1;
                    int columnpivot = p.y-1;
                    for (Point s : queensList) {
                        if (r.x == rowpivot && r.y == columnpivot) {
                            System.out.print("Down->Left Diagonal: ");
                            System.out.println("P: " + p.x + " " + p.y + "R: " + r.x + " " + r.y);

                            if (!pairMap.get(p).contains(r) && !pairMap.get(r).contains(p)) {
                                pairMap.get(p).add(r);
                            }
                            
                        }
                        rowpivot++;
                        columnpivot--;
                    }
                    /*if (r.x == (p.x-1) && r.y == (p.y+1)) {
                        System.out.print("Up->Right Diagonal: ");
                        System.out.println("P: " + p.x + " " + p.y + "R: " + r.x + " " + r.y);
                    }*/
                    
                    
                }
            }

            //NUM OF PAIRS = SIZE OF ALL ARRAYLISTS IN MAP
            Set<Point> mapSet = pairMap.keySet();
            for (Point p : mapSet) {
                System.out.println("Point: " +p.x +"," +p.y + " Size: "+pairMap.get(p).size());
                numOfPairs += pairMap.get(p).size();
            }
            

        //numOfPairs = pairMap.size();
        return numOfPairs;
    }
    
    
    public Board simulatedAnnealing(Board inputBoard) {
        Board currentBoard = inputBoard;
        Board nextBoard;
        double T = 1000;
        
        while (T >= 0) {
            if (T == 0) {
                return currentBoard;
        }
            T = T - .001;
            
            
        }

        
        
        return currentBoard;
    }
    
    public Board generateRandomSuccessorBoard(Board inputBoard) {
        // Randomly move one queen
        Board successorBoard = new Board();
        
    }
   
    
    
    public static boolean equals(Point p1, Point p2) {
        if (p1.x == p2.x && p1.y == p2.y) {
            return true;
        }
        return false;
    }
    
    
    
    
    
    public static void main(String[] args) {
        // Test board
        Board b = new Board();
        
        
        int a = 0;
        for (int[] i : b.gameBoard) {
            System.out.print(a + ": ");
            
            if (a < 10) {
                System.out.print(" ");
            }
            
            a++;
            for (int j : i) {
                System.out.print(j + " ");
            }
            System.out.println();
        }
        
        b.listOfQueens = b.calculateQueens();
        b.heuristic = b.calculateConflictingPairs();
        
        //b.heuristic = b.calculateConflictingPairs(b.gameBoard);
        System.out.println("Conflicting pairs: " + b.calculateConflictingPairs());
        

        
    }
    
}
