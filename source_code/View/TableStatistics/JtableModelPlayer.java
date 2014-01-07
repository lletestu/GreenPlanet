/**
 * Created with IntelliJ IDEA.
 * User: Lauren
 * Date: 02/05/13
 * Time: 17:41
 * To change this template use File | Settings | File Templates.
 */
package GreenPlanet.View.TableStatistics;

import GreenPlanet.Model.Player.PlayerData;
import greenplanetclient.PlayerStateEnum;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

/**
 * Class to define a table
 */
public class JtableModelPlayer extends AbstractTableModel {
    /**
     * Array list of object to contains colums of table
     */
    private ArrayList<Object[]> objectsList = new ArrayList<Object[]>();
    /**
     * Array string that represents all items of this table
     */
    private String[] header = {"STATE","NAME","GRK","CASH","POWER NEED","RATIO","PRODUCTION","NUCLEAR","COAL","SOLAR","WATER",
            "WIND"};

    /**
     * This is used by the JTable to set up a default renderer and editor for the column
     * @param columnIndex he index of the column
     * @return the most specific superclass for all the cell values in the column
     */
    @Override
    public Class<?> getColumnClass(int columnIndex){
        switch (columnIndex){
            case 0:
                return PlayerStateEnum.class;
            case 1:
                return String.class;
            case 2:
                return int.class;
            case 3:
                return float.class;
            case 4:
                return float.class;
            case 5:
                return int.class;
            case 6:
                return int.class;
            case 7:
                return int.class;
            case 8:
                return int.class;
            case 9:
                return int.class;
            case 10:
                return int.class;
            case 11:
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
     * @param p represents the current player
     */
    public void addPlayer(PlayerData p){
        Object[] obj = new Object[header.length];

        obj[0]=p.getState();
        obj[1]=p.getName();
        obj[2]=p.getGreenRank();
        obj[3]=p.getCash();
        obj[4] = p.getPowerNeed();
        obj[5]=p.getSatisfaction();
        obj[6]=p.getBuildingsPlayer().getProductionActual();
        obj[7]=p.getBuildingsPlayer().getBuildingActual().getNbNuclear();
        obj[8]=p.getBuildingsPlayer().getBuildingActual().getNbCoalPlant();
        obj[9]=p.getBuildingsPlayer().getBuildingActual().getNbSolarPlant();
        obj[10]=p.getBuildingsPlayer().getBuildingActual().getNbWaterTurbine();
        obj[11]=p.getBuildingsPlayer().getBuildingActual().getNbWindTurbine();


        objectsList.add(obj);
        fireTableDataChanged();

    }
}
