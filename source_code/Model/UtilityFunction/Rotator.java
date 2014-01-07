/**
 * Created with IntelliJ IDEA.
 * User: Lauren
 * Date: 29/04/13
 * Time: 12:14
 * To change this template use File | Settings | File Templates.
 */
package GreenPlanet.Model.UtilityFunction;

import org.jfree.chart.plot.PiePlot3D;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Class allows to animate a pie chart
 */
public class Rotator extends Timer implements ActionListener {

    /**
     * Represents a pie plot
     */
    private PiePlot3D plot;
    /**
     * Angle for rotation
     */
    private int angle;

    /**
     * Default constructor
     * @param piePlot3D represent the current pie plot
     */
    public Rotator(PiePlot3D piePlot3D){
        super(100,null);
        angle = 270;
        plot = piePlot3D;
        addActionListener(this);

    }

    /**
     * Override action performed to animate pie chart
     * @param e represents the current action event
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        plot.setStartAngle(angle);
        angle += 1;

        if(angle == 360) angle = 0;    }
}
