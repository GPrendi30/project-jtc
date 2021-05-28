package com.computation.ast.range;

import com.computation.ast.Node;
import com.computation.ast.Type;
import com.computation.program.Program;
import java.util.ArrayList;

public class ArrayNode extends Node {

    private final Type arrayType;
    private final ArrayList<Node> contents;

    public ArrayNode(final Type arrayType) {
        this.arrayType = arrayType;
        contents = new ArrayList<>();
    }


    public void add(final Node child) throws Exception {
        checkType(child);
        contents.add(child);
    }

    private void checkType(final Node child) throws Exception {
        if (child.getType() != arrayType) {
            throw new Exception("Mismatching type, argument has different type than array");
        }
    }


    @Override
    public Type getType() {
        return Type.ARRAY;
    }

    @Override
    public boolean isConstant() {
        for(final Node child : contents) {
            if (!child.isConstant()) {
                return false;
            }
        }
        return true;
    }

    @Override
    public void compile(final Program p) throws Exception {
        for(final Node child : contents) {
            child.compile(p);
        }
    }
}
