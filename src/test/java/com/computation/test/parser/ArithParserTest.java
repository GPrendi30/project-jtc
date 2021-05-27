package com.computation.test.parser;

import com.computation.ast.Node;
import com.computation.ast.function.Function;
import com.computation.ast.function.FunctionList;
import com.computation.ast.intnodes.IntAddition;
import com.computation.ast.intnodes.IntDivision;
import com.computation.ast.intnodes.IntLiteral;
import com.computation.ast.intnodes.IntMultiplication;
import com.computation.ast.intnodes.IntNegation;
import com.computation.ast.intnodes.IntSubtraction;
import com.computation.ast.intnodes.IntVariable;
import com.computation.parser.ArithParser;
import com.computation.parser.Parser;
import org.junit.Test;

import static org.junit.Assert.assertEquals;


/**
 * This test class will test some aspects of the rules
 * of the Arith language.
 * 
 * <code>
 * EXPRESSION   ::= [ "+" | "-" ] TERM { ( "+" | "-" ) TERM }
 * TERM         ::= FACTOR { ( "*" | "/" ) FACTOR }
 * FACTOR       ::= Literal | 
 *                  Identifier| 
 *                  "(" EXPRESSION ")"
 * </code>
 */
public class ArithParserTest {

    @Test
    public void testLiteral() throws Exception {
        // setup
        final Parser parser = new ArithParser();
        // test input
        final String sourceCode = "12";
        // code under test
        final Node actualRoot = parser.parse(sourceCode);
        // expected tree
        final Node expectedRoot = new IntLiteral(12);
        // assertion
        assertEquals(expectedRoot.toString(), actualRoot.toString());
    }

    @Test
    public void testVariable() throws Exception {
        // setup
        final Parser parser = new ArithParser();
        // test input
        final String sourceCode = "x";
        // code under test
        final Node actualRoot = parser.parse(sourceCode);
        // expected tree
        final Node expectedRoot = new IntVariable("x");
        // assertion
        assertEquals(expectedRoot.toString(), actualRoot.toString());
    }

    @Test
    public void testNegation() throws Exception {
        // setup
        final Parser parser = new ArithParser();
        // test input
        final String sourceCode = "-11";
        // code under test
        final Node actualRoot = parser.parse(sourceCode);
        // expected tree
        final Node expectedRoot = new IntNegation(new IntLiteral(11));
        // assertion
        assertEquals(expectedRoot.toString(), actualRoot.toString());
    }

    @Test
    public void testUnaryPlus() throws Exception {
        // setup
        final Parser parser = new ArithParser();
        // test input
        final String sourceCode = "+11";
        // code under test
        final Node actualRoot = parser.parse(sourceCode);
        // expected tree
        final Node expectedRoot = new IntLiteral(11);
        // assertion
        assertEquals(expectedRoot.toString(), actualRoot.toString());
    }

    @Test
    public void testAddition() throws Exception {
        // setup
        final Parser parser = new ArithParser();
        // test input
        final String sourceCode = "12+2";
        // code under test
        final Node actualRoot = parser.parse(sourceCode);
        // expected tree
        final Node expectedRoot = new IntAddition(new IntLiteral(12), new IntLiteral(2));
        // assertion
        assertEquals(expectedRoot.toString(), actualRoot.toString());
    }

    @Test
    public void testSubtraction() throws Exception {
        // setup
        final Parser parser = new ArithParser();
        // test input
        final String sourceCode = "12-2";
        // code under test
        final Node actualRoot = parser.parse(sourceCode);
        // expected tree
        final Node expectedRoot = new IntSubtraction(new IntLiteral(12), new IntLiteral(2));
        // assertion
        assertEquals(expectedRoot.toString(), actualRoot.toString());
    }

    @Test
    public void testMultiplication() throws Exception {
        // setup
        final Parser parser = new ArithParser();
        // test input
        final String sourceCode = "12*2";
        // code under test
        final Node actualRoot = parser.parse(sourceCode);
        // expected tree
        final Node expectedRoot = new IntMultiplication(new IntLiteral(12), new IntLiteral(2));
        // assertion
        assertEquals(expectedRoot.toString(), actualRoot.toString());
    }

    @Test
    public void testDivision() throws Exception {
        // setup
        final Parser parser = new ArithParser();
        // test input
        final String sourceCode = "12/2";
        // code under test
        final Node actualRoot = parser.parse(sourceCode);
        // expected tree
        final Node expectedRoot = new IntDivision(new IntLiteral(12), new IntLiteral(2));
        // assertion
        assertEquals(expectedRoot.toString(), actualRoot.toString());
    }


    @Test
    public void testParentheses() throws Exception {
        // setup
        final Parser parser = new ArithParser();
        // test input
        final String sourceCode = "(12)";
        // code under test
        final Node actualRoot = parser.parse(sourceCode);
        // expected tree
        final Node expectedRoot = new IntLiteral(12);
        // assertion
        assertEquals(expectedRoot.toString(), actualRoot.toString());
    }

    @Test
    public void testParentheses2() throws Exception {
        // setup
        final Parser parser = new ArithParser();
        // test input
        final String sourceCode = "-(12+4)";
        // code under test
        final Node actualRoot = parser.parse(sourceCode);
        // expected tree
        final Node expectedRoot = new IntNegation(new IntAddition(new IntLiteral(12), new IntLiteral(4)));
        // assertion
        assertEquals(expectedRoot.toString(), actualRoot.toString());
    }

    @Test
    public void testExpressionIntMultiplicationDivision() throws Exception {
        // setup
        final Parser parser = new ArithParser();
        // test input
        final String sourceCode = "(12*4)/3";
        // code under test
        final Node actualRoot = parser.parse(sourceCode);
        // expected tree
        final Node expectedRoot = new IntDivision(new IntMultiplication(new IntLiteral(12), new IntLiteral(4)), new IntLiteral(3));
        // assertion
        assertEquals(expectedRoot.toString(), actualRoot.toString());
    }

    @Test
    public void testExpressionIntNegationAddition() throws Exception {
        // setup
        final Parser parser = new ArithParser();
        // test input
        final String sourceCode = "-(9)+3";
        // code under test
        final Node actualRoot = parser.parse(sourceCode);
        // expected tree
        final Node expectedRoot = new IntAddition(new IntNegation(new IntLiteral(9)), new IntLiteral(3));
        // assertion
        assertEquals(expectedRoot.toString(), actualRoot.toString());
    }

    @Test
    public void testFormulaUnary() throws Exception {
        // setup
        final Parser parser = new ArithParser();
        // test input
        final String sourceCode = "SIN(3)";
        // code under test
        final Node actualRoot = parser.parse(sourceCode);
        // expected tree
        final Function expectedRoot = FunctionList.stringToFunction("SIN");
        expectedRoot.addParameter(new IntLiteral(3));
        // assertion
        assertEquals(expectedRoot.toString(), actualRoot.toString());
    }

    @Test
    public void testFormulaBinary() throws Exception {
        // setup
        final Parser parser = new ArithParser();
        // test input
        final String sourceCode = "SUM(3,4,5)";
        // code under test
        final Node actualRoot = parser.parse(sourceCode);
        // expected tree
        final Function expectedRoot = FunctionList.stringToFunction("SUM");
        expectedRoot.addParameter(new IntLiteral(3));
        expectedRoot.addParameter(new IntLiteral(4));
        expectedRoot.addParameter(new IntLiteral(5));
        // assertion
        assertEquals(expectedRoot.toString(), actualRoot.toString());
    }
}
