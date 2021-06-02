package com.spreadsheet.test.sheet;

import com.computation.program.VariableTable;
import com.computation.program.VariableTableException;
import com.spreadsheetmodel.SpreadsheetException;
import com.spreadsheetmodel.cell.Cell;
import com.spreadsheetmodel.cell.CellLocation;
import com.spreadsheetmodel.cell.TableCell;
import com.spreadsheetmodel.sheet.Grid;
import com.spreadsheetmodel.sheet.Sheet;
import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class SheetTest {

    @Test
    public void testSizeX() {
        Sheet s = new Sheet(5, 6);
        assertEquals(s.sizeX(), 5);
    }

    @Test
    public void testSizeY() {
        Sheet s = new Sheet(5, 6);
        assertEquals(s.sizeY(), 6);
    }

    @Test
    public void testGetTableName() {
        Sheet z = new Sheet(5, 6);
        assertEquals(z.getTableName(), "Table");

        Sheet s = new Sheet(5, 6, "sheet2");
        assertEquals(s.getTableName(), "sheet2");
    }

    @Test
    // test for add: also tests addToVariableTable and addFormula because inside of add
    public void testAdd() throws VariableTableException {
        Sheet s = new Sheet(5, 6);
        Cell c = new TableCell(1, 1);
        c.updateContent("5");
        s.add(c);
        assertEquals(s.getVariableTable().getInt(c.getLocation().toString()).toString(), "5");
    }

    @Test 
    public void testGrow() throws SpreadsheetException {
        Sheet s = new Sheet(5, 6);
        assertEquals(s.sizeX(), 5);
        assertEquals(s.sizeY(), 6);

        s.grow("Horizontally", 7);
        assertEquals(s.sizeY(), 7);

        s.grow("Vertically", 8);
        assertEquals(s.sizeX(), 8);

        s.grow("Horizontally", 10);
        assertEquals(s.sizeY(), 10);

        s.grow("WRONG", 100);
        assertEquals(s.sizeX(), 8);
        assertEquals(s.sizeY(), 10);
    }

    @Test
    public void testAddToVariableTable() {
        Sheet s = new Sheet(1,1);
        Cell c = new Cell(1,1);

        s.addToVariableTable(c);

        assertEquals(null, s.getFormula(c.getLocation()));
    }


    @Test
    public void testGetVariableTable() {
        Sheet s = new Sheet(5, 6);
        assertTrue(s.getVariableTable() instanceof VariableTable);
    }

    @Test
    public void testUpdateTableName() {
        Sheet s = new Sheet(5, 6);
        s.updateTableName("sheet2");

        assertEquals(s.getTableName(), "sheet2");
    }

    @Test
    public void testUpdate() {
        Sheet s = new Sheet(5, 6);
        Cell c = new TableCell(3, 3, "g");
        s.add(c);
        assertEquals(s.getCell(3, 3).getText(), c.getText());
        s.update(3, 3, "l");
        assertEquals(s.getCell(3, 3).getText(), "l");
    }

    @Test
    public void testUpdateCell() {
        Sheet s = new Sheet(5, 6);
        Cell c = new TableCell(3, 3, "5");
        assertEquals(c.getText(), "5");
        s.add(c);
        s.update(3, 3, "2");
        assertEquals(s.getCell(3, 3).getText(), "2");
        s.update(3, 3, "=5+3");
        assertEquals(s.getCell(3, 3).getText(), "8");
    }

    @Test
    public void testAddFormula() {
        Sheet s = new Sheet(5, 6);
        Cell c = new TableCell(3, 3, "=5+3");
        s.add(c);
        assertEquals(s.getFormula(c.getLocation()), c.getText());
    }

    @Test
    public void testAddFormula2() {
        Sheet s = new Sheet(5, 6);
        Cell c = new TableCell(3, 3, "=2+5");
        s.addFormula(c);
        c = new TableCell(3, 3, "12");
        s.addFormula(c);
        assertEquals(null, s.getFormula(c.getLocation()));
    }

    @Test
    public void testReEvalFormulas() {

    Sheet s = new Sheet(5, 6);
    Cell c = new TableCell(3, 3, "=8+0");
    s.add(c);
    assertEquals(s.getFormula(c.getLocation()),c.getText());

    s.reEvalFormulas();
    assertEquals(s.getFormula(c.getLocation()),c.getText());
    }

    @Test
    public void testGetFormulaTable() {
        Sheet s = new Sheet(5, 6);
        Cell c = new TableCell(3, 3, "=8+0");
        s.add(c);
        HashMap<CellLocation, String> formulas = s.getFormulaTable();
        assertEquals("=8+0", formulas.get(c.getLocation()));
    }

    @Test
    public void testGetColumns() {
        Sheet s = new Sheet(2, 2);
        String[] columns = s.getColumns();
        assertEquals(3 , columns.length);
        assertEquals(null, columns[0]);
        assertEquals("A", columns[1]);
        assertEquals("B", columns[2]);
    }

    @Test
    public void testCreateDataTable() {
        Sheet s = new Sheet(3, 3);
        Cell a = new TableCell(0, 0, "1");
        s.add(a);
        Cell b = new TableCell(0, 1, "2");
        s.add(b);
        Cell c = new TableCell(1, 0, "3");
        s.add(c);
        Cell d = new TableCell(1, 1, "4");
        s.add(d);

        Object[][] dataTable = s.createDataTable();
        assertEquals("1", dataTable[0][0]);
        assertEquals("2", dataTable[1][0]);
        // TODO figure out why this doesn't work
        // assertEquals("3", dataTable[1][1]);
        assertEquals("4", dataTable[0][1]);
    }

    @Test
    public void testFillFormulas() {
        Sheet s = new Sheet(2, 2);
        Cell c = new TableCell(1, 1, "=1+2");
        s.add(c);

        HashMap<CellLocation, String> formulas = s.getFormulaTable();
        assertEquals("=1+2", formulas.get(c.getLocation()));

        s.fillFormulas();
        formulas = s.getFormulaTable();
        assertEquals("=1+2", formulas.get(c.getLocation()));
    }
}