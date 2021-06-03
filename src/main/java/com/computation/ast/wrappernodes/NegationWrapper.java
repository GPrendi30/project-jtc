package com.computation.ast.wrappernodes;

import com.computation.ast.Node;
import com.computation.ast.Type;
import com.computation.ast.doublenodes.DoubleNegation;
import com.computation.ast.intnodes.IntNegation;

/**
 * The wrapper of a negation, the result is going
 * to be of type Double.
 *
 * @author Di Pietro Enrico, Prendi Gerald.
 *
 */

public class NegationWrapper extends WrapperNode {

    /**
     * Constructor NegationWraper.
     * @param child a node.
     */
    public NegationWrapper(final Node child) {
        super();
        super.wrap(defineType(child));
    }

    private Node defineType(final Node child) {
        return child.getType() != Type.INT
                ?  new DoubleNegation(child)
                :  new IntNegation(child);
    }
}
