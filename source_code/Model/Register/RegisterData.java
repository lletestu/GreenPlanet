/**
 * Created with IntelliJ IDEA.
 * User: Lauren
 * Date: 20/04/13
 * Time: 13:19
 * To change this template use File | Settings | File Templates.
 */
package GreenPlanet.Model.Register;

import GreenPlanet.Model.Player.PlayerData;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.HashMap;

/**
 * Class to store data of the game in the file
 */
public class RegisterData {
    /**
     * String header for the file
     */
    private static final String header=String.format("%5s %15s %6s %10s %5s %10s %10s %10s %10s %30s  %10s","ID","Login","State","Cash","GRK",
            "Poll","powerA","powerN","Ratio","Nuclear Solar Water Wind Coal","Production");

    /**
     * Register data
     */
    public RegisterData(){}

    /**
     * This method allows to register all information of the game
     * @param playersDataGame  hash map of the game contain all information for each player
     * @throws Exception thrown Exception if it is not possible to write in the file
     */
    public static void registerDatabaseGame(HashMap<Integer,HashMap<Integer,PlayerData>> playersDataGame) throws Exception{
        String nameFile= ManagerDirectory.getAbsolutePath()+"DatabaseGame.txt";

        if(ManagerDirectory.directoryExist()){
            PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(nameFile,true)));

            int i=1;
            while (playersDataGame.get(i) != null){
                out.println("Turn : " + i);
                out.println();
                out.println(header);

                HashMap<Integer,PlayerData> tmp = playersDataGame.get(i);

                for(Integer mapKey : tmp.keySet()){
                    out.println(tmp.get(mapKey).toString());
                }

                for(int j=0;j<135;++j) out.print("*");
                out.println();

                ++i;
            }


            out.close();
        }
    }

    /**
     * This method allows to register all information of the game at each turn
     * @param playersDataGame  hash map of the game contain all information for each player
     * @param turn represents the current turn of the game
     * @throws Exception thrown Exception if it is not possible to write in the file
     */
    public static void updateRegister(HashMap<Integer,HashMap<Integer,PlayerData>> playersDataGame, int turn) throws Exception{
        String nameFile= ManagerDirectory.getAbsolutePath()+"DatabaseGame.txt";

        if(ManagerDirectory.directoryExist()){
            PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(nameFile,true)));

                out.println("Turn : " + turn);
                out.println();
                out.println(header);

                HashMap<Integer,PlayerData> tmp = playersDataGame.get(turn);

                for(Integer mapKey : tmp.keySet()){
                    out.println(tmp.get(mapKey).toString());
                }

                for(int j=0;j<135;++j) out.print("*");
                out.println();

            out.close();
        }
    }




}
