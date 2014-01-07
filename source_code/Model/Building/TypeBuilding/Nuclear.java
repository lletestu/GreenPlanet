/**
 * Created with IntelliJ IDEA.
 * User: Lauren
 * Date: 20/04/13
 * Time: 13:16
 * To change this template use File | Settings | File Templates.
 */
package GreenPlanet.Model.Building.TypeBuilding;

/**
 * Derived to the Abuilding represent the type of nuclear building
 */
public class Nuclear extends ABuilding{
    /**
     * Default constructor
     */
    public Nuclear(){
        super();

        name = "nuclear";
        production=1000;
        price=1000;
        life=10;
        bonusEco=-100;

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
