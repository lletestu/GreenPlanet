/**
 * Created with IntelliJ IDEA.
 * User: Lauren
 * Date: 04/05/13
 * Time: 14:18
 * To change this template use File | Settings | File Templates.
 */
package GreenPlanet.View.PanelGame;

import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;

/**
 * Class to define the turn panel
 */
public class PanelTurnGame {
    /**
     * Main panel
     */
    private JPanel turnGame;
    /**
     * Dimension for the main panel
     */
    private Dimension dimension = new Dimension(120,130);
    /**
     * Text area to display the current turn
     */
    private JTextArea textArea;

    /**
     * Constructor
     */
    public PanelTurnGame(){
        turnGame = new JPanel(new MigLayout());
        turnGame.setPreferredSize(dimension);
        turnGame.setOpaque(false);

        textArea = new JTextArea();
        textArea.setSize(dimension);
        textArea.setOpaque(false);
        textArea.setEditable(false);

        textArea.setFont(new Font("Bazar", Font.BOLD, 55));
        textArea.setForeground(new Color(66,67,69));
        textArea.setText("#");

        turnGame.add(textArea, "gaptop 40");
    }

    /**
     * This method allows to update panel
     * @param turn represents the current turn of the game
     */
    public void updateTextArea(int turn){
        textArea.removeAll();
        textArea.setText("#"+turn);
    }

    /**
     * Get the main panel
     * @return the current turn panel
     */
    public JPanel getJPanelTurn(){
        return turnGame;
    }
}
