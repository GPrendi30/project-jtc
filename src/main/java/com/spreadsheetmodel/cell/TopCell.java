package com.spreadsheetmodel.cell;

/**
 * A TopCell is a Cell that helps the user to understand
 * which Cells is interacting with. It only contains the
 * x coordinate (because the y coordinate is always 0).
 * Is not selectable nor modifiable.
 *
 * @author Di Pietro Enrico, Prendi Gerald.
 *
 */
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