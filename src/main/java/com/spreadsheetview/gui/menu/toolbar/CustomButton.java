package com.spreadsheetview.gui.menu.toolbar;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Insets;
import javax.swing.*;

public class CustomButton extends JButton {

    /* default */ final String description;

    /**
     * Create a customButton.
     * @param i an Icon for the button.
     * @param description a String that describes the button.
     */
    public CustomButton(final ImageIcon i, final String description) {
    
        // TODO implement hover

        super(i);
        this.description = description;
        setBorder(null);
        setMargin(new Insets(10,0,10,0));

        setMaximumSize(new Dimension(100, 100));
        setMinimumSize(new Dimension(64, 64));

        setToolTipText(description);
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(final java.awt.event.MouseEvent evt) {
                setBackground(Color.GRAY);
            }

            public void mouseExited(final java.awt.event.MouseEvent evt) {
                setBackground(UIManager.getColor("control"));
            }
        });
    }

}
