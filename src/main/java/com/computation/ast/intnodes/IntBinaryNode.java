package com.computation.ast.intnodes;

import com.computation.ast.Node;
import com.computation.ast.Type;
import com.computation.instruction.Instruction;
import com.computation.program.Program;

public class IntBinaryNode extends Node {

    private final Node leftChild;
    private final Node rightChild;

    /**
     * Constructor.
     * @param leftChild a Node
     * @param rightChild a Node
     */
    public IntBinaryNode(final Node leftChild, final Node rightChild) {
        super();
        this.rightChild = rightChild;
        this.leftChild = leftChild;
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
        try {
            leftChild.compile(p);
            rightChild.compile(p);
            p.append(instruction());
        } catch (Exception exception) {
            exception.printStackTrace();
        }


    }

    /**
     * Returns the String representation of the node.
     * @param op -> String
     * @return the String representation of the node
     */
    public String toString(final String op) {
        return "(" + leftChild.toString() + op + rightChild.toString() + ")";
    }

    /**
     * Returns the Instruction of the node. 
     * @return the jvm Instruction 
     */
    public Instruction instruction() {
        return null;
    }

}
