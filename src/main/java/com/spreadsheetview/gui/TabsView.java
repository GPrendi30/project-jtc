package com.spreadsheetview.gui;

import com.spreadsheetmodel.Spreadsheet;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import java.util.ArrayList;

public class TabsView extends JPanel {

    private Spreadsheet model;
    private FlowLayout sheetSelecterButtons;
    private ArrayList<JButton> buttonList;
    private JButton addNewSheetButton;
    private final ArrayList<TabsViewListener> listeners;

    public TabsView(Spreadsheet model) {
        super();
        this.model = model;
        sheetSelecterButtons = new FlowLayout();
        buttonList = new ArrayList<>();
        listeners = new ArrayList<>();

        setLayout(sheetSelecterButtons);
        JButton firstSheet = new JButton(model.getCurrentSheetName());


        addNewSheetButton = new JButton("+");
        addNewSheetButton.addActionListener(new ActionListener() {
           public void actionPerformed(ActionEvent e) {
               addNewSheet();
           }
        });

        buttonList.add(firstSheet);
        buttonList.add(addNewSheetButton);

        add(firstSheet);
        add(addNewSheetButton);
    }

    private void addNewSheet() {
        // TODO Create a new JButton with the name of the sheet that you want and connect it to a sheet
        JButton newSheetButton = new JButton();
        String name = "Placeholder";
        buttonList.add(buttonList.size()-1, newSheetButton);
        model.addNewSheet(name);
        newSheetButton.setText(name);
        add(newSheetButton);
        removeAllButtons();
        addAllButtons();
        fireTabsViewChanged();
    }

    private void removeAllButtons() {
        for (JButton j : buttonList) {
            remove(j);
        }
    }

    private void addAllButtons() {
        for (JButton j : buttonList) {
            add(j);
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
