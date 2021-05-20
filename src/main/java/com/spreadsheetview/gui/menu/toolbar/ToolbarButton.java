package com.spreadsheetview.gui.menu.toolbar;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;

public enum ToolbarButton {

    UNDO(ToolbarButton.newIcon("/home/geri/Desktop/University/PF2/HOMEWORK/project-jtc/src/main/resources/toolbar/images/undo.png", "undo action"),
            new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent actionEvent) {
                    System.out.println("undo");
                }
            }),

    REDO(newIcon("/home/geri/Desktop/University/PF2/HOMEWORK/project-jtc/src/main/resources/toolbar/images/redo.png", "redo action"),
            new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent actionEvent) {
                    System.out.println("redo");
                }
            }),

    COPY(newIcon("/home/geri/Desktop/University/PF2/HOMEWORK/project-jtc/src/main/resources/toolbar/images/copy.png", "copy action"),
            new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent actionEvent) {
                    System.out.println("copy");
                }
            }),

    PASTE(newIcon("/home/geri/Desktop/University/PF2/HOMEWORK/project-jtc/src/main/resources/toolbar/images/paste.png", "paste action"),
            new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent actionEvent) {
                    System.out.println("paste");
                }
            }),

    CUT(newIcon("/home/geri/Desktop/University/PF2/HOMEWORK/project-jtc/src/main/resources/toolbar/images/cut.png", "cut action"),
            new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent actionEvent) {
                    System.out.println("cut");
                }
            }),

    TOGGLE_FORMULAS(newIcon("/home/geri/Desktop/University/PF2/HOMEWORK/project-jtc/src/main/resources/toolbar/images/toggle_formulas.png", "toggle action"),
            new ActionListener() {
                private boolean toggled = false;

                @Override
                public void actionPerformed(ActionEvent actionEvent) {
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


    private ToolbarButton(ImageIcon i,
                          ActionListener listener) {
        this.icon = i;
        this.listener = listener;
    }

    public static void main(String[] args) {
        //System.out.println(new ImageIcon(Paths.get(imagesPath.toString(), "undo.png").toString(), "desc"));
        //System.out.println(Paths.get(imagesPath.toString(), "home.png").toString());
        //newIcon("undo.png", "a picture");
    }

   public static ImageIcon newIcon(String name, String desc) {
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

    public ImageIcon getIcon() {
        return icon;
    }

    public ActionListener getListener() {
        return listener;
    }
}