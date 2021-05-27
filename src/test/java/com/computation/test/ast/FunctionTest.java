package com.computation.test.ast;

import com.computation.ast.Node;
import com.computation.ast.NodeLiteral;
import com.computation.ast.NodeVariable;
import com.computation.ast.Type;
import com.computation.ast.function.Function;
import com.computation.ast.function.FunctionList;
import com.computation.ast.function.FunctionOperation;
import com.computation.instruction.Instruction;
import com.computation.instruction.doubleinstruction.BDPUSH;
import com.computation.instruction.doubleinstruction.DLOAD;
import com.computation.instruction.intinstruction.BIPUSH;
import com.computation.program.OperandStack;
import com.computation.program.Program;
import com.computation.program.Storage;
import com.computation.program.VariableTable;
import org.junit.Test;

import static org.junit.Assert.*;


public class FunctionTest {

    Function func1 = new Function("sum",
            new FunctionOperation() {
                @Override
                public void execute(final Storage storage) {
                    final OperandStack op = storage.getOperandStack();
                    final double a = op.dpop();
                    final double b = op.dpop();
                    op.dpush(a + b);
                }

                @Override
                public String toString() {
                    return "sum";
                }
            },
            Function.BINARY,
            Function.NO_LIMIT,
            Type.INT);

    Function func2 = new Function("cos", new FunctionOperation() {
                @Override
                public void execute(final Storage storage) {
                    final OperandStack op = storage.getOperandStack();
                    final double a = op.dpop();
                    op.dpush(Math.cos(a));
                }

                @Override
                public String toString() {
                    return "cos";
                }
            },
                    Function.UNARY,
                    Type.DOUBLE);

    @Test
    public void testGetType() throws Exception {

        assertEquals("INT", func1.getType().toString());

        // TODO find something that makes it double
        func1.addArgument(new NodeLiteral(5.0));
        assertEquals("INT", func1.getType().toString());
    }

    @Test
    public void testIsConstant() throws Exception {

        assertTrue(func1.isConstant());

        func1.addArgument(new NodeVariable("x"));
        assertFalse(func1.isConstant());

        // TODO isConstant has not 100% coverage
    }

    @Test
    public void testToStringAddArgument()  throws Exception {

        assertEquals("sum()", func1.toString());

        // test for addArgument method
        func1.addArgument(new NodeLiteral(5));
        assertEquals("sum(5)", func1.toString());
    }

    @Test
    public void testAddArgumentThrowsNumberOfArgumentsExcedeed() throws Exception {
        boolean thrown = false;

        func2.addArgument(new NodeLiteral(5));

        try {
            func2.addArgument(new NodeLiteral(5));
        } catch (Exception e) {
            thrown = true;
        }

        assertTrue(thrown);
    }

    @Test
    public void testGetFunctionOperation() throws Exception {

        assertEquals("sum", func1.getFunctionOperation().toString());

        // test with addArgument method
        func1.addArgument(new NodeLiteral(5));
        assertEquals("sum",func1.getFunctionOperation().toString());
    }

    @Test
    public void testRemoveAllArguments() throws Exception {

        assertEquals("sum()", func1.toString());

        // test for addArgument method
        func1.addArgument(new NodeLiteral(5));
        assertEquals("sum(5)", func1.toString());

        func1.removeAllArguments();
        assertEquals("sum()", func1.toString());
    }

    @Test
    public void testGetName() {
        assertEquals("sum", func1.getName());
    }

    @Test
    public void testCompileThrowException() throws Exception {

        boolean thrown = false;

        Program p = new Program();
        p.append(func1.getFunctionOperation());

        try {
            func1.compile(p);
        } catch (Exception e) {
            thrown = true;
        }

        assertTrue(thrown);
    }

    @Test
    public void testCompileThrowNoException() throws Exception {

        boolean notThrown = true;

        Program p = new Program();
        func2.addArgument(new NodeLiteral(5.0));
        p.append(func2.getFunctionOperation());

        try {
            func2.compile(p);
        } catch (Exception e) {
            notThrown = false;
        }

        assertTrue(notThrown);
    }

    // TODO add a throw exception with 'numArguments != Function.NO_LIMIT'
    //  (seems like the one before is already that case but coverage is not working 100%)

    //TODO  FunctionOperation ---> PRIVATE
    // maybe passes after calling the functions for testing (SIN,COS,...)
}
