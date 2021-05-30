package com.spreadsheetview.gui.menu.toolbar;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.nio.file.Paths;

import javax.swing.ImageIcon;
import javax.swing.JToolBar;

public class ToolBar extends JToolBar {

    /**
     * Create a toolBar.
     */
    public ToolBar() {
        super("Toolbar");

        addButton(newIcon(getPath("undo32.png"), "undo action"),
            new ActionListener() {
                @Override
                public void actionPerformed(final ActionEvent actionEvent) {
                    System.out.println("undo");
                }
            });

        addButton(newIcon(getPath("redo32.png"), "redo action"),
            new ActionListener() {
                @Override
                public void actionPerformed(final ActionEvent actionEvent) {
                    System.out.println("redo");
                }
            });

        addButton(newIcon(getPath("copy32.png"), "copy action"),
            new ActionListener() {
                @Override
                public void actionPerformed(final ActionEvent actionEvent) {
                    System.out.println("copy");
                }
            });

        addButton(newIcon(getPath("paste32.png"), "paste action"),
            new ActionListener() {
                @Override
                public void actionPerformed(final ActionEvent actionEvent) {
                    System.out.println("paste");
                }
            });

        addButton(newIcon(getPath("cut32.png"), "cut action"),
            new ActionListener() {
                @Override
                public void actionPerformed(final ActionEvent actionEvent) {
                    System.out.println("cut");
                }
            });

        addButton(newIcon(getPath("toggle_formulas32.png"), "toggle action"),
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

        FlowLayout layout = new FlowLayout();
        layout.setAlignment(FlowLayout.LEFT);
        setLayout(layout);

    }

    private void addButton(final ImageIcon i, final ActionListener li) {
        final CustomButton temp = new CustomButton(i, i.getDescription());
        temp.addActionListener(li);
        add(temp);
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
}
