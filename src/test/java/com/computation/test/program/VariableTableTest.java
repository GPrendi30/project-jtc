package com.computation.test.program;

import com.computation.instruction.Instruction;
import com.computation.instruction.intinstruction.BIPUSH;
import com.computation.program.Program;
import com.computation.program.VariableTable;
import com.computation.program.VariableTableException;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.junit.Assert.assertSame;

public class VariableTableTest {

    @Test
    public void testRemove() {
        VariableTable vt = new VariableTable();
        vt.iset("four", 4);
        vt.iset("five", 5);
        vt.remove("five");

        boolean thrown = false;
        try{
            vt.getInt("five");
        } catch (Exception e) {
            thrown = true;
        }
        assertTrue(thrown);
    }

    @Test
    public void testGetInt () {
        VariableTable vt = new VariableTable();

        boolean thrown = false;
        try {
            vt.getInt("x");
        } catch (VariableTableException exception) {
            thrown = true;
        }
        assertTrue(thrown);
    }
}
