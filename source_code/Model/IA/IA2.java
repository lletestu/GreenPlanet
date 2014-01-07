package GreenPlanet.Model.IA;

import GreenPlanet.Model.Player.PlayerAlive;
import greenplanetclient.Game;
import greenplanetclient.Order;
import greenplanetclient.Player;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: Lucas
 * Date: 07/05/13
 * Time: 19:15
 * To change this template use File | Settings | File Templates.
 */
public class IA2  {

    ArrayList<String> newBuilding = new ArrayList<String>();
    TheSimplexV3 mySimplex;
    PlayerAlive myP;
    private ArrayList<int[][]> myCommandHistoric=new ArrayList<int[][]>();
    public ArrayList<Player> arrayAlivePlayer=new ArrayList() ;   
    private String name;
    
    /**
 * 
 * @param g game object
 * @param name it's our pseudo
 * @return 
 */
    public Order run(Game g,String name){
    
     if((g.getTurn()>=1)){
         
        mySimplex=new TheSimplexV3(g,searchMyPlayer(g.getPlayers(),name),myCommandHistoric);
        int[][] IATable;
        IATable=mySimplex.getWhatWeNeed();
        int achat=0;
        this.name=name;
        newBuilding=new ArrayList<String>();
        
          for(int i=0;i<5;i++){
            if(IATable[i][1]>0) {
                addBuilding(newBuilding,IATable[i][0],IATable[i][1]);
                int[][] TableStock=new int[1][3];
                TableStock[0][0]=IATable[i][0];
                TableStock[0][1]=IATable[i][1];
                TableStock[0][2]=g.getTurn();
              
               if((g.getTurn()<40)||((g.getTurn()>40)&&(g.getPowerPrice()>1))) myCommandHistoric.add(TableStock);
            }
        }
         if(g.getTurn()==1)   achat=(int) ((-1)*((PowerLeft((g.getTurn()+1),myCommandHistoric,g))-(500)));
         else      achat=(int) ((-1)*((PowerLeft((g.getTurn()+1),myCommandHistoric,g))-(1000)));
          if(g.getTurn()<36||((g.getTurn()>40)&&(g.getPowerPrice()>1))){
              return new Order(achat,newBuilding);
          }
          else if((g.getTurn()>=40)) {
                if(g.getPowerPrice()>1) {
                    return new Order(0,newBuilding);
                    
                }
                else{
              return new Order(achat,new ArrayList<String>());
                }
          }
          else return new Order(achat,newBuilding); 
        
     }
      
           
           
     else{     return new Order(0,new ArrayList<String>());
     }

    }

    private Player searchMyPlayer(ArrayList<Player> pListe, String name){
       if(pListe!=null){
         for(Player p:pListe){
            if(p.getName().equals(name)) return p;
         }
       }

        return null;
    }

    public ArrayList<String> getNewBuilding() {
        return newBuilding;
    }

    public ArrayList<String> addBuilding(ArrayList<String> list,int a,int b){
     ArrayList<String> newbuilding =list;
     for(int i=0;i<b;i++){
        switch(a){
case 1:newbuilding.add("nuclear");
        break;
case 2:newbuilding.add("wind_turbine");
        break;
case 3:newbuilding.add("solar_plant");
       break;
case 4:newbuilding.add("water_turbine");
        break;
case 5:newbuilding.add("coal_fired_plant");
        break;
    }
}
     return newbuilding;
}
    /**
     * Calculates or own poweravailable whith a Security marge
     * in order to sell it
     * @param Turn 
     * @param CommandHistoric
     * @param g
     * @return 
     */
public float PowerLeft(int Turn,ArrayList<int[][]> CommandHistoric,Game g){
    float powerleft=0;
    float powerneed=0;
    int powerproduction=0;
    powerneed=CalculateThePowerNeedAtTurn(Turn,mySimplex.getAliveNumberPlayer(g),g);
    powerproduction=mySimplex.GetProductionTurnAsked(Turn, CommandHistoric);
    
    powerleft=powerproduction-powerneed;
    System.out.println("the powerprod is   " +powerproduction);
    System.out.println("the powerneed is   " +powerneed);
    System.out.println("the powerleft is   " +powerleft);
            
            
            return powerleft;
}    

 
    /**
     * important function which return the futur powerneed thanks this one to observations more 
     * made on 30 plays
     * @param TurnAsked the turn we need the information
     * @param Alive number of player still Alive
     * @param g the game object
     * @return 
     */
public float CalculateThePowerNeedAtTurn(int TurnAsked,int Alive,Game g){
    float powerneedAfter=0;
    int powerneedCurrent=(searchMyPlayer(g.getPlayers(),name).getPowerNeed());
    
    float coefficient=0;
    int AliveNumber=mySimplex.getAliveNumberPlayer(g);
    System.out.println("we are at turn " + g.getTurn());
     if((AliveNumber>0)&&(AliveNumber<=1)) {
        coefficient=(float) 1.4;
    } 
    if((AliveNumber>1)&&(AliveNumber<=2)) {
        coefficient=(float) 1.4;
    } 
    if((AliveNumber>2)&&(AliveNumber<=5)) {
        coefficient=(float) 1.2;
    } 
    if((AliveNumber>5)&&(AliveNumber<=10)) {
        coefficient=(float) 1.12;
    } 
    if((AliveNumber>10)&&(AliveNumber<=15)) {
        coefficient=(float) 0.95;
    }
    if((AliveNumber>15)&&(AliveNumber<=20)) {
        coefficient=(float) 0.8;
    }
    if((AliveNumber>20)&&(AliveNumber<=25)) {
        coefficient=(float) 0.6;
    }
    powerneedAfter=(float) (powerneedCurrent*coefficient);

    return powerneedAfter;
}
  

}