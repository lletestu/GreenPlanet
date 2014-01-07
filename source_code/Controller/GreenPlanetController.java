package GreenPlanet.Controller;

import GreenPlanet.Model.ManagerBD;
import GreenPlanet.Model.ManagerModel;
import GreenPlanet.Model.Player.PlayerData;
import GreenPlanet.Model.UtilityFunction.GetPlayer;

/**
 * Created with IntelliJ IDEA.
 * User: Lauren
 * Date: 05/05/13
 * Time: 18:02
 * To change this template use File | Settings | File Templates.
 */

/**
 * The main controller of the game green planet
 */
public class GreenPlanetController {

    /**
     * Controller to create a new game
     */
    private CreateGameController createGameController = new CreateGameController();

    /**
     * Controller to manage the database SQL
     */
    private ManagerBD managerBD = new ManagerBD();

    /**
     * Boolean to check if the user is connected to database or not
     */
    private boolean bdActivate = false;

    /**
     * Return the object Create Game Controller
     * @return the current controller to create a new game
     */
    public CreateGameController getCreateGameController() {
        return createGameController;
    }

    /**
     * Return the object Manager database
     * @return the current manager of the database SQL
     */
    public ManagerBD getManagerBD() {
        return managerBD;
    }

    /**
     * Function allowing to add a player in the database
     * @param managerModel field that contains the main game data
     */
    public void insertPlayerWinToBD(ManagerModel managerModel){
        Object[] tmp = GetPlayer.getWinnerPlayer(managerModel.getDataGame().getHashMapData());

        int lastTurn = (Integer) tmp[0];
        PlayerData winPlayer = (PlayerData) tmp[1];

        try {
            managerBD.insertData(winPlayer.getId(),winPlayer.getName(),winPlayer.getCash(),winPlayer.getGreenRank(),winPlayer.getPowerNeed(),lastTurn);
        }catch (Exception e){
            System.err.println("Error insert data");
        }

    }

    /**
     * Return a boolean to check if the user is connecting on the database
     * @return the current boolean to check the connection to the database
     */
    public boolean isBdActivate() {
        return bdActivate;
    }

    /**
     * Set the boolean for the connection to the database
     * @param bdActivate boolean which represents the connection to the database
     */
    public void setBdActivate(boolean bdActivate) {
        this.bdActivate = bdActivate;
    }
}
