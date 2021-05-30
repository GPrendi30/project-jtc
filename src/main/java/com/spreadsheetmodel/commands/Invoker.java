package com.spreadsheetmodel.commands;

import com.spreadsheetmodel.Spreadsheet;

public class Invoker {

    private static Spreadsheet model;
    private static Invoker instance = new Invoker();

    public Invoker() {
    }

    public static void setModel(Spreadsheet s) {
        model = s;
    }

    public static Invoker getInstance() {
        return new Invoker();
    }

    public void invoke(Command d) {
        d.execute(model);
    }
}
