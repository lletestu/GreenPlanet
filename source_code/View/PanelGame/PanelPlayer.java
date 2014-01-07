/**
 * Created with IntelliJ IDEA.
 * User: Lauren
 * Date: 04/05/13
 * Time: 14:18
 * To change this template use File | Settings | File Templates.
 */
package GreenPlanet.View.PanelGame;

import GreenPlanet.Model.Player.PlayerData;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;

/**
 * Class allows to define the panel player
 */
public class PanelPlayer {

    /**
     * Main panel
     */
    private JPanel panelPlayer;
    /**
     * Header panel
     */
    private JPanel headerPanel;
    /**
     * Data panel
     */
    private JPanel dataPanel;

    /**
     * Text area to display the name of the player
     */
    private JTextArea name = new JTextArea();
    /**
     * Text area to display the state of the player
     */
    private JTextArea state = new JTextArea();
    /**
     * Text area to display the cash of the player
     */
    private JTextArea cash = new JTextArea();
    /**
     * Text area to display the production of the player
     */
    private JTextArea production = new JTextArea();
    /**
     * Text area to display the ratio of the player
     */
    private JTextArea ratio = new JTextArea();
    /**
     * Text area to display the green rank of the player
     */
    private JTextArea grk = new JTextArea();
    /**
     * Text area to display the powerNeed of the player
     */
    private JTextArea powerNeed = new JTextArea();

    /**
     * Text area to display the name header
     */
    private JTextArea nameH = new JTextArea();
    /**
     * Text area to display the state header
     */
    private JTextArea stateH = new JTextArea();
    /**
     * Text area to display the cash header
     */
    private JTextArea cashH = new JTextArea();
    /**
     * Text area to display the production header
     */
    private JTextArea productionH = new JTextArea();
    /**
     * Text area to display the ratio header
     */
    private JTextArea ratioH = new JTextArea();
    /**
     * Text area to display the green rank header
     */
    private JTextArea grkH = new JTextArea();
    /**
     * Text area to display the powerNeed header
     */
    private JTextArea powerNeedH= new JTextArea();

    /**
     * Represents the width of panel
     */
    private final int WIDTH = 330;
    /**
     * Represents the height of panel
     */
    private final int HEIGHT = 400;

    /**
     * Font header
     */
    private Font fontHeader = new Font("Bazar",Font.BOLD,24);
    /**
     * Font data
     */
    //private Font fontData = new Font("Bazar",Font.PLAIN,24); // for MAc
    private Font fontData = new Font("Arial",Font.PLAIN,24); // for Windows
    /**
     * Color header
     */
    private Color colorHeader = new Color(80,150,32);
    /**
     * Color data
     */
    private Color colorData = new Color(66,67,69);

    /**
     * Constructor
     */
    public PanelPlayer(){
        panelPlayer = new JPanel(new GridLayout(1,2));
        panelPlayer.setSize(new Dimension(WIDTH,HEIGHT));
        panelPlayer.setOpaque(false);

        headerPanel = new JPanel(new MigLayout());
        headerPanel.setSize(100,HEIGHT);
        headerPanel.setOpaque(false);
        headerPanel.add(nameH,"wrap, gaptop 34");
        headerPanel.add(stateH,"wrap, gaptop 22");
        headerPanel.add(grkH,"wrap, gaptop 22");
        headerPanel.add(cashH,"wrap, gaptop 22");
        headerPanel.add(productionH,"wrap, gaptop 22");
        headerPanel.add(powerNeedH,"wrap, gaptop 22");
        headerPanel.add(ratioH,"wrap, gaptop 22");

        dataPanel = new JPanel(new MigLayout());
        dataPanel.setSize(230,HEIGHT);
        dataPanel.setOpaque(false);
        dataPanel.add(name,"wrap, gaptop 34");
        dataPanel.add(state,"wrap, gaptop 22");
        dataPanel.add(grk,"wrap, gaptop 22");
        dataPanel.add(cash,"wrap, gaptop 22");
        dataPanel.add(production,"wrap, gaptop 22");
        dataPanel.add(powerNeed,"wrap, gaptop 22");
        dataPanel.add(ratio,"wrap, gaptop 22");

        initializeHeaderTextArea();
        initializeDataTextArea();

        panelPlayer.add(headerPanel);
        panelPlayer.add(dataPanel);

    }

    /**
     * This method allows to initialize header
     */
    public void initializeHeaderTextArea(){
        nameH.setFont(fontHeader);
        nameH.setForeground(colorHeader);
        nameH.setOpaque(false);
        nameH.setText("NAME  ");
        nameH.setEditable(false);

        stateH.setFont(fontHeader);
        stateH.setForeground(colorHeader);
        stateH.setOpaque(false);
        stateH.setText("STATE  ");
        stateH.setEditable(false);

        grkH.setFont(fontHeader);
        grkH.setForeground(colorHeader);
        grkH.setOpaque(false);
        grkH.setText("GREEN RANK  ");
        grkH.setEditable(false);

        cashH.setFont(fontHeader);
        cashH.setForeground(colorHeader);
        cashH.setOpaque(false);
        cashH.setText("CASH  ");
        cashH.setEditable(false);

        productionH.setFont(fontHeader);
        productionH.setForeground(colorHeader);
        productionH.setOpaque(false);
        productionH.setText("PRODUCTION  ");
        productionH.setEditable(false);

        powerNeedH.setFont(fontHeader);
        powerNeedH.setForeground(colorHeader);
        powerNeedH.setOpaque(false);
        powerNeedH.setText("POWER NEED  ");
        powerNeedH.setEditable(false);

        ratioH.setFont(fontHeader);
        ratioH.setForeground(colorHeader);
        ratioH.setOpaque(false);
        ratioH.setText("RATIO  ");
        ratioH.setEditable(false);
    }

    /**
     * This method allows to initialize data
     */
    public void initializeDataTextArea(){
        name.setFont(fontData);
        name.setForeground(colorData);
        name.setOpaque(false);
        name.setEditable(false);

        state.setFont(fontData);
        state.setForeground(colorData);
        state.setOpaque(false);
        state.setEditable(false);

        grk.setFont(fontData);
        grk.setForeground(colorData);
        grk.setOpaque(false);
        grk.setEditable(false);

        cash.setFont(fontData);
        cash.setForeground(colorData);
        cash.setOpaque(false);
        cash.setEditable(false);

        production.setFont(fontData);
        production.setForeground(colorData);
        production.setOpaque(false);
        production.setEditable(false);

        powerNeed.setFont(fontData);
        powerNeed.setForeground(colorData);
        powerNeed.setOpaque(false);
        powerNeed.setEditable(false);

        ratio.setFont(fontData);
        ratio.setForeground(colorData);
        ratio.setOpaque(false);
        ratio.setEditable(false);

    }

    /**
     * This method allow to update data for player
     * @param p represents my player
     */
    public void updateDataArea(PlayerData p){
        name.setText(p.getName());
        state.setText(p.getState().toString());
        grk.setText(String.valueOf(p.getGreenRank()));
        cash.setText(String.valueOf(p.getCash())+" $");
        production.setText(String.valueOf(p.getBuildingsPlayer().getProductionActual()));
        powerNeed.setText(String.valueOf(p.getPowerNeed()));
        ratio.setText(String.valueOf(p.getSatisfaction()));
    }

    /**
     * Get the main panel
     * @return the current panel player
     */
    public JPanel getPanelPlayer() {
        return panelPlayer;
    }
}
