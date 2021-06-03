package com.spreadsheet.test.command;

import com.spreadsheetmodel.Spreadsheet;
import com.spreadsheetmodel.SpreadsheetException;
import com.spreadsheetmodel.cell.Cell;
import com.spreadsheetmodel.cell.TableCell;
import com.spreadsheetmodel.commands.AddNewSheetCommand;
import com.spreadsheetmodel.commands.SelectCellCommand;
import com.spreadsheetmodel.sheet.Sheet;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import org.junit.Test;

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

}
