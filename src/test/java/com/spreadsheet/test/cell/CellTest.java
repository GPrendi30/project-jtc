package com.spreadsheet.test.cell;


import com.computation.program.Program;
import com.computation.program.VariableTable;
import com.spreadsheetmodel.Spreadsheet;
import com.spreadsheetmodel.cell.*;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;


public class CellTest {

    @Test
    public void testCellType() {
        Cell c = new Cell(0, 0);
        assertEquals(CellType.INVALID, c.getType());
    }

    @Test
    public void testCellText() {
        Cell c = new Cell(0, 0);
        assertNull(c.getText());
    }

    @Test
    public void testCellLocation() {
        final String stringLocation = "C3";
        int[] c3 = Cell.parseLocation(stringLocation);
        final int x = c3[0];
        final int y = c3[1];
        Cell c = new Cell(x,y);

        assertEquals(stringLocation, c.getLocation().toString());
        assertEquals("C", c.getLocation().getColumn());
        assertEquals(3, c.getLocation().getRow());
    }

    @Test
    public void testCellSelectable() {
        Cell c = new Cell(0,0);
        assertFalse(c.isSelectable());
        c.makeSelectable();
        assertTrue(c.isSelectable());
    }

    @Test
    public void testGetLocation() {
        Cell c = new Cell(3,3);
        assertEquals(new CellLocation(3,3).toString(), c.getLocation().toString());
    }

    @Test
    public void testUpdateContent() {
        // empty methods, overridden
        Cell c = new Cell(3,3);
        c.updateContent("g");
        c.evaluate(null, null);
    }

    @Test
    public void testLateralCell() {
        Cell c = new LateralCell(5);
        assertEquals(CellType.INVALID, c.getType());
        assertEquals("E", c.getText());
    }

    @Test
    public void testTopCell() {
        Cell c = new TopCell(5);
        assertEquals(CellType.INVALID, c.getType());
        assertEquals("5", c.getText());
    }

    @Test
    public void testTableCell() {
        Cell c = new TableCell(3,3, "Cell 3,3");
        assertEquals("Cell 3,3", c.getText());
        assertEquals(CellType.STRING, c.getType());
        c.updateContent("=SUM(A1+B2)");
        assertEquals("=SUM(A1+B2)", c.getText());
        assertEquals(CellType.FORMULA, c.getType());
    }

    @Test
    public void testTableCellEvaluation() {
        final Program p = new Program();
        final VariableTable vt = new VariableTable();
        Cell c = new TableCell(2,2, "3+6");
        assertEquals("3+6", c.getText());
        c.evaluate(p, vt);
        assertEquals("9", c.getText());
    }

    @Test
    public void testTableCellEvaluationInvalid() {
        final Program p = new Program();
        final VariableTable vt = new VariableTable();
        Cell c = new TableCell(2,2, "=B3+6");
        assertEquals("=B3+6", c.getText());
        try {
            c.evaluate(p, vt);
        } catch (Exception ex) {
            assertTrue(ex instanceof Exception);
        }
        System.out.println(c.getText());
        assertEquals(CellType.INVALID, c.getType());
    }

    @Test
    public void testTableCellRemove() {
        final Cell c = new TableCell(3,3);
        c.updateContent("test");
        assertEquals("test", c.getText());
        c.remove();
        assertNull(c.getText());
    }

    @Test
    public void testIntColumn() {
        CellLocation cl = new CellLocation(1, 1);
        assertEquals(1, cl.getIntColumn());
    }

    @Test
    public void testGetColumnFromInt() {
        CellLocation cl = new CellLocation(1, 1);
        assertEquals("AA", cl.getColumnFromInt(27));
        assertEquals("AB", cl.getColumnFromInt(28));
        assertEquals("AZ", cl.getColumnFromInt(52));
    }

    @Test
    public void testCellRange() throws Exception {
        Spreadsheet s = new Spreadsheet();
        s.updateCell(1,1,"1");
        s.updateCell(1,2,"2");
        s.updateCell(2,1,"3");
        s.updateCell(2,2,"4");
        CellRange cr = new CellRange(s.getCurrentSheet(),
                s.getCurrentSheet().getCell(1,1),
                s.getCurrentSheet().getCell(2,2));

        cr.copyDataTo(s.getCurrentSheet(),
                s.getCurrentSheet().getCell(3,3),
                s.getCurrentSheet().getCell(4,4));
        assertEquals("1", s.getCurrentSheet().getCell(3,3).getText());
        assertEquals("2", s.getCurrentSheet().getCell(3,4).getText());
        assertEquals("3", s.getCurrentSheet().getCell(4,3).getText());
        assertEquals("4", s.getCurrentSheet().getCell(4,4).getText());
    }

    @Test
    public void testCellRange2() throws Exception {
        Spreadsheet s = new Spreadsheet();
        s.updateCell(1,1,"1");
        s.updateCell(1,2,"2");
        s.updateCell(2,1,"3");
        s.updateCell(2,2,"4");
        CellRange cr = new CellRange(s.getCurrentSheet(),
                s.getCurrentSheet().getCell(1,1),
                s.getCurrentSheet().getCell(2,2));

        cr.copyDataTo(s.getCurrentSheet(), s.getCurrentSheet().getCell(3,3));
        assertEquals("1", s.getCurrentSheet().getCell(3,3).getText());
        assertEquals("2", s.getCurrentSheet().getCell(3,4).getText());
        assertEquals("3", s.getCurrentSheet().getCell(4,3).getText());
        assertEquals("4", s.getCurrentSheet().getCell(4,4).getText());
    }

    @Test
    public void testCellRangeThrowsException() {
        Spreadsheet s = new Spreadsheet();
        s.updateCell(1,1,"1");
        s.updateCell(1,2,"2");
        s.updateCell(2,1,"3");
        s.updateCell(2,2,"4");
        CellRange cr = new CellRange(s.getCurrentSheet(),
                s.getCurrentSheet().getCell(1,1),
                s.getCurrentSheet().getCell(2,2));

        boolean thrown = false;
        try {
            cr.copyDataTo(s.getCurrentSheet(),
                    s.getCurrentSheet().getCell(3,3),
                    s.getCurrentSheet().getCell(4,56));
        } catch (Exception e) {
            thrown = true;
        }
        assertTrue(thrown);
    }

    @Test
    public void testCellRangeThrowsException2() {
        Spreadsheet s = new Spreadsheet();
        s.updateCell(1,1,"1");
        s.updateCell(1,2,"2");
        s.updateCell(2,1,"3");
        s.updateCell(2,2,"4");
        CellRange cr = new CellRange(s.getCurrentSheet(),
                s.getCurrentSheet().getCell(1,1),
                s.getCurrentSheet().getCell(2,2));

        boolean thrown = false;
        try {
            cr.copyDataTo(s.getCurrentSheet(),
                    s.getCurrentSheet().getCell(3,3),
                    s.getCurrentSheet().getCell(56,4));
        } catch (Exception e) {
            thrown = true;
        }
        assertTrue(thrown);
    }



}
