package com.spreadsheetcontroller;


import com.spreadsheetmodel.Spreadsheet;
import com.spreadsheetmodel.cell.Cell;
import com.spreadsheetmodel.commands.Invoker;
import com.spreadsheetview.SpreadsheetView;
import com.spreadsheetview.gui.SpreadsheetGui;
import com.spreadsheetview.tui.SpreadsheetTui;

import java.io.IOException;
import java.util.Locale;


public class SpreadsheetController {

    //TODO update controller with command design pattern
    private final Spreadsheet model;
    private final SpreadsheetView view;
    private REPL repl;
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
            repl = new REPL(this);
        }
    }


    /**
     * Main function.
     * @param args any args that you want.
     */
    public static void main(final String[] args) {
        boolean guiBool = true;

        if (args.length != 0) {
            //args[0].replace(" ", "");
            guiBool = hasGui(args[0]);
            System.out.println(args[0]);
        }



        final Spreadsheet s = new Spreadsheet();

        final SpreadsheetView view = guiBool
                        ?   new SpreadsheetGui(s)
                        :   new SpreadsheetTui(s);

        final SpreadsheetController controller = new SpreadsheetController(s, view, guiBool);
        Invoker.setModel(s);
        controller.start();
    }

    private static boolean hasGui(final String arg) {
        return "gui".equals(arg);
    }

    private void start() {
        if (this.isGui) {
            view.init();
        } else {
            repl.init();
        }
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
            model.sortCol(Integer.parseInt(arrCommands[arrCommands.length - 1]));
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
            model.updateCurrentCell(arrCommands[arrCommands.length - 1]);
            found = true;
        }
        return found;
    }

    private boolean sheetCommands(final String command, final String[] arrCommands) {
        boolean found = false;
        if (command.startsWith("add new sheet")) {
            final String sheetName = arrCommands[arrCommands.length - 1];
            model.addNewSheet(sheetName);
            found = true;
        } else if (command.startsWith("select sheet")) {
            model.selectSheet(arrCommands[arrCommands.length - 1]);
            found = true;
        } else if (command.startsWith("grow sheet h")) {
            model.grow("Horizontally", Integer.parseInt(arrCommands[arrCommands.length - 1]));
            found = true;
        } else if (command.startsWith("grow sheet v")) {
            model.grow("Vertically", Integer.parseInt(arrCommands[arrCommands.length - 1]));
            found = true;
        }
        return found;
    }

    private boolean importExportCommands(final String command, final String[] arrCommands) {
        boolean found = false;
        if (command.startsWith("import")) {
            model.importCsv(arrCommands[arrCommands.length - 1]);
            found = true;
        } else if (command.startsWith("save")) {
            try {
                model.exportCsv(arrCommands[arrCommands.length - 1]);
            } catch (IOException exception) {
                System.out.println("Path doesnt exist");
                exception.printStackTrace();
            }
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
