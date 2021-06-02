package com.spreadsheetview.gui.center;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

class ColorRenderer extends DefaultTableCellRenderer {

    @Override
    public Component getTableCellRendererComponent(
            final JTable table,
            final Object value, final boolean isSelected,
            final boolean hasFocus,final int row,final int column) {
        super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

        if (isSelected) {
            setBackground(new Color(172, 225, 175));
        } else {
            setBackground(table.getBackground());
        }

        return this;
    }
}