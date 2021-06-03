package com.computation.ast.doublenodes;

import com.computation.ast.Node;
import com.computation.ast.Type;
import com.computation.ast.intnodes.IntUnaryNode;

/**
 * A unary node of a Double.
 *
 * @author Prendi Gerald.
 *
 */

public class DoubleUnaryNode extends IntUnaryNode {
    /**
     * Create a new DoubleUnaryNode node.
     * @param child the operand we will negate
     */
    public DoubleUnaryNode(final Node child) {
        super(child);
    }

    @Override
    public Type getType() {
        return Type.DOUBLE;
    }
}

