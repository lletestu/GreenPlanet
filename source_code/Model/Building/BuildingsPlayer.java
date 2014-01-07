/**
 * Created with IntelliJ IDEA.
 * User: Lauren
 * Date: 28/04/13
 * Time: 19:37
 * To change this template use File | Settings | File Templates.
 */

package GreenPlanet.Model.Building;

import GreenPlanet.Model.Building.NumberBuilding.NumberBuildingActual;
import GreenPlanet.Model.Building.NumberBuilding.NumberBuildingPrevision;
import GreenPlanet.Model.Building.TypeBuilding.*;
import greenplanetclient.Game;
import greenplanetclient.Player;

/**
 * Class to save the player's building
 */
public class BuildingsPlayer {
    /**
     * Number of actual building
     */
    private NumberBuildingActual buildingActual;
    /**
     * Number of building for the
     */
    private NumberBuildingPrevision buildingNextTurn;
    /**
     * Represents the actual production
     */
    private int productionActual=0;

    /**
     * Constructor to initialize the building player
     * @param p Object which represents the current player
     * @param g Object which represents the current game
     */
    public BuildingsPlayer(Player p, Game g){
        buildingActual = new NumberBuildingActual(p.getBuildings(),g.getTurn());
        buildingNextTurn = new NumberBuildingPrevision(p.getBuildings(),g.getTurn());

        computePrevisionProduction(g);

    }

    /**
     * Compute the production
     * @param g Object which represents the current game
     */
    private void computePrevisionProduction(Game g){

        //productionActual = p.getPowerAvailable();

        if(buildingActual!=null){
            if(buildingActual.getNbCoalPlant()!=0){
                CoalPlant tmp=new CoalPlant();
                tmp.computeUnitNRJProduce();
                productionActual += buildingActual.getNbCoalPlant()*tmp.getUnitNRJProduce();
            }
            if(buildingActual.getNbNuclear()!=0){
                Nuclear tmp=new Nuclear();
                tmp.computeUnitNRJProduce();
                productionActual += buildingActual.getNbNuclear()*tmp.getUnitNRJProduce();
            }
            if(buildingActual.getNbSolarPlant() != 0){
                SolarPlant tmp=new SolarPlant();
                tmp.computeUnitNRJProduce(g.getLight());
                productionActual += buildingActual.getNbSolarPlant()*tmp.getUnitNRJProduce();
            }
            if(buildingActual.getNbWaterTurbine() != 0){
                WaterTurbine tmp=new WaterTurbine();
                tmp.computeUnitNRJProduce(g.getWater());
                productionActual += buildingActual.getNbWaterTurbine()*tmp.getUnitNRJProduce();
            }
            if(buildingActual.getNbWindTurbine()!=0){
                WindTurbine tmp=new WindTurbine();
                tmp.computeUnitNRJProduce(g.getWind());
                productionActual += buildingActual.getNbWindTurbine()*tmp.getUnitNRJProduce();
            }
        }
    }

    /**
     * Getter of the actual production
     * @return current production
     */
    public int getProductionActual() {
        return productionActual;
    }

    /**
     * Getter of the number actual building
     * @return Object NumberBuildingActual that represents the building of the player
     */
    public NumberBuildingActual getBuildingActual() {
        return buildingActual;
    }

    /**
     * Getter of player's building for the next turn
     * @return Object NumberBuildingActual that represents the building of the player for the next turn
     */
    public NumberBuildingPrevision getBuildingNextTurn() {
        return buildingNextTurn;
    }
}
