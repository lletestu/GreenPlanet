/**
 * Created with IntelliJ IDEA.
 * User: Lauren
 * Date: 07/05/13
 * Time: 00:08
 * To change this template use File | Settings | File Templates.
 */
package GreenPlanet.View.TableHighScore.Renderer;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

/**
 * Class to custom cell header
 */
public class CellNumberRendererHS extends DefaultTableCellRenderer {
    /**
     * Font
     */
    private Font font = new Font("Arial",Font.PLAIN,15);
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
        setHorizontalAlignment(SwingConstants.CENTER);
        setForeground(colorData);
        setOpaque(false);
        return this;
    }
}
