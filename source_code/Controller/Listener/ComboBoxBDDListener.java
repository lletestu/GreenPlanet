package GreenPlanet.Controller.Listener;

import GreenPlanet.View.MainViewGame.WindowBDD;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Action Listener for the JComboBox of the WindowBDD
 * Get the activation of the BDD.
 * STATUS: DONE
 * LAST UPDATE: 08/05/13
 * @author Jérémy
 */
public class ComboBoxBDDListener implements ActionListener {
    
    /**
     * WindowBDD object to perform actions
     */
    private WindowBDD myWindow;
    
    /**
     * Default constructor
     * @param window is the current object WindowBDD used in the program
     */
    public ComboBoxBDDListener(WindowBDD window) {
        myWindow = window;
    }
    
    /**
     * Action performed
     * @param ae is the ActionEvent
     */
    @Override
    public void actionPerformed(ActionEvent ae) {

        if (myWindow.getChoice().getSelectedItem() == "Active BDD") {
            myWindow.setSkip(false);
            myWindow.getUrlName().setEnabled(true);
            myWindow.getBddName().setEnabled(true);
            myWindow.getPassword().setEnabled(true);

        } else {
            myWindow.setSkip(true);
            myWindow.getBddName().setEnabled(false);
            myWindow.getPassword().setEnabled(false);
            myWindow.getUrlName().setEnabled(false);
        }
    }
}
