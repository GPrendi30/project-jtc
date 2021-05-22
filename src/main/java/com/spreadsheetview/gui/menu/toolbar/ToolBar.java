package com.spreadsheetview.gui.menu.toolbar;

import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JToolBar;

public class ToolBar extends JToolBar {

    /**
     * Create a toolBar.
     */
    public ToolBar() {
        super("Draggable");

        for (final ToolbarButton t : ToolbarButton.values()) {
            addButton(t.getIcon(), t.getListener());
        }
    }

    private void addButton(final ImageIcon i, final ActionListener li) {
        final CustomButton temp = new CustomButton(i, i.getDescription());
        temp.addActionListener(li);
        add(temp);
    }
}
