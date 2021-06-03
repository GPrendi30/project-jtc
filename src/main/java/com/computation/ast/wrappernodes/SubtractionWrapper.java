package com.computation.ast.wrappernodes;

import com.computation.ast.Node;
import com.computation.ast.Type;
import com.computation.ast.doublenodes.DoubleSubtraction;
import com.computation.ast.intnodes.IntSubtraction;

/**
 * The wrapper of a subtraction, the result is going
 * to be of type Double.
 *
 * @author Di Pietro Enrico, Prendi Gerald.
 *
 */

public class SubtractionWrapper extends BinaryWrapperNode {

    /**
     * Constructor for SubstractionWrapper.
     * @param left a node
     * @param right a node
     */
    public SubtractionWrapper(final Node left, final Node right) {
        super(left, right);
        wrap(getChildrenType() != Type.DOUBLE
                ? new IntSubtraction(leftChild, rightChild)
                : new DoubleSubtraction(leftChild, rightChild));
    }


}
