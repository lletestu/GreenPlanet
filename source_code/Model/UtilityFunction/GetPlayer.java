/**
 * Created with IntelliJ IDEA.
 * User: Lauren
 * Date: 28/04/13
 * Time: 19:55
 * To change this template use File | Settings | File Templates.
 */
package GreenPlanet.Model.UtilityFunction;

import GreenPlanet.Model.Player.PlayerData;
import greenplanetclient.Player;
import greenplanetclient.PlayerStateEnum;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Class to search a player
 */
public class GetPlayer {

    /**
     * Find a player
     * @param name String represents the name of the player that we want
     * @param players Array list of player
     * @return  player correspondent to the name player
     */
    public static Player getMyPlayer(String name, ArrayList<Player> players){

        if(players != null){
            for(Player p : players){
                if(p.getName().equals(name)) return p;
            }
        }
        return null;
    }

    /**
     * Get a player name correspondent to an id
     * @param id Int that represents the id player that we want name
     * @param players Array list of player
     * @return name correspondent to the id player
     */
    public static String getPlayerName(int id, ArrayList<Player> players){
        if(players != null){
            for(Player p : players){
                if(p.getId() == id) return p.getName();
            }
        }
        return null;
    }

    /**
     * Find a player
     * @param name String represents the name of the player that we want
     * @param player  Array list of player
     * @return object PlayerData correspondent to the name player
     */
    public static PlayerData getPlayerData(String name, HashMap<Integer,PlayerData> player){
        if (player != null){
            for(int myKey : player.keySet()){
                if(player.get(myKey).getName().equals(name)) return player.get(myKey);
            }
        }
        return null;
    }

    /**
     * Find the player that won the game
     * @param gameData Represents hash map that store all information for one game
     * @return ane array of object containing the number of turn and the player who win
     */
    public static Object[] getWinnerPlayer(HashMap<Integer,HashMap<Integer,PlayerData>> gameData){
        int numberTurn = gameData.size();
        Object[] tmp = new Object[2];

        for(int i = numberTurn;i>0;--i){
            if(searchAlivePlayer(gameData.get(i)) == 1){
                tmp[0] = i ;
                tmp[1] = searchWinner(gameData.get(i));
                return tmp;
            }
        }

        return null;
    }

    /**
     * Function that allows to find how many player alive
     * @param playerDataHashMap represents the all player data registered for on turn
     * @return int represents the number of player alive
     */
    public static int searchAlivePlayer(HashMap<Integer,PlayerData> playerDataHashMap){
        int numberPlayerAlive = 0;
        for(int mapKey : playerDataHashMap.keySet()){
            if(playerDataHashMap.get(mapKey).getState().equals(PlayerStateEnum.ALIVE)) ++numberPlayerAlive;
        }
        return numberPlayerAlive;
    }

    /**
     * This method allows to find the las player alive in on turn
     * @param playerDataHashMap  represents the all player data registered for on turn
     * @return PlayerData which represents the first player alive
     */
    public static PlayerData searchWinner(HashMap<Integer,PlayerData> playerDataHashMap){
        for(int mapKey : playerDataHashMap.keySet()){
            if(playerDataHashMap.get(mapKey).getState().equals(PlayerStateEnum.ALIVE)) return playerDataHashMap.get(mapKey);
        }
        return null;
    }
}
