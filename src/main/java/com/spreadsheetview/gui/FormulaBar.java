import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.GridLayout;
import java.awt.FlowLayout;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.spreadsheetmodel.*;
import com.spreadsheetmodel.cell.*;

/**
 * The main frame of the Function Plotter application.
 * The "GUI".
 * The "GUI" knows the "model", it depends on the "model",
 * and it cannot exist without the "model".
 * The "model" of a SpreadsheetFrame is a Plot.
 */

public final class FormulaBar extends JPanel {
        
    private Cell currentCell;
    private Spreadsheet s = new Spreadsheet();
    private JButton cellName;
    private JTextField contentField;

    /**
     * Create a new SpreadsheetFrame for the given Plot.
     * @param plot The model to show.
     */
    public FormulaBar(final Spreadsheet model) {
        super();
        setLayout(new FlowLayout());

        cellName = new JButton(currentCell.getText());

        // TODO add action listener

        contentField = new JTextField(currentCell.getText());

        
        contentField.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                updateCurrentContent(contentField.getText());
            }
        });

        model.addListener(new SpreadsheetListener() {
            @Override
            public void spreasheetChanged(Spreadsheet s) {
                updateCurrentCell(s.getCurrentCell());       
            }
        });


        add(cellName);
        add(contentField);
    }

    private void updateCurrentCell(Cell newCell) {
        currentCell = newCell;
        updateFormulaBar();
    }

    private void updateCurrentContent(String text) {
        currentCell.updateContent(text);
    }

    private void updateFormulaBar() {
        cellName.setText(currentCell.getText());
        contentField.setText(currentCell.getText());
    }

    public static void main(String[] args){
        Spreadsheet s = new Spreadsheet();
        FormulaBar f = new FormulaBar(s);
        JFrame frame = new JFrame();
        frame.setLayout(new BorderLayout());
        frame.add(f, BorderLayout.NORTH);
        frame.setVisible(true);
    }

}