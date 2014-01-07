package GreenPlanet.Controller.Listener;

import java.awt.event.*;
import javax.swing.*;

/**
 * Mouse Listener
 * Clear the default texts of the JTextFields
 * STATUS: DONE
 * LAST UPDATE: 08/05/13
 * @author Jérémy
 */
public class EntryMouseListener implements  MouseListener{
    
    /**
     * JTextField object to perform actions
     */
    private JTextField myText;
    
    /**
     * Initial text JTextField objects
     */
    private String originalTxt;
    
    /**
     * Default constructor
     * @param txt is the JTextField object on which an action occurs
     */
    public EntryMouseListener(JTextField txt) {
        myText=txt;
        originalTxt=myText.getText();
    }
    
    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        
    }
    
    @Override
    public void mouseExited(MouseEvent e) {

    }
    
    /**
     * Mouse clicked action
     * If the JTextField is enabled and contains the original text, it is cleared.
     * @param e is the MouseEvent
     */
    @Override
    public void mouseClicked(MouseEvent e) {
        
        if((myText.getText().equals(originalTxt)) && (myText.isEnabled() == true)) {
            myText.setText("");
        }
    }
}
