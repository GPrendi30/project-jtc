package com.spreadsheet.table;

import com.spreadsheet.cell.Cell;
import com.spreadsheet.cell.CellLocation;
import com.spreadsheet.cell.LateralCell;
import com.spreadsheet.cell.TableCell;
import com.spreadsheet.cell.TopCell;

import java.util.HashMap;

public class Grid {

    private final HashMap<String, HashMap<Integer, Cell>> table;
    private int xDim;
    private int yDim;

    public Grid(final int x,final int y) {
        table = new HashMap<>();
        xDim = x;
        yDim = y;
        initGrid();
    }

    public int sizeX() {
        return xDim;
    }

    public int sizeY() {
        return yDim;
    }

    public void put(final Cell newCell) {
        final HashMap<Integer, Cell> currCol;
        final String column = newCell.getLocation().getColumn();
        final int row = newCell.getLocation().getRow();

        if (table.containsKey(column)) {
            // get columns from the table
            currCol = table.get(column);

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
            table.put(column, currCol);
        }
    }

    public Cell get(final int row,final int column) {
        final String col = new CellLocation(row, column).getColumn();
        return table.containsKey(col)
                ? table.get(col).get(row)
                : null;
    }

    public void growVertically(final int newSizeX) {
        for (int x = sizeX() + 1; x <= newSizeX; x++) {
            for (int y = 0; y <= sizeY(); y++) {
                final Cell c;
                if (y == 0) {
                    c = new TopCell(x);
                } else {
                    c = new TableCell(x,y);
                }
                put(c);
            }
        }
        xDim = newSizeX;
    }

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

    public void print() {
        for (int x = 0; x <= sizeX(); x++) {
            if (x == 0) {
                /* table upper border */
                System.out.println("_______________________________________"
                        +
                        "__________________________________________");
            }
            for (int y = 0; y <= sizeY(); y++) {
                // for y in col 0 and 1
                if (y == 0 || y == 1) {
                    System.out.print("|");
                }
                // printing the cell
                System.out.print(" " + get(x, y).getText() + " |");
            }
            System.out.println();

            // table down border
            if (x == sizeX()) {
                System.out.println("__________________________________________"
                        +
                        "_______________________________________");
            }

        }
    }
}