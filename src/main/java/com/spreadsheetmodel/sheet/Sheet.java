package com.spreadsheetmodel.sheet;

import com.computation.program.Program;
import com.computation.program.VariableTable;
import com.spreadsheetmodel.cell.Cell;
import com.spreadsheetmodel.cell.CellLocation;
import com.spreadsheetmodel.cell.CellType;

import java.util.HashMap;

public class Sheet {
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
        if (c.getType() == CellType.INT && !c.getText().equals("!NIL")) {
            variableTable.set(c.getLocation().toString(), Integer.parseInt(c.getText()));
        }
    }

    /**
     * Dynamically grows the array, to a direction and a given size.
     * @param direction a String direction.
     * @param size  the new size of the table.
     */
    public void grow(final String direction,final int size) {
        if ("Vertically".equals(direction)) {
            table.growVertically(size);
        } else if ("Horizontally".equals(direction)) {
            table.growHorizontally(size);
        } else {
            // throw error
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
        //c.updateContent(c.evaluate(PROGRAM, variableTable));
        addFormula(c);
        if (c.getType() == CellType.INT) {
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
     */
    public void update(final int x,final int y,final String content) {
        final Cell c = get(x,y);
        updateCell(c, content);
    }

    /**
     * Update the content of a selected cell.
     * @param c a Cell.
     * @param content the new Content.
     */
    public void updateCell(final Cell c, final String content) {
        c.updateContent(content);

        if (c.getType() == CellType.INT) {
            c.evaluate(PROGRAM, variableTable);
        }

        /* add methods have their own checks. */
        addToVariableTable(c);
        addFormula(c);
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
     * Gets the cell at a given location.
     * @param x the x coordinate.
     * @param y the y coordinate.
     * @return the cell at the x,y location.
     */
    public Cell get(final int x,final int y) {
        return table.get(x,y);
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
}
