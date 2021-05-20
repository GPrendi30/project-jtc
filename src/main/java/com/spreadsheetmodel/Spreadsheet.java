package com.spreadsheetmodel;

import com.spreadsheetmodel.cell.Cell;
import com.spreadsheetmodel.sheet.Sheet;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Scanner;


public class Spreadsheet {

    /* TODO add import export for csv, and other formats.
       TODO add tests for packages: com.spreadsheet, com.spreadsheet.cell, com.spreadsheet.table
       TODO add controller, add cli, polish model
    */
    private static final int DEFAULT_TABLE_X;
    private static final int DEFAULT_TABLE_Y;


    static {
        DEFAULT_TABLE_X = 5;
        DEFAULT_TABLE_Y = 5;
    }

    private final ArrayList<SpreadsheetListener> listeners;
    private Sheet currentSheet;
    private Cell currentCell;
    private int openSheets;
    private final LinkedHashMap<String, Sheet> sheets;

    /**
     * Creates a new Spreadsheet.
     */
    public Spreadsheet() {
        sheets = new LinkedHashMap<>();
        final Sheet t = new Sheet(DEFAULT_TABLE_X, DEFAULT_TABLE_Y); // default values;
        listeners = new ArrayList<>();

        t.updateTableName("Sheet 1");
        sheets.put(t.getTableName(), t); // first sheet;
        openSheets = 1;
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
        fireSpreadsheetChanged();
    }


    /**
     * Selects cell at the given coordinates.
     * @param x the x coordinate
     * @param y the y coordinate
     */

    public void selectCell(final int x, final int y) {
        currentCell = currentSheet.get(x, y);
        fireSpreadsheetChanged();
    }

    /**
     * Updates content of the current Cell.
     * @param content a String, new content of the cell.
     */
    public void updateCurrentCell(final String content) {
        currentSheet.updateCell(currentCell, content);
        fireSpreadsheetChanged();
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
        fireSpreadsheetChanged();
    }

    /**
     * Returns the name of the current sheet.
     * @return name of the current sheet
     */
    public String getCurrentSheetName() {
        return currentSheet.getTableName();
    }

    /**
     * Returns the current sheet.
     * @return the currentSheet.
     */
    public Sheet getCurrentSheet() {
        return currentSheet;
    }

    /**
     * Returns the sheets of a Spreadsheet.
     * @return the sheets in an array of Sheet[].
     */
    public Sheet[] getSheets() {
        return sheets.values().toArray(new Sheet[sheets.size()]);
    }

    /**
     * Imports the content of the csv file from a path.
     * @param path a String representation of a path.
     */
    public void importCsv(final String path) {
        Scanner sc = null;
        try {
            final File csvFile = new File(path);
            sc = new Scanner(csvFile);
            this.addNewSheet(csvFile.getName());
        } catch (FileNotFoundException exception) {
            exception.printStackTrace();
        }

        sc.useDelimiter(System.getProperty("line.separator"));
        int y = 1;
        int x = 1;
        while (sc.hasNext())  //returns a boolean value
        {
            final String m = sc.next();
            final String[] l = m.split(",");
            for (final String v : l) {

                final Cell c = currentSheet.get(x,y);
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

                currentSheet.updateCell(c, v);

            }
        }
        sc.close();
        fireSpreadsheetChanged();
    }


    /**
     * Writes to a csv file
     * @param path Location where to save the csv file.
     * @throws IOException throws error if path doesnt exist.
     */
    public void exportCsv(final String path) throws IOException {
        final Path baseDir = Paths.get(path);
        if (!Files.exists(baseDir)) {
            // throw exception.
        }

        final File csvFile = new File(path);
        if (csvFile.exists()) {
            //ask for
            System.out.println("File exists, it will be overwritten.");
        } else {
            // create new file.
            csvFile.createNewFile();
        }

        final StringBuilder sb = new StringBuilder();
        for (int x = 1; x <= currentSheet.sizeX(); x++) {
            for (int y = 1; y <= currentSheet.sizeY(); y++) {
                final Cell c = currentSheet.get(x,y);
                sb.append(c.getText());
                sb.append(",");
            }
            sb.append(System.getProperty("line.separator"));
        }

        final FileWriter writer = new FileWriter(csvFile.getAbsolutePath());
        writer.write(sb.toString());
        writer.close();
    }

    /**
     * Dynamically grow the sheet.
     * @param dir String direction
     * @param size int size in that direction.
     */
    public void grow(final String dir,final int size) {
        currentSheet.grow(dir, size);
        fireSpreadsheetChanged();
    }

    /**
     * Gets the number of the open sheets currently.
     * @return current openSheets.
     */
    public int getOpenSheets() {
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
    public void incrementOpenSheets() {
        openSheets++;
    }


    /**
     * Adds a SpreadsheetListener to the list of listener objects.
     * @param li a SpreadsheetListener that will be added.
     */
    public void addListener(final SpreadsheetListener li) {
        listeners.add(li);
    }

    /**
     * Removes a listener from the list of listeners.
     * @param li a Spreadsheet listener that will be removed.
     */
    public void removeListener(final SpreadsheetListener li) {
        listeners.remove(li);
    }

    private void fireSpreadsheetChanged() {
        for (final SpreadsheetListener li : listeners) {
            li.spreasheetChanged(this);
        }
    }
}