package com.spreadsheetcontroller;


import com.spreadsheet.Spreadsheet;
import com.spreadsheet.cell.Cell;
import com.spreadsheetview.cli.SpreadsheetView;

import java.util.Scanner;


public class SpreadsheetController {

    private final Spreadsheet model;
    private final SpreadsheetView view;

    /**
     * Creates a new SpreadsheetController.
     * @param s a SpreadSheet.
     * @param sv a SpreadSheetView.
     */
    public SpreadsheetController(final Spreadsheet s, final SpreadsheetView sv) {
        model = s;
        view = sv;
    }

    /**
     * Main function.
     * @param args any args that you want.
     */
    public static void main(final String[] args) {

        final Spreadsheet s = new Spreadsheet();
        final SpreadsheetView sv = new SpreadsheetView();
        final SpreadsheetController controller = new SpreadsheetController(s, sv);
        System.out.println("Input Command");
        //System.out.println(s.getCurrentCell().getText());

        while (true) {
            final Scanner sc = new Scanner(System.in);
            final String command = sc.nextLine();

            if (command.startsWith("!QUIT")) {
                System.out.println("BYE");
                break;
            } else if (command.startsWith("HELP")) {
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
                continue;
            }

            controller.executeCommand(command);
        }

    }

    /**
     * Executes a command given by the user.
     * @param command a String
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
            view.printSpreadSheet(model);
        } else {
            System.out.println("Command not found");
        }

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
        view.printSpreadSheet(model);
    }

}
