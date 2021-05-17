package com.spreadsheetmodel.cell;

public class TopCell extends Cell {

    /**
     * Creates a new TopCell.
     * @param x the x coordinates.
     */
    public TopCell(final int x) {
        super(x,0);
    } 

    @Override
    public CellType getType() {
        return CellType.INVALID;
    }

    @Override
    public String getText() {
        return getLocation().toString();
    }
}