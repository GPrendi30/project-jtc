package com.spreadsheetview.tui;

import java.util.Scanner;

/**
 * The REPL.
 *
 * @author Di Pietro Enrico, Prendi Gerald.
 *
 */
public class REPL {

    private final TuiCommand tuiCommand;

    /**
     * Creates a new repl for a SpreadsheetTui.
     * @param tuiCommand a tuiCommand that the repl uses to execute.
     */
    public REPL(final TuiCommand tuiCommand) {
        this.tuiCommand = tuiCommand;
    }

    /**
     * Starts the repl.
     */
    public void init() {
        System.out.println("Welcome to JavaTabularCalculator. \n"
                + "Type HELP to see a list of commands");
        System.out.println("Input Command");
        while (true) {
            final Scanner sc = new Scanner(System.in);
            final String command;
            if (sc.hasNext()) {
                command = sc.nextLine();
                System.out.println(command);
                if (command.startsWith("!QUIT")) {
                    System.out.println("BYE");
                    break;
                }
                tuiCommand.executeCommand(command);
            } else {
                System.out.println("Type something");
            }


        }
    }

}
