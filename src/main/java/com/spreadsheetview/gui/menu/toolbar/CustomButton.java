package com.spreadsheetview.gui.menu.toolbar;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Insets;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.UIManager;

/**
 * A custom button.
 *
 * @author Di Pietro Enrico, Prendi Gerald.
 *
 */
public class CustomButton extends JButton {


    /**
     * Create a customButton.
     * @param i an Icon for the button.
     * @param description a String that describes the button.
     */
    public CustomButton(final ImageIcon i, final String description) {

        super(i);
        setBorder(null);
        setMargin(new Insets(10,0,10,0));

        setMaximumSize(new Dimension(100, 100));
        setMinimumSize(new Dimension(64, 64));
        setBackground(new Color(255,255,255));
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
