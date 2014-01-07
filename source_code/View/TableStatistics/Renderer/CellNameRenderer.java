/**
 * Created with IntelliJ IDEA.
 * User: Lauren
 * Date: 02/05/13
 * Time: 21:57
 * To change this template use File | Settings | File Templates.
 */
package GreenPlanet.View.TableStatistics.Renderer;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

/**
 * Class to custom cell header
 */
public class CellNameRenderer extends DefaultTableCellRenderer {
    /**
     * Font
     */
    //private Font font = new Font("Arial", Font.PLAIN, 11); // for Mac
    private Font font = new Font("Arial", Font.PLAIN, 11); // for Windows
    /**
     * Color
     */
    private Color colorData = new Color(66, 67, 69);

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
        super.getTableCellRendererComponent(table, value, isSelected, hasFocus,
                row, column);
        setFont(font);
        setForeground(colorData);
        setHorizontalAlignment(SwingConstants.CENTER);
        setOpaque(false);
        return this;
    }
}
