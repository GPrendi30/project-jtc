package com.computation.test.ast;

import com.computation.ast.NodeException;
import com.computation.ast.Type;
import com.computation.ast.doublenodes.DoubleLiteral;
import com.computation.ast.function.Function;
import com.computation.ast.function.FunctionList;
import com.computation.ast.range.ArrayNode;
import com.computation.instruction.InstructionException;
import com.computation.instruction.doubleinstruction.BDPUSH;
import com.computation.instruction.intinstruction.BIPUSH;
import com.computation.program.Program;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * The tests for the FunctionList Classes.
 *
 * @author Di Pietro Enrico, Prendi Gerald.
 *
 */
public class FunctionListTest {


    @Test
    public void testStringToFunction() throws Exception {
        boolean thrown = false;

        try {
            FunctionList.stringToFunction("XYZ");
        } catch (Exception e) {
            thrown = true;
        }

        assertTrue(thrown);
    }

    @Test
    public void testSUM() throws InstructionException {
        Function SUM = FunctionList.SUM.getFunction();

        Program p = new Program();
        p.append(new BDPUSH(5.0));
        p.append(new BDPUSH(5.0));
        p.append(SUM.getFunctionOperation());
        p.iexecute();

        assertEquals("  BDPUSH 5.0" + "\n" +
                        "  BDPUSH 5.0" + "\n" +
                        "  sum" + "\n", p.toString());
    }

    @Test
    public void testSIN() throws InstructionException {
        Function SIN = FunctionList.SIN.getFunction();

        Program p = new Program();
        p.append(new BDPUSH(5.0));
        p.append(SIN.getFunctionOperation());
        p.iexecute();

        assertEquals("  BDPUSH 5.0" + "\n" +
                "  sin" + "\n", p.toString());
    }

    @Test
    public void testCOS() throws InstructionException {
        Function COS = FunctionList.COS.getFunction();

        Program p = new Program();
        p.append(new BDPUSH(5.0));
        p.append(COS.getFunctionOperation());
        p.iexecute();

        assertEquals("  BDPUSH 5.0" + "\n" +
                "  cos" + "\n", p.toString());
    }

    @Test
    public void testABS() throws InstructionException {
        Function ABS = FunctionList.ABS.getFunction();

        Program p = new Program();
        p.append(new BDPUSH(5.0));
        p.append(ABS.getFunctionOperation());
        p.iexecute();

        assertEquals("  BDPUSH 5.0" + "\n" +
                "  abs" + "\n", p.toString());
    }

    @Test
    public void testABS2() throws InstructionException {
        Function ABS = FunctionList.ABS.getFunction();

        Program p = new Program();
        p.append(new BDPUSH(-5.0));
        p.append(ABS.getFunctionOperation());
        p.iexecute();

        assertEquals("  BDPUSH -5.0" + "\n" +
                "  abs" + "\n", p.toString());
    }

    @Test
    public void testAVG() throws InstructionException {
        Function AVG = FunctionList.AVG.getFunction();

        Program p = new Program();
        p.append(new BDPUSH(5.0));
        p.append(new BIPUSH(4));
        p.append(AVG.getFunctionOperation());
        p.iexecute();

        assertEquals("  BDPUSH 5.0" + "\n" +
                "  BIPUSH 4" + "\n" +
                "  avg" + "\n", p.toString());
    }

    @Test
    public void testMAX() throws InstructionException {
        Function MAX = FunctionList.MAX.getFunction();

        Program p = new Program();
        p.append(new BDPUSH(5.0));
        p.append(new BIPUSH(4));
        p.append(MAX.getFunctionOperation());
        p.iexecute();

        assertEquals("  BDPUSH 5.0" + "\n" +
                "  BIPUSH 4" + "\n" +
                "  max" + "\n", p.toString());
    }

    @Test
    public void testMIN() throws InstructionException {
        Function MIN = FunctionList.MIN.getFunction();

        Program p = new Program();
        p.append(new BDPUSH(5.0));
        p.append(new BIPUSH(4));
        p.append(MIN.getFunctionOperation());
        p.iexecute();

        assertEquals("  BDPUSH 5.0" + "\n" +
                "  BIPUSH 4" + "\n" +
                "  min" + "\n", p.toString());
    }

    @Test
    public void testMOD() throws InstructionException {
        Function MOD = FunctionList.MOD.getFunction();

        Program p = new Program();
        p.append(new BDPUSH(5.0));
        p.append(new BIPUSH(4));
        p.append(MOD.getFunctionOperation());
        p.iexecute();

        assertEquals("  BDPUSH 5.0" + "\n" +
                "  BIPUSH 4" + "\n" +
                "  mod" + "\n", p.toString());
    }



    @Test
    public void testASUM() throws InstructionException {
        Function ASUM = FunctionList.ASUM.getFunction();

        Program p = new Program();
        p.append(new BDPUSH(5.0));
        p.append(new BIPUSH(4));
        p.append(ASUM.getFunctionOperation());
        p.iexecute();

        assertEquals("  BDPUSH 5.0" + "\n" +
                "  BIPUSH 4" + "\n" +
                "  sum" + "\n", p.toString());
    }

    @Test
    public void testASUM2() throws NodeException {
        Function ASUM = FunctionList.ASUM.getFunction();

        Program p = new Program();
        ArrayNode an = new ArrayNode(Type.DOUBLE);
        an.append(new DoubleLiteral(5.0));
        an.append(new DoubleLiteral(2.0));
        an.append(new DoubleLiteral(3.0));

        ASUM.addParameter(an);
        ASUM.compile(p);

        assertEquals("  BDPUSH 5.0\n" +
                "  BDPUSH 2.0\n" +
                "  sum\n" +
                "  BDPUSH 3.0\n" +
                "  sum\n", p.toString());
    }
}
