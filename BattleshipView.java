package battleship;


/**
 * This is the view for the Battleship game. This converts the model of the game into viewable 
 * features in order to play the game.
 * 
 * @author Samuel Grant Aiman
 * @version 11/25/14
 */
public class BattleshipView implements java.util.Observer 
{
    // instance variables - replace the example below with your own
    private BattleshipModel model;
    private String playerAOff, playerBOff;
    /**
     * Constructor for objects of class BattleshipView
     */
    public BattleshipView(BattleshipModel m){
        model = m;
        playerAOff = "";
        playerBOff = "";
        model.addObserver(this);
    }
    
    /**
     * Overidden method that calls the boardChanged() method that updates the board when 
     * either a fixGiven or setValue is called
     * 
     * @param o Observable being used
     * @param arg Object being passed through
     */
    public void update(java.util.Observable o, Object arg){
        playerAOff = AToString();
        playerBOff = BToString();
    }
    
    /**
     * Method turn player A's offensive board as a string
     * @return the player A's offensive board as a string
     */
    public String AToString(){
        //implement a toString method here
        return playerAOff;
    }
    
    /**
     * Method turn player B's offensive board as a string
     * @return the player B's offensive board as a string
     */
    public String BToString(){
        //implement a toStringmethod here
        return playerBOff;
    }
}
