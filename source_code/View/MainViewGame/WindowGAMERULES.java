package GreenPlanet.View.MainViewGame;

import java.awt.event.*;
import javax.swing.*;

/**
 * ActionListener
 * Initialises and displays WindowGAMERULES
 * STATUS: DONE
 * LAST UPDATE: 11/05/13
 * @author Jérémy
 */
public class WindowGAMERULES  implements ActionListener{
    
    /**
     * WindowGAMERULES main panel with the background image
     */
    private FontImagePanel fontPanel;
    
    /**
     * WindowSPLASH source frame
     */
    private JFrame myFrame;
    
    /**
     * Default constructor
     * @param myFrame is the WindowSPLASH source frame
     */
    public WindowGAMERULES(WindowSPLASH myFrame) {
        this.myFrame = myFrame;
    }
    
    /**
     * Action performed
     * Creates and displays the WindowGAMERULES
     * @param ae is the ActionEvent
     */
    @Override
    public void actionPerformed(ActionEvent ae) {
        myFrame.getContentPane().removeAll();
        fontPanel = new FontImagePanel("fontGR1.png");
        myFrame.setContentPane(fontPanel);
        myFrame.validate();
    }
}
