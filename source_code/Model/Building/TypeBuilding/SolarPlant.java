/**
 * Created with IntelliJ IDEA.
 * User: Lauren
 * Date: 20/04/13
 * Time: 13:15
 * To change this template use File | Settings | File Templates.
 */
package GreenPlanet.Model.Building.TypeBuilding;

/**
 * Derived to the Abuilding represent the type of solar building
 */
public class SolarPlant extends ABuilding{
    /**
     * Percentage of the dependence to the weather
     */
    private int percentageDependenceWeather;

    /**
     * Default constructor
     */
    public SolarPlant(){
        super();

        name ="solar_plant";
        production=100;
        price=200;
        life=10;
        bonusEco=0;
        percentageDependenceWeather =100;

        sunImpact =1;
    }

    /**
     * This method that allows to compute the minimum of the production
     */
    @Override
    public void computeMinProduction() {
        minProduction=production- percentageDependenceWeather /100;
    }

    /**
     * Compute the unit power produce
     * @param percentSolar represent the percentage of the dependence to the weather
     */
    public void computeUnitNRJProduce(int percentSolar) {
        /*int unitDependance=production - (production* percentageDependenceWeather)/100;
        unitNRJProduce=(production - unitDependance) + (unitDependance*percentSolar)/100;  */

        unitNRJProduce = 0;

        float energyUsed = 0;
        unitNRJProduce = (float)production*((float)percentSolar /(float) 100) * ((float)sunImpact);
    }
}
