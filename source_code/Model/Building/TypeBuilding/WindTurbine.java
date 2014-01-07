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
public class WindTurbine extends ABuilding{
    /**
     * Percentage of the dependence to the weather
     */
    private int percentageDependenceWeather;

    /**
     * Default constructor
     */
    public WindTurbine(){
        super();

        name = "wind_turbine";
        production=100;
        price=100;
        life=10;
        bonusEco=100;
        percentageDependenceWeather =100;

        windImpact =1;
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
     * @param percentWind represent the percentage of the dependence to the weather
     */
    public void computeUnitNRJProduce(int percentWind) {
       /* int unitDependance=production - (production* percentageDependenceWeather)/100;
        unitNRJProduce=(production - unitDependance) + (unitDependance*percentWind)/100; */

        unitNRJProduce = (float)production *((float)percentWind  / (float)100) * ((float)windImpact);

    }
}
