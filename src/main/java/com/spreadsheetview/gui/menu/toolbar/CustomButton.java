package com.spreadsheetview.gui.menu.toolbar;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Insets;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.UIManager;

public class CustomButton extends JButton {

    private final String description;

    /**
     * Create a customButton.
     * @param i an Icon for the buttton.
     * @param description a String that describes the button.
     */
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
