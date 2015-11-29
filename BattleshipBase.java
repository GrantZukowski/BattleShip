package battleship.bin;

/**
 * This interface holds the model for the Battleship game, including the offensive and defensive boards
 * for both the player and the opponent
 * 
 * @author Samuel No
 * @version AD310 11/9/15

/**
 * This interface holds the model for the Battleship game, including the offensive and defensive boards
 * for both the player and the opponent
 * 
 * @author Samuel No
 * @version AD310 11/9/15
 */
public interface BattleshipBase {
    /**
     * Sets the game mode
     */
    public void setGameMode(GAMESTATE state);

    /**
     * Checks the game state to see whether methods can be called or not
     */
    public GAMESTATE getGameMode();

    /**
     * Checks the health of the particular ship given,
     * @param a the ship that you are trying to inquire
     */
    public SHIPSTATE getShipState(char a);

    /**
     * Sets ship to player A's defensive board, used by the controller
     * @param row the letter of the corresponding row
     * @param col the number of the corresponding column
     * @param ship which ship is being placed
     * @param player which player placed the ship and places to corresponding player
     * @throws IllegalArgumentException Row is not A-J or col not 1-10
     * @throws IllegalStateException can not place a ship outside of SETUP mode
     */
    public void placePlayerAShip(char row, int col, char ship);

    /**
     * Sets ship to each person's defensive board, used by the controller
     * @param row the letter of the corresponding row
     * @param col the number of the corresponding column
     * @param ship which ship is being placed
     * @param player which player placed the ship and places to corresponding player
     * @throws IllegalArgumentException Row is not A-J or col not 1-10
     * @throws IllegalStateException can not place a ship outside of SETUP mode
     */
    public void placePlayerBShip(char row, int col, char ship);

    /**
     * Method that checks if the placement of the ship part is adjacent to another piece
     */
    public boolean checkShipPlacement(char row, int col);

    /**
     * Method that check's whose turn it is
     */
    public boolean getCurrentTurn();

    /**
     * Method that sets whose turn it is, will be changed when a player misses a shot
     */
    public void setTurn();

    /**
     * Method that is used to take a shot, sets value on board as 'H' used by controller, calls 
     * setValue() and sets the hit value on the the person's offensive board, checks the opponents
     * defensive board to check whether hit or miss, used by controller and viewer updates the 
     * appropriate spot on the board
     * @param row the letter of the correspondin
     * g row
     * @param col the number of the corresponding column
     * @throws IllegalStateException Cannot make move not in Play Mode
     */
    public void makeShot(char row, int col); 

    /**
     * Method that checks whether the shot made was a hit or a miss by checking if the appropriate 
     * spot on the opponent's board is occupied by a ship
     * @param row the letter of the corresponding row
     * @param col the number of the corresponding column
     */
    public STATUS checkShotState(char row, int col);

    /**
     * Method that checks if the shot made is able to be executed, used by the controller to check
     * if the current square has already taken a hit
     * @param row the letter of the corresponding row
     * @param col the number of the corresponding column
     * @throws IllegalArgumentException Row is not A-J or col not 1-10
     */
    public boolean isLegalShot(char row, int col);

    /**
     * Gets which char will be in each spot on the board, this will be used by the view
     * to get which chars to display in a board
     * @param player picks which player will write board to
     * @param board picks between defensive and offensive
     * @param row the letter of the corresponding row
     * @param col the number of the corresponding column
     */
    public char getValue(char[][] board, char row, int col);    

    /**
     * Sets which char will be in each spot on the board, this will update the model, used by
     * the controller, updates the appropriate views
     * @param value sets which char will be set into the grid
     * @param player picks which player will write board to
     * @param board picks between defensive and offensive
     * @param row the letter of the corresponding row
     * @param col the number of the corresponding column
     */
    public void setValue(char value, char[][] board, char row, int col);

    /**
     * Method that sets the game to be over, to be used by the controller when a player
     * has all his ships destroyed, also sets the game's winner
     * @throws IllegalStateException can not be called when game state is not in PLAY
     */
    public void setGameOver();

    /**
     * Method to get the winner of the game, to be used by the view
     * @throws IllegalStateException can not be called when the game state is not in GAMEOVER
     */
    public char getWinner();

    /**
     * Resets game by setting all spots on the board blank, used by controller, updates the views
     */
    public void resetGame();

}
/**
 * Helper enumerated type to return the status of the game and what phase it is on
 */
enum GAMESTATE {
    //return status, setup phase
    SETUP,
    //return status, setup play mode, can make shots
    PLAY,
    //return status, setup game over, can not make shots, can reset game
    GAMEOVER
};
/**
 * Helper enumerated type to return the status on the ship
 */
enum SHIPSTATE{
    //return status, checks if the ship is alive or dead
    ALIVE,
    //return status, checks if the ship is alive or dead
    DEAD
};
/**
 * Helper enumerated type for return status The SUNK_XXX
 * values indicate HIT. The current player's turn continues
 * until the return status is MISS.
 */
enum STATUS {
    // return status, a miss
    MISS, 
    // return status, a hit, doesn't sink a ship
    HIT, 
    // return status, a hit, sunk destroyer 
    SUNK_DESTROYER, 
    // return status, a hit, sunk cruiser
    SUNK_CRUISER, 
    // return status, a hit, sunk battleship
    SUNK_BATTLESHIP, 
    // return status, a hit, sunk aircraft carrier
    SUNK_AIRCRAFT_CARRIER, 
    // return status, location was already played or invalid
    DO_OVER
};
