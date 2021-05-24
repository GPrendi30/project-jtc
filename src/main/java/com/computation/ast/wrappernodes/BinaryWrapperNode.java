package com.computation.ast.wrappernodes;

import com.computation.ast.Node;
import com.computation.ast.Type;

public class BinaryWrapperNode extends WrapperNode {

    protected Node leftChild;
    protected Node rightChild;

    /**
     * A Wrapper for binaryNodes.
     * @param left lhs Node
     * @param right rhs Node
     */
    public BinaryWrapperNode(final Node left, final Node right) {
        super();
        this.leftChild = left;
        this.rightChild = right;

        castNodesIfNeeded();
    }

    /**
     * Gets the type of the children Nodes.
     * @return the type of the childer Nodes.
     */
    public Type getChildrenType() {
        return leftChild.getType();
    }

    protected void castNodesIfNeeded() {
        if (leftChild.getType() != Type.INT || rightChild.getType() != Type.INT) {
            if (leftChild.getType() != Type.DOUBLE) {
                leftChild = castToDouble(leftChild);
            } else if (rightChild.getType() != Type.DOUBLE) {
                rightChild = castToDouble(rightChild);
            }
        } else {
            //do nothing.
        }
    }

}
