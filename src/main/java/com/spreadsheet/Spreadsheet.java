package com.spreadsheet;

import com.spreadsheet.cell.Cell;
import com.spreadsheet.sheet.Sheet;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedHashMap;
import java.util.Scanner;

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

    private Sheet currentSheet;
    private Cell currentCell;
    private final LinkedHashMap<String, Sheet> sheets;

    /**
     * Creates a new Spreadsheet.
     */
    public Spreadsheet() {
        sheets = new LinkedHashMap<>();
        final Sheet t = new Sheet(DEFAULT_TABLE_X, DEFAULT_TABLE_Y); // default values;
        t.updateTableName("Sheet 1");
        sheets.put(t.getTableName(), t); // first sheet;
        incrementOpenSheets();
        selectSheet("Sheet 1");
    }

    /**
     * Adds a new Sheet with a given name.
     * @param tableName a String tableName.
     */
    public void addNewSheet(final String tableName) {
        if (openSheets >= 10) {
            setOpenSheets(10);
            return;
        }

        final Sheet t = new Sheet(DEFAULT_TABLE_X, DEFAULT_TABLE_Y, tableName);
        incrementOpenSheets();
        if (tableName == null || tableName.length() == 0) {
            t.updateTableName("Sheet " + openSheets);
        }

        sheets.put(t.getTableName(), t);
        selectSheet(t.getTableName());
    }


    /**
     * Selects cell at the given coordinates.
     * @param x the x coordinate
     * @param y the y coordinate
     */

    public void selectCell(final int x, final int y) {
        currentCell = currentSheet.get(x, y);
    }

    /**
     * Updates content of the current Cell.
     * @param content a String, new content of the cell.
     */
    public void updateCurrentCell(final String content) {
        currentSheet.updateCell(currentCell, content);
    }

    /**
     * Returns current Cell.
     * @return a Cell: currentCell
     */
    public Cell getCurrentCell() {
        return currentCell;
    }

    /**
     * Selects a sheet with the given name.
     * @param sheetName a String sheetName.
     */
    public void selectSheet(final String sheetName) {
        currentSheet = sheets.get(sheetName);
        selectCell(0,0);
    }

    /**
     * Prints currentSheet.
     */
    public void printCurrentSheet() {
        currentSheet.print();
        this.printSheetNames();
        currentSheet.printFormulas();
    }

    private void printSheetNames() {
        for (final Sheet t : sheets.values()) {
            String tName = t.getTableName();
            if (tName.equals(currentSheet.getTableName())) {
                tName += "*";
            }
            System.out.print("          \\ " + tName + " /            ");
        }
        System.out.println();
    }

    /**
     * Imports the content of the csv file from a path.
     * @param path a String representation of a path.
     */
    public void importCsv(final String path) {
        Scanner sc = null;
        try {
            sc = new Scanner(new File(path));
        } catch (FileNotFoundException exception) {
            exception.printStackTrace();
        }
        sc.useDelimiter("\n");
        int y = 1;
        int x = 1;
        while (sc.hasNext())  //returns a boolean value
        {
            final String m = sc.next();
            final String[] l = m.split(",");
            for (final String v : l) {

                selectCell(x,y);
                if (y < l.length) {
                    y++;
                } else {
                    x++;
                    y = 1;
                }

                if (x > currentSheet.sizeX()) {
                    currentSheet.grow("Vertically", 2 * currentSheet.sizeX());
                }

                if (y > currentSheet.sizeY()) {
                    currentSheet.grow("Horizontally", 2 * currentSheet.sizeY());
                }

                updateCurrentCell(v);

            }
        }
        sc.close();
    }


    /**
     * Dynamically grow the sheet.
     * @param dir String direction
     * @param size int size in that direction.
     */
    public void grow(final String dir,final int size) {
        currentSheet.grow(dir, size);
    }

    /**
     * Gets the number of the open sheets currently.
     * @return current openSheets.
     */
    public static int getOpenSheets() {
        return openSheets;
    }

    /**
     * Sets the number of openSheets.
     * @param i the number of the openSheets.
     */
    private void setOpenSheets(final int i) {
        openSheets = i;
    }

    /**
     * Increments openSheets by 1.
     */
    public static void incrementOpenSheets() {
        openSheets++;
    }

}