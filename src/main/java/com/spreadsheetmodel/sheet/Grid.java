package com.spreadsheetmodel.sheet;

import com.spreadsheetmodel.cell.Cell;
import com.spreadsheetmodel.cell.CellLocation;
import com.spreadsheetmodel.cell.LateralCell;
import com.spreadsheetmodel.cell.TableCell;
import com.spreadsheetmodel.cell.TopCell;

import java.io.Serializable;
import java.util.HashMap;

/**
 * The Grid where the Cells are contained.
 *
 * @author Di Pietro Enrico, Prendi Gerald.
 *
 */
public class Grid implements Serializable {
    private final HashMap<String, HashMap<Integer, Cell>> matrix;
    private int xDim;
    private int yDim;

    /**
     * Creates a new Grid with x, y size.
     * @param x the horizontal size.
     * @param y the vertical size.
     */
    public Grid(final int x,final int y) {
        matrix = new HashMap<>();
        xDim = x;
        yDim = y;
        initGrid();
    }

    /**
     * Returns the size of X dimension.
     * @return xDim an int.
     */
    public int sizeX() {
        return xDim;
    }

    /**
     * Returns the size of Y dimension.
     * @return yDim an int.
     */
    public int sizeY() {
        return yDim;
    }

    /**
     * Puts a Cell in the table.
     * @param newCell a Cell that you want to put in the table.
     */
    public void put(final Cell newCell) {
        final HashMap<Integer, Cell> currCol;
        final String column = newCell.getLocation().getColumn();
        final int row = newCell.getLocation().getRow();

        if (matrix.containsKey(column)) {
            // get columns from the table
            currCol = matrix.get(column);

            // if key already exists in the columns
            if (currCol.containsKey(row)) {
                // overwrite content
                currCol.get(row).updateContent(newCell.getText());
            } else {
                // put a new Cell;
                currCol.put(row, newCell);
            }

        } else {
            // if table doesn't contain the row
            currCol = new HashMap<>();
            currCol.put(row, newCell);
            matrix.put(column, currCol);
        }
    }

    /**
     * Get a cell from given coordinates.
     * @param row the x coordinate.
     * @param column the y coordinate.
     * @return the Cell at x,y coordinates.
     */
    public Cell get(final int row,final int column) {
        final String col = CellLocation.getColumnFromInt(column);
        return matrix.containsKey(col)
                ? matrix.get(col).get(row)
                : null;
    }

    /**
     * Grows the grid vertically.
     * @param newSizeX the new Size of x.
     */
    public void growVertically(final int newSizeX) {
        for (int x = sizeX() + 1; x <= newSizeX; x++) {
            for (int y = 0; y <= sizeY(); y++) {
                final Cell c;
                if (y == 0) {
                    c = new TopCell(x);
                } else {
                    c = new TableCell(x,y,"");
                }
                put(c);
            }
        }
        xDim = newSizeX;
    }

    /**
     * Grows the grid horizontally.
     * @param newSizeY the new Size of y.
     */
    public void growHorizontally(final int newSizeY) {
        for (int x = 0; x <= sizeX(); x++) {
            for (int y = sizeY() + 1; y <= newSizeY; y++) {
                final Cell c;
                if (x == 0) {
                    c = new LateralCell(y);
                } else {
                    c = new TableCell(x, y);
                }
                put(c);
            }
        }
        yDim = newSizeY;
    }

    /**
     * Initializes the grid, the borders with TopCell and LateralCells,
     * and the others with empty TableCells.
     */
    private void initGrid() {
        for (int x = 0; x <= sizeX(); x++) {
            for (int y = 0; y <= sizeY(); y++) {
                final Cell c;
                if (x == 0) {
                    c = new LateralCell(y);
                } else if (y == 0) {
                    c = new TopCell(x);
                } else {
                    c = new TableCell(x,y);
                }
                put(c);
            }

        }
    }

    /**
     * Creates an array containing the first row content (the columns header).
     * @return String[] the array with the first row content 
     */
    public String[] getColumns() {
        return matrix.keySet().toArray(new String[sizeY()]);
    }

}