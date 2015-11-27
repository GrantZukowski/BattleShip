
/**
 * Write a description of class BattleshipMain here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class BattleshipMain
{
    public static void main(String[] args){
        BattleshipModel model = new BattleshipModel();
        BattleshipView view = new BattleshipView(model);
        model.setValue('A', model.getPlayerADefBoard(), 'A', 10);
        // testing for out of bonds error
        //model.setValue('A', model.getPlayerADefBoard(), 'A', 0);
        model.placePlayerAShip('D', 15, 'E');

        System.out.println(model.getValue(model.getPlayerADefBoard(), 'A', 10));
    }
}
