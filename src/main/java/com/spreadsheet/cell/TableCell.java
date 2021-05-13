package com.spreadsheet.cell;

import com.computation.ast.Node;
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

    @Override
    public CellType getType() {
        return content.startsWith("=")
                ? CellType.FORMULA
                : CellType.INT;
    }

    @Override
    public void evaluate(final Program pr,final VariableTable vt) {
        try {
            final Node result = PARSER.parse(getText());
            result.compile(pr);
            content = "" + pr.execute(vt);
        } catch (Exception exception) {
            exception.printStackTrace();
            content = "!NIL";
            //throw exception;
        }
    }

    @Override
    public String getText() {
        return content;
    }

    @Override
    public void updateContent(final String newContent) {
        this.content = newContent;
    }

    @Override
    public void remove() {
        this.content = null;
    }

}