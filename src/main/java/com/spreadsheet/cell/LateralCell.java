package com.spreadsheet.cell;


public class LateralCell extends Cell {

    /**
     * Creates a new LateralCell.
     * @param y the y coordinates.
     */
    public LateralCell(final int y) {
        super(0, y);
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