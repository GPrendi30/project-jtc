package com.spreadsheet.cell;

public class topCell extends Cell() {

    public tableCell(final x, final content) {
        super();
        this.x = x;
        this.content = content;
    } 

    @Override
    public Type getType() {
        return Type.TOP();
    }

    public String getText() {
        return content.toString();
    }
}