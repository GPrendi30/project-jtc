package com.spreadsheetmodel.cell;

/**
 * A LateralCell is a Cell that helps the user to understand
 * which Cells is interacting with. It only contains the
 * y coordinate (because the x coordinate is always 0).
 * Is not selectable nor modifiable.
 *
 * @author Di Pietro Enrico, Prendi Gerald.
 *
 */
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