package com.spreadsheetview.gui.menu.toolbar;

import com.spreadsheetmodel.commands.CommandException;
import com.spreadsheetmodel.commands.CopyCommand;
import com.spreadsheetmodel.commands.CopyPasteStack;
import com.spreadsheetmodel.commands.CutCommand;
import com.spreadsheetmodel.commands.FormulasOnCommand;
import com.spreadsheetmodel.commands.Invoker;
import com.spreadsheetmodel.commands.PasteCommand;
import com.spreadsheetview.gui.GuiHandlerUtil;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.nio.file.Paths;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JToolBar;

/**
 * The toolbar.
 *
 * @author Di Pietro Enrico, Prendi Gerald.
 *
 */
public class ToolBar extends JToolBar {

    /**
     * Create a toolBar.
     */
    public ToolBar() {
        super("Toolbar");

        final ImageIcon undoIcon = newIcon(getPath("undo32.png"), "undo action");
        final ImageIcon redoIcon = newIcon(getPath("redo32.png"), "redo action");
        final ImageIcon copyIcon = newIcon(getPath("copy32.png"), "copy action");
        final ImageIcon pasteIcon = newIcon(getPath("paste32.png"), "paste action");
        final ImageIcon cutIcon = newIcon(getPath("cut32.png"), "cut action");
        final ImageIcon toggleIcon = newIcon(getPath("toggle_formulas32.png"), "toggle action");

        addButton(undoIcon,
                new ActionListener() {
                    @Override
                    public void actionPerformed(final ActionEvent actionEvent) {
                        try {
                            Invoker.getInstance().undo();
                        } catch (CommandException exception) {
                            JOptionPane.showMessageDialog(null, exception.getMessage());
                        }
                    }
                });

        addButton(redoIcon,
                new ActionListener() {
                    @Override
                    public void actionPerformed(final ActionEvent actionEvent) {
                        try {
                            Invoker.getInstance().redo();
                        } catch (CommandException exception) {
                            JOptionPane.showMessageDialog(null, exception.getMessage());
                        }
                    }
                });

        addButton(copyIcon,
                new ActionListener() {
                    @Override
                    public void actionPerformed(final ActionEvent actionEvent) {
                        GuiHandlerUtil.handleCommand(
                                new CopyCommand(CopyPasteStack.getInstance()));
                    }
                });

        addButton(pasteIcon,
            new ActionListener() {
                @Override
                public void actionPerformed(final ActionEvent actionEvent) {
                    GuiHandlerUtil.handleCommand(new PasteCommand(CopyPasteStack.getInstance()));
                }
            });


        addButton(cutIcon,
            new ActionListener() {

                @Override
                public void actionPerformed(final ActionEvent actionEvent) {
                    GuiHandlerUtil.handleCommand(new CutCommand(CopyPasteStack.getInstance()));
                }
            });

        addButton(toggleIcon,
            new ActionListener() {
                private boolean toggled = false;
                @Override
                public void actionPerformed(final ActionEvent actionEvent) {
                    if (!toggled) {
                        GuiHandlerUtil.handleCommand(new FormulasOnCommand());
                    }
                }
            });


        final FlowLayout layout = new FlowLayout(FlowLayout.LEFT);
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
     * @param path a String.
     * @param desc a String.
     * @return ImageIcon the Icon.
     */
    public static ImageIcon newIcon(final String path, final String desc) {
        return new ImageIcon(path, desc);
    }

}
