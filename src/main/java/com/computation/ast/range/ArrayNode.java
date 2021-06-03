package com.computation.ast.range;

import com.computation.ast.Node;
import com.computation.ast.NodeException;
import com.computation.ast.Type;
import com.computation.program.Program;

import java.util.ArrayList;

/**
 * An array of nodes that is used by functions
 * that uses ranges.
 *
 * @author Prendi Gerald.
 *
 */

public class ArrayNode extends Node {

    private final Type arrayType;
    private final ArrayList<Node> contents;

    /**
     * Creates a new array Node.
     * @param arrayType the Type of the elements of the array.
     */
    public ArrayNode(final Type arrayType) {
        super();
        this.arrayType = arrayType;
        contents = new ArrayList<>();
    }

    /**
     * Get element at a given index.
     * @param index the int index.
     * @return the node at that index.
     */
    public Node getElement(final int index) {
        return contents.get(index);
    }

    /**
     * Appends a value to the Array.
     * @param child a Node.
     * @throws NodeException throws an Exception if not matching types.
     */
    public void append(final Node child) throws NodeException {
        checkType(child);
        contents.add(child);
    }

    private void checkType(final Node child) throws RangeException {
        if (child.getType() != arrayType) {
            throw new RangeException("Mismatching type, argument has different type than array");
        }
    }

    /**
     * Return the length of the array.
     * @return the length of the array.
     */
    public int getLength() {
        return contents.size();
    }

    @Override
    public Type getType() {
        return Type.ARRAY;
    }

    @Override
    public boolean isConstant() {
        for (final Node child : contents) {
            if (!child.isConstant()) {
                return false;
            }
        }
        return true;
    }

    @Override
    public void compile(final Program p) throws NodeException {
        for (final Node child : contents) {
            child.compile(p);
        }
    }
}
