package battleship.bin;

import java.util.*;
import java.lang.*;
/**
 * Write a description of class BattleshipMain here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class BattleshipMain {
    public static void main(String[] args){
        BattleshipModel model = new BattleshipModel();
        BattleshipView view = new BattleshipView(model);
        //model.setValue('A', model.getPlayerADefBoard(), 'A', 10);
        // testing for out of bonds error
        //model.setValue('A', model.getPlayerADefBoard(), 'A', 0);
        //model.placePlayerAShip('d', 9, 'E');

        //System.out.println(model.getValue(model.getPlayerADefBoard(), 'A', 10));
        //model.addObserver(view);
        System.out.println("Player A gets to place his ships, you will place one ship at a time, at " +
            "one piece of the ship at a time.");
        Scanner input = new Scanner(System.in);
        //sets up the game, players place pieces
        setupGameMode(model, view, input);

        //setups a game with ships placed
        //setupTestGame(model);

        //setting the game state here
        model.setPlayMode();
        System.out.println("The game is now entering: PLAY MODE");

        while(!model.isGameOver()){
            if(model.getCurrentTurn()){
                System.out.println("Player A, please place your shot." + "\nType your " 
                    + "row, followed by a space, and then " +
                    "the column.");
                char row = validateUserChar(input);
                int col = validateUserInt(input);      
                //validates to make sure the shot being place is not a duplicate
                while(!model.isLegalShot(row, col)){
                    // print error message
                    System.out.println("Unacceptable move. Try again.");
                    // announce whose turn it is
                    System.out.println("Still player A's turn:");
                    // get user input again
                    System.out.println("Player A, please place your shot." + "\nType your " 
                        + "row, followed by a space, and then " +
                        "the column.");
                    row = validateUserChar(input);
                    col = validateUserInt(input); 
                }
                //saves the ship that is getting attacked
                char ship = (model.getValue(model.getPlayerBDefBoard(), row, col));
                //makes the shot
                model.makeShot(row, col);
                System.out.println(view.getAOffBoard());
                if(model.checkShotState(row, col) == STATUS.MISS){
                    model.setTurn();
                } else {
                    //check if the ship is dead
                    if(model.getShipState(ship) == SHIPSTATE.DEAD){
                        System.out.println(model.checkShipStatePostShot(ship));
                    }
                    if(model.isGameOver()){
                        System.out.println("Player A is the winner!");
                        model.setGameOver();
                    }
                }
            } else {
                System.out.println("Player B, please place your shot." + "\nType your " 
                    + "row, followed by a space, and then " +
                    "the column.");
                char row = validateUserChar(input);
                int col = validateUserInt(input);   
                //validates to make sure the shot being place is not a duplicate
                while(!model.isLegalShot(row, col)){
                    // print error message
                    System.out.println("Unacceptable move. Try again.");
                    // announce whose turn it is
                    System.out.println("Still player B's turn:");
                    // get user input again
                    System.out.println("Player B, please place your shot." + "\nType your " 
                        + "row, followed by a space, and then " +
                        "the column.");
                    row = validateUserChar(input);
                    col = validateUserInt(input); 
                }
                char ship = (model.getValue(model.getPlayerADefBoard(), row, col));
                model.makeShot(row, col);
                System.out.println(view.getBOffBoard());
                if(model.checkShotState(row, col) == STATUS.MISS){
                    model.setTurn();
                } else {
                    if(model.getShipState(ship) == SHIPSTATE.DEAD){
                        System.out.println(model.checkShipStatePostShot(ship));
                    }
                    if(model.isGameOver()){
                        System.out.println("Player B is the winner!");
                        model.setGameOver();
                    }
                }
            }
        }
    }

    /**
     * Method that sets up the game to place ships
     * 
     * @param model the model that holds game state
     * @param view the view of the model that prints out the board
     * @param input the scanner that is being used for input
     */
    private static void setupGameMode(BattleshipModel model, BattleshipView view, Scanner input){
        int shipPlacementCounter = 0;
        while(model.getCurrentTurn()){
            char currentShip = shipSelector(shipPlacementCounter, model);
            System.out.println("Player A, please place your " + shipToString(model, currentShip)
                + ". \nType your " + "row, followed by a space, and then " +
                "the column.");
            //converts a char to a string

            //validates the input
            char row = validateUserChar(input);
            int col = validateUserInt(input);         
            while(!model.checkShipPlacement(row, col, currentShip)){
                // print error message
                System.out.println("Unacceptable move. Try again.");
                // get user input again
                System.out.println("Player A, please place your ship." + "\nType your " 
                    + "row, followed by a space, and then " +
                    "the column.");
                row = validateUserChar(input);
                col = validateUserInt(input); 
            }
            model.placePlayerAShip(row, col, currentShip);
            System.out.println(view.getADefBoard());
            if(model.checkShipMax(currentShip)){
                //moves onto the next ship for placement
                shipPlacementCounter++;
                if(shipPlacementCounter == 5){
                    model.setTurn();
                }
                currentShip = shipSelector(shipPlacementCounter, model);
            }
        }
        shipPlacementCounter = 0; 
        while(!model.getCurrentTurn()){
            char currentShip = shipSelector(shipPlacementCounter, model);
            System.out.println("Player B, please place your " + shipToString(model, currentShip)
                + ". \nType your " + "row, followed by a space, and then " +
                "the column.");
            //converts a char to a string
            //need to do some input validation
            char row = validateUserChar(input);
            int col = validateUserInt(input);   
            while(!model.checkShipPlacement(row, col, currentShip)){
                // print error message
                System.out.println("Unacceptable move. Try again.");
                // get user input again
                System.out.println("Player B, please place your ship." + "\nType your " 
                    + "row, followed by a space, and then " +
                    "the column.");
                row = validateUserChar(input);
                col = validateUserInt(input); 
            }
            model.placePlayerBShip(row, col, currentShip);
            System.out.println(view.getBDefBoard());        
            if(model.checkShipMax(currentShip)){
                //moves onto the next ship for placement
                shipPlacementCounter++;
                if(shipPlacementCounter == 5){
                    model.setTurn();
                }
                currentShip = shipSelector(shipPlacementCounter, model);
            }
        }
    }

    /**
     * Method that returns an arraylist that is used to check if the characters passed in are in 
     * a certain range
     * 
     * @return list of chars that are used to comapre
     */
    private static ArrayList<Character> rangeChar(){
        ArrayList<Character> rowChars = new ArrayList();
        rowChars.add('A');
        rowChars.add('B');
        rowChars.add('C');
        rowChars.add('D');
        rowChars.add('E');
        rowChars.add('F');
        rowChars.add('G');
        rowChars.add('H');
        rowChars.add('I');
        rowChars.add('J');
        rowChars.add('a');
        rowChars.add('b');
        rowChars.add('c');
        rowChars.add('d');
        rowChars.add('e');
        rowChars.add('f');
        rowChars.add('g');
        rowChars.add('h');
        rowChars.add('i');
        rowChars.add('j');
        return rowChars;
    }

    /**
     * Method that validates an integer to make sure it is an int and is in the correct bounds
     * 
     * @param in the Scanner that is used to get user input
     */
    private static int validateUserInt(Scanner in){
        int a;
        do {
            System.out.println("Please input a int:");
            while(!in.hasNextInt()){
                System.out.println("Please input a valid int (1-10):");
                in.next();
            }
            a = in.nextInt();
        }while(a <= 0 || a > 10);
        return a;
    }

    /**
     * Method that validates an character to make sure it is an int and is in the correct bounds
     * 
     * @param in the Scanner that is used to get user input
     */
    private static char validateUserChar(Scanner in){
        char a;
        do {
            System.out.println("Please input a char: ");
            while(!in.hasNext()){
                System.out.println("Please input a valid char (a-j): ");
                in.next();
            }
            a = in.next().charAt(0);
        } while(Collections.frequency(rangeChar(), a) == 0);
        return a;
    }

    /**
     * Method gets the corresponding ship in order to place all the ships in order, one by one
     * 
     * @param place gets which ship to place based on a counter
     * @return the current ship that will be placed on the board
     */
    private static char shipSelector(int place, BattleshipModel model){
        char a = ' ';
        switch(place){
            case 0: a = model.AIRCARRIER;
            break;
            case 1: a = model.BATTLESHIP;
            break;
            case 2: a = model.CRUISER;
            break;
            case 3: a = model.DESTROYER1;
            break;
            case 4: a = model.DESTROYER2;
            break;
        }
        return a;
    }

    /**
     * Method that prints out which ship is being checked
     * 
     * @param model the model that holds the state
     * @return the current ship that is being checked
     */
    private static String shipToString(BattleshipModel model, char ship){
        String shipState = null;;
        switch(ship){
            case 'A': shipState = "AirCarrier";
            break;
            case 'B': shipState = "Battleship";
            break;
            case 'C': shipState = "Cruiser";
            break;
            case 'D': shipState = "Destroyer 1";
            break;
            case 'E': shipState = "Destroyer 2";
            break;
        }
        return shipState;
    }

    /**
     * Method that sets up a test game where all ships are preset into the top left corner
     * 
     * @param model the model that holds all the game state	
     */
    private static void setupTestGame(BattleshipModel model){
        model.setValue('A', model.getPlayerADefBoard(), 'A', 1);
        model.setValue('A', model.getPlayerADefBoard(), 'A', 2);
        model.setValue('A', model.getPlayerADefBoard(), 'A', 3);
        model.setValue('A', model.getPlayerADefBoard(), 'A', 4);
        model.setValue('A', model.getPlayerADefBoard(), 'A', 5);
        model.setValue('B', model.getPlayerADefBoard(), 'A', 6);
        model.setValue('B', model.getPlayerADefBoard(), 'A', 7);
        model.setValue('B', model.getPlayerADefBoard(), 'A', 8);
        model.setValue('B', model.getPlayerADefBoard(), 'A', 9);
        model.setValue('C', model.getPlayerADefBoard(), 'B', 1);
        model.setValue('C', model.getPlayerADefBoard(), 'B', 2);
        model.setValue('C', model.getPlayerADefBoard(), 'B', 3);
        model.setValue('D', model.getPlayerADefBoard(), 'B', 4);
        model.setValue('D', model.getPlayerADefBoard(), 'B', 5);
        model.setValue('E', model.getPlayerADefBoard(), 'B', 6);
        model.setValue('E', model.getPlayerADefBoard(), 'B', 7);

        model.setValue('A', model.getPlayerBDefBoard(), 'A', 1);
        model.setValue('A', model.getPlayerBDefBoard(), 'A', 2);
        model.setValue('A', model.getPlayerBDefBoard(), 'A', 3);
        model.setValue('A', model.getPlayerBDefBoard(), 'A', 4);
        model.setValue('A', model.getPlayerBDefBoard(), 'A', 5);
        model.setValue('B', model.getPlayerBDefBoard(), 'A', 6);
        model.setValue('B', model.getPlayerBDefBoard(), 'A', 7);
        model.setValue('B', model.getPlayerBDefBoard(), 'A', 8);
        model.setValue('B', model.getPlayerBDefBoard(), 'A', 9);
        model.setValue('C', model.getPlayerBDefBoard(), 'B', 1);
        model.setValue('C', model.getPlayerBDefBoard(), 'B', 2);
        model.setValue('C', model.getPlayerBDefBoard(), 'B', 3);
        model.setValue('D', model.getPlayerBDefBoard(), 'B', 4);
        model.setValue('D', model.getPlayerBDefBoard(), 'B', 5);
        model.setValue('E', model.getPlayerBDefBoard(), 'B', 6);
        model.setValue('E', model.getPlayerBDefBoard(), 'B', 7);
    }

}
