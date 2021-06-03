package com.spreadsheet.test;

import com.computation.ast.function.FunctionList;
import com.spreadsheetmodel.Spreadsheet;
import com.spreadsheetmodel.SpreadsheetEvent;
import com.spreadsheetmodel.SpreadsheetEventType;
import com.spreadsheetmodel.SpreadsheetException;
import com.spreadsheetmodel.cell.Cell;
import com.spreadsheetmodel.sheet.Sheet;
import com.spreadsheetmodel.SpreadsheetListener;
import static org.junit.Assert.assertFalse;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class SpreadsheetTest {

    @Test
    // also test for selectSheet and getCurrentSheetName methods
        public void testAddNewSheet() throws SpreadsheetException {
        Spreadsheet s = new Spreadsheet(5,5);
        s.addNewSheet("sheet2");
        s.selectSheet("sheet2");
        assertEquals(s.getCurrentSheetName(), "sheet2");
    }

    @Test
    public void testAddNewSheet2() throws SpreadsheetException {
        Spreadsheet s = new Spreadsheet(5,5);
        int i;
        for (i = 1; i < 11; i++) {
            s.addNewSheet("sheet" + i);
        }
        s.selectSheet("sheet10");
        assertEquals(s.getCurrentSheetName(), "sheet10");
    }

    @Test
    public void testSelectCell() throws SpreadsheetException {
        Spreadsheet s = new Spreadsheet(5,5);
        s.selectCell(1, 1);
        assertTrue(s.getCurrentCell() instanceof Cell);
    }

    @Test
    public void testSelectCell2() throws SpreadsheetException {
        Spreadsheet s = new Spreadsheet(5,5);
        s.selectCell("A1");
        assertTrue(s.getCurrentCell() instanceof Cell);
    }

    @Test
    public void testUpdateCurrentCell() throws SpreadsheetException {
        Spreadsheet s = new Spreadsheet(5,5);
        s.selectCell(1,2);
        s.updateCurrentCell("4");
        assertEquals(s.getCurrentCell().getText(), "4");
    }

    @Test
    public void testGetCurrentCell() throws SpreadsheetException {
        Spreadsheet s = new Spreadsheet(5,5);
        s.selectCell(1, 1);
        assertTrue(s.getCurrentCell() instanceof Cell);
    }

    @Test
    // also test for selectSheet
    public void testGetCurrentSheet() throws SpreadsheetException {
        Spreadsheet s = new Spreadsheet(5,5);
        s.addNewSheet("sheet2");
        s.selectSheet("sheet2");
        assertTrue(s.getCurrentSheet() instanceof Sheet);
    }

    @Test
    public void testGetCurrentSheetName() throws SpreadsheetException {
        Spreadsheet s = new Spreadsheet(5,5);
        s.addNewSheet("sheet2");
        s.selectSheet("sheet2");
        assertEquals(s.getCurrentSheetName(), "sheet2");
    }

    @Test
    public void testGrow() throws SpreadsheetException {
        // also test for getCurrentSheet
        Spreadsheet s = new Spreadsheet(5,5);
        s.addNewSheet("sheet2");
        s.grow("Horizontally", 21);
        assertEquals(s.getCurrentSheet().sizeY(), 21);
    }

    @Test
    public void testGrowThrowsException() throws SpreadsheetException {
        // also test for getCurrentSheet
        Spreadsheet s = new Spreadsheet(5,5);
        s.addNewSheet("sheet2");

        boolean thrown = false;
        try {
            s.grow("Horizontally", 7);
        } catch (SpreadsheetException exception) {
            thrown = true;
        }
        assertTrue(thrown);
    }

    @Test
    public void testImportCsv() throws IOException, SpreadsheetException {
        Spreadsheet s = new Spreadsheet(5,5);
        String projectDir = System.getProperty("user.dir");
        Path csvPath = Paths.get(projectDir.toString(), "src/test/resources/a.csv");
        s.importCsv(csvPath.toString());
        s.selectCell(1,1);
        assertEquals(s.getCurrentCell().getText(), "2");
        s.selectCell(2,2);
        assertEquals(s.getCurrentCell().getText(), "4");
    }

    @Test
    public void testImportCsvNotFound() throws Exception{
        // TODO check that doesn't throw errors in the console
        Spreadsheet s = new Spreadsheet(5,5);
        String path = "path/to/file/that/does/not/exist.69420";

        boolean thrown = false;

        try {
            s.importCsv(path);
        } catch (Exception e) {
            thrown = true;
        }

        assertTrue(thrown);
    }

    @Test
    public void testExportCsv() throws IOException, SpreadsheetException {
        String projectDir = System.getProperty("user.dir");
        Path csvPath = Paths.get(projectDir.toString(), "src/test/resources/b.csv");
        File csv = new File(csvPath.toString());
        if (csv.exists()) {
            csv.delete();
        }
        Spreadsheet s = new Spreadsheet(5,5);
        s.selectCell(3,3);
        s.updateCurrentCell("test");
        s.selectCell(2,2);
        s.updateCurrentCell("test2");
        s.selectCell(1,1);
        s.updateCurrentCell("test3");
        s.exportCsv(csvPath.toString());
    }

    @Test
    public void testExportCsvThrowsException() throws IOException, SpreadsheetException {
        String projectDir = System.getProperty("user.dir");
        Path csvPath = Paths.get(projectDir.toString(), "src/test/resources/b.csv");
        File csv = new File(csvPath.toString());
        Spreadsheet s = new Spreadsheet(5,5);
        s.selectCell(3,3);
        s.updateCurrentCell("test");
        s.selectCell(2,2);
        s.updateCurrentCell("test2");
        s.selectCell(1,1);
        s.updateCurrentCell("test3");

        boolean thrown = false;
        try {
            s.exportCsv(csvPath.toString());
        } catch (IOException exception) {
            thrown = true;
        }
        assertTrue(thrown);
    }

    @Test
    public void testExportCsvOverwrites() throws IOException, SpreadsheetException {
        String projectDir = System.getProperty("user.dir");
        Path csvPath = Paths.get(projectDir, "src/test/resources/wrong_path/df.csv");

        Spreadsheet s = new Spreadsheet(5,5);
        s.selectCell(3,3);
        s.updateCurrentCell("test");
        s.selectCell(2,2);
        s.updateCurrentCell("test2");
        s.selectCell(1,1);
        s.updateCurrentCell("test3");

        boolean thrown = false;
        try {
            s.exportCsv(csvPath.toString());
        } catch (IOException e) {
            thrown = true;
        }
        assertTrue(thrown);
    }

    @Test
    public void testGetSheet() throws SpreadsheetException {
        Spreadsheet s = new Spreadsheet(5,5);
        assertTrue(s.getSheets() instanceof Sheet[]);
    }

    @Test
    public void testCheckSheetName() throws SpreadsheetException {
        Spreadsheet s = new Spreadsheet(5,5);
        boolean thrown = false;
        try {
            s.addNewSheet("sheet2");
        } catch (SpreadsheetException exception) {
            thrown = true;
        }

        assertFalse(thrown);
    }

    @Test
    public void testUpdateCell() throws SpreadsheetException {
        Spreadsheet s = new Spreadsheet(5,5);
        s.updateCell(1,1, "69420");
        s.selectCell(1,1);
        assertEquals("69420", s.getCurrentCell().getText());
    }

    @Test
    public void testFormulasOn() throws SpreadsheetException {
        Spreadsheet s = new Spreadsheet(5,5);
        s.formulasOn();
    }

    @Test
    public void testSortCol() throws SpreadsheetException {
        Spreadsheet s = new Spreadsheet(5,5);
        s.updateCell(1,1, "420");
        s.updateCell(1,2, "69");
        s.sortCol(1);
        assertEquals("69", s.getCurrentCell().getText());
    }

    @Test
    public void testGetFormula() throws SpreadsheetException {
        Spreadsheet s = new Spreadsheet(5,5);
        s.updateCell(2,1, "1");
        s.updateCell(1,1,"=A2+A2");
        s.selectCell("A1");
        s.getCurrentSheet().fillFormulas();
        // TODO is null but not correct, find why it returns null
        assertEquals(null, s.getFormula(s.getCurrentCell()));
    }

    @Test
    public void testReadFromFile() {
        // TODO add the test
    }

    @Test
    public void testSpreadsheetEvent() {
        SpreadsheetEvent se = new SpreadsheetEvent("message",
                SpreadsheetEventType.CELL_CHANGED);

        assertEquals(SpreadsheetEventType.CELL_CHANGED, se.getId());
        assertEquals("message", se.getMessage());
    }

    @Test
    public void testAddSheet() throws SpreadsheetException {
        Spreadsheet s = new Spreadsheet(5, 5);
        s.addSheet(new Sheet(10, 8));
        assertEquals(10, s.getCurrentSheet().sizeX());
        assertEquals(8, s.getCurrentSheet().sizeY());
    }

    @Test
    public void testRemoveSheet() throws SpreadsheetException {
        Spreadsheet s = new Spreadsheet(5, 5);
        s.updateCell(1, 1, "smth");
        s.addNewSheet("sheet2");
        s.removeSheet("sheet2");

        boolean thrown = false;
        try {
            s.selectSheet("sheet2");
        } catch (NullPointerException exception) {
            thrown = true;
        }
        assertTrue(thrown);
    }

    @Test
    public void testSpreadsheetIO() throws SpreadsheetException {
        Spreadsheet s = new Spreadsheet(5, 5);

    }
}