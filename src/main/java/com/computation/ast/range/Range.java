package com.computation.ast.range;

import com.computation.ast.Node;
import com.computation.ast.Type;
import com.computation.program.Program;


public class Range extends Node {

    protected final Node start;
    protected final Node end;


    /**
     * Creates a new Range based on a start Node and an end Node.
     * @param start a Node.
     * @param end a Node.
     */
    public Range(final Node start, final Node end) {
        super();
        this.start = start;
        this.end = end;
    }


    private void parseRange() {
        //to be overwritten.
    }

    @Override
    public Type getType() {
        return Type.INVALID;
    }

    @Override
    public boolean isConstant() {
        return false;
    }

    /**
     * Get the node Values stores in the Range.
     * @return node value.
     */
    public Node getValues() {
        return null;
    }

    @Override
    public void compile(final Program p) throws Exception {
        // to be overwritten.
    }

    @Override
    public String toString() {
        return start.toString() + ":" + end.toString();
    }
}
