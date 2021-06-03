package com.computation.ast.intnodes;

import com.computation.ast.Node;
import com.computation.instruction.Instruction;
import com.computation.instruction.intinstruction.ISUB;

/**
 * A subtraction of two nodes containing numbers of type Integer.
 *
 * @author Prendi Gerald.
 *
 */

public class IntSubtraction extends IntBinaryNode {
    
    /**
     * Create a new IntSubtraction node.
     * @param leftChild the left operand
     * @param rightChild the right operand
     */
    public IntSubtraction(final Node leftChild, final Node rightChild) {
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
