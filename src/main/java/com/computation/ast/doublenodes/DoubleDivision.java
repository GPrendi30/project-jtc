package com.computation.ast.doublenodes;

import com.computation.ast.Node;
import com.computation.instruction.Instruction;
import com.computation.instruction.doubleinstruction.DDIV;

/**
 * A division of two nodes containing numbers of type Double.
 *
 * @author Prendi Gerald.
 *
 */
public class DoubleDivision extends DoubleBinaryNode {
    /**
     * Creates a new IntDivision Node.
     * @param leftChild a Node
     * @param rightChild a Node
     */
    public DoubleDivision(final Node leftChild, final Node rightChild) {
        super(leftChild, rightChild);
    }

    @Override
    public Instruction instruction() {
        return new DDIV();
    }

    @Override
    public String toString() {
        return super.toString("/");
    }
}