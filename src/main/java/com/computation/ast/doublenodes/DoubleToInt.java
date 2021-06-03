package com.computation.ast.doublenodes;

import com.computation.ast.Node;
import com.computation.ast.intnodes.IntUnaryNode;
import com.computation.instruction.Instruction;
import com.computation.instruction.doubleinstruction.D2I;

/**
 * Cast a Double into an Int.
 *
 * @author Prendi Gerald.
 *
 */

public class DoubleToInt extends IntUnaryNode {

    /**
     * Constructs a DoubleToInt object.
     * @param child a Node
     */
    public DoubleToInt(final Node child) {
        super(child);
    }

    @Override
    public Instruction instruction() {
        return new D2I();
    }

    @Override
    public String toString() {
        return "int " + child.toString();
    }
}
