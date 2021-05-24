package com.computation.ast.wrappernodes;

import com.computation.ast.Node;
import com.computation.ast.Type;
import com.computation.ast.doublenodes.DoubleDivision;
import com.computation.ast.intnodes.IntDivision;

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
