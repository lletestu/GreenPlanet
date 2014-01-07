package GreenPlanet.Controller;

import GreenPlanet.View.MainViewGame.WindowINGAME;
import GreenPlanet.View.PanelChart.ManagerDataChart;

/**
 * Created with IntelliJ IDEA.
 * User: Lauren
 * Date: 03/05/13
 * Time: 20:21
 * To change this template use File | Settings | File Templates.
 */

/**
 * Controller allowing to create a new game
 */
public class CreateGameController {

    /**
     * Manager allowing to generate graphics player
     */
    private ManagerDataChart managerDataChart;

    /**
     * Object which represents a new game
     */
    private MyGame myGame;

    /**
     * Function allowing to create a new game
     * @param windowINGAME object that represents the frame of the game
     * @param myName String that represents the user's login
     * @param myIA String that represents the IA chosen by the user
     * @param myGType String that represents the game type offline or online
     * @param nBots int that is the number of bots against which the user will play
     * @param gameID represents the game id if the user play online
     */
    public void createGame(WindowINGAME windowINGAME, String myName, String myIA, String myGType, int nBots, int gameID) {
        try {

            myGame = new MyGame();
            managerDataChart = myGame.getManagerDataChart();
            myGame.addManagerViewGame(windowINGAME);

            myGame.initializeGame(windowINGAME.getMyFrame().getGreenPlanetController(),myName, myIA, myGType, nBots, gameID);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    /**
     * Function allowing to launch the game
     */
    public void startGame(){
        myGame.start();
    }

    /**
     * Return the object Maanager dataChart
     * @return the current manager data chart
     */
    public ManagerDataChart getManagerDataChart() {
        return managerDataChart;
    }
}
