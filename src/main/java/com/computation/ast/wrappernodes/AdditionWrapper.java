package com.computation.ast.wrappernodes;

import com.computation.ast.Node;
import com.computation.ast.Type;
import com.computation.ast.doublenodes.DoubleAddition;
import com.computation.ast.intnodes.IntAddition;

public class AdditionWrapper extends BinaryWrapperNode  {

    /**
     * Constructor for AdditionWrapper.
     * @param left a Node
     * @param right a Node
     */
    public AdditionWrapper(final Node left, final Node right) {
        super(left, right);
        wrap(getChildrenType() != Type.DOUBLE
                ? new IntAddition(leftChild, rightChild)
                : new DoubleAddition(leftChild, rightChild));
    }
}
