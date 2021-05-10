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

        Spreadsheet s = new Spreadsheet();
        SpreadsheetView sv = new SpreadsheetView();
        SpreadsheetController controller = new SpreadsheetController(s, sv);
        boolean cont = true;
        System.out.println("Input Command");
        //System.out.println(s.getCurrentCell().getText());

        while (true) {
            Scanner sc = new Scanner(System.in);
            String command = sc.nextLine();

            if (command.startsWith("!QUIT")) {
                System.out.println("BYE");
                break;
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
            int x;
            int y;
            int[] loc = Cell.parseLocation(arrCommands[arrCommands.length - 1]);
            x = loc[0];
            y = loc[1];
            model.selectCell(x,y);
        } else if (command.startsWith("Update Cell")) {
            model.updateCurrentCell(arrCommands[arrCommands.length - 1]);

        } else if (command.startsWith("Select Sheet")) {
            model.selectSheet(arrCommands[arrCommands.length - 1]);

        } else if (command.startsWith("Print")) {
            view.printSpreadSheet(model);
        } else {
            System.out.println("Command not found");
        }

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
