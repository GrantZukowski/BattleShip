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
        
        System.out.println("Player A gets to place his ships, you will place one ship at a time, at" +
                            "one piece of the ship at a time.");
        Scanner input = new Scanner(System.in);
        while(model.getCurrentTurn()){
            int shipPlacementCounter = 0;
            if(model.checkShipMax(model.AIRCARRIER)){
                shipPlacementCounter++;
            }
            System.out.println("Player A, please place your aircraft carriers. \nPlace your " + 
                                "row, and then column.");
            //converts a char to a string
            //need to do some input validation
            char row = input.next().charAt(0);
            int col = input.nextInt();
            model.placePlayerAShip(row, col, model.AIRCARRIER);
            System.out.println(view.aToString());
        }
    }
}
