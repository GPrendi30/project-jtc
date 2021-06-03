package com.spreadsheet.test.command;

import com.spreadsheetmodel.Spreadsheet;
import com.spreadsheetmodel.SpreadsheetException;
import com.spreadsheetmodel.SpreadsheetIO;
import com.spreadsheetmodel.cell.Cell;
import com.spreadsheetmodel.cell.TableCell;
import com.spreadsheetmodel.commands.*;
import com.spreadsheetview.gui.SpreadsheetGui;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

/**
 * The tests for the Command Classes.
 *
 * @author Di Pietro Enrico, Prendi Gerald.
 *
 */
public class CommandTest {

    @Test
    public void testSelectUpdateCellCommand() throws SpreadsheetException {
        Spreadsheet s = new Spreadsheet(4,3);
        SelectCellCommand sel = new SelectCellCommand(2,2);
        sel.execute(s);

        assertSame(s.getCurrentSheet().getCell(2,2), s.getCurrentCell());
        //empty methods
        sel.redo(s);
        sel.undo(s);
    }



    @Test
    public void testAddSheet() throws SpreadsheetException {
        Spreadsheet s = new Spreadsheet(5, 5);
        AddNewSheetCommand c = new AddNewSheetCommand("Sheet 2");
        c.execute(s);
        assertEquals("Sheet 2", s.getCurrentSheetName());
        c.undo(s);
        assertEquals("Sheet 1", s.getCurrentSheetName());
        c.redo(s);
        assertEquals("Sheet 2", s.getCurrentSheetName());
    }

    @Test
    public void testCut() throws SpreadsheetException {
        Spreadsheet s = new Spreadsheet(5, 5);
        s.selectCell(2,2);
        s.updateCurrentCell("gg");
        CutCommand c = new CutCommand(CopyPasteStack.getInstance());
        c.execute(s);
        assertEquals("", s.getCurrentCell().getText());
        c.undo(s);
        assertEquals("gg", s.getCurrentCell().getText());
        c.redo(s);
        assertEquals("", s.getCurrentCell().getText());
    }

    @Test
    public void testPaste() throws SpreadsheetException {
        Spreadsheet s = new Spreadsheet(5, 5);
        s.selectCell(2,2);
        s.updateCurrentCell("gg");
        CopyCommand copy = new CopyCommand(CopyPasteStack.getInstance());
        PasteCommand paste = new PasteCommand(CopyPasteStack.getInstance());
        s.selectCell(3,3);
        paste.execute(s);
        assertEquals("gg", s.getCurrentCell().getText());
        paste.undo(s);
        assertEquals("", s.getCurrentCell().getText());
        paste.redo(s);
        assertEquals("gg", s.getCurrentCell().getText());
        copy.execute(s);
        copy.redo(s);
        copy.undo(s);
    }

    @Test
    public void testSave() throws SpreadsheetException {
        String projectDir = System.getProperty("user.dir");
        Path csvPath = Paths.get(projectDir, "src/test/resources/test_file.jtc");

        Spreadsheet s = new Spreadsheet(5,5);
        s.selectCell(3,3);
        s.updateCurrentCell("test");
        s.selectCell(2,2);
        s.updateCurrentCell("test2");
        s.selectCell(1,1);
        s.updateCurrentCell("test3");

        SaveCommand save = new SaveCommand(csvPath.toString());
        save.execute(s);

        s.selectCell(1, 1);
        assertEquals("test3", s.getCurrentCell().getText());
        // empty methods.
        save.undo(s);
        save.redo(s);
    }

    @Test
    public void testGrow() throws SpreadsheetException {

        Spreadsheet s = new Spreadsheet(5,5);
        GrowSheetCommand c = new GrowSheetCommand("Horizontally", 25);
        c.execute(s);
        assertEquals(s.getCurrentSheet().sizeY(), 25);

        //empty methods.
        c.undo(s);
        c.redo(s);
    }

    @Test
    public void testImport() throws SpreadsheetException, IOException {
        Spreadsheet s = new Spreadsheet(5,5);
        String projectDir = System.getProperty("user.dir");
        Path csvPath = Paths.get(projectDir.toString(), "src/test/resources/a.csv");
        ImportCommand i = new ImportCommand(csvPath.toString());
        i.execute(s);
        s.selectCell(1,1);
        assertEquals(s.getCurrentCell().getText(), "2");
        s.selectCell(2,2);
        assertEquals(s.getCurrentCell().getText(), "4");
        i.undo(s);
        assertEquals(s.getCurrentSheetName(), "Sheet 1");
        i.redo(s);
        assertEquals(s.getCurrentSheetName(), "a.csv");
    }

    @Test
    public void testExport() throws SpreadsheetException, IOException {
        String projectDir = System.getProperty("user.dir");
        Path csvPath = Paths.get(projectDir.toString(), "src/test/resources/c.csv");
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
        ExportCommand c = new ExportCommand(csvPath.toString());
        c.execute(s);
        File f = new File(csvPath.toString());
        assertTrue(f.exists());
        // empty methods.
        c.redo(s);
        c.undo(s);
    }

    @Test
    public void testUpdateCell() throws SpreadsheetException {
        Spreadsheet s = new Spreadsheet(3,3);
        s.selectCell(2,2);
        UpdateCellCommand c = new UpdateCellCommand(s.getCurrentCell(), "ggg");
        c.execute(s);
        assertEquals(s.getCurrentCell().getText(), "ggg");
        c.undo(s);
        assertEquals(s.getCurrentCell().getText(), "");
        c.redo(s);
        assertEquals(s.getCurrentCell().getText(), "ggg");

    }




    @Test
    public void testFormulasOnCommand() throws SpreadsheetException {
        Spreadsheet s = new Spreadsheet(5,5);
        s.selectCell(2,2);
        s.updateCurrentCell("=SUM(2,2)");
        assertEquals(s.getCurrentCell().getText(), "4");
        FormulasOnCommand c = new FormulasOnCommand();
        c.execute(s);
        assertEquals(s.getCurrentCell().getText(), "=SUM(2,2)");
        c.undo(s);
        assertEquals(s.getCurrentCell().getText(), "4");
        c.redo(s);
        assertEquals(s.getCurrentCell().getText(), "=SUM(2,2)");

    }
}
