package com.spreadsheetview.gui.center;

import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

/**
 * The custom table.
 *
 * @author Di Pietro Enrico, Prendi Gerald.
 *
 */
public class CustomTable extends JTable {

    @Override
    public TableCellRenderer getCellRenderer(final int row, final int column) {
        if (isRowSelected(row) && column == 0) {
            return new ColumnSelector();
        }
        if (isRowSelected(row) && isColumnSelected(column)) {
            return new ColorRenderer();
        }
        if (column == 0) {
            return getTableHeader().getDefaultRenderer();
        }
        return super.getCellRenderer(row, column);
    }

    @Override
    public boolean isCellEditable(final int rowindex, final int colindex) {

        return colindex != 0;
    }

}

