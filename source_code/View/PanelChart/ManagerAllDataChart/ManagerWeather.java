/**
 * Created with IntelliJ IDEA.
 * User: Lauren
 * Date: 30/04/13
 * Time: 18:19
 * To change this template use File | Settings | File Templates.
 */
package GreenPlanet.View.PanelChart.ManagerAllDataChart;

import GreenPlanet.View.PanelChart.TypesChart.PieChart3D;

/**
 * Class to define the weather chart
 */
public class ManagerWeather {
    /**
     * Represent the type of chart Pie chart 3D
     */
    private PieChart3D weatherChart;
    /**
     * Int array to store data weather
     */
    private int [] weatherData = new int[3];

    /**
     * Default Constructor
     */
    public ManagerWeather(){
        weatherChart = new PieChart3D("Weather","Images/fondWeather.jpg");
    }

    /**
     * This method allows to update data and chart
     * @param solar represent the percent of sol
     * @param water represent the percent of water
     * @param wind represent the percent of wind
     */
    public void updateChart(int solar, int water, int wind){
       int[] weatherGame = new int[3];
        weatherGame[0] = solar;
        weatherGame[1] = water;
        weatherGame[2] = wind;

        updateWeatherChart(weatherGame);
    }

    /**
     * This method allows to update weather chart
     * @param weatherGame array containing data to update chart
     */
    public void updateWeatherChart(int[] weatherGame){
        weatherData[0] = weatherGame[0];
        weatherData[1] = weatherGame[1];
        weatherData[2] = weatherGame[2];

        weatherChart.setValue(weatherData);
    }

    /**
     * Getter pie chart
     * @return the current chart to the weather
     */
    public PieChart3D getWeatherChart() {
        return weatherChart;
    }
}
