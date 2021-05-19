package com.spreadsheetcontroller;

import java.util.Scanner;

public class Repl {

    private final SpreadsheetController controller;

    /**
     * Creates a new repl for a Spreadsheet controller.
     * @param controller a SpreadsheetController.
     */
    public Repl(final SpreadsheetController controller) {
        this.controller = controller;
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
                controller.executeCommand(command);
            } else {

                System.out.println("Type something");
            }


        }
    }

}
