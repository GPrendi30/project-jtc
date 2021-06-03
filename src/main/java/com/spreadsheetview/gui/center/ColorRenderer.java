package com.spreadsheetview.gui.center;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 * The color renderer.
 *
 * @author Di Pietro Enrico, Prendi Gerald.
 *
 */
class ColorRenderer extends DefaultTableCellRenderer {

    private final Color selectionColor = new Color(172, 225, 175);

    @Override
    public Component getTableCellRendererComponent(
            final JTable table,
            final Object value, final boolean isSelected,
            final boolean hasFocus,final int row,final int column) {


        super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

        if (isSelected) {
            setBackground(selectionColor);
        } else {
            setBackground(table.getBackground());
        }

        return this;
    }
}