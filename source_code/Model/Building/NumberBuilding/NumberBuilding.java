package GreenPlanet.Model.Building.NumberBuilding;

import greenplanetclient.Building;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created with IntelliJ IDEA.
 * User: Lauren
 * Date: 20/04/13
 * Time: 17:28
 * To change this template use File | Settings | File Templates.
 */

/**
 * Abstract class to store the information to the different buildings
 */
public abstract class NumberBuilding {
    /**
     * Enumeration that represents the different type of the building
     */
    protected static enum batType {NUCLEAR, WIND, SOLAR, WATER, COAL}

    /**
     * Hash map that represents the different type of the building
     */
    protected HashMap<String,batType> nameBuilding=new HashMap<String, batType>(){
        {put("nuclear",batType.NUCLEAR);}{put("wind_turbine",batType.WIND);}{put("solar_plant", batType.SOLAR);}
        {put("water_turbine", batType.WATER);}{put("coal_fired_plant", batType.COAL);}
    };

    /**
     * Number of solar plant
     */
    protected int nbSolarPlant=0;
    /**
     * Number of water turbine
     */
    protected int nbWaterTurbine=0;
    /**
     * Number of wind turbine
     */
    protected int nbWindTurbine=0;
    /**
     * Number of coal plant
     */
    protected int nbCoalPlant=0;
    /**
     * Number of nuclear plant
     */
    protected int nbNuclear=0;

    /**
     * Default constructor
     */
    public NumberBuilding(){}

    /**
     * Constructor with arrayLis of building and number of the turn in parameter
     * @param b represents the array list of the building
     * @param turn represents the current turn
     */
    public NumberBuilding(ArrayList<Building> b,int turn){
        computeNumberBuilding(b,turn);
    }

    /**
     * abstract method to calculate the number of the building
     * @param buildingPlayer  array list of the building
     * @param turn represents the current turn
     */
    public abstract void computeNumberBuilding(ArrayList<Building> buildingPlayer, int turn);

    /**
     * Getter of the number of solar plant
     * @return number of solar plant
     */
    public int getNbSolarPlant() {
        return nbSolarPlant;
    }

    /**
     * Getter of the number of water turbine
     * @return number of water turbine
     */
    public int getNbWaterTurbine() {
        return nbWaterTurbine;
    }

    /**
     * Getter of the number of wind turbine
     * @return  number of the wind tubine
     */
    public int getNbWindTurbine() {
        return nbWindTurbine;
    }

    /**
     * Getter of the number of coal plant
     * @return number of the coal plant
     */
    public int getNbCoalPlant() {
        return nbCoalPlant;
    }

    /**
     * Getter of the number of nuclear plant
     * @return number of the nuclear plant
     */
    public int getNbNuclear() {
        return nbNuclear;
    }

    public String toStringFile(){
        return "Nb Nuclear= "+nbNuclear+" Nb SolarPlant= "+nbSolarPlant + " Nb WaterTurbine= " +nbWaterTurbine + " Nb WindTurbine= " +nbWindTurbine+" Nb CoalPlant= "+nbCoalPlant;
    }

    /**
     * Override method to string
     * @return String with the number of each building
     */
    @Override
    public String toString(){
        return String.format(" %-7d %-6d%-6d%-5d%-5d",nbNuclear,nbSolarPlant,nbWaterTurbine,nbWindTurbine,nbCoalPlant);
    }
}
