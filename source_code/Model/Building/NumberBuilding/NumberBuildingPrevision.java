/**
 * Created with IntelliJ IDEA.
 * User: Lauren
 * Date: 20/04/13
 * Time: 19:42
 * To change this template use File | Settings | File Templates.
 */
package GreenPlanet.Model.Building.NumberBuilding;

import greenplanetclient.Building;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Derived class to NumberBuilding allowing to compute the building for the next turn
 */
public class NumberBuildingPrevision extends NumberBuilding implements Serializable {

    /**
     * Constructor with array list of building and current turn in parameter
     * @param b represents the array list of building
     * @param turn represents the current turn of the game
     */
    public NumberBuildingPrevision(ArrayList<Building> b, int turn) {
        super(b, turn);
    }

    /**
     * This method allows to return an array list of the name of building that available for the next turn
     * @param buildingPlayer array list of building
     * @param turn current turn of the game
     * @return array list string of the building
     */
    private ArrayList<String> availableBuildingNextTurn(ArrayList<Building> buildingPlayer, int turn){
        ArrayList<String> availableBuilding = new ArrayList<String>();

        if(buildingPlayer!=null){
            for(Building b:buildingPlayer){
                if((b.getExpireAt()-turn)>0) availableBuilding.add(b.getType());
            }
        }
         return availableBuilding;
    }

    /**
     * Override method to compute the number of building
     * @param buildingPlayer  array list of the building
     * @param turn represents the current turn
     */
    @Override
    public void computeNumberBuilding(ArrayList<Building> buildingPlayer, int turn) {
        nbSolarPlant=0;
        nbWaterTurbine=0;
        nbWindTurbine=0;
        nbCoalPlant=0;
        nbNuclear=0;

        ArrayList<String> availableBuilding = availableBuildingNextTurn(buildingPlayer,turn);

        for(String b:availableBuilding){
            switch (nameBuilding.get(b)){
                case NUCLEAR:
                    ++nbNuclear;
                    break;

                case WIND:
                    ++nbWindTurbine;
                    break;

                case SOLAR:
                    ++nbSolarPlant;
                    break;

                case WATER:
                    ++nbWaterTurbine;
                    break;

                case COAL:
                    ++nbCoalPlant;
                    break;

                default:
                    break;
            }
        }
    }

}
