package com;


import com.computation.ast.NodeException;
import com.computation.ast.function.FunctionException;
import com.computation.ast.range.RangeException;
import com.computation.ast.wrappernodes.WrapperNodeException;
import com.computation.instruction.Instruction;
import com.computation.instruction.InstructionException;
import com.computation.instruction.doubleinstruction.DDIV;
import com.computation.lexer.LexerException;
import com.computation.parser.ArithException;
import com.computation.program.CompilerException;
import com.computation.program.OperandStack;
import com.computation.program.Storage;
import com.computation.program.VariableTable;
import com.computation.program.VariableTableException;
import com.spreadsheetmodel.SpreadsheetException;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * The tests for the Exception Classes.
 *
 * @author Di Pietro Enrico, Prendi Gerald.
 *
 */
public class ExceptionTest {

    Throwable exceptionCause = new Throwable();

    // InstructionException

    public InstructionException instructionException() throws InstructionException {
        throw new InstructionException();
    }

    public InstructionException instructionException(final String message,
                                                     final Throwable cause)
            throws InstructionException {
        throw new InstructionException(message, cause);
    }


    @Test
    public void testInstructionException() {

        boolean thrown = false;
        try {
            instructionException();
        } catch (InstructionException exception) {
            thrown = true;
        }
        assertTrue(thrown);

        thrown = false;
        try {
            instructionException("Message", exceptionCause);
        } catch (InstructionException exception) {
            thrown = true;
        }
        assertTrue(thrown);
    }

    // NodeException

    public InstructionException nodeException() throws NodeException {
        throw new NodeException();
    }

    public InstructionException nodeException(final String message,
                                                     final Throwable cause)
            throws NodeException {
        throw new NodeException(message, cause);
    }

    @Test
    public void testNodeException() {

        boolean thrown = false;
        try {
            nodeException();
        } catch (NodeException exception) {
            thrown = true;
        }
        assertTrue(thrown);

        thrown = false;
        try {
            nodeException("Message", exceptionCause);
        } catch (NodeException exception) {
            thrown = true;
        }
        assertTrue(thrown);
    }

    // RangeException
    public RangeException rangeException() throws RangeException {
        throw new RangeException();
    }

    public InstructionException rangeException(final String message,
                                              final Throwable cause)
            throws RangeException {
        throw new RangeException(message, cause);
    }

    @Test
    public void testRangeException() {

        boolean thrown = false;
        try {
            rangeException();
        } catch (RangeException exception) {
            thrown = true;
        }
        assertTrue(thrown);

        thrown = false;
        try {
            rangeException("Message", exceptionCause);
        } catch (RangeException exception) {
            thrown = true;
        }
        assertTrue(thrown);
    }

    // FunctionException
    public FunctionException functionException() throws FunctionException {
        throw new FunctionException();
    }

    public FunctionException functionException(final String message,
                                               final Throwable cause)
            throws FunctionException {
        throw new FunctionException(message, cause);
    }

    @Test
    public void testFunctionException() {

        boolean thrown = false;
        try {
            functionException();
        } catch (FunctionException exception) {
            thrown = true;
        }
        assertTrue(thrown);

        thrown = false;
        try {
            functionException("Message", exceptionCause);
        } catch (FunctionException exception) {
            thrown = true;
        }
        assertTrue(thrown);
    }

    // WrapperNodeException
    public WrapperNodeException wrapperNodeException() throws WrapperNodeException {
        throw new WrapperNodeException();
    }

    public WrapperNodeException wrapperNodeException(final String message)
            throws WrapperNodeException {
        throw new WrapperNodeException(message);
    }

    public WrapperNodeException wrapperNodeException(final String message,
                                               final Throwable cause)
            throws WrapperNodeException {
        throw new WrapperNodeException(message, cause);
    }

    @Test
    public void testWrapperNodeException() {

        boolean thrown = false;
        try {
            wrapperNodeException();
        } catch (WrapperNodeException exception) {
            thrown = true;
        }
        assertTrue(thrown);

        thrown = false;
        try {
            wrapperNodeException("Message");
        } catch (WrapperNodeException exception) {
            thrown = true;
        }
        assertTrue(thrown);

        thrown = false;
        try {
            wrapperNodeException("Message", exceptionCause);
        } catch (WrapperNodeException exception) {
            thrown = true;
        }
        assertTrue(thrown);
    }

    // LexerException

    public LexerException lexerException(final String message)
            throws LexerException {
        throw new LexerException(message);
    }

    @Test
    public void testLexerException() {

        boolean thrown = false;
        try {
            lexerException("Message");
        } catch (LexerException exception) {
            thrown = true;
        }
        assertTrue(thrown);
    }

    // CompilerException
    public CompilerException compilerException() throws CompilerException {
        throw new CompilerException();
    }

    public CompilerException compilerException(final String message)
            throws CompilerException {
        throw new CompilerException(message);
    }

    public CompilerException compilerException(final String message,
                                                     final Throwable cause)
            throws CompilerException {
        throw new CompilerException(message, cause);
    }

    @Test
    public void testCompilerException() {

        boolean thrown = false;
        try {
            compilerException();
        } catch (CompilerException exception) {
            thrown = true;
        }
        assertTrue(thrown);

        thrown = false;
        try {
            compilerException("Message");
        } catch (CompilerException exception) {
            thrown = true;
        }
        assertTrue(thrown);

        thrown = false;
        try {
            compilerException("Message", exceptionCause);
        } catch (CompilerException exception) {
            thrown = true;
        }
        assertTrue(thrown);
    }

    // VariableTableException
    public VariableTableException variableTableException() throws VariableTableException {
        throw new VariableTableException();
    }

    public VariableTableException variableTableException(final String message,
                                               final Throwable cause)
            throws VariableTableException {
        throw new VariableTableException(message, cause);
    }

    @Test
    public void testVariableTableException() {

        boolean thrown = false;
        try {
            variableTableException();
        } catch (VariableTableException exception) {
            thrown = true;
        }
        assertTrue(thrown);

        thrown = false;
        try {
            variableTableException("Message", exceptionCause);
        } catch (VariableTableException exception) {
            thrown = true;
        }
        assertTrue(thrown);
    }

    // ArithException
    public ArithException arithException() throws ArithException {
        throw new ArithException();
    }

    @Test
    public void testArithException() {

        boolean thrown = false;
        try {
            arithException();
        } catch (ArithException exception) {
            thrown = true;
        }
        assertTrue(thrown);
    }

    // SpreadsheetException
    public SpreadsheetException spreadsheetException() throws SpreadsheetException {
        throw new SpreadsheetException();
    }

    public SpreadsheetException spreadsheetException(final String message,
                                                         final Throwable cause)
            throws SpreadsheetException {
        throw new SpreadsheetException(message, cause);
    }

    @Test
    public void testSpreadsheetException() {

        boolean thrown = false;
        try {
            spreadsheetException();
        } catch (SpreadsheetException exception) {
            thrown = true;
        }
        assertTrue(thrown);

        thrown = false;
        try {
            spreadsheetException("Message", exceptionCause);
        } catch (SpreadsheetException exception) {
            thrown = true;
        }
        assertTrue(thrown);
    }
}
