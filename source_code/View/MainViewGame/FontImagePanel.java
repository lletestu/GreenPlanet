package GreenPlanet.View.MainViewGame;

import GreenPlanet.Images.Images;
import java.awt.*;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;

/**
 * Create a JPanel with an image in background
 * STATUS: DONE
 * LAST UPDATE: 08/05/13
 * @author Jérémy
 */
public class FontImagePanel extends JPanel {
    
    /**
     * Image in the background of the panel
     */
    private Image img;
    
    /**
     * Images object to get images ressources
     */
    private Images i = new Images();
    
    /**
     * Default constructor
     * Load the image from the ressources.
     * @param imgPath 
     */
    public FontImagePanel(String imgPath){
        
        try {
            img=ImageIO.read(i.getClass().getResource(imgPath));
            
        }
        catch (IOException e){
            e.getStackTrace();
        }
    }
    
    /**
     * Paint the image in the background of the panel
     * @param g is the Graphics object used
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(img, 0, 0,this.getWidth(),this.getHeight(), this);
    } 
}
