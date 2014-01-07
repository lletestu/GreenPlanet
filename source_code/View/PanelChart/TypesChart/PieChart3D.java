/**
 * Created with IntelliJ IDEA.
 * User: Lauren
 * Date: 29/04/13
 * Time: 12:14
 * To change this template use File | Settings | File Templates.
 */
package GreenPlanet.View.PanelChart.TypesChart;

import GreenPlanet.Model.UtilityFunction.Rotator;
import GreenPlanet.View.UtilityFunction.LoadImage;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.util.Rotation;

import javax.swing.*;
import java.awt.*;


public class PieChart3D extends ApplicationFrame{
    /**
     * Panel containig dynamics chart
     */
    private JPanel jPanel;
    /**
     * Default pie dataset
     */
    private static DefaultPieDataset union = new DefaultPieDataset();
    /**
     * Object to load an image
     */
    private static LoadImage image;
    /**
     * Represents the height of panel
     */
    private final int HEIGHT = 260;
    /**
     * Represents the width of panel
     */
    private final int WIDTH = 200;

    /**
     * Constructor
     * @param str Represents the chart tittle
     * @param background represents the name of the background image
     */
    public PieChart3D(String str, String background){
        super(str);

        int[] tmp = new int[3];
        for(int i:tmp) i=0;
        image = new LoadImage(background);
        jPanel = createPanelPieChart(tmp);
        jPanel.setPreferredSize(new Dimension(WIDTH,HEIGHT));

        setContentPane(jPanel);
    }

    /**
     * Constructor
     * @param str Represents the chart tittle
     * @param value represents the value for the chart
     * @param background represents the name of the background image
     */
    public PieChart3D(String str, int [] value, String background){
        super(str);
        image = new LoadImage(background);
        jPanel = createPanelPieChart(value);
        jPanel.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        jPanel.setOpaque(false);
        setOpacity(0.0f);
        setContentPane(jPanel);
    }

    /**
     * This method allows to create a new chart
     * @param pieDataset represent a pie dataset
     * @return a Jfreechart that represents a pie chart 3D
     */
    private static JFreeChart createCharte (PieDataset pieDataset){
        JFreeChart jFreeChart = ChartFactory.createPieChart3D("", pieDataset, false, true, false);

        jFreeChart.setBackgroundPaint(new Color(249,244,215));

        PiePlot3D piePlot3D = (PiePlot3D)jFreeChart.getPlot();
        piePlot3D.setStartAngle(270D);
        piePlot3D.setDirection(Rotation.ANTICLOCKWISE);
        piePlot3D.setForegroundAlpha(0.6f);

        piePlot3D.setOutlineVisible(false);
        piePlot3D.setSimpleLabels(true);
        piePlot3D.setLabelBackgroundPaint(new Color(253, 238, 195));
        piePlot3D.setLabelFont(new Font("Bazar", Font.PLAIN, 14));

        piePlot3D.setBackgroundAlpha(0.0f);

        piePlot3D.setSectionPaint("LIGHT", new Color(250,249,40));
        piePlot3D.setSectionPaint("WATER", new Color(60,150,204));
        piePlot3D.setSectionPaint("WIND", new Color(80,150,32));


        return jFreeChart;
    }

    /**
     * This method allows to create a new pie chart
     * @param value represents the value for the chart
     * @return panel which containing the chart
     */
    public JPanel createPanelPieChart(int [] value){
        createDataset(value);
        JFreeChart jFreeChart = createCharte(union);
        Rotator rotator = new Rotator((PiePlot3D)jFreeChart.getPlot());
        rotator.start();


        return new ChartPanel(jFreeChart);
    }

    /**
     * Add new value to the pie chart
     * @param value represents the value for the chart
     */
   private static void createDataset(int[] value){

        union.setValue("LIGHT",value[0]);
        union.setValue("WATER",value[1]);
        union.setValue("WIND",value[2]);
    }

    /**
     * This method allows to update value for chart
     * @param value represents the value for the chart
     */
    public static void setValue(int [] value){
        union.setValue("LIGHT",value[0]);
        union.setValue("WATER",value[1]);
        union.setValue("WIND",value[2]);
    }

    /**
     * Getter of panel which containing the chart
     * @return the current panel
     */
    public JPanel getjPanel() {
        return jPanel;
    }
}
