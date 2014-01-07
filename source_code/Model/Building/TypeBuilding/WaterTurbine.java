/**
 * Created with IntelliJ IDEA.
 * User: Lauren
 * Date: 20/04/13
 * Time: 13:15
 * To change this template use File | Settings | File Templates.
 */
package GreenPlanet.Model.Building.TypeBuilding;

/**
 * Derived to the Abuilding represent the type of water building
 */
public class WaterTurbine extends ABuilding {
    /**
     * Percentage of the dependence to the weather
     */
    private int percentageDependenceWeather;
    /**
     * Default constructor
     */
    public WaterTurbine(){
        super();

        name = "water_turbine";
        production=300;
        price=500;
        life=10;
        bonusEco=300;
        percentageDependenceWeather =50;

        normalImpact = (float)0.5;
        waterImpact =(float)0.5;
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
     * @param percentWater represent the percentage of the dependence to the weather
     */
    public void computeUnitNRJProduce(int percentWater) {
       /* int unitDependance=production - (production* percentageDependenceWeather)/100;
        unitNRJProduce=(production-unitDependance) + (unitDependance*percentWater)/100; */

        unitNRJProduce = 0;

        unitNRJProduce= (float)production*(float)normalImpact + (float)production*(float)((float)percentWater / (float)100) * ((float)waterImpact);
    }
}
