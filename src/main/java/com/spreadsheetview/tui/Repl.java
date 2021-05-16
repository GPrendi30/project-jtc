package com.spreadsheetview.tui;

import java.util.Scanner;

public class Repl {

    private final SpreadsheetTui spreadsheetTui;

    /**
     * Creates a new repl for a tui.
     * @param spreadsheetTui a TUI.
     */
    public Repl(final SpreadsheetTui spreadsheetTui) {
        this.spreadsheetTui = spreadsheetTui;
    }

    /**
     * Starts the repl.
     */
    public void init() {
        System.out.println("Welcome to JavaTabularCalculator. \nInput Command");
        while (true) {
            final Scanner sc = new Scanner(System.in);
            final String command = sc.nextLine();
            if (command.startsWith("!QUIT")) {
                System.out.println("BYE");
                break;
            }

            spreadsheetTui.executeCommand(command);
        }
    }

}
