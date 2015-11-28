package battleship.bin;

/**
 * Write a description of class BattleshipView here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
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
        playerAOff = aToString();
        playerBOff = bToString();
    }

    /**
     * Method that converts row placement from char to int
     * 
     * @throws IllegalArgumentException if char is not from a-j
     */
    private char convertIntToChar(int a){
        char convertedInt = ' ';
        switch(a){
            case 0: convertedInt = 'a';
            break;
            case 1: convertedInt = 'b';
            break;
            case 2: convertedInt = 'c';
            break;
            case 3: convertedInt = 'd';
            break;
            case 4: convertedInt = 'e';
            break;
            case 5: convertedInt = 'f';
            break;
            case 6: convertedInt = 'g';
            break;
            case 7: convertedInt = 'h';
            break;
            case 8: convertedInt = 'i';
            break;
            case 9: convertedInt = 'j';
            break;
        }
        return convertedInt;
    }

    /**
     * Method turn player A's offensive board as a string
     * @return the player A's offensive board as a string
     */
    public String aToString(){
        //implement a toString method here
        //clear the board to recreate a new one
        playerAOff = "";
        for(int i = 0; i < 9; i++){
            playerAOff += "\n";
            for(int ix = 0; ix < 9; ix++){
                playerAOff += " " + model.getValue(model.getPlayerAOffBoard(), convertIntToChar(i), ix+1) + " |";
            }
            //adds the last piece 
            playerAOff += " " + model.getValue(model.getPlayerAOffBoard(), convertIntToChar(i), 10) + " ";
            playerAOff += "\n---+---+---+---+---+---+---+---+---+---";
        }
        //prints out the last line so seaparately so the last row doesn't display the separation block
        playerAOff += "\n";
        for(int ix = 0; ix < 9; ix++){
            playerAOff += " " + model.getValue(model.getPlayerAOffBoard(), convertIntToChar(9), ix+1) + " |";
        }
        playerAOff += " " + model.getValue(model.getPlayerAOffBoard(), convertIntToChar(9), 10) + " ";
        return playerAOff;
    }

    /**
     * Method turn player B's offensive board as a string
     * @return the player B's offensive board as a string
     */
    public String bToString(){
        //implement a toStringmethod here
        playerBOff = "";
        for(int i = 0; i < 9; i++){
            playerBOff += "\n";
            for(int ix = 0; ix < 9; ix++){
                playerBOff += " " + model.getValue(model.getPlayerBOffBoard(), convertIntToChar(i), ix+1) + " |";
            }
            //adds the last piece 
            playerBOff += " " + model.getValue(model.getPlayerBOffBoard(), convertIntToChar(i), 10) + " ";
            playerBOff += "\n---+---+---+---+---+---+---+---+---+---";
        }
        //prints out the last line so seaparately so the last row doesn't display the separation block
        playerBOff += "\n";
        for(int ix = 0; ix < 9; ix++){
            playerBOff += " " + model.getValue(model.getPlayerBOffBoard(), convertIntToChar(9), ix+1) + " |";
        }
        playerBOff += " " + model.getValue(model.getPlayerBOffBoard(), convertIntToChar(9), 10) + " ";
        return playerBOff;
    }
}
