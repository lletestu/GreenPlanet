package GreenPlanet.View.MainViewGame;

import GreenPlanet.Controller.CreateGameController;
import GreenPlanet.Model.ManagerModel;
import GreenPlanet.Model.UtilityFunction.GetPlayer;
import GreenPlanet.View.PanelChart.ManagerDataChart;
import GreenPlanet.View.PanelGame.GameBackground;
import greenplanetclient.Game;
import java.awt.event.*;
import javax.swing.JOptionPane;

/**
 * Action Listener for "Launch the game!" JButton
 * Verify the inputs of WindowGAMECONF
 * STATUS: DONE
 * LAST UPDATE: 10/05/13
 * @author Jérémy
 */
public class WindowINGAME implements ActionListener {
    
    /**
     * WindowGAMECONF source window
     */
    private WindowGAMECONF myFrame;
    
    /**
     * GameBackground object
     */
    private GameBackground gameBackground;
    
    /**
     * CreateGameController object
     */
    private CreateGameController createGameController;

    /**
     * IA type : "IA1" or "IA2"
     */
    private String myIA;
    
    /**
     * User(player) name
     */
    private String myName;
    
    /**
     * Game type : "Online" or "Offline"
     */
    private String myGType;
    
    /**
     * Number of bots
     */
    private int nb;
    
    /**
     * Internet game ID
     */
    private int gameId;

    /**
     * Default constructor
     * @param myFrame is WindowGAMECONF source window
     */
    public WindowINGAME(WindowGAMECONF myFrame) {
        this.myFrame = myFrame;
        createGameController = myFrame.getGreenPlanetController().getCreateGameController();
    }
    
    /**
     * Action performed when the "Launch the game!" JButton is clicked
     * Verify inputs before prior to launch the game. Displays JDialog windows if errors.
     * @param ae is the ActionEvent
     * @exception NumberFormatException
     */
    @Override
    public void actionPerformed(ActionEvent ae) {

        boolean next = true;
        myIA = myFrame.getIa();


        myName = myFrame.getEntrys()[1].getText();
        if ("".equals(myName)) {
            JOptionPane.showMessageDialog(myFrame, "Wrong pseudo, retape it.");
            myFrame.getEntrys()[1].setText("");
            next = false;
        }

        if (myName.length() > 12) {
            //POP UP : nom trop long
            JOptionPane.showMessageDialog(myFrame, "Pseudo is too long, retape it.");
            myFrame.getEntrys()[1].setText("");
            next = false;
        }


        if ("Online".equals(myFrame.getComboBox().getSelectedItem().toString())) {
            try {
                myGType = myFrame.getComboBox().getSelectedItem().toString();
                gameId = Integer.parseInt(myFrame.getEntrys()[0].getText());

            } catch (NumberFormatException ex) {
                System.err.println(ex);
                JOptionPane.showMessageDialog(myFrame, "Wrong Game ID, retape it.");
                myFrame.getEntrys()[0].setText("");
                next = false;
            }

        } else {
            try {
                myGType = myFrame.getComboBox().getSelectedItem().toString();
                nb = Integer.parseInt(myFrame.getEntrys()[2].getText());
                if (nb > 24) {
                    JOptionPane.showMessageDialog(myFrame, "NB bots is too high, retape it.");
                    myFrame.getEntrys()[2].setText("");
                    next = false;
                }

            } catch (NumberFormatException ex) {
                System.err.println(ex);
                JOptionPane.showMessageDialog(myFrame, "Wrong NB bots, retape it.");
                myFrame.getEntrys()[2].setText("");
                next = false;
            }
        }

        if (next) {
            createGameController.createGame(this, myName, myIA, myGType, nb, gameId);
            gameBackground = new GameBackground(createGameController.getManagerDataChart());

            myFrame.getMyFrame().getContentPane().removeAll();
            myFrame.getMyFrame().setContentPane(gameBackground);
            myFrame.getMyFrame().validate();

            createGameController.startGame();
        }
    }

    /**
     * 
     * @return myFrame
     */
    public WindowGAMECONF getMyFrame() {
        return myFrame;
    }

    /**
     * Update panels Data
     * @param managerDataChart ManagerDataChart object use in the game
     * @param managerModel ManagerModel object use in the game
     * @param game Game object use in the game
     */
    public void update(ManagerDataChart managerDataChart, ManagerModel managerModel, Game game) {
        gameBackground.updateChartPanel(managerDataChart, managerModel, game.getLight(), game.getWater(), game.getWind());
        gameBackground.updateTablePanel(managerModel.getAllPlayer());
        gameBackground.updateTurnPanel(game.getTurn());
        gameBackground.updatePlayerPanel(managerModel.getMyPlayer());
        gameBackground.updateEventPanel(game.getEvents(), game.getPowerPrice(), game.getPlayers(), game.getState(), null);
    }

    /**
     * Displays game is finished event
     * @param game Game object use in the game
     * @param managerModel ManagerModel object use in the game
     */
    public void UpdateFinishGameEvent(Game game, ManagerModel managerModel) {
        Object[] tmp = GetPlayer.getWinnerPlayer(managerModel.getDataGame().getHashMapData());
        gameBackground.updateEventPanel(game.getEvents(), game.getPowerPrice(), game.getPlayers(), game.getState(), tmp);
    }
}
