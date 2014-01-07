/**
 * Created with IntelliJ IDEA.
 * User: Lauren
 * Date: 06/05/13
 * Time: 23:40
 * To change this template use File | Settings | File Templates.
 */
package GreenPlanet.View.TableHighScore.Renderer;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

/**
 * Class to custom header table
 */
public class TableHeaderRendererHighScore implements TableCellRenderer{
    /**
     * Font
     */
    private static final Font labelFont = new Font("Bazar", Font.PLAIN, 15);
    /**
     * Table cell renderer
     */
    private TableCellRenderer delegate;

    /**
     * Constructor
     * @param delegate table cell renderer
     */
    public TableHeaderRendererHighScore(TableCellRenderer delegate){
        this.delegate = delegate;
    }

    /**
     * Override This method is used to configure the renderer appropriately before drawing
     * @param table the JTable that is asking the renderer to draw
     * @param value the value of the cell to be rendered
     * @param isSelected true if the cell is to be rendered with the selection highlighted
     * @param hasFocus if true, render cell appropriately
     * @param row  the row index of the cell being draw
     * @param column  the column index of the cell being drawn
     * @return the component used for drawing the cell
     */
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        Component c = delegate.getTableCellRendererComponent(table,value,isSelected,hasFocus,row,column);

        if(c instanceof JLabel){
            JLabel label = (JLabel)(c);
            label.setFont(labelFont);
            label.setHorizontalAlignment(SwingConstants.CENTER);
            label.setBorder(BorderFactory.createEtchedBorder());
            label.setBackground(new Color(80,150,32));
            label.setForeground(new Color(66,67,69));
        }
        return c;
    }
}
