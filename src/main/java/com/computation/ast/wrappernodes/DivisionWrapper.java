package com.computation.ast.wrappernodes;

import com.computation.ast.Node;
import com.computation.ast.Type;
import com.computation.ast.doublenodes.DoubleDivision;
import com.computation.ast.intnodes.IntDivision;

/**
 * The wrapper of a division, the result is going
 * to be of type Double.
 *
 * @author Di Pietro Enrico, Prendi Gerald.
 *
 */

public class DivisionWrapper extends BinaryWrapperNode {

    /**
     * Constructor for DivisionWrapper.
     * @param left a Node
     * @param right a Node
     */
    public DivisionWrapper(final Node left, final Node right) {
        super(left, right);
        wrap(getChildrenType() != Type.DOUBLE
                ? new IntDivision(leftChild, rightChild)
                : new DoubleDivision(leftChild, rightChild));
    }
}
