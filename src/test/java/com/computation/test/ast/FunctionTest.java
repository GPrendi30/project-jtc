package com.computation.test.ast;

import com.computation.ast.NodeException;
import com.computation.ast.NodeLiteral;
import com.computation.ast.NodeVariable;
import com.computation.ast.Type;
import com.computation.ast.doublenodes.DoubleLiteral;
import com.computation.ast.doublenodes.DoubleVariable;
import com.computation.ast.function.Function;
import com.computation.ast.function.FunctionException;
import com.computation.ast.function.FunctionList;
import com.computation.ast.function.FunctionOperation;
import com.computation.ast.function.FunctionWithRanges;
import com.computation.ast.function.SumRange;
import com.computation.ast.intnodes.IntLiteral;
import com.computation.ast.intnodes.IntVariable;
import com.computation.instruction.InstructionException;
import com.computation.instruction.doubleinstruction.DADD;
import com.computation.instruction.intinstruction.BIPUSH;
import com.computation.instruction.intinstruction.IADD;
import com.computation.program.OperandStack;
import com.computation.program.Program;
import com.computation.program.Storage;
import com.computation.program.VariableTable;
import org.junit.Test;

import static com.computation.ast.function.FunctionList.SUM;
import static org.junit.Assert.*;

/**
 * The tests for the Function Classes.
 *
 * @author Di Pietro Enrico, Prendi Gerald.
 *
 */
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
            new Type[]{Type.INT, Type.DOUBLE},
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
            new Type[]{Type.INT, Type.DOUBLE},
                    Type.DOUBLE);

    @Test
    public void testGetType() throws Exception {

        assertEquals(Type.INT, func1.getType());

        assertEquals(Type.DOUBLE, func2.getType());
    }

    @Test
    public void testIsConstant() throws Exception {

        assertTrue(func1.isConstant());

        func1.addParameter(new IntVariable("x"));
        assertFalse(func1.isConstant());

        // TODO isConstant has not 100% coverage
    }

    @Test
    public void testToStringAddParameter()  throws Exception {

        assertEquals("sum()", func1.toString());

        // test for addParameter method
        func1.addParameter(new IntLiteral(5));
        assertEquals("sum(5)", func1.toString());
    }

    @Test
    public void testAddParameterThrowsNumberOfArgumentsExceeded() throws Exception {
        boolean thrown = false;

        func2.addParameter(new IntLiteral(5));

        try {
            func2.addParameter(new IntLiteral(5));
        } catch (Exception e) {
            thrown = true;
        }

        assertTrue(thrown);
    }

    @Test
    public void testGetFunctionOperation() throws Exception {

        assertEquals("sum", func1.getFunctionOperation().toString());

        // test with addParameter method
        func1.addParameter(new IntLiteral(5));
        assertEquals("sum",func1.getFunctionOperation().toString());
    }

    @Test
    public void testRemoveAllArguments() throws Exception {

        assertEquals("sum()", func1.toString());

        // test for addParameter method
        func1.addParameter(new IntLiteral(5));
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
        func2.addParameter(new DoubleLiteral(5.0));
        p.append(func2.getFunctionOperation());

        try {
            func2.compile(p);
        } catch (Exception e) {
            notThrown = false;
        }

        assertTrue(notThrown);
    }

    @Test
    public void testFunctionWithRanges() {

        FunctionWithRanges fwr = new FunctionWithRanges(
                "newFunction",
                new FunctionOperation() {
                    @Override
                    public void execute(final Storage storage) {
                        final OperandStack op = storage.getOperandStack();
                        op.dpush(op.dpop() + op.dpop());
                    }
                    @Override
                    public String toString() {
                        return "sum";
                    }
                },
                Type.DOUBLE);

        Function copy_fwr = fwr.copy();

        assertTrue(copy_fwr.getName() == fwr.getName());
        assertTrue(copy_fwr.getType() == fwr.getType());
    }

    @Test
    public void testCompileThrowsException() throws FunctionException {
        boolean thrown = false;
        Function sum = FunctionList.SUM.getFunction();
        Program p = new Program();
        sum.addParameter(new DoubleLiteral(5.0));

        try {
            sum.compile(p);
        } catch (NodeException e) {
            thrown = true;
        }

        assertTrue(thrown);
    }

    @Test
    public void testCheckType() throws FunctionException {

        Function funcReturnInt = new Function("sum",
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
                new Type[]{Type.INT},
                Type.INT);

        boolean thrown = false;
        Program p = new Program();

        try {
            funcReturnInt.addParameter(new DoubleVariable("ciao"));
            funcReturnInt.compile(p);
        } catch (NodeException e) {
            thrown = true;
        }

        assertTrue(thrown);
    }

    @Test
    public void testSumRangeCopy() {

        SumRange sr = new SumRange("sum_range",
                FunctionList.SUM.getFunction().getFunctionOperation(),
                Type.DOUBLE);

        Function sameFunc = sr.copy();

        assertTrue(sameFunc instanceof SumRange);
    }
}
