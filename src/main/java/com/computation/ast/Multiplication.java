package com.computation.ast;

import com.computation.instruction.IMUL;
import com.computation.instruction.Instruction;

/**
 * An Integer multiplication Node.
 */
public class Multiplication extends BinaryNode {
    /**
     * Creates a new IntMultiplication Node.
     * @param leftChild a Node
     * @param rightChild a Node
     */
    public Multiplication(final Node leftChild, final Node rightChild) {
        super(leftChild, rightChild);
    }

    @Override
    public Instruction instruction() {
        return new IMUL();
    }

    @Override
    public String toString() {
        return super.toString("*");
    }
}
