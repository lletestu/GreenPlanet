package GreenPlanet.Model.IA;

import GreenPlanet.Model.Building.TypeBuilding.*;
import greenplanetclient.Game;
import greenplanetclient.Player;
import greenplanetclient.PlayerStateEnum;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: Lucas
 * Date: 07/05/13
 * Time: 19:16
 * To change this template use File | Settings | File Templates.
 */
public final class TheSimplexV3 {
      
/**
 * creation of variables needed for TheSimplexV3 Algorithm
    We are going to work with Float variables
 */  
    private Nuclear myNuclear=new Nuclear();
    private WindTurbine myWindTurbine=new WindTurbine();
    private SolarPlant mySolarPlant=new SolarPlant();
    private WaterTurbine myWaterTurbine=new WaterTurbine();
    private CoalPlant myCoalPlant=new CoalPlant();
    private Game myGame;
    private Player myPlayer;
    private float myCash;
    private int myBonus;
    private int WorkColumn;
    private int WorkLine;
    private int Lines=0;
    private int Columns=0;
    private int Variables=0;
    public ArrayList<Player> arrayAlivePlayer=new ArrayList() ;   
    private float[][] SimplexTable;//=new float[Lines][Columns ];
    private ArrayList<int[][]> HistoricCommand;
    
    /**
     * 
     * @param myGame stocks the game object we need for the turn
     * @param myPlayer Stocke our player's object so we can use information when needed
     * @param HistoricCommand attribut which is received by constructor et enable the programme to calculate the old production
     */
   
     public TheSimplexV3(Game myGame,Player myPlayer,ArrayList<int[][]> HistoricCommand){
    //Table initialization
         this.myGame=myGame;
         this.myPlayer=myPlayer;
         myCash=myPlayer.getCash();
         myBonus=2000;
         this.HistoricCommand=HistoricCommand;         
         SimplexTable=mainLoopSimplex(InitializingSimplexTable(myPlayer,myGame));
         
       
    }
 
  /**
   * this function calculate the Workcolumn First Step of Simplex
   * @param Table it's the Simplex table the table on which we made calculations
   * @return 
   */
    public int getWorkColumn(float[][] Table){
        
        float maximum=Table[Lines-1][1];       
          WorkColumn = 1;
        for(int i=1;i<Columns-1;i++){
         
            if(maximum<Table[Lines-1][i]){
                maximum=Table[Lines-1][i];
                WorkColumn=i;       
            }
        }
    
        return WorkColumn;
    }
/**
 * This function calculates the work Line 2nd Step of Simplex
 * @param woco we need the workcolumn as 'woko'
 * @param Table it's the Simplex Table we work on
 * @return 
 */
    public int getWorkLine(int woco,float[][] Table){
        float maximum;
        
        // table where we stock the last column number
        float ValueTable[]=new float[Lines-1];
        for(int i=0;i<(Lines-1);i++){
            if(Table[i][Columns-1]!=0){
            ValueTable[i]=(Table[i][woco])/(Table[i][Columns-1]);
            }
            else {
                ValueTable[i]=0;
            }
        }
        maximum=ValueTable[0];
        
        for(int i=0;i<(Lines-1);i++){
            if(maximum<((Table[i][woco])/(ValueTable[i]))){             
                   maximum=(Table[i][woco])/ValueTable[i];
                   WorkLine=i;  
               }
        }

        return WorkLine;
    }
    /**
     * //After having determined  which variable is going to go out of the base 3rd Step of Simplex
    // we have to divide this line ( about the same method that gauss pivot)
     * @param WorkColumn we need it for determining the pivot
     * @param WorkLine   we need it for determining the pivot
     * @param Table
     * @pivot we divide the whole line by pivot
     * @return 
     */
  
   
    public float[][] UnitingTheLine(int WorkColumn,int WorkLine,float[][] Table){
        //First we have to change the variable getting out by the one getting in
        Table[WorkLine][0]=WorkColumn;        
        //determining the pivot
        float Pivot=Table[WorkLine][WorkColumn];
     //then dividing the whole Line by the pivot determined by the intersection of the Workcolumn et work line
        //Which gives us:
        for(int i=1;i<Columns;i++){
            Table[WorkLine][i]=(Table[WorkLine][i])/(Pivot);
        }
        return Table;
    }
   
    
    /**
     *  //Part of the TheSimplexV3 to reduce all elements in the Work Column to Zero 
     * it's the 4th Step Of Simplex
     * @param WorkColumn
     * @param WorkLine
     * @param Table 
     */
   
    public void ReducingToZero(int WorkColumn,int WorkLine,float[][] Table){
        float[] ValueTemp=new float[Lines-1];
        int temp=0;        
         int CurrentLine=0;
        for(CurrentLine=0;CurrentLine<Lines;CurrentLine++){
            if(CurrentLine!=(WorkLine)){
                ValueTemp[temp]=Table[CurrentLine][WorkColumn];            
                temp++;
            }                
            }
        temp=0;
       CurrentLine=0;
        for(CurrentLine=0;CurrentLine<Lines;CurrentLine++){
            if((CurrentLine-(WorkLine))!=0){
                    for(int i=1;i<Columns;i++){
                    Table[CurrentLine][i]=Table[CurrentLine][i]-(ValueTemp[temp])*(Table[WorkLine][i]);  //(ValueTemp[temp])*(Table[WorkLine-1][i]);
                }temp++;
            }         
        }        
    }

    // if all the coefficient of the last line or negative so its true
    /**
     * we made this calculation until the coefficients of the last line be negative
     * it's the 5th Step of Simplex
     * @param Table
     * @return 
     */
    public boolean DoWeStop(float[][] Table){
        boolean Answer=true;
        for(int i=1;i<Columns-1;i++){
            if(Table[Lines-1][i]>0){
                Answer=false;
            }
        }
        return Answer;
    }
    /**
     * It's the mainloop os Simplex which calls each function step by Step
     * @param Table
     * @return 
     */
    public float[][] mainLoopSimplex(float[][] Table){        
        
       while(DoWeStop(Table)==false){            
            WorkColumn=getWorkColumn(Table);
            WorkLine=getWorkLine(WorkColumn,Table);
            Table=UnitingTheLine(WorkColumn,WorkLine,Table);
            ReducingToZero(WorkColumn,WorkLine,Table);
       }
            return Table;         
     } 
    /**
     * this function is here in order to read the Simplex table
     * So it return a table where is written wich plants we have to buy and the amount of it
     * @return 
     */
           public int[][] getWhatWeNeed(){
               float[][] Table;
               Table=SimplexTable;
        int[][] WeNeedTable;
        WeNeedTable=new int[Lines-1][2];
        int ReadingVariable=0;
        int ReadingNeed=0;
        for(int i=0;i<(Lines-1);i++){
            ReadingVariable=(int)Table[i][0];
            ReadingNeed=(int)Table[i][Columns-1];
            WeNeedTable[i][0]=ReadingVariable;
            WeNeedTable[i][1]=ReadingNeed;

        }
        // we nee now to make a tri
        int[][] WhatWeNeedTable=new int[Variables][2];
        for(int i=0;i<Variables;i++){
            for(int j=0;j<Lines-1;j++){
                if((i+1)==WeNeedTable[j][0]){
                    WhatWeNeedTable[i][0]=(i+1);
                    WhatWeNeedTable[i][1]=WeNeedTable[j][1];
                    System.out.println("Algorithm results  "+ WhatWeNeedTable[i][0] + " "+  WhatWeNeedTable[i][1]  );
                }
            }
            
        }
        
        return WhatWeNeedTable;
    }
       /**
        * Because havin some trouble we the Server
        * I made my own function on order to have the Production in hard give by each Plants
        * @param a
        * @return 
        */
           public int getProductionAmount(int a){
               int rate=0;
               switch(a){
                   case 1:rate=(myNuclear.getProduction())/100;
                         break;
                   case 2:rate=(myWindTurbine.getProduction())/100;
                        break;
                   case 3:rate=(mySolarPlant.getProduction())/100;
                        break;
                   case 4:rate=(myWaterTurbine.getProduction())/100;
                       break;
                   case 5:rate=myCoalPlant.getProduction();
                       break;
                       
               }
               return rate;
           }
           /**
            * The Same than getProductionamount
            * it gives us the cost of each plants
            * @param a
            * @return 
            */
           public int getCostBuildings(int a){
               int price=0;
               switch(a){
                   case 1:price=1000;
                         break;
                   case 2:price=100;
                        break;
                   case 3:price=200;
                        break;
                   case 4:price=500;
                       break;
                   case 5:price=400;
                       break;
                       
               }

               return price;
           }
           /**
            * This function gives us the bonus (or malus) for each plants
            * @param a
            * @return 
            */
           public int getBonusEco(int a){
               int Eco=0;
               switch(a){
                   case 1:Eco=myNuclear.getBonusEco();
                         break;
                   case 2:Eco=myWindTurbine.getBonusEco();
                        break;
                   case 3:Eco=mySolarPlant.getBonusEco();
                        break;
                   case 4:Eco=myWaterTurbine.getBonusEco();
                       break;
                   case 5:Eco=myCoalPlant.getBonusEco();
                       break;
                       
               }
               return Eco;
           }
           /**
            * This function calculate thanks to the Historic the production at Turn n+1
            * Thanks to the older plants bought until Turn n
            * @param Turn the Turn the production is asked for
            * @param CommanHistoric the Historic of the Command
            * @return 
            */
     public int GetProductionTurnAsked(int Turn,ArrayList<int[][]> CommanHistoric){
         int production=0;
         System.out.println("the turn asked is "+Turn);
         // we need to divide by 2 cases
         // the first is turn is not arrived until 10
         if(Turn<11){
             
             for(int j=0;j<CommanHistoric.size();j++){
                 if((((CommanHistoric.get(j))[0][2])<Turn)&&(((CommanHistoric.get(j))[0][2])>0)) {
                     for(int a=1;a<=5;a++){
                         if(((CommanHistoric.get(j))[0][0])==a) {
                             production=production+(CommanHistoric.get(j)[0][1])*(getProductionAmount(CommanHistoric.get(j)[0][0]));
                         }
                     }
                     
                 }
             }
             
         }
         // the turn is greater than 10
         else{
         for(int i=Turn-10;i<(Turn-1);i++){
             for(int j=0;j<CommanHistoric.size();j++){
                 if(((CommanHistoric.get(j))[0][2])==i) {
                     for(int a=1;a<=5;a++){
                         if(((CommanHistoric.get(j))[0][0])==a) {
                             production=production+(CommanHistoric.get(j)[0][1])*(getProductionAmount(CommanHistoric.get(j)[0][0]));
                         }
                     }
                     
                 }
             }
         }
         
         }
         
         return production;
     }    
           /**
            * This Function is quiet Fundamental
            * It's set the initials variable of the Simplex Table
           
            * @param myPlayer we need our player object
            * @param myGamewe need our player object
            * @return 
            */
    public float[][] InitializingSimplexTable(Player myPlayer,Game myGame){  
        float[][] Table;
         int line=0;
         int column=0;
         Lines=4;
         line=Lines;  
         Variables=5;
         column=Variables+line+1;
         Columns=column;
         Table=new float[line][column];
         
         for(int i=1;i<=Variables;i++){
         
             if (i==1) {
                 Table[line-1][i]=getProductionAmount(1);
             }
             if (i==2) {
                 Table[line-1][i]=getProductionAmount(2);
             }
             if (i==3) {
                 Table[line-1][i]=getProductionAmount(3);
             }
             if (i==4) {
                 Table[line-1][i]=getProductionAmount(4);
             }
             if (i==5) {
                 Table[line-1][i]=getProductionAmount(5);
             }
         }
         for(int j=0;j<line-1;j++){
       
            for(int i=1;i<=Variables;i++){
             
                
                if (j==0) {
                    if(i==1) {
                        Table[j][i]=getCostBuildings(1);
                    }
                    if(i==2) {
                        Table[j][i]=getCostBuildings(2);
                    } 
                    if(i==3) {
                        Table[j][i]=getCostBuildings(3);
                    }
                    if(i==4) {
                        Table[j][i]=getCostBuildings(4);
                    }
                    if(i==5) {
                        Table[j][i]=getCostBuildings(5);
                    }
                }
                if(j==1) {
                    if(i==1) {
                        Table[j][i]=(-1)*getBonusEco(1);
                    }
                    if(i==2) {
                        Table[j][i]=(-1)*getBonusEco(2);
                    }
                    if(i==3) {
                        Table[j][i]=(-1)*getBonusEco(3);
                    }
                    if(i==4) {
                        Table[j][i]=(-1)*getBonusEco(4);
                    }
                    if(i==5) {
                        Table[j][i]=(-1)*getBonusEco(5);
                    }
                }
                if(j==2) {
                    if(i==1) {
                        Table[j][i]=(-1)*getProductionAmount(1);
                    }
                    if(i==2) {
                        Table[j][i]=(-1)*getProductionAmount(2);
                    }
                    if(i==3) {
                        Table[j][i]=(-1)*getProductionAmount(3);
                    }
                    if(i==4) {
                        Table[j][i]=(-1)*getProductionAmount(4);
                    }
                    if(i==5) {
                        Table[j][i]=(-1)*getProductionAmount(5);
                    }
                }                
            }
         }
        for(int i=0;i<line-1;i++){
          
             if(i==0) {
                 if(myGame.getTurn()==1) {
                     Table[i][column-1]=(float) (10000/(1));
                 }
               if(myGame.getTurn()>=10) {
                     Table[i][column-1]=(float) (myCash/(6));
                 }
                if(myGame.getTurn()>=11) {
                     Table[i][column-1]=(float) (myCash/(6));
                 }
               if(myGame.getTurn()>=15) {
                     Table[i][column-1]=(float)(myCash/5);
                 }
             if(myGame.getTurn()>20) {
                     Table[i][column-1]=(float)(myCash/5.0);
                 }
              if(myGame.getTurn()>30) {
                     Table[i][column-1]=(float)(myCash/5.0);
                 }
              if(myGame.getTurn()>40) {
                     Table[i][column-1]=(float)(myCash/3);
                 }
              if(myGame.getTurn()>50) {
                     Table[i][column-1]=(float)(myCash/2);
                 }
              if(myGame.getTurn()>55) {
                     Table[i][column-1]=(float)(myCash/1);
                 }
           }
             if(i==1) {
                 Table[i][column-1]=500;
                 if(myGame.getTurn()==1) {
                     Table[i][column-1]=4000;
                 }
                 if(myPlayer.getPowerNeed()==1){
                     Table[i][column-1]=100;
                 }
                if(getAliveNumberPlayer(myGame)==1) {
                     Table[i][column-1]=0;
                 }
                
             }
             if(i==2) {                    
                 Table[i][column-1]=(float)(-0.8*1.4)*(myPlayer.getPowerNeed());//(((-0.8)*(myPlayer.getPowerNeed())+(-1)*(myPlayer.getPowerAvailable())));
                 if(myGame.getTurn()==1) {
                     Table[i][column-1]=(float)(-0.8*1.4*100);
                 }                
                 if(myGame.getTurn()>=10){                     
                     Table[i][column-1]=(float)((-0.82*myPlayer.getPowerNeed()));
                 }                
             }
                     }

         for(int i=0;i<line-1;i++){
             Table[i][0]=Variables+i+1;
         }
             for(int i=0;i<line-1;i++){
             Table[i][Variables+i+1]=1;          
         }
             return Table;
    } 
    /**
     * It's gives us the PowerNeed augmentation thanks to a formula
     * @param g the game parameter
     * @return 
     */
    public  float getPowerNeedAugmentation(Game g){
     
        float pourcentage=0;
        int AliveNumberPlayer=0; 
        
        int Greenrank=0;
               
        for(Player p : g.getPlayers())
        {
            if(p.getState()==PlayerStateEnum.ALIVE)
            {
                arrayAlivePlayer.add(p);
            }
        }

        AliveNumberPlayer=arrayAlivePlayer.size();
        Greenrank=myPlayer.getGreenRank();
        pourcentage=(1+(AliveNumberPlayer-Greenrank)/(AliveNumberPlayer));
     
        return pourcentage;
    }
    
    /**
     * Gives us the alive number player
     * Which is important for the evolution of game
     * @param g the game of oject in order to get the Arraylist of players
     * @return 
     */
     public  int getAliveNumberPlayer(Game g){
      
           
        int compteur=0;
               
        for(Player p : g.getPlayers())
        {
            if(p.getState()== (PlayerStateEnum.ALIVE))/**************************************************/
            {
                arrayAlivePlayer.add(p);
                compteur++;                
            }            
        }
        return compteur;
    }  
}