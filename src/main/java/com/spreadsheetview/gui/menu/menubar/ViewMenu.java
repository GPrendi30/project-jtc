package com.spreadsheetview.gui.menu.menubar;

import com.spreadsheetmodel.commands.FormulasOnCommand;
import com.spreadsheetview.gui.GuiHandlerUtil;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The menu view.
 *
 * @author Di Pietro Enrico, Prendi Gerald.
 *
 */
public class ViewMenu extends Menu {

    /**
     * Makes the view of the menu.
     */
    public ViewMenu() {
        super("View");


        addMenu("Show/Hide formulae", new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent actionEvent) {
                GuiHandlerUtil.handleCommand(new FormulasOnCommand());
            }
        });

    }


}
