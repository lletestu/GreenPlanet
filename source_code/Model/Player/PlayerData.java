/**
 * Created with IntelliJ IDEA.
 * User: Lauren
 * Date: 28/04/13
 * Time: 19:32
 * To change this template use File | Settings | File Templates.
 */

package GreenPlanet.Model.Player;

import GreenPlanet.Model.Building.BuildingsPlayer;
import greenplanetclient.Game;
import greenplanetclient.Player;
import greenplanetclient.PlayerStateEnum;

import java.io.Serializable;

/**
 * Class which represents one player
 */
public class PlayerData implements Serializable {

    /**
     * Id of the player
     */
    private int id=0;
    /**
     * Pseudo
     */
    private String name="";
    /**
     * State of the player dead or alive
     */
    private PlayerStateEnum state=null;
    /**
     * Cash
     */
    private float cash=0;
    /**
     * Green rank of the player
     */
    private int greenRank=0;
    /**
     * Pollution
     */
    private int pollution=0;
    /**
     * Power available
     */
    private int powerAvailable=0;
    /**
     * Power need for the curren turn
     */
    private int powerNeed=-1;
    /**
     * Last power need
     */
    private int lastPowerNeed=-1;
    /**
     * Ratio powerAvailable / PowerNeed
     */
    private float satisfaction=0;
    /**
     * Impact green
     */
    private final double impactGreen=0.2;
    /**
     * Evolution of the population
     */
    private final double populationEvolution=0.2;

    /**
     * Player's building
     */
    private BuildingsPlayer buildingsPlayer;

    /**
     * Defaut constructor
     */
    public PlayerData(){}

    /**
     * Constructor allows to initialize all data
     * @param p represent the current player
     * @param g represent the current game
     */
    public PlayerData(Player p, Game g){
        updateData(p,g);
    }

    /**
     * Constructor allows to initialize all data
     * @param p represent the current player
     * @param g represent the current game
     * @param lastPowerNeed represents the last power need for thi player
     */
    public PlayerData(Player p,Game g,int lastPowerNeed){
        this.lastPowerNeed = lastPowerNeed;
        updateData(p,g);
    }

    /**
     * This method allows to update data for this player
     * @param p represent the current player
     * @param g represent the current game
     */
    public void updateData(Player p, Game g){
        id=p.getId();
        name=p.getName();
        state = p.getState();
        cash = p.getCash();
        greenRank = p.getGreenRank();
        pollution = p.getPollution();
        powerAvailable = p.getPowerAvailable();

        powerNeed = lastPowerNeed;
        lastPowerNeed = p.getPowerNeed();
        computeRatioSatisfaction();

        buildingsPlayer = new BuildingsPlayer(p,g);

    }

    /**
     * This method allows to update data for this player
     * @param p represent the current player
     * @param g represent the current game
     * @param lastPowerNeed represents the last power need for thi player
     */
    public void updateData(Player p, Game g, int lastPowerNeed){
        id=p.getId();
        name=p.getName();
        state = p.getState();
        cash = p.getCash();
        greenRank = p.getGreenRank();
        pollution = p.getPollution();
        powerAvailable = p.getPowerAvailable();

        powerNeed = lastPowerNeed;
        this.lastPowerNeed = p.getPowerNeed();
        computeRatioSatisfaction();

        buildingsPlayer = new BuildingsPlayer(p,g);


    }

    /**
     * This method allows to compute the satisfaction
     */
    public void computeRatioSatisfaction(){
        satisfaction = (float)powerAvailable / (float)powerNeed ;
        satisfaction = (float)(Math.round(satisfaction*100))/100;
    }

    /**
     * Override method to string
     * @return String that represent alla information for this player
     */
    @Override
    public String toString(){

        return String.format("%5s %15s %6s %10s %5s %10s %10s %10s %10s %30s %10s",String.valueOf(id),name,state.toString(),
                String.valueOf(cash), String.valueOf(greenRank),String.valueOf(pollution),String.valueOf(powerAvailable),
                String.valueOf(powerNeed),String.valueOf(satisfaction),buildingsPlayer.getBuildingActual().toString(),
                String.valueOf(buildingsPlayer.getProductionActual()));
    }

    /**
     * Getter id player
     * @return id of the player
     */
    public int getId() {
        return id;
    }

    /**
     * Getter of the name player
     * @return the name of the player
     */
    public String getName() {
        return name;
    }

    /**
     * Getter of the state player
     * @return the state of the player
     */
    public PlayerStateEnum getState() {
        return state;
    }

    /**
     * Getter of the cash player
     * @return the cash of the player
     */
    public float getCash() {
        return cash;
    }

    /**
     * Getter of the green rank player
     * @return  the green rank of the player
     */
    public int getGreenRank() {
        return greenRank;
    }

    /**
     * Getter of the pollution
     * @return int that represents the pollution
     */
    public int getPollution() {
        return pollution;
    }

    /**
     * Getter of the power available
     * @return int that represents the power available
     */
    public int getPowerAvailable() {
        return powerAvailable;
    }

    /**
     * Getter of the power need
     * @return int that represents the power need
     */
    public int getPowerNeed() {
        return powerNeed;
    }

    /**
     * Getter of the last power need
     * @return int that represents the last power need
     */
    public int getLastPowerNeed() {
        return lastPowerNeed;
    }

    /**
     * Getter of the satisfaction
     * @return float that represents the satisfaction
     */
    public float getSatisfaction() {
        return satisfaction;
    }

    /**
     * Getter of the player's building
     * @return Object BuildingPlayer that represents the building
     */
    public BuildingsPlayer getBuildingsPlayer() {
        return buildingsPlayer;
    }
}
