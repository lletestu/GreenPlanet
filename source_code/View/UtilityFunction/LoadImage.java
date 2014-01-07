
/**
 * Created with IntelliJ IDEA.
 * User: Lauren
 * Date: 01/05/13
 * Time: 20:46
 * To change this template use File | Settings | File Templates.
 */
package GreenPlanet.View.UtilityFunction;

import javax.imageio.ImageIO;
import java.applet.Applet;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Class allows to load an image
 */
public class LoadImage extends Applet {
    /**
     * Buffered image
     */
    private BufferedImage img = null;

    /**
     * Constructor
     * @param filename name of image that want load
     */
    public LoadImage(String filename) {
        try {
            img = ImageIO.read(new File(filename));
        } catch (IOException e) {
        }
    }

    /**
     * Paints this component's children
     * @param g the Graphics context in which to pain
     */
    @Override
    public void paint(Graphics g) {
        g.drawImage(img, 50, 50, null);
    }

    /**
     * Get the current image
     * @return buffered image
     */
    public BufferedImage getImg() {
        return img;
    }
}
