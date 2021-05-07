package com.computation.ast;

import com.computation.program.Program;
import com.computation.instruction.Instruction;

public class BinaryNode extends Node {
    private final Node leftChild;
    private final Node rightChild;

    /**
     * Constructor.
     *
     * @param leftChild  a Node
     * @param rightChild a Node
     */
    public BinaryNode(final Node leftChild, final Node rightChild) {
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
        p.append(instruction());
    }

    /**
     * Returns the String representation of the node.
     *
     * @param op -> String
     * @return the String representation of the node
     */
    public String toString(final String op) {
        return "(" + leftChild.toString() + op + rightChild.toString() + ")";
    }

    /**
     * Returns the Instruction of the node.
     *
     * @return the jvm Instruction
     */
    public Instruction instruction() {
        return null;
    }
}

