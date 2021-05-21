package com.spreadsheet.test;

import com.spreadsheetmodel.Spreadsheet;
import com.spreadsheetmodel.cell.Cell;
import com.spreadsheetmodel.sheet.Sheet;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

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
        s.selectCell(1,2);
        s.updateCurrentCell("4");
        assertEquals(s.getCurrentCell().getText(), "4");
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

    @Test
    public void testImportCsv() {
        Spreadsheet s = new Spreadsheet();
        String projectDir = System.getProperty("user.dir");
        Path csvPath = Paths.get(projectDir.toString(), "src/test/resources/a.csv");
        s.importCsv(csvPath.toString());
        s.selectCell(1,1);
        assertEquals(s.getCurrentCell().getText(), "2");
        s.selectCell(2,2);
        assertEquals(s.getCurrentCell().getText(), "4");
    }

    @Test
    public void testExportCsv() throws IOException {
        String projectDir = System.getProperty("user.dir");
        Path csvPath = Paths.get(projectDir.toString(), "src/test/resources/b.csv");
        File csv = new File(csvPath.toString());
        if (csv.exists()) {
            csv.delete();
        }
        Spreadsheet s = new Spreadsheet();
        s.selectCell(3,3);
        s.updateCurrentCell("test");
        s.selectCell(2,2);
        s.updateCurrentCell("test2");
        s.selectCell(1,1);
        s.updateCurrentCell("test3");
        s.exportCsv(csvPath.toString());
    }

    @Test
    public void testGetSheet() {
        Spreadsheet s = new Spreadsheet();
        assertTrue(s.getSheets() instanceof Sheet[]);
    }

}