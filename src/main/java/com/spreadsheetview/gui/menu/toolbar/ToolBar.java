package com.spreadsheetview.gui.menu.toolbar;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeListener;
import javax.swing.*;

public class ToolBar extends JToolBar {

    /**
     * Create a toolBar.
     */
    public ToolBar() {
        super("Toolbar");

        for (final ToolbarButton t : ToolbarButton.values()) {
            addButton(t.getIcon(), t.getListener());
        }
        FlowLayout layout = new FlowLayout();
        layout.setAlignment(FlowLayout.LEFT);
        setLayout(layout);

    }

    private void addButton(final ImageIcon i, final ActionListener li) {
        final CustomButton temp = new CustomButton(i, i.getDescription());
        temp.addActionListener(li);
        add(temp);
    }
}
