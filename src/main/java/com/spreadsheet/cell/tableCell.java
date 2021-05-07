package com.spreadsheet.cell;

import com.computation.parser.Parser;

public class tableCell extends Cell() {
    
    protected Node content;

    public tableCell(final x, final y) {
        super();
        this.x = x;
        this.y = y;
        this.content = null;
    }

    @Override
    public Type getType() {
        return Type.TABLE();
    }

    public Node evaluate() {
        return Parser.parse(this.content.getText());
    }

    public String getText() {
        return content.toString();
    }

    public void updateContent(Node newContent) {
        this.content = newContent;
    }

    public void remove() {
        this.content = null;
    }
}