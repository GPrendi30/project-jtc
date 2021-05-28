package com.computation.test.ast;

import com.computation.ast.function.Function;
import com.computation.ast.function.FunctionList;
import com.computation.instruction.doubleinstruction.BDPUSH;
import com.computation.instruction.intinstruction.BIPUSH;
import com.computation.program.Program;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

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
    public void testSUM() {
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
    public void testSIN() {
        Function SIN = FunctionList.SIN.getFunction();

        Program p = new Program();
        p.append(new BDPUSH(5.0));
        p.append(SIN.getFunctionOperation());
        p.iexecute();

        assertEquals("  BDPUSH 5.0" + "\n" +
                "  sin" + "\n", p.toString());
    }

    @Test
    public void testCOS() {
        Function COS = FunctionList.COS.getFunction();

        Program p = new Program();
        p.append(new BDPUSH(5.0));
        p.append(COS.getFunctionOperation());
        p.iexecute();

        assertEquals("  BDPUSH 5.0" + "\n" +
                "  cos" + "\n", p.toString());
    }

    @Test
    public void testABS() {
        Function ABS = FunctionList.ABS.getFunction();

        Program p = new Program();
        p.append(new BDPUSH(-5.0));
        p.append(ABS.getFunctionOperation());
        p.iexecute();

        assertEquals("  BDPUSH -5.0" + "\n" +
                "  abs" + "\n", p.toString());
    }

    @Test
    public void testAVG() {
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
    public void testMAX() {
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
    public void testMIN() {
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
    public void testMOD() {
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


}
