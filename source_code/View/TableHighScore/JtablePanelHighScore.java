/**
 * Created with IntelliJ IDEA.
 * User: Lauren
 * Date: 06/05/13
 * Time: 22:28
 * To change this template use File | Settings | File Templates.
 */
package GreenPlanet.View.TableHighScore;

import GreenPlanet.View.TableHighScore.Renderer.CellNameRendererHS;
import GreenPlanet.View.TableHighScore.Renderer.CellNumberRendererHS;
import GreenPlanet.View.TableHighScore.Renderer.TableHeaderRendererHighScore;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.util.ArrayList;

/**
 * Class to define the jtable panel high score
 */
public class JtablePanelHighScore extends JPanel {
    /**
     * ScollPane
     */
    private JScrollPane scrollPane;

    /**
     * Default Constructor
     */
    public JtablePanelHighScore() {
        setLayout(new BorderLayout());
        createTable();
        setOpaque(false);

    }

    /**
     * This method allows to create a new table
     * @return JtableModelHighScore represent a table to store the best player
     */
    public JtableModelHighScore createTable() {
        if (scrollPane != null) remove(scrollPane);

        JtableModelHighScore jtableModelHighScore = new JtableModelHighScore();
        JTable table = new JTable(jtableModelHighScore);
        table.setShowGrid(true);
        table.setBackground(new Color(253, 238, 195));
        table.getTableHeader().setReorderingAllowed(false);

        JTableHeader header = table.getTableHeader();
        header.setDefaultRenderer(new TableHeaderRendererHighScore(header.getDefaultRenderer()));

        for(int i=0;i<table.getColumnCount();++i){
            if (i == 0 || i == 2)
                table.getColumnModel().getColumn(i).setCellRenderer(new CellNameRendererHS());
            else
                table.getColumnModel().getColumn(i).setCellRenderer(new CellNumberRendererHS());


        }
        scrollPane = new JScrollPane(table);
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);

        add(scrollPane, BorderLayout.CENTER);
        revalidate();
        return jtableModelHighScore;

    }

    /**
     * This method allows to update table
     * @param player array list of object[]  that represents the list of best player
     */
    public void update(ArrayList<Object[]> player) {
        JtableModelHighScore jtableModelHighScore = createTable();
        int i = 1;
        for (Object[] obj : player) {
            int ranking = i;
            int idPlayer = (Integer)obj[1];
            String name = (String)obj[2];
            float cash = (Float)obj[3];
            int grennRank = (Integer)obj[4];
            int powerNeed = (Integer)obj[5];
            int nbTurn = (Integer)obj[6];
            jtableModelHighScore.addPlayer(ranking,idPlayer,name,cash,powerNeed,grennRank,nbTurn);
            ++i;
        }
    }

}
