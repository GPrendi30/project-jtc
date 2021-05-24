package com.computation.ast.intnodes;

import com.computation.ast.Node;
import com.computation.instruction.Instruction;
import com.computation.instruction.intinstruction.IADD;

/**
 * An integer IntAddition.
 */
public class IntAddition extends IntBinaryNode {
    
    /**
     * Create a new IntAddition node.
     * @param leftChild the left operand
     * @param rightChild the right operand
     */
    public IntAddition(final Node leftChild, final Node rightChild) {
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
