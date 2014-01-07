/**
 * Created with IntelliJ IDEA.
 * User: Lauren
 * Date: 02/05/13
 * Time: 18:05
 * To change this template use File | Settings | File Templates.
 */
package GreenPlanet.View.TableStatistics;

import GreenPlanet.Model.Player.PlayerData;
import GreenPlanet.View.TableStatistics.Renderer.CellNameRenderer;
import GreenPlanet.View.TableStatistics.Renderer.CellNumberRenderer;
import GreenPlanet.View.TableStatistics.Renderer.TableHeaderRenderer;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.util.HashMap;

/**
 * Class to define the jtable panel statistics
 */
public class JtablePanel extends JPanel {
    /**
     * Scoll pane
     */
    private JScrollPane scrollPane;

    /**
     * Default constructor
     */
    public JtablePanel() {
        setLayout(new BorderLayout());
        createTable();
        setOpaque(false);

    }

    /**
     * This method allows to create a new table
     * @return JtableModel represent a table to store all player presents on the current game
     */
    public JtableModelPlayer createTable() {
        if (scrollPane != null) remove(scrollPane);

        JtableModelPlayer jtableModelPlayer = new JtableModelPlayer();
        JTable table = new JTable(jtableModelPlayer);
        table.getTableHeader().setReorderingAllowed(false);
        table.setShowGrid(true);
        table.setOpaque(false);

        JTableHeader header = table.getTableHeader();
        header.setDefaultRenderer(new TableHeaderRenderer(header.getDefaultRenderer()));

        table.getColumnModel().getColumn(0).setCellRenderer(new CellNameRenderer());
        table.getColumnModel().getColumn(1).setCellRenderer(new CellNameRenderer());

        for(int i=2;i<table.getColumnCount();++i){
            table.getColumnModel().getColumn(i).setCellRenderer(new CellNumberRenderer());
        }
        scrollPane = new JScrollPane(table);
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);

        add(scrollPane, BorderLayout.CENTER);
        revalidate();
        return jtableModelPlayer;

    }

    /**
     * This method allows to update table
     * @param player array list of player data
     */
    public void update(HashMap<Integer, PlayerData> player) {

        JtableModelPlayer jtableModelPlayer = createTable();
        for (Integer mapKey : player.keySet()) {
            jtableModelPlayer.addPlayer(player.get(mapKey));
        }
    }

}
