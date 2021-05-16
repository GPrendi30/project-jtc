package com.spreadsheet.test;
import com.spreadsheet.Spreadsheet;
import com.spreadsheet.cell.*;
import com.spreadsheet.sheet.*;
import org.junit.Test;

import static org.junit.Assert.*;

public class SpreadsheetTest {

    @Test
    // also test for selectSheet and getCurrentSheetName methods
        public void testAddNewSheet() {
        Spreadsheet s = new Spreadsheet();
        s.addNewSheet("sheet2");
        s.selectSheet("sheet2");
        assertEquals(s.getCurrentSheetName(), "sheet2");
        s.addNewSheet(null);
        s.addNewSheet("");
        int i;
        for (i = 4; i < 11; i++) {
            s.addNewSheet("sheet" + i);
        }
    }

    @Test
    public void testSelectCell() {
        Spreadsheet s = new Spreadsheet();
        s.selectCell(1, 1);
        assertTrue(s.getCurrentCell() instanceof Cell);
    }

    @Test
    public void testUpdateCurrentCell() {
        Spreadsheet s = new Spreadsheet();
        s.selectCell(1,1);
        s.updateCurrentCell("5");
        assertEquals(s.getCurrentCell().getText(), "5");
    }

    @Test
    public void testGetCurrentCell() {
        Spreadsheet s = new Spreadsheet();
        s.selectCell(1, 1);
        assertTrue(s.getCurrentCell() instanceof Cell);
    }

    @Test
    // also test for selectSheet
    public void testGetCurrentSheet() {
        Spreadsheet s = new Spreadsheet();
        s.addNewSheet("sheet2");
        s.selectSheet("sheet2");
        assertTrue(s.getCurrentSheet() instanceof Sheet);
    }

    @Test
    public void testGetCurrentSheetName() {
        Spreadsheet s = new Spreadsheet();
        s.addNewSheet("sheet2");
        s.selectSheet("sheet2");
        assertEquals(s.getCurrentSheetName(), "sheet2");
    }

    @Test
    public void testGrow() {
        // also test for getCurrentSheet
        Spreadsheet s = new Spreadsheet();
        s.addNewSheet("sheet2");
        s.grow("Horizontally", 7);
        assertEquals(s.getCurrentSheet().sizeY(), 7);
    }

    @Test
    public void testGetOpenSheets() {
        Spreadsheet s = new Spreadsheet();
        assertEquals(s.getOpenSheets(), 1);
    }
    
    @Test
    public void testIncrementOpenSheets() {
        Spreadsheet s = new Spreadsheet();
        assertEquals(s.getOpenSheets(), 1);
        s.incrementOpenSheets();
        assertEquals(s.getOpenSheets(), 2);
    } 

    // TODO add test for importCsv

    // TODO add test for exportCsv

}