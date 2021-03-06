package com.spreadsheet.test.sheet;

import com.spreadsheetmodel.cell.Cell;
import com.spreadsheetmodel.sheet.Grid;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * The tests for the Grid Class.
 *
 * @author Di Pietro Enrico, Prendi Gerald.
 *
 */
public class GridTest {

    @Test
    public void testSizeX() {
        Grid g = new Grid(5, 6);
        assertEquals(g.sizeX(), 5);
    }

    @Test
    public void testSizeY() {
        Grid g = new Grid(5, 6);
        assertEquals(g.sizeY(), 6);
    }

    @Test
    public void testPutGet() {
        Grid g = new Grid(5, 5);
        Cell c = new Cell(1, 1);
        c.updateContent("5");
        g.put(c);
        assertEquals(g.get(1,1).getText(), c.getText());
        c.updateContent("10");
        g.put(c);
        assertEquals(g.get(1,1).getText(), c.getText());
        assertNull(g.get(20, 10));
    }

    @Test
    public void testGrowVertically() {
        Grid g = new Grid(5, 5);
        assertEquals(g.sizeX(), 5);
        g.growVertically(10);
        assertEquals(g.sizeX(), 10);
    }

    @Test
    public void testGrowHorizontally() {
        Grid g = new Grid(3, 3);
        assertEquals(g.sizeY(), 3);
        g.growHorizontally(8);
        assertEquals(g.sizeY(), 8);
    }

    @Test
    public void testGetColumns() {
        Grid g = new Grid(2, 2);
        String[] columns = g.getColumns();
        assertEquals(3 , columns.length);
        assertEquals(null, columns[0]);
        assertEquals("A", columns[1]);
        assertEquals("B", columns[2]);
    }
}

