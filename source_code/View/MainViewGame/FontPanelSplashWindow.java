package GreenPlanet.View.MainViewGame;

import GreenPlanet.Images.Images;
import java.awt.*;
import java.io.*;
import javax.imageio.ImageIO;
import javax.swing.*;

/**
 * Create the WindowSPLASH contentPane
 * STATUS: DONE
 * LAST UPDATE: 08/05/13
 * @author Jérémy
 */
public class FontPanelSplashWindow extends JPanel{
    
    /**
     * Background image
     */
    private Image img;
    
    /**
     * Title image
     */
    private Image img2;
    
    /**
     * SubTitle image
     */
    private Image img3;
    
    /**
     * Images object to get images ressources
     */
    private Images i = new Images();
    
    /**
     * Default constructor
     * @param imgPath is the pathname of the background image
     * @param imgPath2 is the pathname of the title image
     * @param imgPath3 is the pathname of the subtitle image
     * @throws IOException 
     */
    public FontPanelSplashWindow(String imgPath, String imgPath2, String imgPath3) throws IOException{
        
        try {
            img=ImageIO.read(i.getClass().getResource(imgPath));
            img2=ImageIO.read(i.getClass().getResource(imgPath2));
            img3=ImageIO.read(i.getClass().getResource(imgPath3));
        }
        catch (IOException e){
            e.getStackTrace();
        }
    }
    
    /**
     * Paint the loaded images on the panel
     * @param g is the Graphics object used
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);
        g.drawImage(img2, 130, 40, this);
        g.drawImage(img3, 230, 200, this);
    } 
}
