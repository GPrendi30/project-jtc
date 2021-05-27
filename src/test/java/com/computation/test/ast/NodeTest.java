package com.computation.test.ast;

import com.computation.ast.Node;
import com.computation.ast.NodeLiteral;
import com.computation.ast.NodeVariable;
import com.computation.ast.Type;
import com.computation.ast.doublenodes.DoubleAddition;
import com.computation.ast.doublenodes.DoubleDivision;
import com.computation.ast.doublenodes.DoubleLiteral;
import com.computation.ast.doublenodes.DoubleMultiplication;
import com.computation.ast.doublenodes.DoubleNegation;
import com.computation.ast.doublenodes.DoubleSubtraction;
import com.computation.ast.doublenodes.DoubleToInt;
import com.computation.ast.doublenodes.DoubleVariable;
import com.computation.ast.intnodes.IntAddition;
import com.computation.ast.intnodes.IntBinaryNode;
import com.computation.ast.intnodes.IntDivision;
import com.computation.ast.intnodes.IntLiteral;
import com.computation.ast.intnodes.IntMultiplication;
import com.computation.ast.intnodes.IntNegation;
import com.computation.ast.intnodes.IntSubtraction;
import com.computation.ast.intnodes.IntToDouble;
import com.computation.ast.intnodes.IntUnaryNode;
import com.computation.ast.intnodes.IntVariable;
import org.junit.Test;

import static org.junit.Assert.*;



/**
 * Tests toString(), isConstant(), and getType() of Node subclasses.
 */
public class NodeTest {

    @Test
    public void testBinaryNode() {
        IntBinaryNode b = new IntBinaryNode(null, null);
        assertNull(b.instruction());
    }

    @Test
    public void testUnaryNode() {
        IntUnaryNode b = new IntUnaryNode(null);
        assertNull(b.instruction());
    }

    // class testing
    @Test
    public void testNode() {
        // we don't have the keyword abstract in the Node implementation.
        // tests work.
        Node e = new Node();
        assertTrue(e.isConstant());
        assertEquals("?", e.toString());
        assertEquals(Type.INVALID, e.getType());
        try {
            e.compile(null);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    @Test
    public void testIntLiteral() {
        Node e = new IntLiteral(5);
        assertTrue(e.isConstant());
        assertEquals("5", e.toString());
        assertEquals(Type.INT, e.getType());
    }

    @Test
    public void testIntNegation() {
        Node e = new IntNegation(new IntLiteral(5));
        assertTrue(e.isConstant());
        assertEquals("(-5)", e.toString());
        assertEquals(Type.INT, e.getType());
    }

    // IntAddition Tests
    @Test
    public void testIntAdditionLiteralLiteral() {
        Node e = new IntAddition(new IntLiteral(5), new IntLiteral(6));
        assertTrue(e.isConstant());
        assertEquals("(5+6)", e.toString());
        assertEquals(Type.INT, e.getType());
    }

    @Test
    public void testIntAdditionLiteralVariable() {
        Node e = new IntAddition(new IntLiteral(5), new IntVariable("y"));
        assertFalse(e.isConstant());
        assertEquals("(5+y)", e.toString());
        assertEquals(Type.INT, e.getType());
    }

    @Test
    public void testIntAdditionVariableVariable() {
        Node e = new IntAddition(new IntVariable("x"), new IntVariable("y"));
        assertFalse(e.isConstant());
        assertEquals("(x+y)", e.toString());
        assertEquals(Type.INT, e.getType());
    }

    // Substraction Tests

    @Test
    public void testIntSubtractionLiteralLiteral() {
        Node e = new IntSubtraction(new IntLiteral(5), new IntLiteral(6));
        assertTrue(e.isConstant());
        assertEquals("(5-6)", e.toString());
        assertEquals(Type.INT, e.getType());
    }

    @Test
    public void testIntSubtractionLiteralVariable() {
        Node e = new IntSubtraction(new IntLiteral(5), new IntVariable("y"));
        assertFalse(e.isConstant());
        assertEquals("(5-y)", e.toString());
        assertEquals(Type.INT, e.getType());
    }

    @Test
    public void testIntSubtractionVariableVariable() {
        Node e = new IntSubtraction(new IntVariable("x"), new IntVariable("y"));
        assertFalse(e.isConstant());
        assertEquals("(x-y)", e.toString());
        assertEquals(Type.INT, e.getType());
    }

    // IntMultiplication tests
    @Test
    public void testIntMultiplicationLiteralLiteral() {
        Node e = new IntMultiplication(new IntLiteral(5), new IntLiteral(6));
        assertTrue(e.isConstant());
        assertEquals("(5*6)", e.toString());
        assertEquals(Type.INT, e.getType());
    }

    @Test
    public void testIntMultiplicationLiteralVariable() {
        Node e = new IntMultiplication(new IntLiteral(5), new IntVariable("y"));
        assertFalse(e.isConstant());
        assertEquals("(5*y)", e.toString());
        assertEquals(Type.INT, e.getType());
    }

    @Test
    public void testIntMultiplicationVariableVariable() {
        Node e = new IntMultiplication(new IntVariable("x"), new IntVariable("y"));
        assertFalse(e.isConstant());
        assertEquals("(x*y)", e.toString());
        assertEquals(Type.INT, e.getType());
    }

    // IntDivision Tests
    @Test
    public void testIntDivisionLiteralLiteral() {
        Node e = new IntDivision(new IntLiteral(5), new IntLiteral(6));
        assertTrue(e.isConstant());
        assertEquals("(5/6)", e.toString());
        assertEquals(Type.INT, e.getType());
    }

    @Test
    public void testIntDivisionLiteralVariable() {
        Node e = new IntDivision(new IntLiteral(5), new IntVariable("y"));
        assertFalse(e.isConstant());
        assertEquals("(5/y)", e.toString());
        assertEquals(Type.INT, e.getType());
    }

    @Test
    public void testIntDivisionVariableVariable() {
        Node e = new IntDivision(new IntVariable("x"), new IntVariable("y"));
        assertFalse(e.isConstant());
        assertEquals("(x/y)", e.toString());
        assertEquals(Type.INT, e.getType());
    }

    @Test
    public void testVariable() {
        Node e = new IntVariable("x");
        assertFalse(e.isConstant());
        assertEquals("x", e.toString());
        assertEquals(Type.INT, e.getType());
    }


    @Test
    public void testDoubleLiteral() {
        Node e = new DoubleLiteral(5.1);
        assertTrue(e.isConstant());
        assertEquals("5.1", e.toString());
        assertEquals(Type.DOUBLE, e.getType());
    }

    @Test
    public void testDoubleNegation() {
        Node e = new DoubleNegation(new DoubleLiteral(5.1));
        assertTrue(e.isConstant());
        assertEquals("(-5.1)", e.toString());
        assertEquals(Type.DOUBLE, e.getType());
    }

    // IntAddition Tests
    @Test
    public void testDoubleAdditionLiteralLiteral() {
        Node e = new DoubleAddition(new DoubleLiteral(5.1), new DoubleLiteral(6.1));
        assertTrue(e.isConstant());
        assertEquals("(5.1+6.1)", e.toString());
        assertEquals(Type.DOUBLE, e.getType());
    }

    @Test
    public void testDoubleAdditionLiteralVariable() {
        Node e = new DoubleAddition(new DoubleLiteral(5), new DoubleVariable("y"));
        assertFalse(e.isConstant());
        assertEquals("(5.0+y)", e.toString());
        assertEquals(Type.DOUBLE, e.getType());
    }

    @Test
    public void testDoubleAdditionVariableVariable() {
        Node e = new DoubleAddition(new DoubleVariable("x"), new DoubleVariable("y"));
        assertFalse(e.isConstant());
        assertEquals("(x+y)", e.toString());
        assertEquals(Type.DOUBLE, e.getType());
    }

    // Substraction Tests

    @Test
    public void testDoubleSubtractionLiteralLiteral() {
        Node e = new DoubleSubtraction(new DoubleLiteral(5), new DoubleLiteral(6));
        assertTrue(e.isConstant());
        assertEquals("(5.0-6.0)", e.toString());
        assertEquals(Type.DOUBLE, e.getType());
    }

    @Test
    public void testDoubleSubtractionLiteralVariable() {
        Node e = new DoubleSubtraction(new DoubleLiteral(5), new DoubleVariable("y"));
        assertFalse(e.isConstant());
        assertEquals("(5.0-y)", e.toString());
        assertEquals(Type.DOUBLE, e.getType());
    }

    @Test
    public void testDoubleSubtractionVariableVariable() {
        Node e = new DoubleSubtraction(new DoubleVariable("x"), new DoubleVariable("y"));
        assertFalse(e.isConstant());
        assertEquals("(x-y)", e.toString());
        assertEquals(Type.DOUBLE, e.getType());
    }

    // DoubleMultiplication tests
    @Test
    public void testDoubleMultiplicationLiteralLiteral() {
        Node e = new DoubleMultiplication(new DoubleLiteral(5), new DoubleLiteral(6));
        assertTrue(e.isConstant());
        assertEquals("(5.0*6.0)", e.toString());
        assertEquals(Type.DOUBLE, e.getType());
    }

    @Test
    public void testDoubleMultiplicationLiteralVariable() {
        Node e = new DoubleMultiplication(new DoubleLiteral(5), new DoubleVariable("y"));
        assertFalse(e.isConstant());
        assertEquals("(5.0*y)", e.toString());
        assertEquals(Type.DOUBLE, e.getType());
    }

    @Test
    public void testDoubleMultiplicationVariableVariable() {
        Node e = new DoubleMultiplication(new DoubleVariable("x"), new DoubleVariable("y"));
        assertFalse(e.isConstant());
        assertEquals("(x*y)", e.toString());
        assertEquals(Type.DOUBLE, e.getType());
    }

    // DoubleDivision Tests
    @Test
    public void testDoubleDivisionLiteralLiteral() {
        Node e = new DoubleDivision(new DoubleLiteral(5), new DoubleLiteral(6));
        assertTrue(e.isConstant());
        assertEquals("(5.0/6.0)", e.toString());
        assertEquals(Type.DOUBLE, e.getType());
    }

    @Test
    public void testDoubleDivisionLiteralVariable() {
        Node e = new DoubleDivision(new DoubleLiteral(5), new DoubleVariable("y"));
        assertFalse(e.isConstant());
        assertEquals("(5.0/y)", e.toString());
        assertEquals(Type.DOUBLE, e.getType());
    }

    @Test
    public void testDoubleDivisionVariableVariable() {
        Node e = new DoubleDivision(new DoubleVariable("x"), new DoubleVariable("y"));
        assertFalse(e.isConstant());
        assertEquals("(x/y)", e.toString());
        assertEquals(Type.DOUBLE, e.getType());
    }

    @Test
    public void testDoubleVariable() {
        Node e = new DoubleVariable("x");
        assertFalse(e.isConstant());
        assertEquals("x", e.toString());
        assertEquals(Type.DOUBLE, e.getType());
    }

    @Test
    public void testDoubleToInt() {
        Node e = new DoubleToInt(new DoubleLiteral(4.1));
        assertTrue(e.isConstant());
        assertEquals("int 4.1", e.toString());
        assertEquals(Type.INT, e.getType());
    }

    @Test
    public void testIntToDouble() {
        Node e = new IntToDouble(new IntLiteral(4));
        assertTrue(e.isConstant());
        assertEquals("double 4", e.toString());
        assertEquals(Type.DOUBLE, e.getType());
    }

    @Test
    public void testNodeVariable() {
        Node e = new NodeVariable("x");
        assertFalse(e.isConstant());
        assertEquals("x", e.toString());
        assertEquals(Type.INVALID, e.getType());
        assertNull(new NodeVariable("x").instruction());
    }

    @Test
    public void testNodeLiteral() {
        Node e = new NodeLiteral(0);
        assertTrue(e.isConstant());
        assertEquals("0", e.toString());
        assertEquals(Type.INVALID, e.getType());
        assertNull(new NodeLiteral(1.0).instruction());
    }

}
