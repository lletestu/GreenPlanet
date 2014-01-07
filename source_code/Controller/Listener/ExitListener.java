package GreenPlanet.Controller.Listener;

import java.awt.event.ActionListener;
import java.awt.event.*;

/**
 * Action Listener 
 * Leave (terminate) the program.
 * STATUS: DONE
 * LAST UPDATE: 08/05/13
 * @author Jérémy
 */
public class ExitListener implements ActionListener, ItemListener{
    
    /**
     * Action performed
     * Exit the program.
     * @param ae is the ActionEvent
     */
    @Override
    public void actionPerformed(ActionEvent ae) {
        System.exit(0);
    }
    
    @Override
    public void itemStateChanged(ItemEvent e) {
        
    }
}
