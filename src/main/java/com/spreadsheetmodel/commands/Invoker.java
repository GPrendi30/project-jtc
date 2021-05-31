package com.spreadsheetmodel.commands;

import com.spreadsheetmodel.Spreadsheet;

public class Invoker {

    private static Spreadsheet model;
    private static Invoker instance = new Invoker();

    /**
     * Creates a new Invoker instance.
     */
    public Invoker() {
    }

    /**
     * Sets the model of the Invoker.
     * @param s a Spreadsheet Receiver.
     */
    public static void setModel(Spreadsheet s) {
        model = s;
    }

    /**
     * Get the instance of the Invoker.
     * @return the Invoker instance.
     */
    public static Invoker getInstance() {
        return new Invoker();
    }

    /**
     * Invoke(execute) a Command
     * @param d a command.
     */
    public void invoke(Command d) {
        d.execute(model);
    }
}
