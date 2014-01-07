/**
 * Created with IntelliJ IDEA.
 * User: Lauren
 * Date: 29/04/13
 * Time: 14:36
 * To change this template use File | Settings | File Templates.
 */
package GreenPlanet.View.PanelChart.TypesChart;

import GreenPlanet.View.UtilityFunction.LoadImage;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.DefaultDrawingSupplier;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.time.DynamicTimeSeriesCollection;
import org.jfree.data.time.Second;
import org.jfree.data.xy.XYDataset;
import org.jfree.ui.ApplicationFrame;

import javax.swing.*;
import java.awt.*;

/**
 * Class to create a Dynamics XY
 */
public class DynamicXY extends ApplicationFrame{
    /**
     * Represent the number of series present in this chart
     */
    private static final int NUMBER_SERIES = 1;
    /**
     * Counter
     */
    private static final int COUNT = 2*60;
    /**
     * Represents the height of panel
     */
    private final int HEIGHT = (int)(880/4);
    /**
     * Represents the width of panel
     */
    private final int WIDTH = (int)(420/4);
    /**
     * Object to load an image
     */
    private LoadImage image;
    /**
     * Dynamic time series collection
     */
    private final DynamicTimeSeriesCollection dataset = new DynamicTimeSeriesCollection(NUMBER_SERIES,COUNT, new Second());

    /**
     * Panel containig dynamics chart
     */
    JPanel chartPanel;

    /**
     * Constructor
     * @param title represents the title of the chart
     * @param legend represents the legend for chart
     * @param background represents the name of the background image
     */
    public DynamicXY(String title, String legend, String background){
        super(title);
        dataset.setTimeBase(new Second(0, 0, 0, 1, 1, 2011));
        image = new LoadImage(background);

        float[] tmp = new float[1];
        tmp[0] = 0;
        dataset.addSeries(tmp, 0, "DATA");

        JFreeChart chart = createChart(dataset,title,legend);

        chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(WIDTH,HEIGHT));

        add(chartPanel);
    }

    /**
     * This method allows to create a new chart
     * @param dataset XY dataset to store value of the chart
     * @param title represents the title of the chart
     * @param legend represents the legend for chart
     * @return a Jfreechart that represents a XY chart
     */
    private JFreeChart createChart(final XYDataset dataset,String title, String legend){


        final JFreeChart result = ChartFactory.createTimeSeriesChart(title, "hh:mm:ss",legend, dataset, false, false, true);

        result.setBackgroundImage(image.getImg());

        result.getTitle().setPaint(new Color(110,120,112));
        TextTitle textTitle = result.getTitle();
        textTitle.setFont(new Font("Bazar", Font.BOLD, 12));

        final XYPlot plot = (XYPlot)result.getXYPlot();

        plot.setBackgroundAlpha(0.0f);
        plot.setOutlineVisible(false);

        plot.setDrawingSupplier(new DefaultDrawingSupplier(new Paint[]{new Color(80,150,32)},
                DefaultDrawingSupplier.DEFAULT_OUTLINE_PAINT_SEQUENCE,
                DefaultDrawingSupplier.DEFAULT_STROKE_SEQUENCE,
                DefaultDrawingSupplier.DEFAULT_OUTLINE_STROKE_SEQUENCE,
                DefaultDrawingSupplier.DEFAULT_SHAPE_SEQUENCE));


        ValueAxis domain = plot.getDomainAxis();
        domain.setLabelFont(new Font("Bazar", Font.BOLD, 12));

        domain.setAutoRange(true);
        domain.setFixedAutoRange(60000.0);
        ValueAxis range = plot.getRangeAxis();

        range.setAutoRange(true);
        range.setLabelFont(new Font("Bazar", Font.BOLD, 12));

        return result;
    }

    /**
     * This method allows to update value for chart
     * @param value represents the value for the chart
     */
    public void setData(float[] value){
        dataset.advanceTime();
        dataset.appendData(value);
    }

    /**
     * Getter of panel which containing the chart
     * @return the current panel
     */
    public JPanel getChartPanel() {
        return chartPanel;
    }
}
