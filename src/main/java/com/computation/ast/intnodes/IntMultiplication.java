package com.computation.ast.intnodes;

import com.computation.ast.Node;
import com.computation.instruction.Instruction;
import com.computation.instruction.intinstruction.IMUL;

/**
 * A multiplication of two nodes containing numbers of type Integer.
 *
 * @author Prendi Gerald.
 *
 */

public class IntMultiplication extends IntBinaryNode {

    /**
     * Creates a new IntMultiplication Node.
     * @param leftChild a Node
     * @param rightChild a Node
     */
    public IntMultiplication(final Node leftChild, final Node rightChild) {
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

