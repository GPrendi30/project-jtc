import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;


/**
 * The main frame of the Function Plotter application.
 * The "GUI".
 * The "GUI" knows the "model", it depends on the "model",
 * and it cannot exist without the "model".
 * The "model" of a SpreadsheetFrame is a Plot.
 */


public final class SpreadsheetFrame extends JFrame {

    /**
     * Create a new PlotterFrame for the given Plot.
     * @param plot The model to show.
     */
    public PlotterFrame(final Plot plot) {
        //super();
        setTitle("Java Tabular Calculator");
        setLayout(new BorderLayout());
        // add the field were the content of a selected cell is shown
        final JTextField contentField = new JTextField("placeholder");
        add(contentField, BorderLayout.NORTH);

        
    }
}