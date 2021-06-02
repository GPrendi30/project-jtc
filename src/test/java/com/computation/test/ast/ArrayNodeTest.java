package com.computation.test.ast;

import com.computation.ast.NodeException;
import com.computation.ast.Type;
import com.computation.ast.doublenodes.DoubleLiteral;
import com.computation.ast.intnodes.IntLiteral;
import com.computation.ast.intnodes.IntVariable;
import com.computation.ast.range.ArrayNode;
import com.computation.ast.range.RangeException;
import com.computation.instruction.intinstruction.IADD;
import com.computation.program.Program;
import org.junit.Test;

import static org.junit.Assert.*;

public class ArrayNodeTest {

    @Test
    public void testArrayNode() throws NodeException {
        ArrayNode n = new ArrayNode(Type.DOUBLE);
        n.append(new DoubleLiteral(5.0));

        assertEquals("5.0", n.getElement(0).toString());
    }

    @Test
    public void testGetLength() throws NodeException {
        ArrayNode n = new ArrayNode(Type.DOUBLE);
        n.append(new DoubleLiteral(1.0));
        n.append(new DoubleLiteral(2.0));
        n.append(new DoubleLiteral(3.0));

        assertEquals(3, n.getLength());
    }

    @Test
    public void testGetType() throws NodeException {
        ArrayNode n = new ArrayNode(Type.INT);
        n.append(new IntLiteral(1));
        n.append(new IntLiteral(2));
        n.append(new IntLiteral(3));

        assertEquals(Type.ARRAY, n.getType());
    }

    @Test
    public void testCheckTypeThrowsException() throws NodeException {
        ArrayNode n = new ArrayNode(Type.INT);
        n.append(new IntLiteral(1));
        n.append(new IntLiteral(2));

        boolean thrown = false;
        try {
            n.append(new DoubleLiteral(3.0));
        } catch (RangeException exception) {
            thrown = true;
        }
        assertTrue(thrown);
    }

    @Test
    public void testIsConstant() throws NodeException {
        ArrayNode n = new ArrayNode(Type.INT);
        n.append(new IntLiteral(1));
        n.append(new IntLiteral(2));
        n.append(new IntLiteral(3));

        assertTrue(n.isConstant());
    }

    @Test
    public void testIsNotConstant() throws NodeException {
        ArrayNode n = new ArrayNode(Type.INT);
        n.append(new IntLiteral(1));
        n.append(new IntLiteral(2));
        n.append(new IntVariable("x"));

        assertFalse(n.isConstant());
    }

    @Test
    public void testCompile() throws NodeException {
        Program p = new Program();
        ArrayNode n = new ArrayNode(Type.INT);
        n.append(new IntLiteral(1));
        n.append(new IntLiteral(2));
        p.append(new IADD());
        n.compile(p);
        assertEquals("IADD", p.get(0).toString());
    }
}
