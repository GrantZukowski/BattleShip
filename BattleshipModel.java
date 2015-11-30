package battleship.bin;


import java.awt.*;
import java.util.*;


/**
 * This is the model of the Battleship game. This will hold all the data structures that will
 * allow the game to be played
 * 
 * @author Samuel Grant Aiman
 * @version 11/25/14
 */
public class BattleshipModel extends java.util.Observable implements BattleshipBase 
{
    // instance variables - replace the example below with your own
    private char[][] playerADefBoard, playerBDefBoard, playerAOffBoard, playerBOffBoard;
    private GAMESTATE gameState;
    private boolean playerATurn;
    public static final int BOARDLEN = 10;
    public static final int AIRCARRIERHP = 5;
    public static final int BATTLESHIPHP = 4;
    public static final int CRUISERHP = 3;
    public static final int DESTROYERHP = 2;
    public static final char AIRCARRIER = 'A';
    public static final char BATTLESHIP = 'B';
    public static final char CRUISER = 'C';
    public static final char DESTROYER1 = 'D';
    public static final char DESTROYER2 = 'E';
    private char winner;
    /**
     * Constructor for objects of class BattleshipModel
     */
    public BattleshipModel(){
        gameState = GAMESTATE.SETUP;
        playerADefBoard = new char[10][10];
        playerBDefBoard = new char[10][10];
        playerAOffBoard = new char[10][10];
        playerBOffBoard = new char[10][10];
        emptyBoard();
        playerATurn = true;
        winner = ' ';
    }

    /**
     * Sets the game mode
     * @param state takes in the state that will change the gamestate of the game
     */
    public void setGameMode(GAMESTATE state){
        gameState = state;
    }
    
    /**
     * Sets the game into play mode
     */
    public void setPlayMode(){
        gameState = GAMESTATE.PLAY;
    }
    
    /**
     * Checks the game state to see whether methods can be called or not
     * @return returns the current gamestate enum
     */
    public GAMESTATE getGameMode(){
        return gameState;
    }

    /**
     * Checks the health of the particular ship given,
     * @param a the ship that you are trying to inquire
     * @return the shipstate of the current ship selected
     */
    public SHIPSTATE getShipState(char a){
        if(playerATurn){
            for(int i = 0; i < BOARDLEN; i++){
                for(int ix = 0; ix <BOARDLEN; ix++){
                    if(playerBDefBoard[i][ix] == a){
                        return SHIPSTATE.ALIVE;
                    }
                }
            }
        } else {
            for(int i = 0; i < BOARDLEN; i++){
                for(int ix = 0; ix <BOARDLEN; ix++){
                    if(playerADefBoard[i][ix] == a){
                        return SHIPSTATE.ALIVE;
                    }
                }
            }
        }
        return SHIPSTATE.DEAD;
    }

    /**
     * Sets ship to player A's defensive board, used by the controller
     * @param row the letter of the corresponding row
     * @param col the number of the corresponding column
     * @param ship which ship is being placed
     * @param player which player placed the ship and places to corresponding player
     * @throws IllegalArgumentException Row is not A-J or col not 1-10
     * @throws IllegalStateException can not place a ship outside of SETUP mode
     */
    public void placePlayerAShip(char row, int col, char ship){
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
        //do bounds checking here, as well as whether or not diagonal overlap
        if (Collections.frequency(rowChars, row) == 0 || col <= 0 || col > 10 ){
            throw new IllegalArgumentException("Row is not A-J or col not 1-10");
        }
        //increment counter
        //direction is set once the second pplace is put
        //check length okay
        if(!checkShipMax(ship) && checkShipPlacement(row, col)){
            setValue(ship, playerADefBoard, row, col);
        }
    }

    /**
     * Sets ship to each person's defensive board, used by the controller
     * @param row the letter of the corresponding row
     * @param col the number of the corresponding column
     * @param ship which ship is being placed
     * @param player which player placed the ship and places to corresponding player
     * @throws IllegalArgumentException Row is not A-J or col not 1-10
     * @throws IllegalStateException can not place a ship outside of SETUP mode
     */
    public void placePlayerBShip(char row, int col, char ship){
        //do bounds checking here, as well as whether or not diagonal overlap
        //maybe bounds check is called before this is called?
        //increment counter
        //direction is set once the second pplace is put
        //check length okay
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
        //do bounds checking here, as well as whether or not diagonal overlap
        if (Collections.frequency(rowChars, row) == 0 || col <= 0 || col > 10 ){
            throw new IllegalArgumentException("Row is not A-J or col not 1-10");
        }
        if(!checkShipMax(ship) && checkShipPlacement(row, col)){
            setValue(ship, playerBDefBoard, row, col);
        }
    }

    /**
     * Checks the amount of ships placed compared to the max, used by controller
     * when controller places ships to make sure no more than the max is placed
     * 
     * @param ship which ship is being checked
     * @return true if ship is at max, false if less than max
     */
    public boolean checkShipMax(char ship){
        //         int shipCounter = 0;
        //         if(playerATurn){
        //             for(int i = 0; i < BOARDLEN; i++){
        //                 for(int ix = 0; ix < BOARDLEN; ix++){
        //                     if(playerADefBoard[i][ix] == ship){
        //                         shipCounter++;
        //                     }
        //                 }
        //             }
        //         } else {
        //             for(int i = 0; i < BOARDLEN; i++){
        //                 for(int ix = 0; ix < BOARDLEN; ix++){
        //                     if(playerBDefBoard[i][ix] == ship){
        //                         shipCounter++;
        //                     }
        //                 }
        //             }
        //         }
        return (ship == AIRCARRIER && checkShipCount(ship) >= AIRCARRIERHP) ||
        (ship == BATTLESHIP && checkShipCount(ship) >= BATTLESHIPHP) ||
        (ship == CRUISER && checkShipCount(ship) >= CRUISERHP) ||
        (ship == DESTROYER1 && checkShipCount(ship) >= DESTROYERHP) ||
        (ship == DESTROYER2 && checkShipCount(ship) >= DESTROYERHP);
    }

    /**
     * Method that checks how many pieces of the ship there are, checks the opposite 
     * defensive board from the player turn since when checking, goes through the 
     * entire board to count up the ship health
     * 
     * @param ship the current ship that is checking the counter
     * @return the amount of pieces of the ship there are on the board
     */
    private int checkShipCount(char ship){
        int shipCounter = 0;
        if(playerATurn){
            for(int i = 0; i < BOARDLEN; i++){
                for(int ix = 0; ix < BOARDLEN; ix++){
                    if(playerADefBoard[i][ix] == ship){
                        shipCounter++;
                    }
                }
            }
        } else {
            for(int i = 0; i < BOARDLEN; i++){
                for(int ix = 0; ix < BOARDLEN; ix++){
                    if(playerBDefBoard[i][ix] == ship){
                        shipCounter++;
                    }
                }
            }
        }
        return shipCounter;
    }

    /**
     * Method that checks if the placement of the ship part is adjacent to another piece,
     * 
     * @param row the letter of the corresponding row
     * @param col the number of the corresponding column
     * @return true if ship can  be placed, false if there's an adjacent part the same
     */
    public boolean checkShipPlacement(char row, int col){
        //needs to check diagonals and adjacent pieces
        if(playerATurn){
            return playerAOffBoard[convertCharToInt(row)][col-1] == ' ';
        } else {
            return playerBOffBoard[convertCharToInt(row)][col-1] == ' ';
        }
    }

    /**
     * Method that converts row placement from char to int
     * 
     * @throws IllegalArgumentException if char is not from a-j
     */
    private int convertCharToInt(char a){
        int convertedInt = 0;
        switch(a){
            case 'a': case 'A': convertedInt = 0;
            break;      
            case 'b':  case 'B' :convertedInt = 1;
            break;
            case 'c':  case 'C': convertedInt = 2;
            break;
            case 'd':  case 'D': convertedInt = 3;
            break;
            case 'e':  case 'E' :convertedInt = 4;
            break;
            case 'f':  case 'F': convertedInt = 5;
            break;
            case 'g':  case 'G': convertedInt = 6;
            break;
            case 'h':  case 'H': convertedInt = 7;
            break;
            case 'i':  case 'I': convertedInt = 8;
            break;
            case 'j':  case 'J': convertedInt = 9;
            break;
        }
        return convertedInt;
    }

    /**
     * Method that check's whose turn it is
     * 
     * @return if true, it is player a's turn, false for player b's turn
     */
    public boolean getCurrentTurn(){
        return playerATurn;
    }

    /**
     * Method that sets whose turn it is, will be changed when a player misses a shot
     * method sets turns to the opposite player
     */
    public void setTurn(){
        playerATurn = !playerATurn;
    }

    /**
     * Method that is used to take a shot, sets value on board as 'H' used by controller, calls 
     * setValue() and sets the hit value on the the person's offensive board, checks the opponents
     * defensive board to check whether hit or miss, used by controller and viewer updates the 
     * appropriate spot on the board
     * @param row the letter of the corresponding row
     * @param col the number of the corresponding column
     * @throws IllegalStateException Cannot make move not in Play Mode
     */
    public void makeShot(char row, int col){
        if(playerATurn){
            //has to set 'O' on player's offensive and opponent's defensive board
            //logic here might be off
            if(playerBDefBoard[convertCharToInt(row)][col-1] != ' ' && 
            (playerBDefBoard[convertCharToInt(row)][col-1] != 'O' &&
                playerBDefBoard[convertCharToInt(row)][col-1] != 'X')){
                //hit
                setValue('O', playerAOffBoard, row, col);
                setValue('O', playerBDefBoard, row, col);
            } else {
                //miss
                setValue('X', playerAOffBoard, row, col);
                setValue('X', playerBDefBoard, row, col);
            }
        } else {
            if(playerADefBoard[convertCharToInt(row)][col-1] != ' ' &&
            (playerADefBoard[convertCharToInt(row)][col-1] != 'O' &&
                playerADefBoard[convertCharToInt(row)][col-1] != 'X')){
                //hit
                setValue('O', playerBOffBoard, row, col);
                setValue('O', playerADefBoard, row, col);
            } else {
                //miss
                setValue('X', playerBOffBoard, row, col);
                setValue('X', playerADefBoard, row, col);
            }
        }
    }

    /**
     * Method to be used after a ship has been sunk
     * @char ship which ship is being checked in
     * @return the ship status
     */
    public String checkShipStatePostShot(char ship){
        String shipState = null;;
        switch(ship){
            case AIRCARRIER: shipState = "You have sunk the AirCarrier.\n";
            break;
            case BATTLESHIP: shipState = "You have sunk the Battleship.\n";
            break;
            case CRUISER: shipState = "You have sunk the Cruiser.\n";
            break;
            case DESTROYER1: shipState = "You have sunk a Destroyer.\n";
            break;
            case DESTROYER2: shipState = "You have sunk a Destroyer.\n";
            break;
        }
        return shipState;
    }

    /**
     * Method that checks whether the shot made was a hit or a miss by checking if the appropriate 
     * spot on the opponent's board is occupied by a ship, to be used after making a shot to get
     * the appropriate state of the shot
     * @param row the letter of the corresponding row
     * @return STATUS enum that represents either a hit or miss
     * @throws IllegalStateException board spot is empty
     */
    public STATUS checkShotState(char row, int col){        
        if(playerATurn){
            //check if the board spot has had a shot taken
            if(playerBDefBoard[convertCharToInt(row)][col-1] != 'O' &&
            playerBDefBoard[convertCharToInt(row)][col-1] != 'X'){
                throw new IllegalStateException("There has not been a shot here yet");
            }
            if(playerBDefBoard[convertCharToInt(row)][col-1] == 'X'){
                return STATUS.MISS;
            } else {
                return STATUS.HIT;
            }
        } else {
            if(playerADefBoard[convertCharToInt(row)][col-1] != 'O' &&
            playerADefBoard[convertCharToInt(row)][col-1] != 'X'){
                throw new IllegalStateException("There has not been a shot here yet");
            }
            if(playerADefBoard[convertCharToInt(row)][col-1] == 'X'){
                return STATUS.MISS;
            } else {
                return STATUS.HIT;
            }
        }
    }

    /**
     * Method that checks if the shot made is able to be executed, used by the controller to check
     * if the current square has already taken a hit
     * @param row the letter of the corresponding row
     * @param col the number of the corresponding column
     * @throws IllegalArgumentException Row is not A-J or col not 1-10
     */
    public boolean isLegalShot(char row, int col){
        if(playerATurn){
            return (playerAOffBoard[convertCharToInt(row)][col-1] != 'O' &&
                    playerAOffBoard[convertCharToInt(row)][col-1] != 'X');
        } else {
            return (playerBOffBoard[convertCharToInt(row)][col-1] != 'O' &&
                    playerBOffBoard[convertCharToInt(row)][col-1] != 'X');
        }
    }

    /**
     * Gets which char will be in each spot on the board, this will be used by the view
     * to get which chars to display in a board
     * @param player picks which player will write board to
     * @param board picks between defensive and offensive
     * @param row the letter of the corresponding row
     * @param col the number of the corresponding column
     * @return returns the value at that square
     */
    public char getValue(char[][] board, char row, int col){
        if(board == playerADefBoard){
            return playerADefBoard[convertCharToInt(row)][col-1];
        } else if (board == playerAOffBoard){
            return playerAOffBoard[convertCharToInt(row)][col-1];
        } else if (board == playerBDefBoard){
            return playerBDefBoard[convertCharToInt(row)][col-1];
        } else {
            return playerBOffBoard[convertCharToInt(row)][col-1];
        }
    }    

    /**
     * Sets which char will be in each spot on the board, this will update the model, used by
     * the controller, updates the appropriate views
     * @param value sets which char will be set into the grid
     * @param board picks between defensive and offensive
     * @param row the letter of the corresponding row
     * @param col the number of the corresponding column
     */
    public void setValue(char value, char[][] board, char row, int col){
        if(board == playerADefBoard){
            playerADefBoard[convertCharToInt(row)][col-1] = value;
        } else if (board == playerAOffBoard){
            playerAOffBoard[convertCharToInt(row)][col-1] = value;
        } else if (board == playerBDefBoard){
            playerBDefBoard[convertCharToInt(row)][col-1] = value;
        } else if (board == playerBOffBoard){
            playerBOffBoard[convertCharToInt(row)][col-1] = value;
        }
        setChanged();
        notifyObservers();
    }

    /**
     * Method to get the playerADefBoard
     * 
     * @return playerADefBoard
     */
    public char[][] getPlayerADefBoard(){
        return playerADefBoard;
    }

    /**
     * Method to get the playerAOffBoard
     * 
     * @return playerAOffBoard
     */
    public char[][] getPlayerAOffBoard(){
        return playerAOffBoard;
    }

    /**
     * Method to get the playerADefBoard
     * 
     * @return playerBDefBoard
     */
    public char[][] getPlayerBDefBoard(){
        return playerBDefBoard;
    }

    /**
     * Method to get the playerBOfBoard
     * 
     * @return playerBOffBoard
     */
    public char[][] getPlayerBOffBoard(){
        return playerBOffBoard;
    }

    /**
     * Method that checks opposite player turns defensive board. Goes
     * through the entire defensive board to check if there's 
     * any ships still alive. Checks per player turn.
     * To be used by controller after a successful hit
     * @return true if any ship piece is alive, false if all are gone
     */
    public boolean isGameOver(){
        return (getShipState(AIRCARRIER) == SHIPSTATE.DEAD &&
            getShipState(BATTLESHIP) == SHIPSTATE.DEAD &&
            getShipState(CRUISER) == SHIPSTATE.DEAD &&
            getShipState(DESTROYER1) == SHIPSTATE.DEAD &&
            getShipState(DESTROYER2) == SHIPSTATE.DEAD);
    }

    /**
     * Method that sets the game to be over, to be used by the controller when a player
     * has all his ships destroyed, also sets the game's winner
     * @throws IllegalStateException can not be called when game state is not in PLAY
     */
    public void setGameOver(){
        gameState = GAMESTATE.GAMEOVER;
    }

    /**
     * Method to get the winner of the game, to be used by the view
     * @throws IllegalStateException can not be called when the game state is not in GAMEOVER
     * @return the winner as a char
     */
    public char getWinner(){
        return winner;   
    }
    
    /**
     * Method that sets all the boards to blank in order to start a new game
     */
    private void emptyBoard(){
        //resets every board to empty spots
        for(int i = 0; i < BOARDLEN; i++){
            for(int ix = 0; ix < BOARDLEN; ix++){
                playerADefBoard[i][ix] = ' ';
            }
        }
        for(int i = 0; i < BOARDLEN; i++){
            for(int ix = 0; ix < BOARDLEN; ix++){
                playerBDefBoard[i][ix] = ' ';
            }
        }for(int i = 0; i < BOARDLEN; i++){
            for(int ix = 0; ix < BOARDLEN; ix++){
                playerAOffBoard[i][ix] = ' ';
            }
        }
        for(int i = 0; i < BOARDLEN; i++){
            for(int ix = 0; ix < BOARDLEN; ix++){
                playerBOffBoard[i][ix] = ' ';
            }
        }
    }
    /**
     * Resets game by setting all spots on the board blank, used by controller, updates the views
     */
    public void resetGame(){
        emptyBoard();
        setGameMode(GAMESTATE.SETUP);
    }
}
