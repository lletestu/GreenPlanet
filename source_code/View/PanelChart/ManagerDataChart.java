/**
 * Created with IntelliJ IDEA.
 * User: Lauren
 * Date: 30/04/13
 * Time: 18:20
 * To change this template use File | Settings | File Templates.
 */
package GreenPlanet.View.PanelChart;

import GreenPlanet.View.PanelChart.ManagerAllDataChart.ManagerChart;
import GreenPlanet.View.PanelChart.ManagerAllDataChart.ManagerWeather;

/**
 * Class to manages all chart
 */
public class ManagerDataChart {
    /**
     * Manager weather chart
     */
    private ManagerWeather managerWeather = new ManagerWeather();
    /**
     * Manager player chart
     */
    private ManagerChart managerChart = new ManagerChart();

    /**
     * Get the manager weather chart
     * @return the current manager weather chart
     */
    public ManagerWeather getManagerWeather() {
        return managerWeather;
    }

    /**
     * Get the manager player chart
     * @return the current manager chart
     */
    public ManagerChart getManagerChart() {
        return managerChart;
    }
}
