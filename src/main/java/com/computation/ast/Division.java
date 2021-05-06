package com.computation.ast;
import com.computation.instruction.IDIV;
import com.computation.program.*;
public class Division extends Node {

    private final Node leftChild;
    private final Node rightChild;
    
    /**
     * Creates a new Division Node.
     * @param leftChild a Node
     * @param rightChild a Node
     */
    public Division(final Node leftChild, final Node rightChild) {
        super();
        this.leftChild = leftChild;
        this.rightChild = rightChild;
    }

    @Override
    public Type getType() {
        return Type.INT;
    }

    @Override
    public boolean isConstant() {
        return leftChild.isConstant() && rightChild.isConstant();
    }
    
    @Override
    public void compile(final Program p) {
        leftChild.compile(p);
        rightChild.compile(p);
        p.append(new IDIV());
    }

    @Override
    public String toString() {
        return "(" + leftChild.toString() + "/" + rightChild.toString() + ")";
    }

    
}
