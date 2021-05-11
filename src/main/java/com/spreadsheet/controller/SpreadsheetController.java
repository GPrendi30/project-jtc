package com.spreadsheet.controller;


import com.spreadsheet.Spreadsheet;
import com.spreadsheet.cell.Cell;
import com.spreadsheet.view.SpreadsheetView;

import java.util.Scanner;


public class SpreadsheetController {

    private static final  String DEFAULT_SHEET_NAME;

    static {
        DEFAULT_SHEET_NAME = "Sheet 1";
    }

    private final Spreadsheet model;
    private final SpreadsheetView view;

    public SpreadsheetController(Spreadsheet s, SpreadsheetView sv) {
        model = s;
        view = sv;
    }

    public static void main(String[] args) {

        final Spreadsheet s = new Spreadsheet();
        final SpreadsheetView sv = new SpreadsheetView();
        final SpreadsheetController controller = new SpreadsheetController(s, sv);
        System.out.println("Input Command");
        //System.out.println(s.getCurrentCell().getText());

        while (true) {
            Scanner sc = new Scanner(System.in);
            String command = sc.nextLine();

            if (command.startsWith("!QUIT")) {
                System.out.println("BYE");
                break;
            } if (command.startsWith("HELP")) {
                System.out.println("Here is a list of commands:");
                System.out.println("Select Cell !() -> () is the string location of the cell \t i.e : B4 C3 C6");
                System.out.println("Update Cell !() -> () is the new string content of the cell \t i.e : 5 C3+C7 =(C5+C9)");
                System.out.println("Print Cell !() -> () is the string location of the cell \t i.e : B5 C3 C9");
                System.out.println("Add new sheet !() -> () is the name of the new sheet");
                System.out.println("Select sheet !() -> () is the name of the sheet");
                System.out.println("Print -> Prints the spreadsheet");
                continue;
            }

            controller.executeCommand(command);
        }

    }

    public void executeCommand(final String command) {

        final String[] arrCommands = command.split("!");

        if (command.startsWith("Add new sheet")) {
            final String sheetName = arrCommands[arrCommands.length - 1];
            model.addNewSheet(sheetName);

        } else if (command.startsWith("Select Cell")) {
            selectCell(arrCommands[arrCommands.length - 1]);
        } else if (command.startsWith("Print Cell")) {
            String location = arrCommands[arrCommands.length - 1];
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

    public void selectCell(final String location) {
        int x;
        int y;
        int[] loc = Cell.parseLocation(location);
        x = loc[0];
        y = loc[1];
        model.selectCell(x,y);
    }

    public String getCell(final String location) {
        selectCell(location);
        return model.getCurrentCell().getText();
    }

    public void addNewSheet(final String newTableName) {
        model.addNewSheet(newTableName);
    }

    public void selectCell(final int x, final int y) {
        model.selectCell(x,y);
    }

    public void selectSheet(final String sheetName) {
        model.selectSheet(sheetName);
    }

    public void updateCurrentCell(final String content) {
        model.updateCurrentCell(content);
    }

    public String getCurrentCell() {
        return model.getCurrentCell().getText();
    }


    public void updateView() {
        view.printSpreadSheet(model);
    }

}
