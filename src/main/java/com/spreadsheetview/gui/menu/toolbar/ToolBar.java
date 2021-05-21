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

        for (ToolbarButton t : ToolbarButton.values()) {
            addButton(t.getIcon(), t.getListener());
        }
    }

    private void addButton(ImageIcon i, ActionListener li) {
        CustomButton temp = new CustomButton(i, i.getDescription());
        temp.addActionListener(li);
        add(temp);
    }
}
