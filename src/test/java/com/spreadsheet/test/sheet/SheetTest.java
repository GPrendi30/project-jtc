package com.spreadsheet.test.sheet;
import com.spreadsheet.sheet.Sheet;
import com.spreadsheet.sheet.Grid;
import com.spreadsheet.cell.*;
import com.computation.program.VariableTable;

import org.junit.Test;

import static org.junit.Assert.*;

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
    public void testAdd() {
        Sheet s = new Sheet(5, 6);
        Cell c = new TableCell(1, 1);
        c.updateContent("5");
        s.add(c);
        assertEquals(s.getVariableTable().get(c.getLocation().toString()), 5);
    }

    @Test 
    public void testGrow() {
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
        Cell c = new TableCell(3,3, "g");
        s.add(c);
        assertEquals(s.get(3, 3).getText(), c.getText());
        s.update(3, 3, "l");
        assertEquals(s.get(3, 3).getText(), c.getText());
    }

    @Test
    public void testUpdateCell() {
        Sheet s = new Sheet(5, 6);
        Cell c = new TableCell(3,3, "5");
        assertEquals(c.getText(), "5");
        s.add(c);
        s.update(3, 3, "2");
        assertEquals(s.get(3, 3).getText(), "2");
        s.update(3, 3, "=5+3");
        assertEquals(s.get(3, 3).getText(), "=5+3");
    }

    @Test
    public void testAddFormula() {
        Sheet s = new Sheet(5, 6);
        Cell c = new TableCell(3,3, "=5+3");
        s.add(c);
        assertEquals(s.getFormula(c.getLocation()), c.getText()); 
    }
}