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

    /*
    TODO: correct this test!!
    @Test
    // test for add: also tests addToVariableTable and addFormula because inside of add
    public void testAdd() {
        Sheet s = new Sheet(5, 6);
        Cell c = new Cell(1, 1);
        c.updateContent("5");
        s.add(c);
        assertEquals(s.getVariableTable().get(c.getLocation(), "5"));
    }
    */

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

    // TODO: testGetVariableTable
    /*@Test
    public void testGetVariableTable() {
        Sheet s = new Sheet(5, 6);
        assertEquals(s.getVariableTable(), ???)
    } */

    @Test
    public void testUpdateTableName() {
        Sheet s = new Sheet(5, 6);
        s.updateTableName("sheet2");

        assertEquals(s.getTableName(), "sheet2");
    }

    // TODO: testUpdate ???
    @Test
    public void testUpdate() {
        Cell c = new TableCell(3,3);
        c.updateContent("g");
        assertEquals(c.getText(), "g");
        assertEquals(new CellLocation(3,3).toString(), c.getLocation().toString());
        //c.update(1, 1, "e");

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

    // TODO: testAddFormula
    /*
    @Test
    public void testAddFormula() {
        Sheet s = new Sheet(5, 6);
        Cell c = new TableCell(3,3);
        c.updateContent("=A2+B3");
        s.add(c);
        //s.addFormula(c);
        assertEquals(getFormula(c.getLocation()), "=A2+B3");
    } */
    
    // TODO: testGet

    // TODO: testGetFormula


}