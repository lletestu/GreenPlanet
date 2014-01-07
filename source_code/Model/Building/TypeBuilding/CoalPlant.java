/**
 * Created with IntelliJ IDEA.
 * User: Lauren
 * Date: 20/04/13
 * Time: 13:16
 * To change this template use File | Settings | File Templates.
 */
package GreenPlanet.Model.Building.TypeBuilding;

/**
 * Derived to the Abuilding represent the type of coal building
 */
public class CoalPlant extends ABuilding{
    /**
     * Default constructor
     */
    public CoalPlant(){
        super();

        name = "coal_fired_plant";
        production=500;
        price=400;
        life=10;
        bonusEco=-200;

        normalImpact = 1;

        computeMinProduction();
        computeUnitNRJProduce();

    }

    /**
     * This method that allows to compute the minimum of the production
     */
    @Override
    public void computeMinProduction() {
        minProduction=production;
    }

    /**
     * Compute the unit power produce
     */
    public void computeUnitNRJProduce() {
        unitNRJProduce=production;
    }
}
