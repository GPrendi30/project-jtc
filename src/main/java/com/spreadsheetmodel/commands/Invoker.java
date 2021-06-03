package com.spreadsheetmodel.commands;

import com.spreadsheetmodel.Spreadsheet;

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
     */
    public void invoke(final Command d) {
        commands.add(d);
        pointer++;
        d.execute(receiver);
        System.out.println("execute command");
    }


    /**
     * Undo a Command.
     */
    public void undo() {
        final Command undoCommand = commands.get(pointer--);
        undoCommand.undo(receiver);
        System.out.println("undo command");
    }


    /**
     * Redo a Command.
     */
    public void redo() {
        final Command redoCommand = commands.get(pointer++);
        redoCommand.redo(receiver);
        System.out.println("redo command");
    }

}
