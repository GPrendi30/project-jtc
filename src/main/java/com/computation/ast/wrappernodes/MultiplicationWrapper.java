package com.computation.ast.wrappernodes;

import com.computation.ast.Node;
import com.computation.ast.Type;
import com.computation.ast.doublenodes.DoubleMultiplication;
import com.computation.ast.intnodes.IntMultiplication;

public class MultiplicationWrapper extends BinaryWrapperNode {

    /**
     * Constructor for MultiplicationWrapper.
     * @param left a Node
     * @param right a Node
     */
    public MultiplicationWrapper(final Node left, final Node right) {
        super(left, right);
        wrap(getChildrenType() != Type.DOUBLE
                ? new IntMultiplication(leftChild, rightChild)
                : new DoubleMultiplication(leftChild, rightChild));
    }


}
