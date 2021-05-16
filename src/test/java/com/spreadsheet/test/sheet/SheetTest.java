package com.spreadsheet.test.sheet;
import com.spreadsheet.sheet.Sheet;
import com.spreadsheet.sheet.Grid;
import com.spreadsheet.cell.*;
import com.computation.program.VariableTable;

import org.junit.Test;

import jdk.javadoc.internal.doclets.formats.html.resources.standard_ja;
import jdk.jfr.Timestamp;

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

        s.grow("Vertically", 8);
        assertEquals(s.sizeX(), 8);

        s.grow("Horizontally", 10);
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
        Cell c = new TableCell(3,3);
        c.updateContent("g");
        assertEquals(c.getText(), "g");
        s.add(c);
    // is it correct that the content of the cell is updated to !NIL ???
        s.updateCell(c, "2");
        assertEquals(c.getText(), "!NIL");
    }
    // TODO: testCheckIfFormula
    /*
    @Test
    public void testCheckIfFormula() {
        Sheet s = new Sheet(5, 6);
        Cell c = new TableCell(3,3);
        c.updateContent("g");
        assertEquals(checkIfFormula(c), false);

    } */

    /* // TEST NOT WORKING
    @Test
    public void testAddFormula() {
        Sheet s = new Sheet(5, 6);
        Cell c = new TableCell(3,3);
        c.updateContent("=5+3");
        s.add(c);
        assertEquals(s.getFormula(c.getLocation()), c.getText()); 
    } */
    
    // TODO: testGet

    // TODO: testGetFormula


}