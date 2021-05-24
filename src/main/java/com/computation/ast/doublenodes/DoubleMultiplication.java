package com.computation.ast.doublenodes;

import com.computation.ast.Node;
import com.computation.instruction.Instruction;
import com.computation.instruction.doubleinstruction.DMUL;

public class DoubleMultiplication extends DoubleBinaryNode {

    /**
     * Creates a new DoubleMultiplication Node.
     * @param leftChild a Node
     * @param rightChild a Node
     */
    public DoubleMultiplication(final Node leftChild, final Node rightChild) {
        super(leftChild, rightChild);
    }

    @Override
    public Instruction instruction() {
        return new DMUL();
    }

    @Override
    public String toString() {
        return super.toString("*");
    }
}

