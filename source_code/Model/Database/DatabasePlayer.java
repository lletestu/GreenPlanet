/**
 * Created with IntelliJ IDEA.
 * User: Lauren
 * Date: 22/04/13
 * Time: 16:40
 * To change this template use File | Settings | File Templates.
 */
package GreenPlanet.Model.Database;

import GreenPlanet.Model.Player.PlayerData;

import java.util.HashMap;

/**
 * Class to store information for player
 */
public class DatabasePlayer {
    /**
     * Hash map to store players
     */
    HashMap<Integer,PlayerData> playersData = new HashMap<Integer, PlayerData>();

    /**
     * This method alllows to add on player
     * @param p pObject that represent one player
     */
    public void addPlayerData(PlayerData p){
        playersData.put(p.getId(),p);
    }

    /**
     * Getter of the hash map player
     * @return an hash map that represent all information for players
     */
    public HashMap<Integer, PlayerData> getPlayerData() {
        HashMap<Integer, PlayerData> tmp=playersData;
        return tmp;
    }
}
