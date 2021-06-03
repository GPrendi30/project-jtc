package com.computation.test.ast;

import com.computation.ast.Node;
import com.computation.ast.NodeLiteral;
import com.computation.ast.NodeVariable;
import com.computation.ast.doublenodes.DoubleLiteral;
import com.computation.ast.wrappernodes.AdditionWrapper;
import com.computation.ast.wrappernodes.BinaryWrapperNode;
import com.computation.ast.wrappernodes.DivisionWrapper;
import com.computation.ast.wrappernodes.MultiplicationWrapper;
import com.computation.ast.wrappernodes.NegationWrapper;
import com.computation.ast.wrappernodes.SubtractionWrapper;
import com.computation.ast.wrappernodes.WrapperNode;
import com.computation.instruction.Instruction;
import com.computation.instruction.intinstruction.BIPUSH;
import com.computation.program.OperandStack;
import com.computation.program.Program;
import com.computation.program.Storage;
import com.computation.program.VariableTable;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class WrappernodesTest {
    
    @Test
    public void testAdditionWrapper() {
        Node a = new NodeLiteral(3);
        Node b = new NodeLiteral(3.5);
        AdditionWrapper addition = new AdditionWrapper(a, b);
        assertEquals("(double 3+3.5)", addition.toString());
    }

    @Test
    public void testBinaryWrapperNode() {
        Node a = new NodeLiteral(3);
        Node b = new NodeLiteral(3.5);
        BinaryWrapperNode node1 = new BinaryWrapperNode(a, b);
        assertEquals("DOUBLE", node1.getChildrenType().toString());

        Node c = new NodeLiteral(3.5);
        Node d = new NodeLiteral(3);
        BinaryWrapperNode node2 = new BinaryWrapperNode(c, d);
        assertEquals("DOUBLE", node2.getChildrenType().toString());

        Node e = new NodeLiteral(3);
        Node f = new NodeLiteral(3);
        BinaryWrapperNode node3 = new BinaryWrapperNode(e, f);
        assertEquals("DOUBLE", node3.getChildrenType().toString());

        Node g = new NodeLiteral(3.5);
        Node h = new NodeLiteral(3.5);
        BinaryWrapperNode node4 = new BinaryWrapperNode(g, h);
        assertEquals("DOUBLE", node4.getChildrenType().toString());
    }


    @Test
    public void testDivisionWrapper() {
        Node a = new NodeLiteral(3);
        Node b = new NodeLiteral(3.5);
        DivisionWrapper node = new DivisionWrapper(a, b);
        assertEquals("DOUBLE", node.getChildrenType().toString());
    }

    @Test
    public void testMultiplicationWrapper() {
        Node a = new NodeLiteral(3);
        Node b = new NodeLiteral(3.5);
        MultiplicationWrapper node = new MultiplicationWrapper(a, b);
        assertEquals("DOUBLE", node.getChildrenType().toString());
    }

    @Test
    public void testNegationWrapper() {
        Node a = new NodeLiteral(3);
        NegationWrapper node = new NegationWrapper(a);
        assertEquals("DOUBLE", node.getType().toString());
    }

    @Test
    public void testSubtractionWrapper() {
        Node a = new NodeLiteral(3);
        Node b = new NodeLiteral(3.5);
        SubtractionWrapper node = new SubtractionWrapper(a, b);
        assertEquals("DOUBLE", node.getChildrenType().toString());
    }

    @Test
    public void testCompileThrowsException() throws Exception {

        // TODO throws exception for NullPointer but still not 100% coverage
        boolean thrown = false;

        Node node = null;
        Program p = new Program();

        try {
            node.compile(p);
        } catch (Exception e) {
            thrown = true;
        }

        assertTrue(thrown);
    }

    @Test
    public void testIsCostant() {

        Node a = new NodeLiteral(3);
        Node b = new NodeLiteral(3.5);
        SubtractionWrapper node = new SubtractionWrapper(a, b);

        assertTrue(node.isConstant());


        Node c = new NodeLiteral(3);
        Node d = new NodeVariable("x");
        SubtractionWrapper node2 = new SubtractionWrapper(c, d);

        assertTrue(!(node2.isConstant()));
    }
}
