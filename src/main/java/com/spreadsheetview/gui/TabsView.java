package com.spreadsheetview.gui;

import com.spreadsheetmodel.Spreadsheet;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
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
            public void actionPerformed(ActionEvent actionEvent) {
                model.selectSheet(firstSheet.getText());
            }
        });

        final JButton addNewSheetButton = new JButton("+");
        addNewSheetButton.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                addNewSheet();
            }
        });

        buttonList.add(firstSheet);
        buttonList.add(addNewSheetButton);

        add(firstSheet);
        add(addNewSheetButton);
    }

    private void addNewSheet() {
        final JButton newSheetButton = new JButton();
        final String name = "Placeholder";
        final boolean addSheet = model.addNewSheet(name);
        System.out.println(addSheet);
        if (addSheet) {
            buttonList.add(buttonList.size() - 1, newSheetButton);
            newSheetButton.setText(name);
            newSheetButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent actionEvent) {
                    model.selectSheet(newSheetButton.getText());
                }
            });
            add(newSheetButton);
            removeAllButtons();
            addAllButtons();
            fireTabsViewChanged();
        }

    }

    private void removeAllButtons() {
        for (final JButton j : buttonList) {
            remove(j);
        }
    }

    private void addAllButtons() {
        for (final JButton j : buttonList) {
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
