package com.computation.ast.doublenodes;

import com.computation.ast.Node;
import com.computation.ast.Type;
import com.computation.ast.intnodes.IntBinaryNode;

public class DoubleBinaryNode extends IntBinaryNode {
    /**
     * Constructor.
     * @param leftChild a Node
     * @param rightChild a Node
     */
    public DoubleBinaryNode(final Node leftChild, final Node rightChild) {
        super(leftChild, rightChild);
    }

    @Override
    public Type getType() {
        return Type.DOUBLE;
    }
}
