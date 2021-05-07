package com.spreadsheet.cell;

public class lateralCell extends Cell() {

    public tableCell(final y, final content) {
        super();
        this.y = y;
        this.content = content;
    } 

    @Override
    public Type getType() {
        return Type.LATERAL();
    }

    public String getText() {
        return content.toString();
    }
}