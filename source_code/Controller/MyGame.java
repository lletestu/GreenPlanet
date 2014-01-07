package GreenPlanet.Controller;

import GreenPlanet.Model.IA.IA1;
import GreenPlanet.Model.IA.IA2;
import GreenPlanet.Model.ManagerModel;
import GreenPlanet.Model.Register.RegisterData;
import GreenPlanet.View.Console;
import GreenPlanet.View.MainViewGame.WindowINGAME;
import GreenPlanet.View.PanelChart.ManagerDataChart;
import greenplanetclient.*;

import java.util.HashMap;

/**
 * Created with IntelliJ IDEA.
 * User: Lauren
 * Date: 28/04/13
 * Time: 19:17
 * To change this template use File | Settings | File Templates.
 */

/**
 * Class to launch a new game extends thread
 */
public class MyGame extends Thread {
    /**
     *  Manager to save and update data of the game
     */
    private ManagerModel managerModel;
    /**
     * Manager allowing to generate graphics player
     */
    private ManagerDataChart managerDataChart = new ManagerDataChart();
    /**
     * Field represent the swing view
     */
    private WindowINGAME windowINGAME;
    /**
     * Main controller of the gam
     */
    private GreenPlanetController greenPlanetController;
    /**
     * Represents the user's login
     */
    private String name;
    /**
     * Represent the client
     */
    private ClientInterface client;
    /**
     * New game
     */
    private Game game;
    /**
     * Boolean to check if the game type is online or offline
     */
    private boolean offline;
    /**
     * IA 1
     */
    private IA1 myIASelected;
    /**
     * IA 2
     */
    private IA2 myIASelected2;

    /**
     * Boolean to selct IA
     */
    private boolean select;

    /**
     * Enum to select type game
     */
    private static enum selectGameType {OFFLINE, ONLINE}

    /**
     * HashMap to select type game
     */
    private static final HashMap<String, selectGameType> selectGame = new HashMap<String, selectGameType>() {
        {
            put("Offline", selectGameType.OFFLINE);
        }

        {
            put("Online", selectGameType.ONLINE);
        }
    };

    /**
     * Boolean to check if the data is well registered
     */
    private boolean registerData = false;

    /**
     * This method allows to initialize the data of the game
     * @param greenPlanetController  The main controller of the game green planet
     * @param name String that represents the user's login
     * @param myIa String that represents the IA chosen by the user
     * @param gameType String that represents the game type offline or online
     * @param nBots int that is the number of bots against which the user will play
     * @param gameID represents the game id if the user play online
     * @throws Exception if there are the problem with the server
     */
    public void initializeGame(GreenPlanetController greenPlanetController, String name, String myIa, String gameType, int nBots, int gameID) throws Exception {
        this.greenPlanetController = greenPlanetController;
        this.name = name;
        managerModel = new ManagerModel(name);
        client = selectTypeGame(gameType, name, nBots, gameID);
        select = getIAType(myIa);
        myIASelected = selectIA();
        myIASelected2 = selectIA2();
        game = client.waitForStart();
    }

    /**
     * Select the first IA
     * @return object that represents the first IA
     */
    private IA1 selectIA() {
        return new IA1();
    }

    /**
     * Select the second IA
     * @return object that represents the second IA
     */
    private IA2 selectIA2() {
        return new IA2();
    }

    /**
     * Get the ia tha user's choice
     * @param iaType String that represents the ia that user's choice
     * @return boolean true if the ia chosen is the first IA
     */
    private boolean getIAType(String iaType) {
        boolean iaSelect = false;

        if ("IA1".equals(iaType)) {
            iaSelect = true;
        }

        return iaSelect;
    }


    /**
     * Return the client interface following the user choices
     * @param gameType String that represents the game type offline or online
     * @param name String that represents the user's login
     * @param nBots int that is the number of bots against which the user will play
     * @param gameID represents the game id if the user play online
     * @return object client interface
     * @throws Exception if the client is not created
     */
    private ClientInterface selectTypeGame(String gameType, String name, int nBots, int gameID) throws Exception {

        switch (selectGame.get(gameType)) {
            case OFFLINE:
                offline = true;
                return new OfflineClient(name, nBots, false);
            case ONLINE:
                offline = false;
                return new Client(gameID, name);
        }

        return null;
    }

    /**
     * Getter manager data chart
     * @return represents the current manager data chart
     */
    public ManagerDataChart getManagerDataChart() {
        return managerDataChart;
    }

    /**
     * This method allows to add a view to the game
     * @param managerViewGame represents the current frame
     */
    public void addManagerViewGame(WindowINGAME managerViewGame) {
        this.windowINGAME = managerViewGame;
    }

    /**
     * Override method run of the thread object it is the main game loop
     */
    @Override
    public void run() {
        try {

            while (game.getState() == GameStateEnum.RUNNING) {

                // display some stufs
                System.out.println("Play turn #" + game.getTurn());

                // Update Model
                managerModel.updateGeneralData(game);

                // Update view
                //managerViewGame.update(managerDataChart, managerModel,game.getLight(),game.getWater(),game.getWind());
                windowINGAME.update(managerDataChart, managerModel, game);

                // Update File
                RegisterData.updateRegister(managerModel.getDataGame().getHashMapData(), game.getTurn());

                // Display info console
                Console.dataGame(managerModel.getAllPlayer());

                Order order;
                if(select){
                    order = myIASelected.run(game,name);
                }else {
                    order = myIASelected2.run(game,name);
                }

                // give order, wait for next turn
                game = client.giveOrder(order);

                if (offline) sleep(1000);

                if (!offline && game.getState().equals(GameStateEnum.FINISHED) && greenPlanetController.isBdActivate() && !registerData){
                    windowINGAME.UpdateFinishGameEvent(game, managerModel);
                    greenPlanetController.insertPlayerWinToBD(managerModel);
                    System.out.println("ecrit on");
                }

            }

            if (!registerData) {
                windowINGAME.UpdateFinishGameEvent(game, managerModel);
                if (greenPlanetController.isBdActivate()) {
                    System.out.println("save");
                    greenPlanetController.insertPlayerWinToBD(managerModel);
                }
            }


        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }


}
