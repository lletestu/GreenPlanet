/**
 * Created with IntelliJ IDEA.
 * User: Lauren
 * Date: 22/04/13
 * Time: 18:44
 * To change this template use File | Settings | File Templates.
 */
package GreenPlanet.Model.Database;

import GreenPlanet.Model.Player.PlayerData;
import greenplanetclient.Game;

import java.util.HashMap;

/**
 * Class allowing to store all player's data for the current game
 */
public class DatabaseGame {
    /**
     * Hash map allowing to store all player's data for hue current game
     */
    private HashMap<Integer,HashMap<Integer,PlayerData>> gameData = new HashMap<Integer, HashMap<Integer,PlayerData>>();

    /**
     * This method allows to add a player on the hash map
     * @param g Represents the current game
     * @param allPlayer Represents player presents in this game
     */
    public void addPlayer(Game g,HashMap<Integer,PlayerData> allPlayer){
        DatabasePlayer onePlayer = new DatabasePlayer();

        for (int maKey : allPlayer.keySet()){
            onePlayer.addPlayerData(allPlayer.get(maKey));
        }
        gameData.put(g.getTurn(),onePlayer.getPlayerData());
    }

    /**
     * Getter of the hash map player's data
     * @return hash map gameData which store all information
     */
    public HashMap<Integer,HashMap<Integer,PlayerData>> getHashMapData(){
        return gameData;
    }

    /**
     * Getter of the last power need for one player
     * @param allPlayer Represents player presents in this game
     * @param id Represents the id of the player that you want last power need
     * @return
     */
    private int getLastPowerNeed(HashMap<Integer,PlayerData> allPlayer,int id){
         return allPlayer.get(id).getLastPowerNeed();
    }

}
