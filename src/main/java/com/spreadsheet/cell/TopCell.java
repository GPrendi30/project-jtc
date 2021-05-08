package com.spreadsheet.cell;

public class TopCell extends Cell {

    public TopCell(final int x) {
        super(x,0);
    } 

    @Override
    public CellType getType() {
        return CellType.INVALID;
    }

    public String getText() {
        return getLocation().toString();
    }
}