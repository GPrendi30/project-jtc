package com.spreadsheetmodel.cell;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CellLocation implements Serializable {

    private static final String ALPHA = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    private final String column;
    private final int row;
    private final int intColumn;

    /**
     * Creates a new CellLocation from coordinates.
     * @param row the x coordinate.
     * @param col the y coordinate.
     */
    public CellLocation(final int row,final int col) {
        this.column = getColumnFromInt(col);

        this.row = row;
        this.intColumn = col;
    }

    /**
     * Gets a column starting from an int.
     * @param col the int representing the column.
     * @return String the column.
     */
    public static String getColumnFromInt(final int col) {

        if (col <= 26) {
            return (col != 0)
                    ? CellLocation.ALPHA.substring(col - 1, col)
                    : null;
        } else {
            int first_letter = (int) Math.floor(col / 26);
            int second_letter =  (col % 26);
            String firstLetter;
            String secondLetter;

            if (getColumnFromInt(second_letter) == null) {
                firstLetter = getColumnFromInt(first_letter - 1);
                secondLetter = getColumnFromInt(26);
            } else {
                firstLetter = getColumnFromInt(first_letter);
                secondLetter = getColumnFromInt(second_letter);
            }

            return firstLetter+secondLetter;
        }
    }

    /**
     * Parses the location of a Cell and returns the coordinates.
     * @param loc a String Representation of a CellLocation.
     * @return the x,y values of the CellLocation.
     */
    public static int[] parse(final String loc) {
        final List<String> location = parseString(loc);
        final String cols = location.get(0);
        final int[] coordinates = new int[2];
        coordinates[0] = Integer.parseInt(location.get(1));
        coordinates[1] = CellLocation.ALPHA.indexOf(cols) + 1;
        return coordinates;
    }

    /**
     * Parses a string.
     * @param str a String
     * @return  returns the output string, of the matched types.
     */
    private static List<String> parseString(final String str) {
        final List<String> output = new ArrayList<String>();
        final Matcher match = Pattern.compile("[0-9]+|[A-Z]+").matcher(str);
        while (match.find()) {
            output.add(match.group());
        }
        return output;
    }

    /**
     * Returns the String Column.
     * @return a String representation of the column.
     */
    public String getColumn() {
        return column;
    }

    /**
     * Returns the number of columns.
     * @return int the number of columns.
     */
    public int getIntColumn() {
        return intColumn;
    }

    /**
     * Returns the row.
     * @return the row of the location.
     */
    public int getRow() {
        return row;
    }

    /**
     * Returns a string representation of the CellLocation.
     * @return a String.
     */
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
