package com.spreadsheetmodel.commands;

import com.spreadsheetmodel.Spreadsheet;

public class Invoker {

    private static Spreadsheet receiver;
    private static Invoker instance = new Invoker();

    /**
     * Sets the model of the Invoker.
     * @param s a Spreadsheet Receiver.
     */
    public static void setReceiver(final Spreadsheet s) {
        receiver = s;
    }

    /**
     * Get the instance of the Invoker.
     * @return the Invoker instance.
     */
    public static Invoker getInstance() {
        return instance;
    }

    /**
     * Invoke(execute) a Command
     * @param d a command.
     */
    public void invoke(final Command d) {
        d.execute(receiver);
    }
}
