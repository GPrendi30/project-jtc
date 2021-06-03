package com.computation.ast.intnodes;

import com.computation.ast.Node;
import com.computation.instruction.Instruction;
import com.computation.instruction.intinstruction.IDIV;

/**
 * A division of two nodes containing numbers of type Integer.
 *
 * @author Prendi Gerald.
 *
 */

public class IntDivision extends IntBinaryNode {
    /**
     * Creates a new IntDivision Node.
     * @param leftChild a Node
     * @param rightChild a Node
     */
    public IntDivision(final Node leftChild, final Node rightChild) {
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