package GreenPlanet.Controller.Listener;

import GreenPlanet.View.MainViewGame.WindowGAMECONF;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Action Listener for the JComboBox of the WindowGAMECONF.
 * Get the AI used for the game: "IA1" or "IA2"
 * STATUS: DONE
 * LAST UPDATE: 08/05/13
 * @author Jérémy
 */
public class ComboBoxListenerIA implements ActionListener {
    
    /**
     * WindowGAMECONF object to perform actions
     */
    private WindowGAMECONF myWindow;
    
    /**
     * Default constructor
     * @param window is the current object WindowGAMECONF used in the program
     */
    public ComboBoxListenerIA(WindowGAMECONF window) {
        myWindow = window;
    }
    
    /**
     * Action performed
     * Set the AI used in the game according to the choice of the user.
     * @param ae is the ActionEvent
     */
    @Override
    public void actionPerformed(ActionEvent ae) {

        if (myWindow.getComboBoxIA().getSelectedItem() == "IA1") {
            myWindow.setIa("IA1");

        } else {
            myWindow.setIa("IA2");
        }
    }
}
