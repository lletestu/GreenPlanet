package GreenPlanet.Model.IA;

import GreenPlanet.Model.Building.TypeBuilding.*;
import greenplanetclient.Game;
import greenplanetclient.Player;

/**
 * Created with IntelliJ IDEA.
 * User: Lauren
 * Date: 07/05/13
 * Time: 19:13
 * To change this template use File | Settings | File Templates.
 */
public class TestTheCombinaisonPollutionLimit {
    int choiceCase = -1;
    ArrayOfSolution tool ;
    float cashCondition;
    float prorataCondition;
    float bonusEcoCondition;
    Player myPlayer ;
    float minimaCash =0;


    /*
     *
     * We compute the quantity of energy producted by our building
     */
    CoalPlant coalPlant = new CoalPlant();
    Nuclear nuclear = new Nuclear();
    SolarPlant solarPlant = new SolarPlant();
    WaterTurbine waterTurbine = new WaterTurbine();
    WindTurbine windTurbine = new WindTurbine();

    public TestTheCombinaisonPollutionLimit(ArrayOfSolution p,float cashCondition,float prorataCondition,float bonusEcoCondition,Player myPlayer,Game g)
    {
        tool = p ;
        this.cashCondition = cashCondition;
        this.prorataCondition = prorataCondition;
        this.bonusEcoCondition = bonusEcoCondition;
        this.myPlayer = myPlayer ;
        minimaCash = (float)g.getTurn()*(float)50.0;

        /*
         * Update the production of each building
         */
        coalPlant.computeUnitNRJProduce();
        nuclear.computeUnitNRJProduce();
        solarPlant.computeUnitNRJProduce(ArrayOfSolution.game.getLight());
        waterTurbine.computeUnitNRJProduce(ArrayOfSolution.game.getWater());
        windTurbine.computeUnitNRJProduce(ArrayOfSolution.game.getWind());



    }


    public void run()
    {


        for (int i = 0 ; i < ArrayOfSolution.nbMaxSolarPlant  ; i++)
        {
            for( int j=0 ; j < ArrayOfSolution.nbMaxWaterTurbine; j++)
            {
                for(int k=0 ; k < ArrayOfSolution.nbMaxNuclear; k++)
                {
                    for( int l = 0 ; l < ArrayOfSolution.nbMaxWindTurbine; l++)
                    {
                        for( int m = 0 ; m < ArrayOfSolution.nbMaxCoalPlant
                                ; m++)
                        {
                            if(tool.getStateResearch()== false)
                            {
                                if((computeCash(k,l,i,j,m)< cashCondition)
                                        &&(computeProduction(k,l,i,j,m)>=prorataCondition))
                                {
                                    tool.addSolution(k,l,i,j,m);
                                }
                            }

                            else
                            {
                                i = ArrayOfSolution.nbMaxSolarPlant +1 ;
                                j= ArrayOfSolution.nbMaxWaterTurbine +1;
                                k = ArrayOfSolution.nbMaxNuclear +1;
                                l = ArrayOfSolution.nbMaxWindTurbine +1 ;
                                m =  ArrayOfSolution.nbMaxCoalPlant +1 ;

                            }
                        }
                    }
                }
            }
        }






    }

    public float computeCash(int nbNuclear ,int nbWindTurbine, int nbSolarPlant, int nbWaterTurbine, int nbCoalPlant)
    {
        return ( nbNuclear*nuclear.getPrice() + nbWindTurbine*windTurbine.getPrice() + nbSolarPlant*solarPlant.getPrice()
                + nbWaterTurbine*waterTurbine.getPrice() + nbCoalPlant*coalPlant.getPrice() );
    }

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

    public float computeBonusEco( int nbNuclear ,int nbWindTurbine, int nbSolarPlant, int nbWaterTurbine, int nbCoalPlant)
    {

        return ( nbNuclear*nuclear.getBonusEco() + nbWindTurbine*windTurbine.getBonusEco() + nbSolarPlant*solarPlant.getBonusEco()
                + nbWaterTurbine*waterTurbine.getBonusEco() + nbCoalPlant*coalPlant.getBonusEco() );
    }
}
