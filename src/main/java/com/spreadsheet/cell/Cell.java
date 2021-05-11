package com.spreadsheet.cell;

import com.computation.program.Program;
import com.computation.program.VariableTable;

public class Cell {

    private final CellLocation location;
    private boolean selectable;

    public Cell(final int x,final int y) {
        location = new CellLocation(x, y);
        selectable = false;
    }

    public CellType getType() {
        return CellType.INVALID;
    }

    public String getText() {
        // maybe change name to toString()
        return null;
    }

    public static int[] parseLocation(String loc) {
        return CellLocation.parse(loc);
    }

    public void makeSelectable() {
        selectable = true;
    }

    public boolean isSelectable() {
        return selectable;
    }

    public void updateContent(final String content) {
        // to be overridden.
    }


    public void evaluate(final Program pr,final VariableTable vt) {
        return;
    }


    public CellLocation getLocation() {
        return this.location;
    }

}
