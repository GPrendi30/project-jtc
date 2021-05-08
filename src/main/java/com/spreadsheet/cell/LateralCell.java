package com.spreadsheet.cell;


public class LateralCell extends Cell {

    public LateralCell(final int y) {
        super(0, y);
    } 

    @Override
    public CellType getType() {
        return CellType.INVALID;
    }

    public String getText() {
        return getLocation().toString();
    }
}