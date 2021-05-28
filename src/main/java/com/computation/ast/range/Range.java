package com.computation.ast.range;

import com.computation.ast.Node;
import com.computation.ast.Type;
import com.computation.ast.doublenodes.DoubleToInt;
import com.computation.program.Program;
import java.util.ArrayList;
import java.util.Arrays;

public class Range extends Node {

    protected final Node start;
    protected final Node end;


    public Range(final Node start, final Node end) {
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

    public Node getValues() {
        return null;
    }

    @Override
    public void compile(final Program p) throws Exception {
    }

    @Override
    public String toString() {
        return start.toString() + ":" + end.toString();
    }
}
