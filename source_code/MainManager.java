/**
 * Created with IntelliJ IDEA.
 * User: Lauren
 * Date: 28/04/13
 * Time: 19:17
 * To change this template use File | Settings | File Templates.
 */
package GreenPlanet;

import GreenPlanet.Controller.GreenPlanetController;
import GreenPlanet.Model.Register.ManagerDirectory;
import GreenPlanet.View.MainViewGame.WindowSPLASH;

/**
 * Main manager
 */
public class MainManager {

    /**
     * Main class
     * @param args argument line command
     */
    public static void main(String [] args){
        initializationData();

        try {

            GreenPlanetController greenPlanetController = new GreenPlanetController();
            WindowSPLASH myFrame = new WindowSPLASH(greenPlanetController);

        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getMessage());
        }
    }

    /**
     * This method allows to initialize directory to store data of the game
     */
    public static void initializationData(){
        boolean tmp= ManagerDirectory.deleteDirectory(ManagerDirectory.getPathFile());
        ManagerDirectory.createDirectory();
    }

}
