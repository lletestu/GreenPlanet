/**
 * Created with IntelliJ IDEA.
 * User: Lauren
 * Date: 01/05/13
 * Time: 19:16
 * To change this template use File | Settings | File Templates.
 */
package GreenPlanet.View.PanelGame;

import GreenPlanet.Images.Images;
import GreenPlanet.Model.ManagerModel;
import GreenPlanet.Model.Player.PlayerData;
import GreenPlanet.View.PanelChart.ManagerAllDataChart.ManagerChart;
import GreenPlanet.View.PanelChart.ManagerAllDataChart.ManagerWeather;
import GreenPlanet.View.PanelChart.ManagerDataChart;
import GreenPlanet.View.TableStatistics.JtablePanel;
import GreenPlanet.View.UtilityFunction.ImagePanel;
import greenplanetclient.GameStateEnum;
import greenplanetclient.Player;
import net.miginfocom.swing.MigLayout;
import org.jfree.ui.RefineryUtilities;
import greenplanetclient.Event;


import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Represent the vieuw for the game
 */
public class GameBackground extends JPanel {
    
    private Images i = new Images();
    /**
     * Panel to contain many panel
     */
    private JPanel containtPanel = new JPanel();
    /**
     * Main panel
     */
    private JPanel mainPanel = new JPanel();
    /**
     * Panel for start page
     */
    private JPanel startPagePanel = new JPanel();
    /**
     * Panel for center page
     */
    private JPanel centerPagePanel = new JPanel();
    /**
     * Panel for player
     */
    private JPanel playerPanel = new JPanel();
    /**
     * Panel for chart
     */
    private JPanel graphsPanel = new JPanel();
    /**
     * Panel for event
     */
    private JPanel eventPanel = new JPanel();
    /**
     * Panel for statistics
     */
    private JPanel statisticPanel = new JPanel();
    /**
     * Panel for the current turn
     */
    private JPanel turnPanel = new JPanel();
    /**
     * Panel for the weather
     */
    private JPanel weatherPanel = new JPanel();
    /**
     * Image panel for the background
     */
    private ImagePanel imagePanel = new ImagePanel(new ImageIcon(i.getClass().getResource("GreenPlanet_Game2.jpg")).getImage());
    /**
     * Jtabbed for panel data
     */
    private JTabbedPane jTabbedPane = new JTabbedPane();
    /**
     * Jtable panel for statistics
     */
    private JtablePanel jtablePanel = new JtablePanel();
    /**
     * PanelTurn game for current turn game
     */
    private PanelTurnGame panelTurnGame = new PanelTurnGame();
    /**
     * Panel player object that define the panel player
     */
    private PanelPlayer panelDataPlayer = new PanelPlayer();
    /**
     * Panel event object that define the panel event
     */
    private PanelEvent panelDataEvent = new PanelEvent();
    /**
     * Size for the main panel
     */
    private Dimension sizeWindows = new Dimension(new Dimension(1280,710));

    /**
     * Constructor
     * @param managerDataChart represents the current manager for all chart
     */
    public GameBackground(ManagerDataChart managerDataChart){
        setPreferredSize(sizeWindows);
        setMinimumSize(sizeWindows);
        setMaximumSize(sizeWindows);
        setBackground(new Color(253,250,237));
        setBorder(BorderFactory.createEmptyBorder(0,0,0,0));

        mainPanel.setLayout(new OverlayLayout((mainPanel)));
        startPagePanel.setLayout(new MigLayout());
        centerPagePanel.setLayout(new MigLayout());
        containtPanel.setLayout(new BorderLayout());
        statisticPanel.setLayout(new BorderLayout());

        startPagePanel.setPreferredSize(new Dimension(1280,140));
        startPagePanel.setMinimumSize(new Dimension(1280,140));
        startPagePanel.setMaximumSize(new Dimension(1280,140));
        startPagePanel.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));

        centerPagePanel.setPreferredSize(new Dimension(1280, 510));
        centerPagePanel.setMaximumSize(new Dimension(1280, 510));
        centerPagePanel.setMinimumSize(new Dimension(1280, 510));
        centerPagePanel.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));

        // Opaque panel
        startPagePanel.setOpaque(false);
        centerPagePanel.setOpaque(false);
        containtPanel.setOpaque(false);
        weatherPanel.setOpaque(false);
        graphsPanel.setOpaque(false);
        eventPanel.setOpaque(false);
        statisticPanel.setOpaque(false);
        playerPanel.setOpaque(false);
        turnPanel.setOpaque(false);


        // Add Component
        panelGraphics(managerDataChart.getManagerChart());
        panelWeather(managerDataChart.getManagerWeather());
        statisticPanel.add(jtablePanel, BorderLayout.CENTER);
        turnPanel.add(panelTurnGame.getJPanelTurn());
        playerPanel.add(panelDataPlayer.getPanelPlayer());
        eventPanel.add(panelDataEvent.getPanelEvent());

        // Alignement Panel
        containtPanel.setAlignmentX(0.0f);
        containtPanel.setAlignmentY(0.0f);
        imagePanel.setAlignmentX(0.0f);
        imagePanel.setAlignmentY(0.0f);

        // JtabbedPanel parameter
        jTabbedPane.setFont(new Font("Bazar", Font.CENTER_BASELINE, 11));
        jTabbedPane.putClientProperty("Quaqua.ComponentVisualMargin", new Insets(3, -3, -4, -3));
        jTabbedPane.putClientProperty("Quaqua.TabbedPane.contentBorderPainted", Boolean.FALSE);

        jTabbedPane.addTab("EVENTS",eventPanel);
        jTabbedPane.addTab("STATISTICS", statisticPanel);
        jTabbedPane.addTab("CHART GRAPHICS",graphsPanel);

        jTabbedPane.setPreferredSize(new Dimension(840,430));
        jTabbedPane.setMaximumSize(new Dimension(840,430));
        jTabbedPane.setMinimumSize(new Dimension(840,430));

        for( int i = 0; i < jTabbedPane.getComponentCount(); i++)
        {
            jTabbedPane.setForegroundAt(i, Color.DARK_GRAY);
            jTabbedPane.setBackgroundAt(i, new Color(80,150,32));
        }

        // Assemble panel
        startPagePanel.add(turnPanel,"w 150,h 100, gapleft 750");
        startPagePanel.add(weatherPanel,"w 220,h 90, gapleft 100, gaptop 35");
        centerPagePanel.add(playerPanel,"w 360,h 410, gapleft 6,gaptop 20");
        centerPagePanel.add(jTabbedPane,"w 845, h 400, gapleft 30");

        containtPanel.add(startPagePanel,BorderLayout.PAGE_START);
        containtPanel.add(centerPagePanel,BorderLayout.CENTER);


        // Add to main panel
        mainPanel.add(containtPanel);
        mainPanel.add(imagePanel);

        add(mainPanel);

    }

    /**
     * This methods initialize the panel for player chart
     * @param managerChart represents the current manager for player chart
     */
    private void panelGraphics( ManagerChart managerChart){
        graphsPanel.setLayout(new GridLayout(2,2));

        RefineryUtilities.centerFrameOnScreen(managerChart.getBuildingChart());
        RefineryUtilities.centerFrameOnScreen(managerChart.getCashChart());
        RefineryUtilities.centerFrameOnScreen(managerChart.getProductionChart());
        RefineryUtilities.centerFrameOnScreen(managerChart.getRatioChart());

        Border raisedbevel = BorderFactory.createRaisedBevelBorder();
        Border loweredbevel = BorderFactory.createLoweredBevelBorder();

        managerChart.getBuildingChart().getJpanel().setBorder(BorderFactory.createCompoundBorder(raisedbevel, loweredbevel));
        managerChart.getCashChart().getChartPanel().setBorder(BorderFactory.createCompoundBorder(raisedbevel, loweredbevel));
        managerChart.getProductionChart().getChartPanel().setBorder(BorderFactory.createCompoundBorder(raisedbevel, loweredbevel));
        managerChart.getRatioChart().getChartPanel().setBorder(BorderFactory.createCompoundBorder(raisedbevel, loweredbevel));


        graphsPanel.add(managerChart.getProductionChart().getChartPanel());
        graphsPanel.add(managerChart.getBuildingChart().getJpanel());
        graphsPanel.add(managerChart.getRatioChart().getChartPanel());
        graphsPanel.add(managerChart.getCashChart().getChartPanel());
    }

    /**
     * This methods initialize the panel chart for weather
     * @param managerWeather represents the current manager for weather chart
     */
    private void panelWeather (ManagerWeather managerWeather){
        weatherPanel.setLayout(new BorderLayout());
        RefineryUtilities.centerFrameOnScreen(managerWeather.getWeatherChart());
        weatherPanel.add(managerWeather.getWeatherChart().getjPanel());
    }

    /**
     * This method allows to update player chart
     * @param managerDataChart represents the current manager for all chart
     * @param managerModel manager that store all data for the current game
     * @param light represents the percent of the sol
     * @param water represents the percent of the water
     * @param wind represents the percent of the wind
     */
    public void updateChartPanel(ManagerDataChart managerDataChart, ManagerModel managerModel, int light, int water, int wind){

        managerDataChart.getManagerChart().updateChart(managerModel.getMyPlayer());
        managerDataChart.getManagerWeather().updateChart(light,water,wind);
    }

    /**
     * This method allows to update table for statistic
     * @param playerDataHashMap hash map that store all player information
     */
    public void updateTablePanel(HashMap<Integer, PlayerData> playerDataHashMap) {
        jtablePanel.update(playerDataHashMap);
    }

    /**
     * This method allows to update turn panel
     * @param turn represents the current turn of the game
     */
    public void updateTurnPanel(int turn){
        panelTurnGame.updateTextArea(turn);
    }

    /**
     * This method alloxs to update player panel
     * @param p represent my player
     */
    public void updatePlayerPanel(PlayerData p){
        panelDataPlayer.updateDataArea(p);
    }

    /**
     * This method allows to update event panel
     * @param event represents all events on the current game
     * @param price represents the current price of the power
     * @param p represent all player presents int he current game
     * @param gameStateEnum represents the current state of the game
     * @param winPlayer represent the player that win this game
     */
    public void updateEventPanel(ArrayList<Event> event,float price, ArrayList<Player> p, GameStateEnum gameStateEnum, Object[] winPlayer){
        panelDataEvent.updateDataEvent(event, price, p, gameStateEnum, winPlayer);
    }

}
