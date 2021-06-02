package com.computation.test.program;

import com.computation.ast.Node;
import com.computation.ast.doublenodes.DoubleAddition;
import com.computation.ast.doublenodes.DoubleDivision;
import com.computation.ast.doublenodes.DoubleLiteral;
import com.computation.ast.doublenodes.DoubleMultiplication;
import com.computation.ast.doublenodes.DoubleNegation;
import com.computation.ast.doublenodes.DoubleSubtraction;
import com.computation.ast.doublenodes.DoubleToInt;
import com.computation.ast.doublenodes.DoubleVariable;
import com.computation.ast.intnodes.IntAddition;
import com.computation.ast.intnodes.IntDivision;
import com.computation.ast.intnodes.IntLiteral;
import com.computation.ast.intnodes.IntMultiplication;
import com.computation.ast.intnodes.IntNegation;
import com.computation.ast.intnodes.IntSubtraction;
import com.computation.ast.intnodes.IntToDouble;
import com.computation.ast.intnodes.IntVariable;
import com.computation.instruction.doubleinstruction.BDPUSH;
import com.computation.instruction.doubleinstruction.D2I;
import com.computation.instruction.doubleinstruction.DADD;
import com.computation.instruction.doubleinstruction.DDIV;
import com.computation.instruction.doubleinstruction.DLOAD;
import com.computation.instruction.doubleinstruction.DMUL;
import com.computation.instruction.doubleinstruction.DNEG;
import com.computation.instruction.doubleinstruction.DSUB;
import com.computation.instruction.intinstruction.BIPUSH;
import com.computation.instruction.intinstruction.I2D;
import com.computation.instruction.intinstruction.IADD;
import com.computation.instruction.intinstruction.IDIV;
import com.computation.instruction.intinstruction.ILOAD;
import com.computation.instruction.intinstruction.IMUL;
import com.computation.instruction.intinstruction.INEG;
import com.computation.instruction.intinstruction.ISUB;
import com.computation.program.Compiler;
import com.computation.program.CompilerException;
import com.computation.program.Program;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


/**
 * Test compile() of Node subclasses
 * (and toString() of Instruction subclasses).
 * This tests that the compile() methods generate the correct
 * sequence of Instructions.
 */
public class CompilerTest {


    @Test
    public void testIntLiteral() throws Exception {
        Compiler c = new Compiler();
        Node n = new IntLiteral(5);
        Program p = c.compile(n);
        assertEquals(1, p.getLength());
        assertTrue(p.get(0) instanceof BIPUSH);
        assertEquals(new BIPUSH(5).toString(), p.get(0).toString());
    }

    @Test
    public void testIntNegation() throws Exception {
        Compiler c = new Compiler();
        Node n = new IntNegation(new IntLiteral(5));
        Program p = c.compile(n);
        assertEquals(2, p.getLength());
        assertTrue(p.get(0) instanceof BIPUSH);
        assertEquals(new BIPUSH(5).toString(), p.get(0).toString());
        assertTrue(p.get(1) instanceof INEG);
    }

    @Test
    public void testIntAddition() throws Exception {
        Compiler c = new Compiler();
        Node n = new IntAddition(new IntLiteral(5), new IntLiteral(6));
        Program p = c.compile(n);
        assertEquals(3, p.getLength());
        assertTrue(p.get(0) instanceof BIPUSH);
        assertEquals(new BIPUSH(5).toString(), p.get(0).toString());
        assertTrue(p.get(1) instanceof BIPUSH);
        assertEquals(new BIPUSH(6).toString(), p.get(1).toString());
        assertTrue(p.get(2) instanceof IADD);
    }

    @Test
    public void testIntSubtraction() throws Exception {
        Compiler c = new Compiler();
        Node n = new IntSubtraction(new IntLiteral(5), new IntLiteral(6));
        Program p = c.compile(n);
        assertEquals(3, p.getLength());
        assertTrue(p.get(0) instanceof BIPUSH);
        assertEquals(new BIPUSH(5).toString(), p.get(0).toString());
        assertTrue(p.get(1) instanceof BIPUSH);
        assertEquals(new BIPUSH(6).toString(), p.get(1).toString());
        assertTrue(p.get(2) instanceof ISUB);
    }

    @Test
    public void testIntMultiplication() throws Exception {
        Compiler c = new Compiler();
        Node n = new IntMultiplication(new IntLiteral(5), new IntLiteral(6));
        Program p = c.compile(n);
        assertEquals(3, p.getLength());
        assertTrue(p.get(0) instanceof BIPUSH);
        assertEquals(new BIPUSH(5).toString(), p.get(0).toString());
        assertTrue(p.get(1) instanceof BIPUSH);
        assertEquals(new BIPUSH(6).toString(), p.get(1).toString());
        assertTrue(p.get(2) instanceof IMUL);
    }

    @Test
    public void testIntDivision() throws Exception {
        Compiler c = new Compiler();
        Node n = new IntDivision(new IntLiteral(5), new IntLiteral(6));
        Program p = c.compile(n);
        assertEquals(3, p.getLength());
        assertTrue(p.get(0) instanceof BIPUSH);
        assertEquals(new BIPUSH(5).toString(), p.get(0).toString());
        assertTrue(p.get(1) instanceof BIPUSH);
        assertEquals(new BIPUSH(6).toString(), p.get(1).toString());
        assertTrue(p.get(2) instanceof IDIV);
    }

    @Test
    public void testVariable() throws Exception {
        Compiler c = new Compiler();
        Node n = new IntVariable("x");
        Program p = c.compile(n);
        assertEquals(1, p.getLength());
        assertTrue(p.get(0) instanceof ILOAD);
        assertEquals(new ILOAD("x").toString(), p.get(0).toString());
    }

    @Test
    public void testDoubleLiteral() throws Exception {
        Compiler c = new Compiler();
        Node n = new DoubleLiteral(5);
        Program p = c.compile(n);
        assertEquals(1, p.getLength());
        assertTrue(p.get(0) instanceof BDPUSH);
        assertEquals(new BDPUSH(5).toString(), p.get(0).toString());
    }

    @Test
    public void testDoubleNegation() throws Exception {
        Compiler c = new Compiler();
        Node n = new DoubleNegation(new DoubleLiteral(5));
        Program p = c.compile(n);
        assertEquals(2, p.getLength());
        assertTrue(p.get(0) instanceof BDPUSH);
        assertEquals(new BDPUSH(5).toString(), p.get(0).toString());
        assertTrue(p.get(1) instanceof DNEG);
    }

    @Test
    public void testDoubleAddition() throws Exception {
        Compiler c = new Compiler();
        Node n = new DoubleAddition(new DoubleLiteral(5), new DoubleLiteral(6));
        Program p = c.compile(n);
        assertEquals(3, p.getLength());
        assertTrue(p.get(0) instanceof BDPUSH);
        assertEquals(new BDPUSH(5).toString(), p.get(0).toString());
        assertTrue(p.get(1) instanceof BDPUSH);
        assertEquals(new BDPUSH(6).toString(), p.get(1).toString());
        assertTrue(p.get(2) instanceof DADD);
    }

    @Test
    public void testDoubleSubtraction() throws Exception {
        Compiler c = new Compiler();
        Node n = new DoubleSubtraction(new DoubleLiteral(5), new DoubleLiteral(6));
        Program p = c.compile(n);
        assertEquals(3, p.getLength());
        assertTrue(p.get(0) instanceof BDPUSH);
        assertEquals(new BDPUSH(5).toString(), p.get(0).toString());
        assertTrue(p.get(1) instanceof BDPUSH);
        assertEquals(new BDPUSH(6).toString(), p.get(1).toString());
        assertTrue(p.get(2) instanceof DSUB);
    }

    @Test
    public void testDoubleMultiplication() throws Exception {
        Compiler c = new Compiler();
        Node n = new DoubleMultiplication(new DoubleLiteral(5), new DoubleLiteral(6));
        Program p = c.compile(n);
        assertEquals(3, p.getLength());
        assertTrue(p.get(0) instanceof BDPUSH);
        assertEquals(new BDPUSH(5).toString(), p.get(0).toString());
        assertTrue(p.get(1) instanceof BDPUSH);
        assertEquals(new BDPUSH(6).toString(), p.get(1).toString());
        assertTrue(p.get(2) instanceof DMUL);
    }

    @Test
    public void testDoubleDivision() throws Exception {
        Compiler c = new Compiler();
        Node n = new DoubleDivision(new DoubleLiteral(5), new DoubleLiteral(6));
        Program p = c.compile(n);
        assertEquals(3, p.getLength());
        assertTrue(p.get(0) instanceof BDPUSH);
        assertEquals(new BDPUSH(5).toString(), p.get(0).toString());
        assertTrue(p.get(1) instanceof BDPUSH);
        assertEquals(new BDPUSH(6).toString(), p.get(1).toString());
        assertTrue(p.get(2) instanceof DDIV);
    }

    @Test
    public void testDoubleVariable() throws Exception {
        Compiler c = new Compiler();
        Node n = new DoubleVariable("x");
        Program p = c.compile(n);
        assertEquals(1, p.getLength());
        assertTrue(p.get(0) instanceof DLOAD);
        assertEquals(new DLOAD("x").toString(), p.get(0).toString());
    }

    @Test
    public void testDoubleToInt() throws Exception {
        Compiler c = new Compiler();
        Node n = new DoubleToInt(new DoubleLiteral(5.3));
        Program p = c.compile(n);
        assertEquals(2, p.getLength());
        assertTrue(p.get(0) instanceof BDPUSH);
        assertTrue(p.get(1) instanceof D2I);
        assertEquals(new D2I().toString(), p.get(1).toString());
    }

    @Test
    public void testIntToDouble() throws Exception {
        Compiler c = new Compiler();
        Node n = new IntToDouble(new IntLiteral(5));
        Program p = c.compile(n);
        assertEquals(2, p.getLength());
        assertTrue(p.get(0) instanceof BIPUSH);
        assertTrue(p.get(1) instanceof I2D);
        assertEquals(new I2D().toString(), p.get(1).toString());
    }
}
