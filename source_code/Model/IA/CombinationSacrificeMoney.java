/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GreenPlanet.Model.IA;

import GreenPlanet.Model.IA.ArrayOfSolution;
import GreenPlanet.Model.Building.TypeBuilding.*;
import greenplanetclient.Game;
import greenplanetclient.Player;

/**
 *
 * @author smjf
 */
public class CombinationSacrificeMoney {
    /*
     * Variaable that wiil contain the result of our research
     */
    ArrayOfSolution tool ;
    /*
     * Criteria on the cash
     */
    float cashCondition;
    /*
     * Criteria on the production
     */
    float prorataCondition;
    /*
     * Criteria on the green aspect
     */
    float bonusEcoCondition;
    /*
     * Copy of the pointer for the data about our player
     */
    Player myPlayer ;
    /*
     * Instantation of a coalPlant building
     */
    CoalPlant coalPlant = new CoalPlant();
    /*
     *  Instantation of a nuclear building
     */
    Nuclear nuclear = new Nuclear();
        
    /*
     *  Instantation of a solar Plant building
     */
    SolarPlant solarPlant = new SolarPlant();
        
    /*
     *  Instantation of a water Turbine building
     */
    WaterTurbine waterTurbine = new WaterTurbine();
        
    /*
     *  Instantation of a wind Turbine building
     */
    WindTurbine windTurbine = new WindTurbine();
    
    /*
     * Function that test all possible combination
     */
    public CombinationSacrificeMoney(ArrayOfSolution p,float cashCondition,float prorataCondition,float bonusEcoCondition,Player myPlayer,Game g)
    {
        tool = p ;
        this.cashCondition = cashCondition;
        this.prorataCondition = prorataCondition;
        this.bonusEcoCondition = bonusEcoCondition;
        this.myPlayer = myPlayer ;
       
        
        /*
         * Update the production of each building
         */
        coalPlant.computeUnitNRJProduce();
        nuclear.computeUnitNRJProduce();
        solarPlant.computeUnitNRJProduce(ArrayOfSolution.game.getLight());
        waterTurbine.computeUnitNRJProduce(ArrayOfSolution.game.getWater());
        windTurbine.computeUnitNRJProduce(ArrayOfSolution.game.getWind());
        

        
    }
    
        
    /*
     * Function that start the algorithm
     */
    public void run()
    {
        

            for (int i = 0  ; i < ArrayOfSolution.nbMaxSolarPlant+1   ; i++)
            {
                for( int j= 0 ; j < ArrayOfSolution.nbMaxWaterTurbine+1 ; j++)
                {
                    for(int k= 0 ; k < ArrayOfSolution.nbMaxWindTurbine+1 ; k++)
                    {
                        for( int l = 0  ; l < ArrayOfSolution.nbMaxNuclear+1 ; l++)
                        {
                            for( int m = ArrayOfSolution.nbMaxCoalPlant+1 ; m > -1 ; m--)
                            {
                                if(tool.getStateResearch()== false)
                                {

                                    
                                if((computeCash(l,k,i,j,m)< cashCondition) 
                                        &&(computeProduction(l,k,i,j,m)>=prorataCondition))
                                    {
                                        tool.addSolution(l,k,i,j,m);
                                    }
                                }
                                
                                else
                                {
                                    i = ArrayOfSolution.nbMaxSolarPlant+1  ;
                                    j= ArrayOfSolution.nbMaxWaterTurbine+1 ;
                                    k = ArrayOfSolution.nbMaxWindTurbine+1 ;
                                    l = ArrayOfSolution.nbMaxNuclear+1 ;
                                    m =  -1 ;
                                    
                                }
                            }   
                        }   
                    }
                }
            }
            
        

        

        
    }
        
    /*
     * Function that cumpute the cash need for one combination of building
     */
    public float computeCash(int nbNuclear ,int nbWindTurbine, int nbSolarPlant, int nbWaterTurbine, int nbCoalPlant)
    {
        return ( nbNuclear*nuclear.getPrice() + nbWindTurbine*windTurbine.getPrice() + nbSolarPlant*solarPlant.getPrice()
                + nbWaterTurbine*waterTurbine.getPrice() + nbCoalPlant*coalPlant.getPrice() );
    }
            
    /*
     * Function that cumpute the production provide by a combination of building
     */
    public float computeProduction( int nbNuclear ,int nbWindTurbine, int nbSolarPlant, int nbWaterTurbine, int nbCoalPlant)
    {
        
        float energyUsed =0;
   
        /*
         * Compute the energy produced
         */
        energyUsed =  nbCoalPlant* coalPlant.getUnitNRJProduce();
        energyUsed += nbNuclear * nuclear.getUnitNRJProduce();
        energyUsed += nbSolarPlant * solarPlant.getUnitNRJProduce();
        energyUsed += nbWaterTurbine * waterTurbine.getUnitNRJProduce();
        energyUsed += nbWindTurbine * windTurbine.getUnitNRJProduce();
        
        return Math.round(energyUsed)  ;
    
        
    }
       
    /*
     * Function that cumpute the bonus eco for one combination of building
     */       
    public float computeBonusEco( int nbNuclear ,int nbWindTurbine, int nbSolarPlant, int nbWaterTurbine, int nbCoalPlant)
    {
               
        return ( nbNuclear*nuclear.getBonusEco() + nbWindTurbine*windTurbine.getBonusEco() + nbSolarPlant*solarPlant.getBonusEco()
                + nbWaterTurbine*waterTurbine.getBonusEco() + nbCoalPlant*coalPlant.getBonusEco() );
    }
    
}
