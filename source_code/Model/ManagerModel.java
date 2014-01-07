/**
 * Created with IntelliJ IDEA.
 * User: Lauren
 * Date: 28/04/13
 * Time: 22:15
 * To change this template use File | Settings | File Templates.
 */
package GreenPlanet.Model;

import GreenPlanet.Model.Database.DatabaseGame;
import GreenPlanet.Model.Player.PlayerData;
import GreenPlanet.Model.UtilityFunction.GetPlayer;
import greenplanetclient.Game;
import greenplanetclient.Player;

import java.util.HashMap;

public class ManagerModel {

    /**
     * All data registered for one player
     */
    private PlayerData myPlayer = new PlayerData();
    /**
     * HashMap containing all player
     */
    private HashMap<Integer,PlayerData> allPlayer = new HashMap<Integer, PlayerData>();
    /**
     * Field allows to register data at each turn
     */
    private DatabaseGame dataGame = new DatabaseGame();
    /**
     * user's login
     */
    public String myPlayerName;

    /**
     * Constructir
     * @param str represents the name of the player
     */
    public ManagerModel(String str){
        myPlayerName = str;
    }

    /**
     * Update all information for my player and all player presents in the game
     */
    public void updateGeneralData( Game g){
        int lastPowerNeedPlayer = searchLastPowerNeed(g,myPlayerName);
        myPlayer.updateData(GetPlayer.getMyPlayer(myPlayerName, g.getPlayers()),g,lastPowerNeedPlayer);
        updateHashMap(g);

        registerData(g);
    }

    /**
     * This method allows to initialize hash map if it if the first turn
     */
    private void initializeAllPlayer(Game g){
        for(Player p : g.getPlayers()){
            allPlayer.put(p.getId(),new PlayerData(p,g));
        }
    }

    /**
     *  This method allows to update data for all player
     */
    private void updateHashMap(Game g){
        if(g.getTurn() == 1) initializeAllPlayer(g);
        else {
            for(Player p : g.getPlayers()){
                //allPlayer.get(p.getId()).updateData(p,g);
                int lastPowerNeed = searchLastPowerNeed(g,p.getName());
                allPlayer.get(p.getId()).updateData(p,g,lastPowerNeed);
            }
        }
    }

    /**
     * This function allows to register hash map for this turn
     * @param g represents the current game
     */
    private void registerData(Game g){

        dataGame.addPlayer(g,allPlayer);
    }

    /**
     * Getter of my name player
     * @return String that represents the name of my player
     */
    public String getMyPlayerName() {
        return myPlayerName;
    }

    /**
     * Getter of hash map containing all player for one turn
     * @return hash map correspondent for all player registered
     */
    public HashMap<Integer, PlayerData> getAllPlayer() {
        return allPlayer;
    }

    /**
     * Getter of my player
     * @return PlayerData that represents my player
     */
    public PlayerData getMyPlayer() {
        return myPlayer;
    }

    /**
     * Getter of the database game
     * @return an object database game which store all information for one game
     */
    public DatabaseGame getDataGame() {
        return dataGame;
    }

    /**
     * Search the last power need for one player
     * @param g represents the current game
     * @param name  represents the name of the player that we want the las player need
     * @return int which represents the last power need
     */
    private int searchLastPowerNeed(Game g, String name){
        if(g.getTurn() == 1) return -1;
        else{
            PlayerData tmp = GetPlayer.getPlayerData(name,dataGame.getHashMapData().get(g.getTurn()-1));
            return tmp.getLastPowerNeed();
        }
    }
}
