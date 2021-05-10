package com.spreadsheet;

import com.spreadsheet.cell.Cell;
import com.spreadsheet.table.Table;

import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
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

    private Table currentSheet;
    private Cell currentCell;
    private final LinkedHashMap<String, Table> sheets;


    public Spreadsheet() {
        sheets = new LinkedHashMap<>();
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
    }

    public Cell getCurrentCell() {
        return currentCell;
    }

    public void selectSheet(final String sheetName) {
        currentSheet = sheets.get(sheetName);
        selectCell(0,0);
    }

    public void printCurrentSheet() {
        currentSheet.print();
        this.printSheetNames();
        currentSheet.printFormulas();
    }

    private void printSheetNames(){
        for (final Table t : sheets.values()) {
            String tName = t.getTableName();
            if (tName.equals(currentSheet.getTableName())) {
                tName += "*";
            }
            System.out.print("          \\ " + tName + " /            ");
        }
        System.out.println();
    }

    public void importCsv(String path) {
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
            String m = sc.next();
            String[] l = m.split(",");
            for (String v : l) {

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



    public void grow(String dir, int size) {
        currentSheet.grow(dir, size);
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