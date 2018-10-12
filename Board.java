/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cs4200_Nqueens;

//import java.awt.Point;
import java.util.ArrayList;
import java.util.Collections;
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
    private ArrayList<Point> listOfQueens;
    
    
    
    Board() {
        gameBoard = generateBoard();
        listOfQueens = calculateQueens(this);
        heuristic = calculateConflictingPairs(this);
    }
    
    
    Board(Board b) {
        gameBoard = new int[21][21];
        
        for (int i = 0; i < gameBoard.length; i++)
            for (int j = 0; j < gameBoard[i].length; j++) {
                gameBoard[i][j] = b.gameBoard[i][j];
            }
        
        this.heuristic = b.heuristic;
        
        this.listOfQueens = new ArrayList<>(b.listOfQueens);
    }
    
    
    @Override
    public String toString() {
        StringBuilder objectString = new StringBuilder();
        objectString.append("Board:\n");
        for (int[] i : this.gameBoard) {
            for (int j : i) {
                objectString.append(j);
            }
            objectString.append("\n");
        }
        objectString.append("Heuristic: " + this.heuristic);

        
        return objectString.toString();
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
    
    public ArrayList<Point> calculateQueens(Board inputBoard) {
        int[][] board = inputBoard.gameBoard;
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
    
    public int calculateConflictingPairs(Board inputBoard) {
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
                        //System.out.print("Horizontal Pair: ");
                        //System.out.println("Row: " + p.x + " Column: " + p.y + ", R.Row: " + r.x + " R.Column: " + r.y);

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
                            //System.out.print("Up->Right Diagonal: ");
                            //System.out.println("P: " + p.x + " " + p.y + "R: " + r.x + " " + r.y);
                            
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
                            //System.out.print("Up->Left Diagonal: ");
                            //System.out.println("P: " + p.x + " " + p.y + "R: " + r.x + " " + r.y);
                            
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
                            //System.out.print("Down->Right Diagonal: ");
                            //System.out.println("P: " + p.x + " " + p.y + "R: " + r.x + " " + r.y);
                            
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
                            //System.out.print("Down->Left Diagonal: ");
                            //System.out.println("P: " + p.x + " " + p.y + "R: " + r.x + " " + r.y);

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
                //System.out.println("Point: " +p.x +"," +p.y + " Size: "+pairMap.get(p).size());
                numOfPairs += pairMap.get(p).size();
            }
            

        //numOfPairs = pairMap.size();
        return numOfPairs;
    }
    
    
    public boolean simulatedAnnealing(Board inputBoard) {
        Board currentBoard = inputBoard;
        Board nextBoard = new Board(currentBoard);
        //initially a clone board
        double T = 1;
        double deltaE;
        
        while (T > 0 && currentBoard.heuristic != 0) {
            
            T = T - .0001;
            nextBoard = new Board(currentBoard);
            //removed this
            nextBoard = nextBoard.generateRandomSuccessorBoard();
            deltaE = nextBoard.heuristic - currentBoard.heuristic;
            
            //System.out.println("Current Board H: " + currentBoard.heuristic + ""
              //      + "\nNext Board H: " + nextBoard.heuristic);
            
                //System.out.println("CURR H IS : " + currentBoard.heuristic);
                //System.out.println("NEXT H IS : " + nextBoard.heuristic);
            
                //problem??
            if (deltaE < 0) {
                //this is desired
                currentBoard = nextBoard;
                
            } else if (deltaE >= 0) {
                //this is bad, calculate probability
                //                                    HERE WAS THE PROBLEM, NEED -1
                double probability = Math.pow(Math.E, (deltaE*-1)/T);
                if (probability > 0.90) {
                    currentBoard = nextBoard;
                }
               
                //System.out.println(currentBoard.heuristic);
                
            }
            //System.out.println("CURR H IS : " + currentBoard.heuristic);
            //System.out.println("NEXT H IS : " + nextBoard.heuristic);
           
            
        }
        
        if (currentBoard.heuristic == 0) {
            // Success.  Board is solved.
            //System.out.println(":-) making more happen");
            //System.out.print(currentBoard.toString());
            return true;
        }

        
        
        return false;
    }
    
    public Board generateRandomSuccessorBoard() {
        // Randomly move one queen up or down by a random number of spaces, checking to make sure the new spot is not the same
        // as the old spot
        // We are moving queens by moving the row of the board up/down random number of spaces
       
        Board successorBoard = new Board();
        successorBoard.gameBoard = cloneBoard(this.gameBoard);
        
        Random rand = new Random();
        int randomQueenIndex = rand.nextInt(21); // 0-20
        Point oldQueenIndex = this.listOfQueens.get(randomQueenIndex);
        
        //The following is unnecessary, the if statement is a more compact way of checking
        //successorBoard.gameBoard[randomQueenIndex][oldQueenIndex.y] == inputBoard.gameBoard[oldQueenIndex.x][oldQueenIndex.y]
        boolean isAdded = false;
        while (!isAdded) {
            if (successorBoard.gameBoard[randomQueenIndex][oldQueenIndex.y] == 1) {
                //System.out.println("A queen is here already.  Generating a new move..");
                randomQueenIndex = rand.nextInt(21); // 0-20
                //oldQueenIndex = inputBoard.listOfQueens.get(randomQueenIndex);    
            } else {
                //System.out.println("Moving one random queen one spot.");
                successorBoard.gameBoard[randomQueenIndex][oldQueenIndex.y] = 1;
                successorBoard.gameBoard[oldQueenIndex.x][oldQueenIndex.y] = 0;
                isAdded = true;
            }
        }
        
        //System.out.println("Old point: " + oldQueenIndex.x + ", " + oldQueenIndex.y);
        //System.out.println("New point: " + randomQueenIndex + ", " + oldQueenIndex.y);
        
        successorBoard.listOfQueens = this.calculateQueens(successorBoard);
        
        /*System.out.println("Successor Board:");
        for (Point p : successorBoard.listOfQueens)
            System.out.println(p.x +", " + p.y);
        */
        successorBoard.heuristic = successorBoard.calculateConflictingPairs(this);
     
        return successorBoard;
    }
   
    public static int[][] cloneBoard(int[][] board) {
        int[][] copyBoard = new int[board.length][board[0].length];

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                copyBoard[i][j] = board[i][j];
            }
            
        }
        
        return copyBoard;
    }
    
    
    public static boolean equals(Point p1, Point p2) {
        if (p1.x == p2.x && p1.y == p2.y) {
            return true;
        } else
            return false;
    }
    
    
    
    
    
    public static void main(String[] args) {
        // Test board
        Board b = new Board();
        b.listOfQueens = b.calculateQueens(b);
        b.heuristic = b.calculateConflictingPairs(b);
        /*
        for (Point p : b.listOfQueens)
            System.out.println(p.x +", "+p.y);

              
        System.out.println(b.toString());
        System.out.println("\n*****************\n");
        Board childBoard = b.generateRandomSuccessorBoard();
        childBoard.listOfQueens = childBoard.calculateQueens(childBoard);
        System.out.println(childBoard.toString());
        */
        
        int numOfSuccesses = 0;
        for (int i = 1; i <= 500; i++) {
            System.out.println("Run " + i +":");
            boolean solved = b.simulatedAnnealing(b);
            if (solved == true) {
                numOfSuccesses += 1;
            }
        }
        double avgSuccesses = numOfSuccesses/500;
        System.out.println("Avg Successes: " + avgSuccesses);
        
        //System.out.println("Heuristic: " + b.heuristic);
        
    }
    
}
