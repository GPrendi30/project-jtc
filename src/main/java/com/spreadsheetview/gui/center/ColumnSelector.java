package com.spreadsheetview.gui.center;

import java.awt.Color;
import java.awt.Component;
import javax.swing.BorderFactory;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 * The column selector.
 *
 * @author Di Pietro Enrico, Prendi Gerald.
 *
 */
public class ColumnSelector extends DefaultTableCellRenderer {

    @Override
    public Component getTableCellRendererComponent(
            final JTable table, final Object value, final boolean isSelected,
            final boolean hasFocus, final int row, final int column) {
        super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

        if (isSelected) {
            setBackground(new Color(60, 179, 113));
        }
        setBorder(BorderFactory.createLineBorder(new Color(60, 179, 113)));


        return this;
    }
}