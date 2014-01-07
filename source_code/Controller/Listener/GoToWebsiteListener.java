package GreenPlanet.Controller.Listener;

import java.awt.*;
import java.awt.event.*;
import java.net.URI;

/**
 * Action Listener 
 * Go to the game website.
 * STATUS: DONE
 * LAST UPDATE: 08/05/13
 * @author Jérémy
 */
public class GoToWebsiteListener implements ActionListener, ItemListener {
    
    /**
     * URL of the game website
     */
    private String myURL;
    
    /**
     * True if the OS is Windows
     */
    private boolean isWindows = false;
    
    /**
     * True if the OS is Mac
     */
    private boolean isMac = false;
    
    /**
     * Default constructor
     * Initialise the URL.
     */
    public GoToWebsiteListener() {
        myURL = "www.green-planet-project.com";
    }
    
    /**
     * Action performed
     * Open the game website page in the default broswer.
     * @param ae is the ActionEvent
     */
    @Override
    public void actionPerformed(ActionEvent ae) {
        String os = System.getProperty("os.name").toLowerCase();
        isWindows = os.contains("win");
        isMac = os.contains("mac");

        try {

            if (isMac) {
                Runtime.getRuntime().exec("open http://www.green-planet-project.com");
            }
            
            if (isWindows) {
                Desktop myDesktopBrowser = Desktop.getDesktop();
                URI myURI = new URI(myURL);
                myDesktopBrowser.browse(myURI);
            }

        } catch (Exception e) {
            System.err.println("internet");
        }
    }
    
    @Override
    public void itemStateChanged(ItemEvent e) {
        
    }
}
