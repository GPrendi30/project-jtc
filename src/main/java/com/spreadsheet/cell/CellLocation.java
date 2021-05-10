package com.spreadsheet.cell;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CellLocation {

    private static final String ALPHA = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    private final String column;
    private final int row;


    public CellLocation(final int row, final int col) {
        this.column = col != 0
                ? CellLocation.ALPHA.substring(col - 1, col)
                : null;
        this.row = row;
    }

    public static int[] parse(String loc) {
        List<String> location = parseString(loc);
        String cols = location.get(0);
        int[] cordinates = new int[2];
        cordinates[0] = Integer.parseInt(location.get(1));
        cordinates[1] = CellLocation.ALPHA.indexOf(cols) + 1;
        return cordinates;
    }

    private static List<String> parseString(String str) {
        List<String> output = new ArrayList<String>();
        Matcher match = Pattern.compile("[0-9]+|[A-Z]+").matcher(str);
        while (match.find()) {
            output.add(match.group());
        }
        return output;
    }

    public String getColumn() {
        return column;
    }

    public int getRow() {
        return row;
    }

    public String toString() {
        String s = "";
        if (column != null) {
            s += column;
        }
        if (row != 0) {
            s += row;
        }
        return s;
    }

}
