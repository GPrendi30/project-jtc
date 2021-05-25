package com.spreadsheet.test.cell;


import com.computation.program.Program;
import com.computation.program.VariableTable;
import com.spreadsheetmodel.cell.*;
import org.junit.Assert;
import org.junit.Test;

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
    public void testTableCellEvaluationNil() {
        final Program p = new Program();
        final VariableTable vt = new VariableTable();
        Cell c = new TableCell(2,2, "B3+6");
        assertEquals("B3+6", c.getText());
        try {
            c.evaluate(p, vt);
        } catch (Exception ex) {
            assertTrue(ex instanceof Exception);
        }
        assertEquals("!NIL", c.getText());
    }

    @Test
    public void testTableCellRemove() {
        final Cell c = new TableCell(3,3);
        c.updateContent("test");
        assertEquals("test", c.getText());
        c.remove();
        assertNull(c.getText());
    }



}
