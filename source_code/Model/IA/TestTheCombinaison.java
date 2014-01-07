package GreenPlanet.Model.IA;

import GreenPlanet.Model.Building.TypeBuilding.*;
import greenplanetclient.Player;

/**
 * Created with IntelliJ IDEA.
 * User: Jean Marc
 * Date: 07/05/13
 * Time: 19:10
 * To change this template use File | Settings | File Templates.
 */
public class TestTheCombinaison {
    int choiceCase = -1;
    ArrayOfSolution tool ;
    float cashCondition;
    float prorataCondition;
    float bonusEcoCondition;
    Player myPlayer ;


    /*
     *
     * We compute the quantity of energy producted by our building
     */
    CoalPlant coalPlant = new CoalPlant();
    Nuclear nuclear = new Nuclear();
    SolarPlant solarPlant = new SolarPlant();
    WaterTurbine waterTurbine = new WaterTurbine();
    WindTurbine windTurbine = new WindTurbine();

    public TestTheCombinaison(ArrayOfSolution p,float cashCondition,float prorataCondition,float bonusEcoCondition,Player myPlayer)
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


    public void run()
    {





        for (int i = 0 ; i < ArrayOfSolution.nbMaxNuclear  ; i++)
        {
            for( int j=0 ; j < ArrayOfSolution.nbMaxWaterTurbine; j++)
            {
                for(int k=0 ; k < ArrayOfSolution.nbMaxCoalPlant; k++)
                {
                    for( int l = 0 ; l < ArrayOfSolution.nbMaxSolarPlant; l++)
                    {
                        for( int m = 0 ; m < ArrayOfSolution.nbMaxWindTurbine; m++)
                        {
                            if(tool.getStateResearch()== false)
                            {
                                if((computeCash(i,m,l,j,k)<= cashCondition)//&&(computeBonusEco(i,m,l,j,k)>= bonusEcoCondition)
                                        &&(computeProduction(i,m,l,j,k)>=prorataCondition))
                                {
                                    tool.addSolution(i,m,l,j,k);
                                }
                            }

                            else
                            {
                                i = ArrayOfSolution.nbMaxNuclear +1 ;
                                j= ArrayOfSolution.nbMaxWaterTurbine +1;
                                k = ArrayOfSolution.nbMaxCoalPlant +1;
                                l = ArrayOfSolution.nbMaxSolarPlant +1 ;
                                m =  ArrayOfSolution.nbMaxWindTurbine +1 ;

                            }
                        }
                    }
                }
            }
        }



        /*{
            for (int i = 0 ; i < ArrayOfSolution.nbMaxWindTurbine ; i++)
            {
                for(int j=0 ; j < ArrayOfSolution.nbMaxSolarPlant; j++)
                {
                    for( int k=0 ; k < ArrayOfSolution.nbMaxWaterTurbine; k++)
                    {
                        for( int l = 0 ; l < ArrayOfSolution.nbMaxCoalPlant ; l++)
                        {

                            if(tool.getStateResearch()== false)
                            {

                                if((computeCash(0,i,j,k,l)<= cashCondition)&&(computeBonusEco(0,i,j,k,l)>= bonusEcoCondition)
                                        &&(computeProduction(0,i,j,k,l)>=prorataCondition))
                                    {
                                        tool.addSolution(0,i, j, k, l);
                                    }


                            }


                            else
                            {
                                    i = ArrayOfSolution.nbMaxWindTurbine +1;
                                    j= ArrayOfSolution.nbMaxSolarPlant +1 ;
                                    k = ArrayOfSolution.nbMaxWaterTurbine +1;
                                    l = ArrayOfSolution.nbMaxCoalPlant + 1 ;

                            }

                        }

                    }

                }
            }

        }*/



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
