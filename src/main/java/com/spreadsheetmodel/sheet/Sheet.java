package com.spreadsheetmodel.sheet;

import static java.util.Collections.reverse;

import com.computation.program.Program;
import com.computation.program.VariableTable;
import com.spreadsheetmodel.SpreadsheetException;
import com.spreadsheetmodel.cell.Cell;
import com.spreadsheetmodel.cell.CellLocation;
import com.spreadsheetmodel.cell.CellType;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

/**
 * The Sheet that contains the cells.
 *
 * @author Di Pietro Enrico, Prendi Gerald.
 *
 */
public class Sheet implements Serializable {
    private static final Program PROGRAM = new Program();

    private String tableName;
    private final Grid table;
    private final HashMap<CellLocation, String> formulas;
    private final VariableTable variableTable;


    /**
     * Creates a new Sheet with given dimensions with a random name.
     * @param x the x dimension.
     * @param y the y dimension.
     */
    public Sheet(final int x, final int y) {
        tableName = "Table";
        table = new Grid(x, y);
        formulas = new HashMap<>();
        variableTable = new VariableTable();
    }

    /**
     * Creates a new Sheet with given dimensions with a given name.
     * @param x the x dimension.
     * @param y the y dimension.
     * @param tableName the name of the Sheet.
     */
    public Sheet(final int x, final int y, final String tableName) {
        this.tableName = tableName;
        table = new Grid(x, y);
        formulas = new HashMap<>();
        variableTable = new VariableTable();
    }

    /**
     * Returns the x dimension.
     * @return the size of the x dimension.
     */
    public int sizeX() {
        return table.sizeX();
    }

    /**
     * Returns the y dimension.
     * @return the size of the y dimension.
     */
    public int sizeY() {
        return table.sizeY();
    }


    /**
     * Gets the name of the current table.
     * @return String, name of the table.
     */
    public String getTableName() {
        return tableName;
    }

    /**
     * Add a Cell's Content to the variableTable.
     * @param c a Cell.
     */
    public void addToVariableTable(final Cell c) {
        if (c.getType() == CellType.NUMBER) {
            variableTable.iset(c.getLocation().toString(), Integer.parseInt(c.getText()));
        }
        if (c.getType() == CellType.INVALID) {
            variableTable.remove(c.getLocation().toString());
        }
    }

    /**
     * Dynamically grows the array, to a direction and a given size.
     * @param direction a String direction.
     * @param size  the new size of the table.
     * @throws SpreadsheetException throws an exception if the spreadsheet can't grow the sheet.
     */
    public void grow(final String direction,final int size) throws SpreadsheetException {
        if ("Vertically".equals(direction)) {
            if (size < sizeX()) {
                throw new SpreadsheetException("Sheet of size "
                        + sizeX() + "can't be resized to " + size);
            }
            table.growVertically(size);
        } else if ("Horizontally".equals(direction)) {
            if (size < sizeY()) {
                throw new SpreadsheetException("Sheet of size "
                        + sizeY() + "can't be resized to " + size);
            }
            table.growHorizontally(size);
        } else {
            throw new SpreadsheetException("Unrecognized direction to grow the table,"
                    + "expected Horizontally or Vertically, got" + direction);
        }
    }

    /**
     * Gets the VariableTable.
     * @return the variable table.
     */
    public VariableTable getVariableTable() {
        return this.variableTable;
    }

    /**
     * Update the name of the table.
     * @param newName the new String name of the table.
     */
    public void updateTableName(final String newName) {
        tableName = newName;
    }

    /**
     * Add a new Cell in the table.
     * @param c a Cell.
     */
    public void add(final Cell c) {
        addFormula(c);
        if (c.getType() == CellType.NUMBER) {
            c.evaluate(PROGRAM, variableTable);
        }
        table.put(c);
        addToVariableTable(c);
    }

    /**
     * Update content of a cell at a given location.
     * @param x the x coordinate.
     * @param y the y coordinate.
     * @param content the content.
     * @throws SpreadsheetException if x or y are outside of the dimensions.
     */
    public void update(final int x,final int y,final String content) throws SpreadsheetException {
        final Cell c = getCell(x,y);
        updateCell(c, content);
    }

    /**
     * Fills formulas in the spreadsheet.
     * @throws SpreadsheetException if it can't get the formulas.
     */
    public void fillFormulas() throws SpreadsheetException {
        for (final CellLocation c : formulas.keySet()) {
            getCell(c.toString()).updateContent(getFormula(c));
        }
    }


    /**
     * Update the content of a selected cell.
     * @param c a Cell.
     * @param content the new Content.
     */
    public void updateCell(final Cell c, final String content) {
        c.updateContent(content);

        /* add methods have their own checks. */

        addFormula(c);

        if (c.getType() == CellType.NUMBER || c.getType() == CellType.FORMULA) {
            c.evaluate(PROGRAM, variableTable);
        }

        addToVariableTable(c);
    }

    /**
     * Checks if the cell's content is a formula.
     * @param c a Cell
     * @return a boolean if the content is a formula.
     */
    private boolean checkIfFormula(final Cell c) {
        return c.getType() == CellType.FORMULA;
    }

    /**
     * Add a formula from a Cell's content in the formula table.
     * @param c a Cell which contains a formula.
     */
    public void addFormula(final Cell c) {
        if (checkIfFormula(c)) {
            formulas.put(c.getLocation(), c.getText());
        }
    }

    /**
     * Sort a column.
     * @param state a String.
     * @param col the int representing a column.
     * @throws SpreadsheetException if the column is outside the sheet.
     */
    public void sortColumn(final String state, final int col) throws SpreadsheetException {
        if (col >= sizeY()) {
            throw new SpreadsheetException("Column is outside of the dimension of the sheet");
        }

        final ArrayList<String> cellValues = new ArrayList<>();

        for (int i = 1; i < sizeX(); i++) {
            final Cell c = getCell(i, col);
            final String content = c.getText();
            if (!"".equals(content)) {
                cellValues.add(content);
            }
            c.updateContent("");
        }

        final String[] finalValues = cellValues.toArray(new String[cellValues.size()]);

        Arrays.sort(finalValues);
        if ("desc".equals(state)) {
            reverse(Arrays.asList(finalValues));
        }
        for (int i = 0; i < finalValues.length; i++) {
            update(i + 1,col, finalValues[i]);
        }
    }

    /**
     * Sort a column.
     * @param col the int representing the column.
     * @throws SpreadsheetException if the column is beyond the dimensions of the Sheet.
     */
    public void sortColumn(final int col) throws SpreadsheetException {
        sortColumn("asc", col);
    }

    /**
     * Re-evalutates a formula.
     * @throws SpreadsheetException if the cell can't be evaluate.
     */
    public void reEvalFormulas() throws SpreadsheetException {
        for (final CellLocation c : formulas.keySet()) {
            updateCell(getCell(c.toString()), formulas.get(c));
        }
    }

    /**
     * Gets the cell at a given location.
     * @param x the x coordinate.
     * @param y the y coordinate.
     * @return Cell the cell at the x,y location.
     * @throws SpreadsheetException if the dimensions of the location are beyond the sheet's.
     */
    public Cell getCell(final int x, final int y) throws SpreadsheetException {
        if (x > sizeX() || y > sizeY()) {
            throw new SpreadsheetException("Cell at coordinates "
                    + x + "," + y + "is not in the dimensions of the sheet");
        }
        return table.get(x,y);
    }

    /**
     * Gets the cell from a location.
     * @param location the location.
     * @return Cell a cell.
     * @throws SpreadsheetException if the dimensions of the location are beyond the sheet's.
     */
    public Cell getCell(final String location) throws SpreadsheetException {
        int x;
        int y;
        final int[] loc = Cell.parseLocation(location);
        x = loc[0];
        y = loc[1];
        return getCell(x,y);
    }

    /**
     * Get formula from the formulaTable with a given location.
     * @param c a CellLocation.
     * @return the content in the formulaTable.
     */
    public String getFormula(final CellLocation c) {
        return formulas.get(c);
    }

    /**
     * Returns the formulas.
     * @return the formulas from FormulaTable as an array of strings.
     */
    public HashMap<CellLocation, String> getFormulaTable() {
        return formulas;
    }

    /**
     * Creates a string array that contains the rows names.
     * @return Object[][] the bidimensional Object array.
     */
    public String[] getColumns() {
        return table.getColumns();
    }

    /**
     * Creates the a data table.
     * @return Object[][] the bi-dimensional Object array.
     * @throws SpreadsheetException if it can't get the cell at x,y.
     */
    public Object[][] createDataTable() throws SpreadsheetException {

        final Object[][] tableData = new Object[sizeX()][sizeY() + 1];

        for (int i = 0; i < sizeX(); i++) {
            for (int j = 0; j < sizeY(); j++) {

                tableData[i][j] = getCell(i + 1, j).getText();
            }
        } 

        return tableData;
    }
}
