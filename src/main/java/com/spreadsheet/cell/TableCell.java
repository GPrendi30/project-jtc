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

    public TableCell(final int x, final int y,final String content) {
        super(x, y);
        updateContent(content);
        makeSelectable();
    }

    public TableCell(final int x, final int y) {
        super(x, y);
        this.content = "";
    }

    @Override
    public CellType getType() {
        return content.startsWith("=")
                ? CellType.FORMULA
                : CellType.STRING;
    }

    @Override
    public String evaluate(final Program pr,final VariableTable vt) {
        try {
            final Node result = PARSER.parse(getText());
            result.compile(pr);
            return "" + pr.execute(vt);
        } catch (Exception exception) {
            return "!NIL";
        }
    }

    public String getText() {
        return content != null
                ? content
                : null;
    }

    public void updateContent(final String newContent) {
        this.content = newContent != null
                ? newContent
                : "";
    }

    public void remove() {
        this.content = null;
    }

}