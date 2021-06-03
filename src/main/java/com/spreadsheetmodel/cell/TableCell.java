package com.spreadsheetmodel.cell;

import com.computation.ast.Node;
import com.computation.ast.NodeException;
import com.computation.instruction.InstructionException;
import com.computation.lexer.LexerException;
import com.computation.parser.ArithException;
import com.computation.parser.ArithParser;
import com.computation.parser.Parser;
import com.computation.program.Program;
import com.computation.program.VariableTable;

public class TableCell extends Cell {

    private static final Parser PARSER;

    static {
        PARSER = new ArithParser();
    }

    protected String content;

    /**
     *  Creates a new TableCell with content.
     * @param x the x coordinates
     * @param y the y coordinates
     * @param content the content of the cell
     */
    public TableCell(final int x, final int y,final String content) {
        super(x, y);
        updateContent(content);
        makeSelectable();
    }

    /**
     * Creates a new TableCell with no content.
     * @param x the x coordinates
     * @param y the y coordinates
     */
    public TableCell(final int x, final int y) {
        super(x, y);
        this.content = "";
    }


    private void updateType() {
        if (content == null || content.startsWith("#")) {
            type = CellType.INVALID;
            return;
        }

        if (content.startsWith("=")) {
            type = CellType.FORMULA;
            return;
        }

        type = isDigits(content)
                ?   CellType.NUMBER
                :   CellType.STRING;
    }

    private boolean isDigits(final String content) {
        if (content.length() == 0) {
            return false;
        }
        for (int i = 0; i < content.length(); i++) {
            final boolean isLetter = Character.isLetter(content.charAt(i));
            if (isLetter) {
                return false;
            }
        }
        return true;
    }

    @Override
    public void evaluate(final Program pr,final VariableTable vt) {
        if (getType() == CellType.FORMULA) {
            this.content = this.content.replace("=", "");
        }
        try {
            final Node result = PARSER.parse(getText());
            result.compile(pr);
            content = "" + pr.iexecute(vt);
            updateType();

        } catch (ArithException exception) {
            content = "#EXP";
            type = CellType.INVALID;
        } catch (LexerException exception) {
            content = "#KEY";
            type = CellType.INVALID;
        } catch (InstructionException | NodeException exception ) {
            content = "#VAL";
            type = CellType.INVALID;
        }
    }

    @Override
    public String getText() {
        return content;
    }

    @Override
    public void updateContent(final String newContent) {
        this.content = newContent;
        updateType();
    }

    @Override
    public void remove() {
        this.content = null;
    }

}