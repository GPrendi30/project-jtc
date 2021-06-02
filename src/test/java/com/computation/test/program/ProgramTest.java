package com.computation.test.program;

import com.computation.instruction.Instruction;
import com.computation.instruction.InstructionException;
import com.computation.instruction.intinstruction.BIPUSH;
import com.computation.program.Program;
import org.junit.Test;

import static org.junit.Assert.*;


/**
 * Test append(), getLength(), get(), toString(), and execute() of Program
 * (and toString() and execute() of Instruction subclasses).
 */
public class ProgramTest {

    @Test
    public void testEmpty() {
        Program p = new Program();
        assertEquals(0, p.getLength());
        assertEquals("", p.toString());
    }

    @Test
    public void testAppend() {
        Program p = new Program();
        Instruction i = new BIPUSH(1);
        p.append(i);
        assertEquals(1, p.getLength());
        assertSame(i, p.get(0));
        assertEquals("  " + i.toString() + "\n", p.toString());
    }
    
    @Test
    public void testAppend2() {
        Program p = new Program();
        Instruction i1 = new BIPUSH(1);
        Instruction i2 = new BIPUSH(2);
        p.append(i1);
        p.append(i2);
        assertEquals(2, p.getLength());
        assertSame(i1, p.get(0));
        assertSame(i2, p.get(1));
        assertEquals(
            "  " + i1.toString() + "\n" +
            "  " + i2.toString() + "\n", 
            p.toString());
    }
    
    @Test
    public void testExecute() throws InstructionException {
        Program p = new Program();
        p.append(new BIPUSH(2));
        assertEquals(2, p.iexecute());
    }

    @Test
    public void testDexecute() throws InstructionException {
        Program p = new Program();
        p.append(new BIPUSH(2));
        p.append(new BIPUSH(4));
        double save = p.dexecute();
        assertTrue(save == 4.0);
    }
}
