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
        int shipPlacementCounter = 0;  
        //SETUP for ship counter
        while(model.getCurrentTurn()){
            char currentShip = shipSelector(shipPlacementCounter, model);
            System.out.println("Player A, please place your " + shipToString(model, currentShip)
                + ". \nType your " + "row, followed by a space, and then " +
                "the column.");
            //converts a char to a string
            //need to do some input validation

            //validates the input
            char row = validateUserChar(input);
            int col = validateUserInt(input);          
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
                model.makeShot(row, col);
                System.out.println(view.getAOffBoard());
                if(model.checkShotState(row, col) == STATUS.MISS){
                    model.setTurn();
                } else {
                    if(model.isGameOver()){
                        model.setGameOver();
                    }
                }
            } else {
                System.out.println("Player B, please place your shot." + "\nType your " 
                    + "row, followed by a space, and then " +
                    "the column.");
                char row = validateUserChar(input);
                int col = validateUserInt(input);   
                model.makeShot(row, col);
                System.out.println(view.getBOffBoard());
                if(model.checkShotState(row, col) == STATUS.MISS){
                    model.setTurn();
                } else {
                    if(model.isGameOver()){
                        model.setGameOver();
                    }
                }
                //check if shot kills ship
            }
        }
    }

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
}
