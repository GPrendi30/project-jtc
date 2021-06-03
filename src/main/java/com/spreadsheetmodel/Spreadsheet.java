package com.spreadsheetmodel;

import com.spreadsheetmodel.cell.Cell;
import com.spreadsheetmodel.sheet.Sheet;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Scanner;


public class Spreadsheet implements Serializable {

    private final ArrayList<SpreadsheetListener> listeners;
    private Sheet currentSheet;
    private Cell currentCell;
    private final LinkedHashMap<String, Sheet> sheets;


    /**
     * Creates a new Spreadsheet.
     * @param x the x coordinate
     * @param y the y coordinate
     */
    public Spreadsheet(final int x, final int y) {
        sheets = new LinkedHashMap<>();
        final Sheet t = new Sheet(x,y); // default values;
        listeners = new ArrayList<>();

        t.updateTableName("Sheet 1");
        sheets.put(t.getTableName(), t); // first sheet
        selectSheet("Sheet 1");

    }

    /**
     * Adds a new sheet.
     * @param sheet a Sheet to be added.
     */
    public void addSheet(final Sheet sheet) {
        sheets.put(sheet.getTableName(), sheet);
        selectSheet(sheet.getTableName());
    }

    /**
     * Removes a sheet from the sheets.
     * @param sheetName a Sheet to be removed.
     */
    public void removeSheet(final String sheetName) throws SpreadsheetException {
        if (sheets.size() <= 1) {
            throw new SpreadsheetException("Is not possible to remove the last sheet");
        }
        sheets.remove(sheetName);
        fireSpreadsheetChanged(new SpreadsheetEvent("Sheet removed",
                SpreadsheetEventType.SHEET_REMOVED));
    }


    /**
     * Adds a new Sheet with a given name.
     * @param tableName a String tableName.
     * @return boolean a boolean.
     */
    public boolean addNewSheet(final String tableName) {
        final Sheet t = new Sheet(20, 20, tableName);

        if (checkSheetName(tableName)) {
            sheets.put(t.getTableName(), t);
            selectSheet(t.getTableName());

            fireSpreadsheetChanged(
                        new SpreadsheetEvent("Sheet added", SpreadsheetEventType.SHEET_ADDED));

            return true;
        }

        return false;
    }

    private boolean checkSheetName(final String tableName) {
        for (final String sheetName : sheets.keySet()) {
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
                new SpreadsheetEvent("Cell " + currentCell.getLocation().toString() + " selected ",
                        SpreadsheetEventType.CELL_SELECTED));
    }

    /**
     * Selects cell at the given coordinates.
     * @param location the string location, ex: A1 B3
     */
    public void selectCell(final String location) {
        currentCell = currentSheet.getCell(location);
        fireSpreadsheetChanged(
                new SpreadsheetEvent("Sheet " + getCurrentSheetName() + " selected",
                        SpreadsheetEventType.CELL_CHANGED));
    }

    /**
     * Update Cell at given coordinates.
     * @param x the x coordinate
     * @param y the y coordinate
     * @param content the String content.
     */
    public void updateCell(final int x, final int y, final String content) {
        currentCell = currentSheet.getCell(x, y);
        currentCell.updateContent(content);
        fireSpreadsheetChanged(
                new SpreadsheetEvent("Cell" + currentCell.getLocation().toString() + " updated" ,
                        SpreadsheetEventType.CELL_CHANGED));
    }

    /**
     * Updates the current Cell.
     * @param c the cell
     * @param content a String, new content of the cell.
     */
    public void updateCell(final Cell c, final String content) {
        currentSheet.updateCell(c, content);
        currentSheet.reEvalFormulas();
        fireSpreadsheetChanged(
                new SpreadsheetEvent("Cell" + currentCell.getLocation().toString() + " updated",
                        SpreadsheetEventType.CELL_CHANGED));
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
                new SpreadsheetEvent("Formulas filled in",
                        SpreadsheetEventType.SHEET_CHANGED));
    }

    /**
     * Used to sort a column.
     * @param col the cell
     */
    public void sortCol(final int col) {
        currentSheet.sortColumn(col);
        fireSpreadsheetChanged(
                new SpreadsheetEvent("Column " + (col + 1) + " sorted ",
                        SpreadsheetEventType.SHEET_CHANGED));
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
                new SpreadsheetEvent("Sheet selected",
                        SpreadsheetEventType.SHEET_SELECTED));
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
     * @throws IOException if it can't find the file to import.
     * @throws SpreadsheetException if it can't import the file.
     */
    public void importCsv(final String path) throws IOException, SpreadsheetException {
        Scanner sc = null;
        try {
            final File csvFile = new File(path);
            sc = new Scanner(csvFile);
            this.addNewSheet(csvFile.getName());
        } catch (FileNotFoundException exception) {
            throw new IOException("File at  the given path " + path + " doesnt exist", exception);
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

                currentSheet.updateCell(c, v.replace(";",","));

            }
        }
        sc.close();
        fireSpreadsheetChanged(
                new SpreadsheetEvent("A csv file imported",
                        SpreadsheetEventType.SHEET_CHANGED));
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
        final Path baseDir = Paths.get(path).getParent();
        if (!Files.exists(baseDir)) {
            throw new IOException("Directory " + baseDir + " doesnt exist");
        }

        final File csvFile = new File(path);
        if (csvFile.exists()) {
            throw new FileAlreadyExistsException("File at given path " + path + "already exists. ");
        } else {
            // create new file.
            csvFile.createNewFile();
        }
        formulasOn();
        final StringBuilder sb = new StringBuilder();
        for (int x = 1; x <= currentSheet.sizeX(); x++) {
            for (int y = 1; y <= currentSheet.sizeY(); y++) {
                final Cell c = currentSheet.getCell(x,y);
                final String content = c.getText();
                sb.append(content.replace(",", ";"));
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
     * @throws SpreadsheetException if it can't grow the Spreadsheet.
     */
    public void grow(final String dir,final int size) throws SpreadsheetException {
        currentSheet.grow(dir, size);
        fireSpreadsheetChanged(
                new SpreadsheetEvent("Sheet grow",
                        SpreadsheetEventType.TABLE_GROW));
    }


    /**
     * Adds a SpreadsheetListener to the list of listener objects.
     * @param li a SpreadsheetListener that will be added.
     */
    public void addListener(final SpreadsheetListener li) {
        listeners.add(li);
    }


    private void fireSpreadsheetChanged(final SpreadsheetEvent se) {
        for (final SpreadsheetListener li : listeners) {
            li.spreadsheetChanged(this, se);
        }
    }
}