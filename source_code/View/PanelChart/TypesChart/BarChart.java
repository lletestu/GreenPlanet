/**
 * Created with IntelliJ IDEA.
 * User: Lauren
 * Date: 30/04/13
 * Time: 02:09
 * To change this template use File | Settings | File Templates.
 */
package GreenPlanet.View.PanelChart.TypesChart;

import GreenPlanet.Model.UtilityFunction.Animator;
import GreenPlanet.View.UtilityFunction.LoadImage;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.ApplicationFrame;

import javax.swing.*;
import java.awt.*;

/**
 * Class to create a bar chart
 */
public class BarChart extends ApplicationFrame {

    /**
     * Panel containig bar chart
     */
    private JPanel jpanel;
    /**
     * Default data set
     */
    private static DefaultCategoryDataset defaultCategoryDataset = new DefaultCategoryDataset();

    /**
     * Represents the first data containing in this chart
     */
    private static String player1 = "My Player";
    /**
     * Represents the first category of building nuclear
     */
    private static String category1 = "Nuclear";
    /**
     * Represents the second category of building coal plant
     */
    private static String category2 = "Coal Plant";
    /**
     * Represents the third category of building solar plant
     */
    private static String category3 = "Solar";
    /**
     * Represents the fourth category of building wind turbine
     */
    private static String category4 = "Wind Turbine";
    /**
     * Represents the fifth category of building nwater turbine
     */
    private static String category5 = "Water Turbine";

    /**
     * Object to load an image
     */
    private static LoadImage image;

    /**
     * Represents the height of panel
     */
    private final int HEIGHT = (int)(880/4);
    /**
     * Represents the width of panel
     */
    private final int WIDTH = (int)(420/4);

    /**
     * Constructor
     * @param title represents the title of the chart
     * @param background represents the name of the background image
     */
    public BarChart(String title, String background){
        super(title);

        double[] tmp = new double[5];
        for(double d:tmp) d=0;
        image = new LoadImage(background);

        jpanel = createPanelBarChart(tmp);
        jpanel.setPreferredSize(new Dimension(WIDTH,HEIGHT));
        setContentPane(jpanel);
    }

    /**
     * Constructor
     * @param title represents the title of the chart
     * @param value represents the value for this chart
     * @param background represents the name of the background image
     */
    public BarChart(String title, double [] value, String background) {
        super(title);

        jpanel = createPanelBarChart(value);
        jpanel.setPreferredSize(new Dimension(500,270));
        setContentPane(jpanel);
    }

    /**
     * This method allows to create a JfreeChart
     * @param categoryDataset represent the current data set
     * @return Jfreechart represents the chart
     */
    private static JFreeChart createCharte (CategoryDataset categoryDataset){
        JFreeChart jFreeChart = ChartFactory.createBarChart("BUILDINGS", "NUMBERS", "VALUE", categoryDataset, PlotOrientation.VERTICAL, false, true, false);

        jFreeChart.setBackgroundImage(image.getImg());
        TextTitle textTitle = jFreeChart.getTitle();
        textTitle.setFont(new Font("Bazar", Font.BOLD, 12));

        CategoryPlot categoryPlot = (CategoryPlot)jFreeChart.getPlot();
        categoryPlot.setDomainGridlinesVisible(true);
        categoryPlot.setBackgroundAlpha(0.0f);
        categoryPlot.setOutlineVisible(false);

        NumberAxis numberaxis = (NumberAxis)categoryPlot.getRangeAxis();
        numberaxis.setStandardTickUnits(NumberAxis.createStandardTickUnits());
        numberaxis.setLabelFont(new Font("Bazar", Font.BOLD, 12));

        BarRenderer barRenderer = (BarRenderer)categoryPlot.getRenderer();
        barRenderer.setDrawBarOutline(false);

        GradientPaint gradientPaint = new GradientPaint(0.0f,0.0f,new Color(23,49,16),0.0f,0.0f,new Color(80,150,32));
        barRenderer.setSeriesPaint(0, gradientPaint);
        CategoryAxis categoryAxis = categoryPlot.getDomainAxis();
        categoryAxis.setCategoryLabelPositions(CategoryLabelPositions.createUpRotationLabelPositions(0.52359877559829882D));
        categoryAxis.setLabelFont(new Font("Bazar", Font.BOLD, 12));

        return jFreeChart;
    }

    /**
     * This method allows to add a new value in this chart
     * @param value represents the value for the chart
     * @return a default category dataset
     */
    private static DefaultCategoryDataset createDataset(double [] value){

        defaultCategoryDataset.addValue(value[0], player1, category1);
        defaultCategoryDataset.addValue(value[1], player1, category2);
        defaultCategoryDataset.addValue(value[2], player1, category3);
        defaultCategoryDataset.addValue(value[3], player1, category4);
        defaultCategoryDataset.addValue(value[4], player1, category5);


        return defaultCategoryDataset;
    }

    /**
     * This method allows to create a panel bar chart
     * @param value represents the value for the chart
     * @return panel which containing the chart
     */
    public static JPanel createPanelBarChart(double [] value){
        JFreeChart jFreeChart = createCharte(createDataset(value));
        Animator animator = new Animator();
        animator.start();


        return new ChartPanel(jFreeChart);
    }

    /**
     * Getter of panel which containing the chart
     * @return the current panel
     */
    public JPanel getJpanel() {
        return jpanel;
    }

    /**
     * This method allows to update value for chart
     * @param value represents the value for the chart
     */
    public void setValue(double [] value){
        defaultCategoryDataset.addValue(value[0], player1, category1);
        defaultCategoryDataset.addValue(value[1], player1, category2);
        defaultCategoryDataset.addValue(value[2], player1, category3);
        defaultCategoryDataset.addValue(value[3], player1, category4);
        defaultCategoryDataset.addValue(value[4], player1, category5);
    }
}
