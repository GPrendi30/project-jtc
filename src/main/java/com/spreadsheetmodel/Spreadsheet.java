package com.spreadsheetmodel;

import com.spreadsheetmodel.cell.Cell;
import com.spreadsheetmodel.sheet.Sheet;

import com.spreadsheetview.tui.SpreadsheetTui;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Scanner;


public class Spreadsheet implements Serializable {

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
     * Writes a Spreadsheet object to a file.
     * @param path a String path
     * @param s a SpreadSheet object.
     * @throws IOException in case the file doesn't exist.
     */
    public static void writeToFile(final String path,final Spreadsheet s) throws IOException  {

        final Path targetPath = Paths.get(path);

        final ObjectOutputStream oos = new ObjectOutputStream(
                new FileOutputStream(targetPath.toString()));

        oos.writeObject(s);

    }

    /**
     * Reads from a file, and returns a Spreadsheet object.
     * @param path the String path to a jtc file.
     * @return a Spreadsheet.
     * @throws IOException throws an error in case it
     */
    public static Spreadsheet readFromFile(final String path) throws IOException  {
        final Path inputPath = Paths.get(path);

        final ObjectInputStream ois = new ObjectInputStream(
                new FileInputStream(inputPath.toString()));


        Spreadsheet deserSpreadsheet = null;

        try {
            deserSpreadsheet = (Spreadsheet) ois.readObject();
        } catch (ClassNotFoundException exception) {
            // TODO Auto-generated catch block
            exception.printStackTrace();
        }
        return deserSpreadsheet;
    }

    /**
     * Adds a new Sheet with a given name.
     * @param tableName a String tableName.
     * @return boolean a boolean.
     */
    public boolean addNewSheet(final String tableName) {
        final int prevOpenSheets = openSheets;
        if (openSheets >= 10) {
            setOpenSheets(10);
            return false;
        }

        final Sheet t = new Sheet(DEFAULT_TABLE_X, DEFAULT_TABLE_Y, tableName);
        incrementOpenSheets();
        if (tableName == null || tableName.length() == 0) {
            t.updateTableName("Sheet " + openSheets);
        }

        if (checkSheetName(tableName)) {
            sheets.put(t.getTableName(), t);
            selectSheet(t.getTableName());
            if (prevOpenSheets < getOpenSheets()) {
                fireSpreadsheetChanged(
                        new SpreadsheetEvent("Sheet added", SpreadsheetEventType.SHEET_ADDED));
                return true;
            }
        }
        return false;

    }

    private boolean checkSheetName(final String tableName) {
        for (String sheetName : sheets.keySet()) {
            if (sheetName.equals(tableName)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Selects cell at the given coordinates.
     * @param x the x coordinate
     * @param y the y coordinate
     */
    public void selectCell(final int x, final int y) {
        currentCell = currentSheet.getCell(x, y);
        fireSpreadsheetChanged(
                new SpreadsheetEvent("Sheet added", SpreadsheetEventType.CELL_CHANGED));
    }

    /**
     * Selects cell at the given coordinates.
     * @param location the string location, ex: A1 B3
     */
    public void selectCell(final String location) {
        currentCell = currentSheet.getCell(location);
        fireSpreadsheetChanged(
                new SpreadsheetEvent("Sheet added", SpreadsheetEventType.CELL_CHANGED));
    }

    /**
     * Updates content of the current Cell.
     * @param content a String, new content of the cell.
     */
    public void updateCurrentCell(final String content) {
        updateCell(currentCell, content);
    }

    /**
     * Fills the formulas.
     */
    public void formulasOn() {
        currentSheet.fillFormulas();
        fireSpreadsheetChanged(
                new SpreadsheetEvent("Sheet added", SpreadsheetEventType.SHEET_CHANGED));
    }

    /**
     * Used to sort a column.
     * @param col the cell
     */
    public void sortCol(final int col) {
        currentSheet.sortColumn(col);
        fireSpreadsheetChanged(
                new SpreadsheetEvent("Sheet added", SpreadsheetEventType.SHEET_CHANGED));
    }

    /**
     * Updates the current Cell.
     * @param c the cell
     * @param content a String, new content of the cell.
     */
    private void updateCell(final Cell c, final String content) {
        currentSheet.updateCell(c, content);
        //currentSheet.reEvalFormulas();
        fireSpreadsheetChanged(
                new SpreadsheetEvent("Sheet added", SpreadsheetEventType.CELL_CHANGED));
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
        selectCell(1,1);
        fireSpreadsheetChanged(
                new SpreadsheetEvent("Sheet added", SpreadsheetEventType.SHEET_SELECTED));
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

                final Cell c = currentSheet.getCell(x,y);
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
        fireSpreadsheetChanged(
                new SpreadsheetEvent("Sheet added", SpreadsheetEventType.SHEET_CHANGED));
    }

    /**
     * Gets the formula specified in the param cell.
     * @param c the cell
     * @return String the formula.
     */
    public String getFormula(final Cell c) {
        return currentSheet.getFormula(c.getLocation());
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
                final Cell c = currentSheet.getCell(x,y);
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
        fireSpreadsheetChanged(
                new SpreadsheetEvent("Sheet added", SpreadsheetEventType.TABLE_GROW));
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

    private void fireSpreadsheetChanged(SpreadsheetEvent se) {
        for (final SpreadsheetListener li : listeners) {
            li.spreadsheetChanged(this, se);
        }
    }

    /**
     * Main function.
     * @param args a String[].
     */
    public static void main(String[] args) {
        Spreadsheet s = new Spreadsheet();
        SpreadsheetTui t = new SpreadsheetTui(s);

        t.updateView();
        s.selectCell(3,3);
        s.updateCurrentCell("=B5");

        s.getCurrentSheet().update(4,4,"3");
        s.updateCurrentCell("3");
        t.updateView();
        s.selectCell("B5");
        t.updateView();
        s.updateCurrentCell("10");
        t.updateView();
    }
}