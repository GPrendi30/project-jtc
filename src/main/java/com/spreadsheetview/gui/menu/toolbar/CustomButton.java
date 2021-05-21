package com.spreadsheetview.gui.menu.toolbar;

import javax.swing.*;
import java.awt.*;

public class CustomButton extends JButton {

    private final String description;

    public CustomButton(Icon i, String description) {
        super(i);
        this.description = description;
        setBorder(null);
        setMargin(new Insets(10,0,10,0));

        setMaximumSize(new Dimension(100, 100));
        setMinimumSize(new Dimension(64, 64));

        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                setBackground(Color.GRAY);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                setBackground(UIManager.getColor("control"));
            }
        });
    }

}
