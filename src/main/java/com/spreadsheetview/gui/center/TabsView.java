package com.spreadsheetview.gui.center;

import com.spreadsheetmodel.Spreadsheet;

import com.spreadsheetmodel.SpreadsheetEvent;
import com.spreadsheetmodel.SpreadsheetEventType;
import com.spreadsheetmodel.SpreadsheetListener;

import com.spreadsheetmodel.commands.AddNewSheetCommand;
import com.spreadsheetmodel.commands.SelectSheetCommand;

import com.spreadsheetview.gui.GuiHandlerUtil;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


public class TabsView extends JPanel {

    private Spreadsheet model;
    private ArrayList<JButton> buttonList;
    private final ArrayList<TabsViewListener> listeners;

    /**
     * Create a tabsView.
     * @param model a Spreadsheet.
     */
    public TabsView(final Spreadsheet model) {
        super();
        this.model = model;
        final FlowLayout sheetSelecterButtons = new FlowLayout();
        buttonList = new ArrayList<>();
        listeners = new ArrayList<>();

        setLayout(sheetSelecterButtons);
        final JButton firstSheet = new JButton(model.getCurrentSheetName());
        firstSheet.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent actionEvent) {
                GuiHandlerUtil.handleCommand(new SelectSheetCommand(firstSheet.getText()));
            }
        });

        final JButton addNewSheetButton = new JButton("+");
        addNewSheetButton.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                final String result = (String) JOptionPane.showInputDialog(
                        null,
                        "Set the name of the new Sheet",
                        "Input name",
                        JOptionPane.PLAIN_MESSAGE,
                        null,
                        null,
                        "New Sheet"
                );
                GuiHandlerUtil.handleCommand(new AddNewSheetCommand(result));
            }
        });

        buttonList.add(firstSheet);
        buttonList.add(addNewSheetButton);

        model.addListener(new SpreadsheetListener() {
            @Override
            public void spreadsheetChanged(final Spreadsheet s,final SpreadsheetEvent se) {
                if (se.getId() == SpreadsheetEventType.SHEET_ADDED) {
                    addNewSheetButton(model.getCurrentSheetName());
                } else if (se.getId() == SpreadsheetEventType.SHEET_REMOVED) {
                    removeAllButtons();
                    addAllButtons();
                }
            }
        });

        add(firstSheet);
        add(addNewSheetButton);
    }


    private void addNewSheetButton(final String name) {
        final JButton newSheetButton = new JButton();
        if (checkButtonNames(name)) {
            buttonList.add(buttonList.size() - 1, newSheetButton);
            newSheetButton.setText(name);
            newSheetButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(final ActionEvent actionEvent) {
                    GuiHandlerUtil.handleCommand(
                            new SelectSheetCommand(newSheetButton.getText()));
                }
            });
            add(newSheetButton);
            removeAllButtons();
            addAllButtons();
            fireTabsViewChanged();
        }

    }

    private boolean checkButtonNames(final String name) {
        for (final JButton j : buttonList) {
            if (j.getText().equals(name)) {
                return false;
            }
        }
        return true;
    }

    private void removeAllButtons() {
        for (final JButton j : buttonList) {
            this.remove(j);
        }
    }

    private void addAllButtons() {
        for (final JButton j : buttonList) {
            this.add(j);
        }
    }

    // adding listeners
    /**
     * Adds a TabsView to the list of listener objects.
     * @param li a TabsView that will be added.
     */
    public void addListener(final TabsViewListener li) {
        listeners.add(li);
    }

    /**
     * Removes a listener from the list of listeners.
     * @param li a Spreadsheet listener that will be removed.
     */
    public void removeListener(final TabsViewListener li) {
        listeners.remove(li);
    }

    private void fireTabsViewChanged() {
        for (final TabsViewListener li : listeners) {
            li.tabsViewChanged(model);
        }
    }


}
