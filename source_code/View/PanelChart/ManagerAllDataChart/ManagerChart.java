/**
 * Created with IntelliJ IDEA.
 * User: Lauren
 * Date: 30/04/13
 * Time: 15:30
 * To change this template use File | Settings | File Templates.
 */
package GreenPlanet.View.PanelChart.ManagerAllDataChart;

import GreenPlanet.Model.Player.PlayerData;
import GreenPlanet.View.PanelChart.TypesChart.BarChart;
import GreenPlanet.View.PanelChart.TypesChart.DynamicXY;

/**
 * Manager for the all chart
 */
public class ManagerChart {

    /**
     * Dynamics XY chart for production
     */
    private DynamicXY productionChart;
    /**
     * float array to store data for the chart
     */
    private float[] productionData = new float[1];

    /**
     * Dynamics XY chart for cash
     */
    private DynamicXY cashChart;
    /**
     * float array to store data for the chart
     */
    private float[] cashData = new float[1];

    /**
     * Dynamics XY chart for ratio
     */
    private DynamicXY ratioChart;
    /**
     * float array to store data for the chart
     */
    private float[] ratioData = new float[1];

    /**
     * Bar chart for player's building
     */
    private BarChart buildingChart;
    /**
     * double array to store data for the chart
     */
    private double[] buildingData = new double[5];

    /**
     * Default constructor
     */
    public ManagerChart(){
        buildingChart = new BarChart("BUILDIGS","Images/fondHautD2.png");
        productionChart = new DynamicXY("PRODUCTION","POWER","Images/fondHautG2.png");
        cashChart = new DynamicXY("CASH","DOLLARS","Images/fondBasD2.png");
        ratioChart = new DynamicXY("RATIO","POURCENT","Images/fondBasG2.png");

    }

    /**
     * This method allows to update all chart
     * @param p current player
     */
    public void updateChart(PlayerData p){
        updateBuilding(p);
        updateProductionChart(p);
        updateCash(p);
        updateRatio(p);
    }

    /**
     * Update building chart
     * @param p current player
     */
    public void updateBuilding(PlayerData p){
        buildingData[0] = (double)p.getBuildingsPlayer().getBuildingActual().getNbNuclear();
        buildingData[1] = (double)p.getBuildingsPlayer().getBuildingActual().getNbCoalPlant();
        buildingData[2] = (double)p.getBuildingsPlayer().getBuildingActual().getNbSolarPlant();
        buildingData[3] = (double)p.getBuildingsPlayer().getBuildingActual().getNbWindTurbine();
        buildingData[4] = (double)p.getBuildingsPlayer().getBuildingActual().getNbWaterTurbine();

        buildingChart.setValue(buildingData);
    }

    /**
     * Update production chart
     * @param p current player
     */
    public void updateProductionChart(PlayerData p){
        productionData[0] = (float)p.getBuildingsPlayer().getProductionActual();
        productionChart.setData(productionData);
    }

    /**
     * Update cash chart
     * @param p current player
     */
    public void updateCash(PlayerData p){
        cashData[0] = p.getCash();
        cashChart.setData(cashData);

    }

    /**
     *  Update ratio chart
     * @param p current player
     */
    public void updateRatio(PlayerData p){
        ratioData[0] = p.getSatisfaction();
        ratioChart.setData(ratioData);
    }

    /**
     * Getter Dynamic XY chart
     * @return the current production chart
     */
    public DynamicXY getProductionChart() {
        return productionChart;
    }

    /**
     * Getter Dynamic XY chart
     * @return the current cash chart
     */
    public DynamicXY getCashChart() {
        return cashChart;
    }

    /**
     * Getter Dynamic XY chart
     * @return the current ratio chart
     */
    public DynamicXY getRatioChart() {
        return ratioChart;
    }

    /**
     * Getter Bar chart
     * @return the current building chart
     */
    public BarChart getBuildingChart() {
        return buildingChart;
    }
}
