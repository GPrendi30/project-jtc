package com.spreadsheetcontroller;


import com.spreadsheet.Spreadsheet;
import com.spreadsheet.cell.Cell;
import com.spreadsheetview.SpreadsheetView;
import com.spreadsheetview.tui.SpreadsheetTui;


public class SpreadsheetController {

    private final Spreadsheet model;
    private final SpreadsheetView view;
    private Repl repl;
    private final boolean isGui;

    /**
     * Creates a new SpreadsheetController.
     * @param s a SpreadSheet.
     * @param sv a SpreadSheetView.
     * @param isGui a boolean flag to init GUI or not.
     */
    public SpreadsheetController(final Spreadsheet s,
                                 final SpreadsheetView sv,
                                 final boolean isGui) {
        model = s;
        view = sv;
        this.isGui = isGui;
        if (isGui) {
            //
        } else {
            repl = new Repl(this);
        }
    }


    /**
     * Main function.
     * @param args any args that you want.
     */
    public static void main(final String[] args) {
        boolean isGui = false;

        if (args.length != 0) {
            isGui = startGui(args[0]);
        }

        final Spreadsheet s = new Spreadsheet();
        final SpreadsheetTui tui = new SpreadsheetTui(s);
        final SpreadsheetController controller = new SpreadsheetController(s, tui, isGui);
        controller.start();

    }

    private static boolean startGui(final String arg) {
        return "gui".equals(arg);
    }

    private void start() {
        if (this.isGui) {
            System.out.println("Gui is not yet ready");
            System.out.println("Come Back Later");
            System.out.println("BYE");
            System.exit(0);
        } else {
            repl.init();
        }
    }

    /**
     * Executes command.
     * @param command a String command.
     */
    public void executeCommand(final String command) {

        final String[] arrCommands = command.split("!");

        if (command.startsWith("Add new sheet")) {
            final String sheetName = arrCommands[arrCommands.length - 1];
            model.addNewSheet(sheetName);
        } else if (command.startsWith("Select Cell")) {
            selectCell(arrCommands[arrCommands.length - 1]);
        } else if (command.startsWith("Print Cell")) {
            final String location = arrCommands[arrCommands.length - 1];
            System.out.printf("Content at cell %1s : %2s \n", location, getCell(location));
        } else if (command.startsWith("Update Cell")) {
            model.updateCurrentCell(arrCommands[arrCommands.length - 1]);
        } else if (command.startsWith("Select sheet")) {
            model.selectSheet(arrCommands[arrCommands.length - 1]);
        } else if (command.startsWith("Print")) {
            updateView();
        } else if (command.startsWith("HELP")) {
            helpCommand();
        } else {
            System.out.println("Command not found");
        }
    }

    private void helpCommand() {
        System.out.println("Here is a list of commands:");
        System.out.println("Select Cell !() -> () is the string location of the cell \t"
                + "i.e : B4 C3 C6");
        System.out.println("Update Cell !() -> () is the new string content of the cell \t"
                + "i.e : 5 C3+C7 =(C5+C9)");
        System.out.println("Print Cell !() -> () is the string location of the cell \t"
                + "i.e : B5 C3 C9");
        System.out.println("Add new sheet !() -> () is the name of the new sheet");
        System.out.println("Select sheet !() -> () is the name of the sheet");
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
        model.selectCell(x,y);
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
        model.addNewSheet(newTableName);
    }

    /**
     * Select a sheet based on the name.
     * @param sheetName the String name of a sheet.
     */
    public void selectSheet(final String sheetName) {
        model.selectSheet(sheetName);
    }

    /**
     * Updates the content of the currentCell.
     * @param content a String, new content.
     */
    public void updateCurrentCell(final String content) {
        model.updateCurrentCell(content);
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
