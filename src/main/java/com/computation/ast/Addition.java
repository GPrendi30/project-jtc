package com.computation.ast;

import com.computation.instruction.IADD;
import com.computation.instruction.Instruction;

/**
 * An integer addition.
 */
public class Addition extends BinaryNode {
    /**
     * Create a new IntAddition node.
     * @param leftChild the left operand
     * @param rightChild the right operand
     */
    public Addition(final Node leftChild, final Node rightChild) {
        super(leftChild, rightChild);
    }

    @Override
    public Instruction instruction() {
        return new IADD();
    }

    @Override
    public String toString() {
        return super.toString("+");
    }

}
