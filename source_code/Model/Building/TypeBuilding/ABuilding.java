/**
 * Created with IntelliJ IDEA.
 * User: Lauren
 * Date: 20/04/13
 * Time: 12:49
 * To change this template use File | Settings | File Templates.
 */

package GreenPlanet.Model.Building.TypeBuilding;

/**
 * Abstract building to store the data to each type of building
 */
public abstract class ABuilding {
    /**
     * Production of the building
     */
    protected int production=0;
    /**
     * Minimum of the power price
     */
    protected int minProduction=0;
    /**
     * price of the building
     */
    protected static int price=0;
    /**
     * life of a building
     */
    protected int life=0;
    /**
     * Ecologic bonus
     */
    protected int bonusEco=0;
    /**
     * Number of unit produce
     */
    protected float unitNRJProduce=0;
    /**
     * Name of the building
     */
    protected String name;
    /**
     * Normal impact
     */
    protected float normalImpact = 0;
    /**
     * Wind impact
     */
    protected float windImpact =0;
    /**
     * Sun impact
     */
    protected float sunImpact =0;
    /**
     * Water impact
     */
    protected float waterImpact =0;

    /**
     * Abstract method that allows to compute the minimum of the production
     */
    public abstract void computeMinProduction();

    /**
     * Getter production
     * @return production of the building
     */
    public int getProduction() {
        return production;
    }

    /**
     * Getter of building price
     * @return the price of the building
     */
    public static int getPrice() {
        return price;
    }

    /**
     * Return the life of the building
     * @return life of the building
     */
    public int getLife() {
        return life;
    }

    /**
     * Return the ecologic bonus
     * @return ecologic bonus
     */
    public int getBonusEco() {
        return bonusEco;
    }

    /**
     * Return the minimum production
     * @return minimum production
     */
    public int getMinProduction() {
        return minProduction;
    }

    /**
     * Return the unit power production
     * @return unit power production
     */
    public float getUnitNRJProduce() {
        return unitNRJProduce;
    }

    public String getName() {
        return name;
    }
}
