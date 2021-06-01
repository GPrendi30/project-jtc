package com.spreadsheetmodel.cell;

import com.computation.program.Program;
import com.computation.program.VariableTable;

import java.io.Serializable;

public class Cell implements Serializable {

    private final CellLocation location;
    private boolean selectable;
    protected CellType type;

    /**
     * Creates a new Cell object.
     * @param x the x coordinate
     * @param y the y coordinate
     */
    public Cell(final int x,final int y) {
        location = new CellLocation(x, y);
        selectable = false;
        type = CellType.INVALID;
    }

    /**
     * Return type of the cell.
     * @return CellType of the cell.
     */
    public CellType getType() {
        return type;
    }

    /**
     * Returns the content of the Cell.
     * @return content
     */
    public String getText() {
        // maybe change name to toString()
        return null;
    }

    /**
     * Returns the coordinates from a String location.
     * @param loc a String location of a Cell
     * @return the location in an int Array.
     */
    public static int[] parseLocation(final String loc) {
        return CellLocation.parse(loc);
    }

    /**
     * Makes the cell selectable.
     */
    public void makeSelectable() {
        selectable = true;
    }

    /**
     * Checks if the cell is selectable.
     * @return boolean
     */
    public boolean isSelectable() {
        return selectable;
    }

    /**
     * Updates content of a Cell.
     * @param content the new Content of a cell
     */
    public void updateContent(final String content) {
        // to be overridden.
    }

    /**
     * Removes content of a cell.
     * Sets it to null.
     */
    public void remove() {
        // to be overridden
    }

    /**
     * Evaluates the content of a Cell and stores them back in the cell.
     * @param pr A Program
     * @param vt A VariableTable
     */
    public void evaluate(final Program pr,final VariableTable vt) {
        // to be overridden
    }

    /**
     * Returns the CellLocation of the Cell.
     * @return CellLocation of this Cell.
     */
    public CellLocation getLocation() {
        return this.location;
    }

}
