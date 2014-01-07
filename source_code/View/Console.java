/**
 * Created with IntelliJ IDEA.
 * User: Lauren
 * Date: 28/04/13
 * Time: 20:18
 * To change this template use File | Settings | File Templates.
 */
package GreenPlanet.View;

import java.util.HashMap;
import GreenPlanet.Model.Player.PlayerData;

/**
 * Class to create a console view
 */
public class Console {

    /**
     * String static represents the header of the array
     */
    private static final String header=String.format("%5s %15s %6s %10s %5s %10s %10s %10s %10s %30s  %10s","ID","Login","State","Cash","GRK",
            "Poll","powerA","powerN","Ratio","Nuclear Solar Water Wind Coal","Production");

    /**
     * This method allows to display all data of the current game
     * @param playersDataGame represents hash map which contain information of player
     */
    public static void dataGame(HashMap<Integer,PlayerData> playersDataGame){

            System.out.println(header);

            for(Integer mapKey : playersDataGame.keySet()){
                System.out.println(playersDataGame.get(mapKey).toString());
            }

            for(int j=0;j<135;++j) System.out.print("*");
            System.out.println();

    }
}
