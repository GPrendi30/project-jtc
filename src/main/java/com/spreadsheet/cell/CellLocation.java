package com.spreadsheet.cell;

public class CellLocation {

    private static final String ALPHA = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    private final String column;
    private final int row;


    public CellLocation(final int row, final int col) {
        this.column = col != 0
                ? CellLocation.ALPHA.substring(col - 1, col)
                : null;
        this.row = row;
    }

    public String getColumn() {
        return column;
    }

    public int getRow() {
        return row;
    }

    public String toString() {
        String s = "";
        if (column != null) {
            s += column;
        }
        if (row != 0) {
            s += row;
        }
        return s;
    }

}
