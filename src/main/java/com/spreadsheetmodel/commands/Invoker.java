package com.spreadsheetmodel.commands;

import com.spreadsheetmodel.Spreadsheet;

import com.spreadsheetmodel.SpreadsheetException;

import java.util.ArrayList;

public class Invoker {

    private ArrayList<Command> commands;
    private int pointer;
    private static Spreadsheet receiver;
    private static Invoker instance = new Invoker();

    private Invoker() {
        commands = new ArrayList<>();
        pointer = -1;
    }

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
     * Invoke(execute) a Command.
     * @param d a command.
     * @throws CommandException if the command has an exception.
     */
    public void invoke(final Command d) throws CommandException {
        if (d instanceof UndoableCommand) {
            commands.add(d);
            pointer++;
        }
        executeCommand(d);
    }


    /**
     * Undo a Command.
     * @throws CommandException if the command has an exception.
     */
    public void undo() throws CommandException {
        if (pointer > 1) {
            final Command undoCommand = commands.get(pointer--);
            undoCommand(undoCommand);
        }
    }

    /**
     * Redo a Command.
     * @throws CommandException if the command has an exception.
     */
    public void redo() throws CommandException {
        if (pointer < commands.size() - 1 ) {
            final Command redoCommand = commands.get(pointer++);
            redoCommand(redoCommand);
            pointer++;
        }
    }

    // private helper methods.
    private void executeCommand(final Command c) throws CommandException {
        try {
            c.execute(receiver);
        } catch (SpreadsheetException exception) {
            throw new CommandException(exception.getMessage(), exception);
        }
    }

    private void undoCommand(final Command c) throws CommandException {
        try {
            c.undo(receiver);
        } catch (SpreadsheetException exception) {
            throw new CommandException(exception.getMessage(), exception);
        }
    }

    private void redoCommand(final Command c) throws CommandException {
        try {
            c.redo(receiver);
        } catch (SpreadsheetException exception) {
            throw new CommandException(exception.getMessage(), exception);
        }
    }
}
