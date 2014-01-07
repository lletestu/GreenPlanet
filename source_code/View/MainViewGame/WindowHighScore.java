/**
 * @author Jérémy
 */
package GreenPlanet.View.MainViewGame;

import GreenPlanet.Model.ManagerBD;
import GreenPlanet.View.TableHighScore.JtablePanelHighScore;
import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.*;
import net.miginfocom.swing.MigLayout;

/**
 * Class to define the view for high score
 */
public class WindowHighScore implements ActionListener {
    /**
     * Font image panel
     */
    private FontImagePanel fontPanel;
    /**
     * Frame
     */
    private JFrame myFrame;
    /**
     * Main panel
     */
    private JPanel jPanel = new JPanel(new MigLayout());
    /**
     * Panel to store data
     */
    private JPanel panelData = new JPanel(new BorderLayout());

    /**
     * Table to display all best player
     */
    private JtablePanelHighScore jtablePanelHighScore = new JtablePanelHighScore();

    /**
     * Array list of object[] to determine the best player
     */
    private ArrayList<Object[]> dataPlayerWin;
    /**
     * Manager databse sql
     */
    private ManagerBD managerBD;
    /**
     * Boolean to check if the user is connected or not to database
     */
    boolean connected = false;

    /**
     * Font
     */
    private Font fontData = new Font("Bazar", Font.BOLD, 22);
    /**
     * Color font
     */
    private Color colorData = new Color(253,238,195);

    /**
     * Constructor
     * @param myFrame represents the current frame
     */
    public WindowHighScore(WindowSPLASH myFrame) {
        this.myFrame = myFrame;
        this.managerBD = myFrame.getGreenPlanetController().getManagerBD();
        this.connected = myFrame.getGreenPlanetController().isBdActivate();
    }

    /**
     * Override action performed
     * @param ae represents the action event
     */
    @Override
    public void actionPerformed(ActionEvent ae) {
        myFrame.getContentPane().removeAll();
        fontPanel = new FontImagePanel("highScoreBacground.jpg");
        myFrame.setContentPane(fontPanel);

        initializePanelHighScore();

        myFrame.add(jPanel);
        myFrame.validate();
    }

    /**
     * This method allows to initialize panel of the high score
     */
    public void initializePanelHighScore() {
        jPanel.setBackground(new Color(253,238,195,4));

        panelData.setOpaque(false);
        panelData.setPreferredSize(new Dimension(760,198));
        panelData.setMinimumSize(new Dimension(760,198));
        panelData.setMaximumSize(new Dimension(760,198));
        panelData.add(jtablePanelHighScore, BorderLayout.CENTER);

        processRequest();

        jPanel.add(panelData, "gaptop 270");
    }

    /**
     * This method update panel data if the user is not connected to database
     */
    public void notConnectedtoBD() {
        JTextArea jtextArea = new JTextArea();
        jtextArea.setFont(fontData);
        jtextArea.setForeground(colorData);
        jtextArea.setOpaque(false);

        jtextArea.setText("NOT CONNECTED TO DATABASE");
        jtextArea.setEditable(false);
        panelData.removeAll();
        panelData.add(jtextArea,BorderLayout.CENTER);


    }

    /**
     * This method update panel data if the user is connected to database
     */
    public void connectedToBD() {
        jtablePanelHighScore.update(dataPlayerWin);
        panelData.add(jtablePanelHighScore,BorderLayout.CENTER);

    }

    /**
     * Process request to retrieve data from the database
     */
    public void processRequest() {
        try {
            managerBD.executeRequest();
            ResultSet resultSet = managerBD.getRset();
            dataPlayerWin = new ArrayList<Object[]>();

            while (resultSet.next()){
                Object[] tmp = new Object[7];
                tmp[0] = resultSet.getInt("GameId");
                tmp[1] = resultSet.getInt("IdPlayer");
                tmp[2] = resultSet.getString("NamePlayer");
                tmp[3] = resultSet.getFloat("Cash");
                tmp[4] = resultSet.getInt("GreenRank");
                tmp[5] = resultSet.getInt("PowerNeed");
                tmp[6] = resultSet.getInt("NBTurn");

                dataPlayerWin.add(tmp);
            }

            connectedToBD();

        } catch (Exception e) {

            notConnectedtoBD();
        }
    }


}
