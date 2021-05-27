package com.spreadsheetview.gui.menu.toolbar;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.nio.file.Paths;
import javax.swing.ImageIcon;

public enum ToolbarButton {

    UNDO(ToolbarButton.newIcon(getPath("undo32.png"), "undo action"),
        new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent actionEvent) {
                System.out.println("undo");
            }
        }),

    REDO(newIcon(getPath("redo32.png"), "redo action"),
        new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent actionEvent) {
                System.out.println("redo");
            }
        }),

    COPY(newIcon(getPath("copy32.png"), "copy action"),
        new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent actionEvent) {
                System.out.println("copy");
            }
        }),

    PASTE(newIcon(getPath("paste32.png"), "paste action"),
        new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent actionEvent) {
                System.out.println("paste");
            }
        }),

    CUT(newIcon(getPath("cut32.png"), "cut action"),
        new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent actionEvent) {
                System.out.println("cut");
            }
        }),

    TOGGLE_FORMULAS(newIcon(getPath("toggle_formulas32.png"), "toggle action"),
        new ActionListener() {
            private boolean toggled = false;

            @Override
            public void actionPerformed(final ActionEvent actionEvent) {
                if (toggled) {
                    System.out.println("Formulas Toggled");
                } else {
                    System.out.println("Formulas untoggled");
                }
                changeState();
            }

            private void changeState() {
                toggled = !toggled;
            }
        });



    private final ImageIcon icon;
    private final ActionListener listener;


    private ToolbarButton(final ImageIcon i, final ActionListener listener) {
        this.icon = i;
        this.listener = listener;
    }

    private static String getPath(final String name) {
        final String path = "src/main/resources/toolbar/images/";
        return Paths.get(System.getProperty("user.dir"), path + name).toString();
    }

    /**
    * Create an Icon.
    * @param name a String.
    * @param desc a String.
    * @return ImageIcon the Icon.
    */
    public static ImageIcon newIcon(final String name, final String desc) {
        try {
            //Path iconPath = Paths.get(ToolbarButton.imagesPath.toString(), name);
            //System.out.println(iconPath.toString());
            //System.out.println(ToolbarButton.projectDir);
            //System.out.println(imagesPath.toString());
            return new ImageIcon(name, desc);
        } catch (Exception ex) {
            System.out.println(name);
            return  null;
        }
    }

    /**
     * Get the icon.
     * @return ImageIcon the Icon.
     */
    public ImageIcon getIcon() {
        return icon;
    }

    /**
     * An ActionListener.
     * @return ActionListener a listener.
     */
    public ActionListener getListener() {
        return listener;
    }
}
