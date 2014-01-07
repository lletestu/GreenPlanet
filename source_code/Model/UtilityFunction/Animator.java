/**
 * Created with IntelliJ IDEA.
 * User: Lauren
 * Date: 30/04/13
 * Time: 02:11
 * To change this template use File | Settings | File Templates.
 */
package GreenPlanet.Model.UtilityFunction;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;

/**
 * Class that allows to animate a jfreechart
 */
public class Animator extends Timer implements ActionListener {
    /**
     * Default constructor
     */
    public Animator(){
        super(100,null);
        addActionListener(this);

    }

    /**
     * Override action performed
     * @param e action event
     */
    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
