package GreenPlanet.Controller.Listener;

import java.awt.event.*;
import GreenPlanet.View.MainViewGame.WindowGAMECONF;

/**
 * Action Listener for the JComboBox of the WindowGAMECONF.
 * Get the type of the game : "Online" or "Offline"
 * STATUS: DONE
 * LAST UPDATE: 08/05/13
 * @author Jérémy
 */
public class ComboBoxListener implements ActionListener{
    
    /**
     * WindowGAMECONF object to perform actions
     */
    private WindowGAMECONF myWindow;
    
    /**
     * Default constructor
     * @param window is the current object WindowGAMECONF used in the program
     */
    public ComboBoxListener(WindowGAMECONF window) {
        myWindow = window;
    }
    
    /**
     * Action performed
     * Enable or disable the JTextField objects according to the the type of the game.
     * @param ae is the ActionEvent
     */
    @Override
    public void actionPerformed(ActionEvent ae) {
        
        if (myWindow.getComboBox().getSelectedItem() == "Offline") {
            myWindow.getEntrys()[0].setEnabled(false);
            myWindow.getEntrys()[1].setEnabled(true);
            myWindow.getEntrys()[2].setEnabled(true);
            myWindow.getEntrys()[3].setEnabled(true);
            
        } else {
            myWindow.getEntrys()[0].setEnabled(true);
            myWindow.getEntrys()[1].setEnabled(true);
            myWindow.getEntrys()[2].setEnabled(false);
            myWindow.getEntrys()[3].setEnabled(true);   
        }
    }
}
