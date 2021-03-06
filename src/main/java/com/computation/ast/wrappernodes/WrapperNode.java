package com.computation.ast.wrappernodes;

import com.computation.ast.Node;
import com.computation.ast.NodeException;
import com.computation.ast.Type;
import com.computation.ast.intnodes.IntToDouble;
import com.computation.program.Program;

/**
 * The wrapper node is a node, that contains
 * something of type Double.
 *
 * @author Di Pietro Enrico, Prendi Gerald.
 *
 */

public class WrapperNode extends Node {

    private Node child;

    /**
     * A node which wraps a node.
     */
    public WrapperNode() {
        super();
        child = null;
    }

    @Override
    public Type getType() {
        return child.getType();
    }

    @Override
    public boolean isConstant() {
        return child.isConstant();
    }

    @Override
    public void compile(final Program p) throws NodeException {
        child.compile(p);
    }

    @Override
    public String toString() {
        // to be implemented in subclasses
        return child.toString();
    }

    protected Node castToDouble(final Node n) {
        return new IntToDouble(n);
    }

    protected void wrap(final Node child) {
        this.child = child;
    }

}
