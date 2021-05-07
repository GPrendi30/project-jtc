package com.computation.ast;

import com.computation.instruction.ISUB;
import com.computation.instruction.Instruction;

/**
 * An integer subtraction.
 */
public class Subtraction extends BinaryNode {
    /**
     * Create a new IntSubtraction node.
     * @param leftChild the left operand
     * @param rightChild the right operand
     */
    public Subtraction(final Node leftChild, final Node rightChild) {
        super(leftChild, rightChild);
    }

    @Override
    public Instruction instruction() {
        return new ISUB();
    }

    @Override
    public String toString() {
        return super.toString("-");
    }
}
