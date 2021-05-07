package com.spreadsheet.cell;

public class Cell {

    final static String alpha = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    protected String cellLocation;

    public cell(int x, int y) {
        this.cellLocation = calcLocation(x, y);
    }

    public Type getType() {
        return Type.INVALID;
    }

    public String getText() {
        // maybe change name to toString()
        return "?";
    }

    public String getLocation() {
        return this.cellLocation;
    }

    public void calcLocation(int x, int y) {
        if (x == 0) {
            this.cellLocation = "" + y;
            return;
        } else if (y == 0) {
            this.cellLocation = Cell.alpha.substring(x-1, x);
            return;
        }       
        this.cellLocation = Cell.alpha.substring(x-1, x) + y;
    }
}
