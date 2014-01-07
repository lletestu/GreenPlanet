/**
 * Created with IntelliJ IDEA.
 * User: Jean-Marc
 * Date: 01/05/13
 * Time: 00:02
 * To change this template use File | Settings | File Templates.
 * 
 */
package GreenPlanet.Model.IA;

import greenplanetclient.Game;

/*
 * Class that will register the number of building that the IA have to buy
 */
public class ArrayOfSolution {
    
    /*
     * The number maximum of nuclear bulding that can be buy with our cash
     */
    public static int nbMaxNuclear = 0;
        
    /*
     * The number maximum of water Turbine bulding that can be buy with our cash
     */
    public static int nbMaxWaterTurbine = 0;
       
    /*
     * The number maximum of Solar bulding that can be buy with our cash
     */
    public static int nbMaxSolarPlant = 0;
        
    /*
     * The number maximum of WindTurbine bulding that can be buy with our cash
     */
    public static int nbMaxWindTurbine = 0;
        
    /*
     * The number maximum of Coal Plant bulding that can be buy with our cash
     */
    public static int nbMaxCoalPlant = 0;

    /*
     * The number of nuclear bulding to satisfy the powerNeed
     */
    public  int nbNuclear = 0;
       
    /*
     * The number of water turbine bulding to satisfy the powerNeed
     */
    public int nbWaterTurbine = 0;
        
    /*
     * The number of Solar Plant bulding to satisfy the powerNeed
     */
    public int nbSolarPlant = 0;
     
    /*
     * The number of wind Turbine bulding to satisfy the powerNeed
     */
    public int nbWindTurbine = 0;
        
    /*
     * The number of Coal Plant bulding to satisfy the powerNeed
     */
    public int nbCoalPlant = 0;

    /*
     * A copy of the variable game in order to return the data about the weather
     */

    public static Game game = null;

    /*
     * Boolean that will be use to stop the exectuion of the research ( in our case it's to stop the thread)
     */

    public boolean stopResearch = false ;

    /*
     * The production in Energy without the new Order
     */
    public static float currentProduction = 0;
    
    /*
     * Constructor
     */
    public ArrayOfSolution(float nbMaxNuclear ,float nbMaxWindTurbine, float nbMaxSolarPlant, float nbMaxWaterTurbine, float nbMaxCoalPlant,float currentProduction, Game g)
    {
        ArrayOfSolution.nbMaxNuclear = Math.round(nbMaxNuclear);
        ArrayOfSolution.nbMaxWaterTurbine = Math.round(nbMaxWaterTurbine);
        ArrayOfSolution.nbMaxSolarPlant = Math.round(nbMaxSolarPlant);
        ArrayOfSolution.nbMaxWindTurbine = Math.round(nbMaxWindTurbine);
        ArrayOfSolution.nbMaxCoalPlant = Math.round(nbMaxCoalPlant);
        ArrayOfSolution.currentProduction = currentProduction;
        ArrayOfSolution.game = g;
    }
    /*
     * Get the state of the research of solutions
     */
    public boolean getStateResearch()
    {
        return this.stopResearch;
    }
        
    /*
     * Set the state of the research 
     */
    public void setStateResearch(boolean p)
    {
        stopResearch = p;
    }
    
    /*
     * Save the solution of our research
     */
    public void addSolution(int nbNuclear ,int nbWindTurbine, int nbSolarPlant, int nbWaterTurbine, int nbCoalPlant)
    {

        this.nbCoalPlant =nbCoalPlant;
        this.nbNuclear = nbNuclear;
        this.nbSolarPlant = nbSolarPlant;
        this.nbWaterTurbine = nbWaterTurbine;
        this.nbWindTurbine = nbWindTurbine;

        this.setStateResearch(true);
    }
    

    public int getNbCoalPlant()
    {
        return nbCoalPlant;
    }

    public int getNbNuclear()
    {
        return nbNuclear;
    }

    public int getNbSolarPlant()
    {
        return nbSolarPlant;
    }

    public int getNbWaterTurbine()
    {
        return nbWaterTurbine;
    }
    public int getNbWindTurbine()
    {
        return nbWindTurbine;
    }

}
