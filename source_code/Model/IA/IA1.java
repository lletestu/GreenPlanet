
 /*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GreenPlanet.Model.IA;

import GreenPlanet.Model.Building.NumberBuilding.NumberBuildingPrevision;
import GreenPlanet.Model.Building.TypeBuilding.*;
import GreenPlanet.Model.UtilityFunction.GetPlayer;
/*import Model.IA.CombinationForGoodGreen;
import Model.IA.CombinationForGoodPolluter;
import Model.IA.CombinationSacrificeMoney;*/
import greenplanetclient.Game;
import greenplanetclient.Order;
import greenplanetclient.Player;
import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: Jean-Marc
 * Date: 07/05/13
 * Time: 19:15
 * To change this template use File | Settings | File Templates.
 */
public class IA1 {
    /**
     * 
     * Function that will manage our first IA
     * @param g
     * @param name
     * @return 
     */
    public Order run(Game g,String name)
    {

        /*
         * Array of String which will contain our futur building and our futur order
         */        
        float powerNeed = 0;
        ArrayList<String> newOrderInBuilding = new ArrayList<String>();
        float newOrderInEnergy =0;
        float conditionInPowerNeed = (float) 1.0;
        /*
         * Firstly we research our data  present in the array of player
         */
        Player myPlayer = GetPlayer.getMyPlayer(name,g.getPlayers());
        myPlayer.getPowerAvailable();

         
        /*
         * Define the condition which will be use to compute the best production
         */
        
        float cashCondition = 0;
        float prorataCondition =0;
        float bonusEcoCondition =0;
        
        
        /*
         * Compute how much energy we have to sell or to buy
         */
        
        if (myPlayer.getPowerNeed() < 0 )
        {
            powerNeed = 5 ;
        }
            
        else
        {
            powerNeed = myPlayer.getPowerNeed() ;
        }
            
            
            
        float remainEnergy = conditionInPowerNeed*powerNeed  - computeProduceEnergy(myPlayer,g.getTurn(),g);
        
        /*
         * If we have to buy
         */
             
               
        if((g.getTurn()!=1) )
        {
                cashCondition = myPlayer.getCash();
                prorataCondition = (float) ((conditionInPowerNeed*powerNeed) - computeProduceEnergy(myPlayer,g.getTurn(),g));
                bonusEcoCondition = 0;
        }

        else
        {
               if(myPlayer.getCash() < 0)
               {
                   cashCondition = 6000;
               }
               else
               {
                  cashCondition = myPlayer.getCash() -4000;  
               }

                prorataCondition = (float) ((conditionInPowerNeed*powerNeed) - computeProduceEnergy(myPlayer,g.getTurn(),g));
                bonusEcoCondition = 0;
        }
            

                    
            /*
            * Compute the maximal number of each building
            */
            float nbMaxNuclear = cashCondition / (new Nuclear()).getPrice() ;
            float nbMaxSolarPlant =  cashCondition  / (new SolarPlant()).getPrice() ;
            float nbMaxCoalPlant =  cashCondition  / (new CoalPlant()).getPrice();
            float nbMaxWaterTurbine =  cashCondition  /( new WaterTurbine()).getPrice() ;
            float nbMaxWindTurbine =  cashCondition  / ( new WindTurbine()).getPrice()  ;
            
            /*
             * Launch the variable which will contain these maximal number
             */
           ArrayOfSolution toolResolve = new ArrayOfSolution(nbMaxNuclear,nbMaxWindTurbine,nbMaxSolarPlant,nbMaxWaterTurbine,nbMaxCoalPlant,
                   computeProduceEnergy(myPlayer,g.getTurn(),g)
                   ,g);
           
 
        /*
         * Lauch the multi thread
         */
               
           
           CombinationForGoodGreen t3 = new CombinationForGoodGreen(toolResolve,cashCondition,prorataCondition,bonusEcoCondition,myPlayer,g);
           CombinationForGoodPolluter t4 = new CombinationForGoodPolluter(toolResolve,cashCondition,prorataCondition,bonusEcoCondition,myPlayer,g);
           CombinationSacrificeMoney t5 = new CombinationSacrificeMoney(toolResolve,cashCondition,prorataCondition,bonusEcoCondition,myPlayer,g);
            

   
           if((g.getTurn()!=1))
           {
               if(g.getPowerPrice()>(float)0.1)
               {
                   if(prorataCondition >0)
                   {
                       if(nbMaxWindTurbine *100 <= prorataCondition)
                       {
                           if(nbMaxCoalPlant*500 <= prorataCondition)
                           {
                               //do nothing
                           }
                           else
                           {
                               t4.run();
                           }
                       }
                       else
                       {
                           t3.run();
                       }
                   }
                   else
                   {
                       //do nothing
                   }
                   
               }
               else
               {
                   //do nothing
               }
                              
           }
           else
           {
               //t4.run();
               t3.run();
           }
                               

               

           
           /*
            * Watch the result
            */
            
           
           int nbNuclear =  toolResolve.getNbNuclear();
           int nbCoalPlant = toolResolve.getNbCoalPlant();
           int nbWindTurbine = toolResolve.getNbWindTurbine();
           int nbWaterTurbine = toolResolve.getNbWaterTurbine();
           int nbSolarPlant = toolResolve.getNbSolarPlant();
           

           
        addBuildingInOrder(newOrderInBuilding,nbNuclear,nbWindTurbine,nbSolarPlant,nbWaterTurbine,nbCoalPlant);
 
        
        
        /*
         * If we don't need to buy but just to sell the extra
         */

        
        /*
         * The final order
         */
        
                            // At this state we sold everything which is not used
            //Be careful we also sold what is in extra in the order
            
            int energyInOrder =0;
            for (String txt : newOrderInBuilding)
            {
                energyInOrder+= computeEnergyForBuilding(txt,g);
            }
           
            

            


            
        newOrderInEnergy =(( ((float)conditionInPowerNeed*powerNeed)  - ( ((float)computeProduceEnergy(myPlayer,g.getTurn(),g) + (float)energyInOrder))));
        



        return new Order((int)newOrderInEnergy,newOrderInBuilding );
        
        
    }
    
    
    
    /*
     * Function that will compute the energy produced for each kind of building
     * The name of the building is given in the variable name
     */
    public float computeEnergyForBuilding(String name, Game g)
    {
        int energy =0 ;
        
        CoalPlant coalPlant = new CoalPlant();
        Nuclear nuclear = new Nuclear();
        SolarPlant solarPlant = new SolarPlant();
        WaterTurbine waterTurbine = new WaterTurbine();
        WindTurbine windTurbine = new WindTurbine();
        
                /*
         * Update the production of each building
         */
        coalPlant.computeUnitNRJProduce();
        nuclear.computeUnitNRJProduce();
        solarPlant.computeUnitNRJProduce(g.getLight());
        waterTurbine.computeUnitNRJProduce(g.getWater());
        windTurbine.computeUnitNRJProduce(g.getWind());
        
        
        
        if( name.equals(coalPlant.getName()))
        {
            return coalPlant.getUnitNRJProduce();
        }
            
        else if(name.equals(nuclear.getName()))
        {
            return nuclear.getUnitNRJProduce();
        }
            
        else if(name.equals(solarPlant.getName()))
        {
            return solarPlant.getUnitNRJProduce();
        }
                
        else if(name.equals(waterTurbine.getName()))
        {
            return waterTurbine.getUnitNRJProduce();
        }
        else                 
        {
            return windTurbine.getUnitNRJProduce();
        }
            
            
        
        
    }
    
    /*
     *Function that will compute the energy produced by all of the building of the player
     */    
    private float computeProduceEnergy(Player p,int turn,Game g)
    {
        float energyUsed =0 ;
        /*
         * We compute the number of solarPlant,WaterTurbine, .... by using the class NumberBuilding
         */
        
        NumberBuildingPrevision myPlayerNumberOfBuilding= new NumberBuildingPrevision(p.getBuildings(),turn);
         
        int nbSolarPlant=myPlayerNumberOfBuilding.getNbSolarPlant();
        int nbWaterTurbine=myPlayerNumberOfBuilding.getNbWaterTurbine();
        int nbWindTurbine=myPlayerNumberOfBuilding.getNbWindTurbine();
        int nbCoalPlant=myPlayerNumberOfBuilding.getNbCoalPlant();
        int nbNuclear=myPlayerNumberOfBuilding.getNbNuclear();
        
        
        /*
         * We compute the quantity of energy producted by our building
         */
        
        CoalPlant coalPlant = new CoalPlant();
        Nuclear nuclear = new Nuclear();
        SolarPlant solarPlant = new SolarPlant();
        WaterTurbine waterTurbine = new WaterTurbine();
        WindTurbine windTurbine = new WindTurbine();
        
        /*
         * Update the production of each building
         */
        coalPlant.computeUnitNRJProduce();
        nuclear.computeUnitNRJProduce();
        solarPlant.computeUnitNRJProduce(g.getLight());
        waterTurbine.computeUnitNRJProduce(g.getWater());
        windTurbine.computeUnitNRJProduce(g.getWind());
        
        /*
         * Compute the energy produced
         */
        energyUsed =  nbCoalPlant* coalPlant.getUnitNRJProduce();
        energyUsed += nbNuclear * nuclear.getUnitNRJProduce();
        energyUsed += nbSolarPlant * solarPlant.getUnitNRJProduce();
        energyUsed += nbWaterTurbine * waterTurbine.getUnitNRJProduce();
        energyUsed += nbWindTurbine * windTurbine.getUnitNRJProduce();
        
        return (energyUsed)  ;
    }
        
        /*
         * Function that add to the array list of order, the building that will be buy in the next turn
         */
        public void addBuildingInOrder(ArrayList<String> newOrderInBuilding,int nbNuclear,int nbWindTurbine,int nbSolarPlant,int nbWaterTurbine,int nbCoalPlant)
        {
            if( nbNuclear != 0)
            {
                while(nbNuclear > 0)
                {
                    newOrderInBuilding.add( (new Nuclear()).getName());
                    
                    nbNuclear --;
                }
            }
            
            if(nbWindTurbine!=0)
            {
                              
                while(nbWindTurbine > 0)
                {
                    newOrderInBuilding.add( (new WindTurbine()).getName());
                    
                    nbWindTurbine --;
                }
                
            }
            
                        
            if(nbSolarPlant!=0)
            {
                              
                while(nbSolarPlant > 0)
                {
                    newOrderInBuilding.add( (new SolarPlant()).getName());
                    
                    nbSolarPlant --;
                }
                
            }
            
                        
            if(nbWaterTurbine!=0)
            {
                              
                while(nbWaterTurbine > 0)
                {
                    newOrderInBuilding.add( (new WaterTurbine()).getName());
                    
                    nbWaterTurbine --;
                }
                
            }
            
                        
            if(nbCoalPlant!=0)
            {
                              
                while(nbCoalPlant > 0)
                {
                    newOrderInBuilding.add( (new CoalPlant()).getName());
                    
                    nbCoalPlant --;
                }
                
            }
        }
}
