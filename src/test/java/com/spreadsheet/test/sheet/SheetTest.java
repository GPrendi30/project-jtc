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
    To correct this test!!
    @Test
    public void testAddToVariableTable() {
        Sheet s = new Sheet(5, 6);
        Cell c = new Cell(1, 1);
        c.updateContent("5");
        s.add(c);
        assertEquals(s.getVariableTable().get(c.getLocation(), "5"));
    }
    */

}

