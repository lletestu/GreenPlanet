/**
 * Created with IntelliJ IDEA.
 * User: Lauren
 * Date: 01/05/13
 * Time: 19:16
 * To change this template use File | Settings | File Templates.
 */
package GreenPlanet.View.UtilityFunction;

import javax.swing.*;
import java.awt.*;

/**
 * This method allows to create an image
 */
public class ImagePanel extends JPanel{

    /**
     * Image
     */
    private Image img;

    /**
     * Constructor
     * @param fileName  name of image
     */
    public ImagePanel(String fileName) {
        this(new ImageIcon(fileName).getImage());
    }

    /**
     * Constructor
     * @param img represent the current image
     */
    public ImagePanel(Image img){
        this.img = img;
        Dimension size = new Dimension(img.getWidth(null),img.getHeight(null));
        setPreferredSize(size);
        setMinimumSize(size);
        setMinimumSize(size);
        setSize(size);
        setLayout(null);
    }

    /**
     * Paints this component's children
     * @param g the Graphics context in which to pain
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(img, 0, 0, null); // see javadoc for more info on the parameters
    }


}
