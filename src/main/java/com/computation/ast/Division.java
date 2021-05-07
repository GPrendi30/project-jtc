package com.computation.ast;

import com.computation.instruction.IDIV;
import com.computation.instruction.Instruction;

/**
 * An integer division node.
 */
public class Division extends BinaryNode {
    /**
     * Creates a new IntDivision Node.
     * @param leftChild a Node
     * @param rightChild a Node
     */
    public Division(final Node leftChild, final Node rightChild) {
        super(leftChild, rightChild);
    }

    @Override
    public Instruction instruction() {
        return new IDIV();
    }

    @Override
    public String toString() {
        return super.toString("/");
    }
}
