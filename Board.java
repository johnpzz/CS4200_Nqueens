/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cs4200_Nqueens;

//import java.awt.Point;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Set;

/**
 *
 * @author John
 */

//                  Look into below
// Figure out a way to have calculateQueenPairs not take in a board as a parameter?
// Error if I remove the parameter and reference board using this
// Note: the passed in parameter to calculateQueens returns the heuristic of THAT!

public class Board {
    private int[][] gameBoard;
    private int heuristic;
    private ArrayList<Point> listOfQueens;
    
    
    
    Board() {
        gameBoard = generateBoard();
        listOfQueens = calculateQueens(this);
        heuristic = calculateConflictingPairs();
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
    
    
    public boolean simulatedAnnealing() {
        Board currentBoard = this;
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
             //       + "\nNext Board H: " + nextBoard.heuristic);
            
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
            System.out.println(":-) making more happen");
            System.out.println(currentBoard.toString());
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
        successorBoard.heuristic = successorBoard.calculateConflictingPairs();
     
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
    
    public ArrayList<Board> makePopulation(int size) {
        Board anIndividual;
        ArrayList<Board> populationList = new ArrayList<Board>();
        
        for (int i = 1; i <= size; i++) {
            anIndividual = new Board();
            anIndividual.gameBoard = anIndividual.generateBoard();
            anIndividual.listOfQueens = anIndividual.calculateQueens(anIndividual);
            anIndividual.heuristic = anIndividual.calculateConflictingPairs();
            populationList.add(anIndividual);
            System.out.println("Board " + i + " heuristic: " + anIndividual.heuristic);
        }
        
        Collections.sort(populationList, new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                Board b1 = (Board) o1;
                Board b2 = (Board) o2;
               
                /*
                if (b1.heuristic > b2.heuristic)
                    return 1;
                else if (b1.heuristic == b2.heuristic)
                    return 0;
                else
                    return -1;
                */

                return b1.heuristic - b2.heuristic;
                
            }
        });
        
        return populationList;
    }
    
    public double[] generateHeuristicFunctionArray(ArrayList<Board> population) {
       double[] heuristicPercentArray = new double[population.size()];
        double fractionRange = 0;
        int index = 0;
        double totalHeuristic = 0;
        System.out.println("Sorted heuristics:");
        
        for (Board b : population) {
            System.out.println("Heuristic: " + b.heuristic);
            //totalHeuristic += b.heuristic;
            totalHeuristic += ((21*(21-1))/2) - population.get(index++).heuristic;
            }
            
        System.out.println("Total Heuristic: " + totalHeuristic);

        index = 0;
        for (Board b : population) {
            double d = (((21*(21-1))/2) - b.heuristic)/totalHeuristic;
            System.out.println("d: " + d);
            d = Math.round(d*100.0);
            System.out.println("rounded d: " + d);
            fractionRange += d;
            
            //Had a bug here, was setting to d and not fractionRange
            heuristicPercentArray[index++] = fractionRange;
            

            //System.out.println("Percent of: " + b.heuristic + " = " + fractionRange + "%");
            System.out.println("Percent of: " + b.heuristic + " = " + fractionRange + "%");
            }

            System.out.println("Heuristic Percent Array Range: ");
            for (double i : heuristicPercentArray) {
                System.out.print(i + "\t");
            }
        
        return heuristicPercentArray;
    }
    
    public Board getCrossOverBoard(ArrayList<Board> population, double[] heuristicPercentArray) {
        Board crossOverBoard = new Board();
        Random rand = new Random();
        int randomIndividualSelection = rand.nextInt(101);
        
        //System.out.println("Random number generated: " + randomIndividualSelection);

        
        for (int d = 0; d < heuristicPercentArray.length; d++) {
            if (randomIndividualSelection < heuristicPercentArray[d]) {
                System.out.println("Getting this array's index to make a child of: " + heuristicPercentArray[d]);
                //select this board index to make a child of
                crossOverBoard = new Board(population.get((int)d));
                break;
            }
        }
        
        return crossOverBoard;
    }
    
    
    
    
    ////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////// genetic algorithm //////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////
    
    public Board geneticAlgorithm() {
        Board inputBoard = this;
        Board anIndividual;
        //k = initial population size 
        int populationSize = 4;
        ArrayList<Board> population = makePopulation(populationSize);
        ArrayList<Board> babies = new ArrayList<>();
        
        // Population is now sorted and exists.  Our babies is empty.
        
        double[] heuristicPercentArray = generateHeuristicFunctionArray(population);
        
        // Each population member now has a heuristic percent range found in heuristicPercentArray


        // Choose how many babies to make
        int numBabies = 100;
        Random rand = new Random();

        for (int i = 1; i <= numBabies; i++) {
            Board crossOverBoard = getCrossOverBoard(population, heuristicPercentArray);
            Board crossOverBoard2 = getCrossOverBoard(population, heuristicPercentArray);
            
            
            // Is board2 the same as board1 ( You can't smash yourself, get a new one!!)
            if (crossOverBoard2.equals(crossOverBoard)) {
                crossOverBoard2 = getCrossOverBoard(population, heuristicPercentArray);
            }
            
            // Now, we have selected two crossover parents as crossOverBoard1 & crossOverBoard2.

            System.out.print("\nGenetic Crossover Parents Heuristics: ");
            System.out.println(crossOverBoard.heuristic + " " + crossOverBoard2.heuristic);

            //////////////////////////// A CROSSOVER CHANCE TOO????????????? /////////////////////
            //int random = new Random().nextInt(100) + 1;
            //if (random <= 60) {
            Board baby = geneticCrossover(crossOverBoard, crossOverBoard2);
            baby.heuristic = baby.calculateConflictingPairs();
            baby.listOfQueens = baby.calculateQueens(baby);

            ////////////////// MUTATION CHANCE //////////////////////
            //Mutation chance of: 80% - gets stuck at h 14 and STUCK at 10.

            int randomNumber = rand.nextInt(100) + 1;
            if (randomNumber <= 40) {
                //mutate
                System.out.println("Mutating!");
                baby = generateRandomSuccessorBoard();
            }

            babies.add(baby);
        }
            
            
        // Sort the 100 babies that we have created.
        babies.sort(new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                Board baby1 = (Board) o1;
                Board baby2 = (Board) o2;
                return baby1.heuristic - baby2.heuristic;
            }
        });
            
        while (babies.get(0).heuristic != 0) {
            //Grab first n babies, where n = size of our population
            population.clear();
            for (int i = 0; i < populationSize; i++) {
                population.add(babies.get(0));
            }
            //Population is now replaced with the "fittest" babies.
            
            heuristicPercentArray = generateHeuristicFunctionArray(population);
            //Each population member now has a heuristic percent range found in heuristicPercentArray
            
            for (int i = 1; i <= numBabies; i++) {
                Board crossOverBoard = getCrossOverBoard(population, heuristicPercentArray);
                Board crossOverBoard2 = getCrossOverBoard(population, heuristicPercentArray);

                // Now, we have selected two crossover parents as crossOverBoard1 & crossOverBoard2.

                System.out.print("\nGenetic Crossover Parents Heuristics: ");
                System.out.println(crossOverBoard.heuristic + " " + crossOverBoard2.heuristic);

                //////////////////////////// A CROSSOVER CHANCE TOO????????????? /////////////////////
                //int random = new Random().nextInt(100) + 1;
                //if (random <= 60) {
                Board baby = geneticCrossover(crossOverBoard, crossOverBoard2);
                baby.heuristic = baby.calculateConflictingPairs();
                baby.listOfQueens = baby.calculateQueens(baby);

                ////////////////// MUTATION CHANCE //////////////////////
                //Mutation chance of: 80% - gets stuck at h 14 and STUCK at 10.

                int randomNumber = rand.nextInt(100) + 1;
                if (randomNumber <= 85) {
                    //mutate
                    //System.out.println("Mutating!");
                    baby = generateRandomSuccessorBoard();
                    //System.out.println(baby.toString());
                }

            babies.add(baby);
        }
            
        // Sort the 100 babies that we have created.
        babies.sort(new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                Board baby1 = (Board) o1;
                Board baby2 = (Board) o2;
                return baby1.heuristic - baby2.heuristic;
            }
        }); 
        
 
        }
   
        return babies.get(0);
    }
    
    public Board geneticCrossover(Board b1, Board b2) {
        Board newBoard = new Board();
        //Split board by a random column, talking halves of both b1 and b2 to create a new board.
        //Let's take the left half of b1, and the right half of b2.
        Random rand = new Random();
        int randomNum = rand.nextInt(21);
        
        System.out.println("Genetic crossover random pivot split column: " + randomNum);
        
        for (int i = 0; i < b1.gameBoard.length; i++) {
            for (int j = 0; j < b1.gameBoard[i].length-randomNum; j++) {
                newBoard.gameBoard[i][j] = b1.gameBoard[i][j];
            }
        }
        
        for (int i = 0; i < b1.gameBoard.length; i++) {
            for (int j = randomNum; j < b2.gameBoard[i].length; j++) {
                newBoard.gameBoard[i][j] = b2.gameBoard[i][j];
            }
        }
        
        /*
        for (int i = 0; i < b1.gameBoard.length; i++) {
            for (int j = 0; j < b1.gameBoard[i].length-randomNum; j++) {
                b2.gameBoard[i][j] = b1.gameBoard[i][j];
            }
        }
        
        for (int i = 0; i < b1.gameBoard.length; i++) {
            for (int j = randomNum; j < b1.gameBoard[i].length; j++) {
                b2.gameBoard[i][j] = b1.gameBoard[i][j];
            }
        }
        */
        
        
        return newBoard;
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    public static void main(String[] args) {
        // Test board
        Board b = new Board();
        b.listOfQueens = b.calculateQueens(b);
        b.heuristic = b.calculateConflictingPairs();
        
        b.geneticAlgorithm();
        
        
        
        /*
        for (Point p : b.listOfQueens)
            System.out.println(p.x +", "+p.y);

              
        System.out.println(b.toString());
        System.out.println("\n*****************\n");
        Board childBoard = b.generateRandomSuccessorBoard();
        childBoard.listOfQueens = childBoard.calculateQueens(childBoard);
        System.out.println(childBoard.toString());
        */
        
        ///////////////////////////////////////////////
        /*
        int numOfSuccesses = 0;
        for (int i = 1; i <= 500; i++) {
            System.out.println("Run " + i +":");
            boolean solved = b.simulatedAnnealing();
            if (solved == true) {
                numOfSuccesses += 1;
            }
        }
        double avgSuccesses = numOfSuccesses/500;
        System.out.println("Avg Successes: " + avgSuccesses);
        */
        //////////////////////////////////////////////
        //System.out.println("Heuristic: " + b.heuristic);
        
    }
    
}
