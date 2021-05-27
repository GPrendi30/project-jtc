package com.computation.test.ast;

import com.computation.ast.function.Function;
import com.computation.ast.function.FunctionList;
import com.computation.instruction.doubleinstruction.BDPUSH;
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


}
