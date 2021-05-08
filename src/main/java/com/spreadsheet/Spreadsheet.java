package com.spreadsheet;

import com.spreadsheet.cell.Cell;
import com.spreadsheet.table.Table;

import java.util.HashMap;

public class Spreadsheet {

    /* TODO add import export for csv, and other formats.
       TODO add tests for packages: com.spreadsheet, com.spreadsheet.cell, com.spreadsheet.table
       TODO add controller, add cli, polish model
    */
    private static final int DEFAULT_TABLE_X;
    private static final int DEFAULT_TABLE_Y;
    private static int openSheets;

    static {
        DEFAULT_TABLE_X = 15;
        DEFAULT_TABLE_Y = 15;
        openSheets = 0;
    }

    private Table currentSheet;
    private Cell currentCell;
    private final HashMap<String, Table> sheets;


    public Spreadsheet() {
        sheets = new HashMap<>();
        final Table t = new Table(DEFAULT_TABLE_X, DEFAULT_TABLE_Y); // default values;
        t.updateTableName("Sheet 1");
        sheets.put(t.getTableName(), t); // first sheet;
        incrementOpenSheets();
        selectSheet("Sheet 1");
    }

    public void addNewSheet(final String tableName) {
        if (openSheets >= 10) {
            setOpenSheets(10);
            return;
        }

        final Table t = new Table(DEFAULT_TABLE_X, DEFAULT_TABLE_Y, tableName);
        incrementOpenSheets();
        if (tableName == null || tableName.length() == 0) {
            t.updateTableName("Sheet " + openSheets);
        }

        sheets.put(t.getTableName(), t);
        selectSheet(t.getTableName());
    }


    public void selectCell(final int x, final int y) {
        currentCell = currentSheet.get(x, y);
    }

    public void updateCurrentCell(final String content) {
        currentSheet.updateCell(currentCell, content);
        currentSheet.addFormula(currentCell);
    }

    public Cell getCurrentCell() {
        return currentCell;
    }

    public void selectSheet(final String sheetName) {
        currentSheet = sheets.get(sheetName);
    }

    public void printCurrentSheet() {
        currentSheet.print();
        for (final Table t : sheets.values()) {
            String tName = t.getTableName();
            if (tName.equals(currentSheet.getTableName())) {
                tName += "*";
            }
            System.out.print("          \\ " + tName + " /            ");
        }
        System.out.println();
        currentSheet.printFormulas();
    }

    public static int getOpenSheets() {
        return openSheets;
    }

    private void setOpenSheets(final int i) {
        openSheets = i;
    }

    public static void incrementOpenSheets() {
        openSheets++;
    }

}