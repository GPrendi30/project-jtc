package com.spreadsheet.table;

import com.computation.program.Program;
import com.computation.program.VariableTable;
import com.spreadsheet.cell.Cell;
import com.spreadsheet.cell.CellLocation;
import com.spreadsheet.cell.CellType;

import java.util.HashMap;
import java.util.Iterator;

public class Table {
    private static final Program PROGRAM = new Program();

    private String tableName;
    private final Grid table;
    private final HashMap<CellLocation, String> formulas;
    private final VariableTable variableTable;


    public Table(final int x,final int y) {
        tableName = "Table";
        table = new Grid(x, y);
        formulas = new HashMap<>();
        variableTable = new VariableTable();
    }

    public Table(final int x, final int y, final String tableName) {
        this.tableName = tableName;
        table = new Grid(x, y);
        formulas = new HashMap<>();
        variableTable = new VariableTable();
    }

    public int sizeX() {
        return table.sizeX();
    }

    public int sizeY() {
        return table.sizeY();
    }


    public String getTableName() {
        return tableName;
    }

    public void addToVariableTable(final Cell c) {
        if (c.getType() == CellType.INT && !c.getText().equals("!NIL")) {
            variableTable.set(c.getLocation().toString(), Integer.parseInt(c.getText()));
        }
    }

    public void grow(final String direction,final int size) {
        if ("Vertically".equals(direction)) {
            table.growVertically(size);
        } else if ("Horizontally".equals(direction)) {
            table.growHorizontally(size);
        } else {
            // throw error
        }
    }

    public VariableTable getVariableTable() {
        return this.variableTable;
    }

    public void updateTableName(final String newName) {
        tableName = newName;
    }

    public void add(final Cell c) {
        //c.updateContent(c.evaluate(PROGRAM, variableTable));
        c.evaluate(PROGRAM, variableTable);
        table.put(c);
        addFormula(c);
        addToVariableTable(c);
    }

    public void update(final int x,final int y,final String content) {
        final Cell c = get(x,y);
        updateCell(c, content);
    }

    public void updateCell(final Cell c, final String content) {
        c.updateContent(content);
        if (c.getType() == CellType.INT) {
            c.evaluate(PROGRAM, variableTable);
        }

        /* add methods have their own checks. */
        addToVariableTable(c);
        addFormula(c);
    }

    private boolean checkIfFormula(final Cell c) {
        return c.getType() == CellType.FORMULA;
    }

    public void addFormula(final Cell c) {
        if (checkIfFormula(c)) {
            formulas.put(c.getLocation(), c.getText());
        }
    }

    public Cell get(final int x,final int y) {
        return table.get(x,y);
    }

    public String getFormula(final CellLocation c) {
        return formulas.get(c);
    }

    public void  print() {
        System.out.println(" ###########  "  + tableName + "  ############# ");
        table.print();
    }

    public void printFormulas() {
        final Iterator<CellLocation> cellLocationIterator = formulas.keySet().iterator();
        System.out.println(" \n\n|     FORMULAE TABLE      |");
        System.out.println("---------------------------");
        for (final String c : formulas.values())
        {
            System.out.println("----------------------------");
            System.out.println(" | " + cellLocationIterator.next() + " : " + c + " | ");

        }
    }

}
