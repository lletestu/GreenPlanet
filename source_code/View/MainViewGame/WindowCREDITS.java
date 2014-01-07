package GreenPlanet.View.MainViewGame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;

/**
 * ActionListener
 * Initialises and displays WindowCREDITS
 * STATUS: DONE
 * LAST UPDATE: 11/05/13
 * @author Jérémy
 */
public class WindowCREDITS implements ActionListener{
    
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
    public WindowCREDITS(WindowSPLASH myFrame) {
        this.myFrame = myFrame;
    }
    
    /**
     * Action performed
     * Creates and displays the WindowCREDITS
     * @param ae is the ActionEvent
     */
    @Override
    public void actionPerformed(ActionEvent ae) {
        myFrame.getContentPane().removeAll();
        fontPanel = new FontImagePanel("fontCR3.png");
        myFrame.setContentPane(fontPanel);
        myFrame.validate();
    }
}
