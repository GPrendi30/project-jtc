package com.spreadsheetview.gui.menu.toolbar;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class ToolBar extends JToolBar {

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
