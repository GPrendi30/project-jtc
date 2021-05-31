package com.spreadsheetview.gui.menu.toolbar;

import com.spreadsheetmodel.commands.Command;
import com.spreadsheetmodel.commands.CopyCommand;
import com.spreadsheetmodel.commands.CutCommand;
import com.spreadsheetmodel.commands.OpenCommand;
import com.spreadsheetmodel.commands.PasteCommand;
import com.spreadsheetmodel.commands.SaveCommand;

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

        ImageIcon undoIcon = newIcon(getPath("undo32.png"), "undo action");
        final ImageIcon redoIcon = newIcon(getPath("redo32.png"), "redo action");
        ImageIcon copyIcon = newIcon(getPath("copy32.png"), "copy action");
        ImageIcon pasteIcon = newIcon(getPath("paste32.png"), "paste action");
        ImageIcon cutIcon = newIcon(getPath("cut32.png"), "cut action");
        ImageIcon toggleIcon = newIcon(getPath("toggle_formulas32.png"), "toggle action");

        addButton(undoIcon,
                new ToolBarActionListener() {
                    @Override
                    public Command command() {
                        return (Command) new CopyCommand();
                }
            });

        addButton(redoIcon,
                new ToolBarActionListener() {
                    @Override
                    public Command command() {
                        return new OpenCommand("g");
                }
            });

        addButton(copyIcon,
                new ToolBarActionListener() {
                    @Override
                    public Command command() {
                        return new SaveCommand("s");
                }
            });

        addButton(pasteIcon,
            new ToolBarActionListener() {
                @Override
                public Command command() {
                    return (Command) new PasteCommand();
                }
            });

        addButton(cutIcon,
            new ToolBarActionListener() {
                @Override
                public Command command() {
                    return (Command) new CutCommand();
                }
            });

        addButton(toggleIcon,
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
            return new ImageIcon(name, desc);
        } catch (Exception ex) {
            System.out.println(name);
            return  null;
        }
    }

    private class ToolBarButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {

        }
    }

}
