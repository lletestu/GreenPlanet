/**
 * Created with IntelliJ IDEA.
 * User: Lauren
 * Date: 06/05/13
 * Time: 22:28
 * To change this template use File | Settings | File Templates.
 */
package GreenPlanet.View.TableHighScore;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

/**
 * Class to define a table
 */
public class JtableModelHighScore extends AbstractTableModel{

    /**
     * Array list of object to contains colums of table
     */
    private ArrayList<Object[]> objectsList = new ArrayList<Object[]>();
    /**
     * Array string that represents all items of this table
     */
    private String[] header = {"RANKING","ID PLAYER", "NAME PLAYER", "CASH" , "POWER NEED" , "GREEN RANK" , "NB TURN"};

    /**
     * This is used by the JTable to set up a default renderer and editor for the column
     * @param columnIndex he index of the column
     * @return the most specific superclass for all the cell values in the column
     */
    @Override
    public Class<?> getColumnClass(int columnIndex){
        switch (columnIndex){
            case 0:
                return int.class;
            case 1:
                return int.class;
            case 2:
                return String.class;
            case 3:
                return float.class;
            case 4:
                return int.class;
            case 5:
                return int.class;
            case 6:
                return int.class;

        }
        return String.class;
    }

    /**
     * A JTable uses this method to determine how many rows it should display
     * * @return the number of rows in the model
     */
    @Override
    public int getRowCount() {
        return objectsList.size();
    }

    /**
     * A JTable uses this method to determine how many columns it should create and display by default
     * @return the number of columns in the model
     */
    @Override
    public int getColumnCount() {
        return header.length;
    }

    /**
     *  Returns the value for the cell at columnIndex and rowIndex
     * @param rowIndex  the row whose value is to be queried
     * @param columnIndex the column whose value is to be queried
     * @return the value Object at the specified cell
     */
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return objectsList.get(rowIndex)[columnIndex];
    }

    /**
     * Returns true if the cell at rowIndex and columnIndex is editable
     * @param rowIndex the row whose value to be queried
     * @param columnIndex the column whose value to be queried
     * @return true if the cell is editable
     */
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {

        return false;
    }

    /**
     * Returns the name of the column at columnIndex
     * @param column the index of the column
     * @return the name of the column
     */
    @Override
    public String getColumnName(int column) {
        return header[column];
    }

    /**
     * This method is used to add a new player in the table
     * @param ranking represents its ranking
     * @param idPlayer represents the id player
     * @param name represents the name player
     * @param cash represents its cash
     * @param powerNeed represents its power need
     * @param grennRank represents its green rank
     * @param nbTurn represents the number of turn achieved by this player
     */
    public void addPlayer(int ranking, int idPlayer, String name, float cash, int powerNeed, int grennRank, int nbTurn){
        Object[] obj = new Object[header.length];

        obj[0] = ranking;
        obj[1] = idPlayer;
        obj[2] = name;
        obj[3] = cash;
        obj[4] = powerNeed;
        obj[5] = grennRank;
        obj[6] = nbTurn;

        objectsList.add(obj);
        fireTableDataChanged();

    }
}
