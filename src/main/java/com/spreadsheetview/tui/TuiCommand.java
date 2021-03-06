package com.spreadsheetview.tui;

import com.spreadsheetmodel.Spreadsheet;
import com.spreadsheetmodel.SpreadsheetException;
import com.spreadsheetmodel.cell.Cell;
import com.spreadsheetmodel.commands.AddNewSheetCommand;
import com.spreadsheetmodel.commands.ExportCommand;
import com.spreadsheetmodel.commands.FormulasOnCommand;
import com.spreadsheetmodel.commands.ImportCommand;
import com.spreadsheetmodel.commands.OpenCommand;
import com.spreadsheetmodel.commands.SelectCellCommand;
import com.spreadsheetmodel.commands.SelectSheetCommand;
import com.spreadsheetmodel.commands.SortColumnCommand;
import com.spreadsheetmodel.commands.UpdateCellCommand;
import com.spreadsheetview.SpreadsheetView;

import java.util.Locale;

/**
 * The Tui commands.
 *
 * @author Di Pietro Enrico, Prendi Gerald.
 *
 */
public class TuiCommand {

    private Spreadsheet model;
    private SpreadsheetView view;

    /**
     * Creates a new TuiCommand that runs the commands for the tui.
     * @param model the model.
     * @param tui the view.
     */
    public TuiCommand(final Spreadsheet model, final SpreadsheetTui tui) {
        this.model = model;
        view = tui;
    }

    /**
     * Executes command.
     * @param givenCommand a String command.
     */
    public void executeCommand(final String givenCommand) {
        final String command = givenCommand.toLowerCase(Locale.ROOT);
        final String[] arrCommands = givenCommand.split("!");

        if (cellCommands(command, arrCommands)) {
            // this got executed;
        } else if (sheetCommands(command, arrCommands)) {
            // this got executed;
        } else if (importExportCommands(command, arrCommands)) {
            // this got executed;
        } else if (command.startsWith("print")) {
            updateView();
        } else if (command.startsWith("sort column")) {
            TuiHandlerUtil.handleCommand(
                    new SortColumnCommand(Integer.parseInt(arrCommands[arrCommands.length - 1])));
        }  else if (command.startsWith("formulas")) {
            TuiHandlerUtil.handleCommand(new FormulasOnCommand());
        } else if (command.startsWith("help")) {
            helpCommand();
        } else {
            System.out.println("Command not found");
        }
    }

    private boolean cellCommands(final String command, final String[] arrCommands) {
        boolean found = false;
        if (command.startsWith("select cell")) {
            selectCell(arrCommands[arrCommands.length - 1]);
            found = true;
        } else if (command.startsWith("print cell")) {
            final String location = arrCommands[arrCommands.length - 1];
            System.out.printf("Content at cell %1s : %2s \n", location, getCell(location));

            found = true;
        } else if (command.startsWith("update cell")) {
            updateCurrentCell(arrCommands[arrCommands.length - 1]);
            found = true;
        }
        return found;
    }

    private boolean sheetCommands(final String command, final String[] arrCommands) {
        boolean found = false;
        if (command.startsWith("add new sheet")) {
            final String sheetName = arrCommands[arrCommands.length - 1];
            addNewSheet(sheetName);
            found = true;
        } else if (command.startsWith("select sheet")) {
            selectSheet(arrCommands[arrCommands.length - 1]);
            found = true;
        } else if (command.startsWith("grow sheet h")) {
            try {
                model.grow("Horizontally", Integer.parseInt(arrCommands[arrCommands.length - 1]));
            } catch (SpreadsheetException exception) {
                System.out.println(exception.getMessage());
            }
            found = true;
        } else if (command.startsWith("grow sheet v")) {
            try {
                model.grow("Vertically", Integer.parseInt(arrCommands[arrCommands.length - 1]));
            } catch (SpreadsheetException exception) {
                System.out.println(exception.getMessage());
            }
            found = true;
        }
        return found;
    }

    private boolean importExportCommands(final String command, final String[] arrCommands) {
        boolean found = false;
        if (command.startsWith("import")) {
            TuiHandlerUtil.handleCommand(new ImportCommand(arrCommands[arrCommands.length - 1]));
            found = true;
        } else if (command.startsWith("open")) {
            TuiHandlerUtil.handleCommand(
                    new OpenCommand(arrCommands[arrCommands.length - 1], view));
            found = true;
        } else if (command.startsWith("export")) {
            TuiHandlerUtil.handleCommand(new ExportCommand(arrCommands[arrCommands.length - 1]));
            found = true;
        }
        return found;
    }


    private void helpCommand() {
        System.out.println("Here is a list of commands:");
        System.out.println("!!!DISCLAIMER: All comands are case insensitive, \"!\" is needed.");
        System.out.println("Select Cell !() -> () is the string location of the cell \t"
                + "i.e : B4 C3 C6");
        System.out.println("Update Cell !() -> () is the new string content of the cell \t"
                + "i.e : 5 C3+C7 =(C5+C9)");
        System.out.println("Print Cell !() -> () is the string location of the cell \t"
                + "i.e : B5 C3 C9");
        System.out.println("Add new sheet !() -> () is the name of the new sheet");
        System.out.println("Select sheet !() -> () is the name of the sheet");
        System.out.println("Grow sheet h !() -> grows sheet horizontally, to a given size");
        System.out.println("Grow sheet v !() -> grows sheet vertically, to a given size");
        System.out.println("import !() -> () -> imports a csv file into the spreadsheet, "
                +
                "from a given location");
        System.out.println("save !() -> () -> exports a csv file from the spreadsheet,"
                +
                " to a given location as a csv");
        System.out.println("Print -> Prints the spreadsheet");
    }

    /**
     * Selects a cell from a given location.
     * @param location a String cell location.
     */
    public void selectCell(final String location) {
        int x;
        int y;
        final int[] loc = Cell.parseLocation(location);
        x = loc[0];
        y = loc[1];
        TuiHandlerUtil.handleCommand(new SelectCellCommand(x,y));
    }

    /**
     * Gets a cell's content from the location.
     * @param location a String
     * @return the content of the cell.
     */
    public String getCell(final String location) {
        selectCell(location);
        return model.getCurrentCell().getText();
    }

    /**
     * Adds a new Sheet.
     * @param newTableName a String.
     */
    public void addNewSheet(final String newTableName) {
        TuiHandlerUtil.handleCommand(new AddNewSheetCommand(newTableName));
    }

    /**
     * Select a sheet based on the name.
     * @param sheetName the String name of a sheet.
     */
    public void selectSheet(final String sheetName) {
        TuiHandlerUtil.handleCommand(new SelectSheetCommand(sheetName));
    }

    /**
     * Updates the content of the currentCell.
     * @param content a String, new content.
     */
    public void updateCurrentCell(final String content) {
        TuiHandlerUtil.handleCommand(new UpdateCellCommand(model.getCurrentCell(), content));
    }

    /**
     * Gets the content of the current cell.
     * @return content of the Cell.
     */
    public String getCurrentCell() {
        return model.getCurrentCell().getText();
    }



    /**
     * Updates the view.
     */
    public void updateView() {
        view.updateView();
    }


}
