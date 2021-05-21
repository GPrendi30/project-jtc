package com.computation.test.parser;

import com.computation.ast.*;
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
    public void testLiteral() {
        // setup
        final Parser parser = new ArithParser();
        // test input
        final String sourceCode = "12";
        // code under test
        final Node actualRoot = parser.parse(sourceCode);
        // expected tree
        final Node expectedRoot = new Literal(12);
        // assertion
        assertEquals(expectedRoot.toString(), actualRoot.toString());
    }

    @Test
    public void testVariable() {
        // setup
        final Parser parser = new ArithParser();
        // test input
        final String sourceCode = "x";
        // code under test
        final Node actualRoot = parser.parse(sourceCode);
        // expected tree
        final Node expectedRoot = new Variable("x");
        // assertion
        assertEquals(expectedRoot.toString(), actualRoot.toString());
    }

    @Test
    public void testNegation() {
        // setup
        final Parser parser = new ArithParser();
        // test input
        final String sourceCode = "-11";
        // code under test
        final Node actualRoot = parser.parse(sourceCode);
        // expected tree
        final Node expectedRoot = new Negation(new Literal(11));
        // assertion
        assertEquals(expectedRoot.toString(), actualRoot.toString());
    }

    @Test
    public void testUnaryPlus() {
        // setup
        final Parser parser = new ArithParser();
        // test input
        final String sourceCode = "+11";
        // code under test
        final Node actualRoot = parser.parse(sourceCode);
        // expected tree
        final Node expectedRoot = new Literal(11);
        // assertion
        assertEquals(expectedRoot.toString(), actualRoot.toString());
    }

    @Test
    public void testAddition() {
        // setup
        final Parser parser = new ArithParser();
        // test input
        final String sourceCode = "12+2";
        // code under test
        final Node actualRoot = parser.parse(sourceCode);
        // expected tree
        final Node expectedRoot = new Addition(new Literal(12), new Literal(2));
        // assertion
        assertEquals(expectedRoot.toString(), actualRoot.toString());
    }

    @Test
    public void testSubtraction() {
        // setup
        final Parser parser = new ArithParser();
        // test input
        final String sourceCode = "12-2";
        // code under test
        final Node actualRoot = parser.parse(sourceCode);
        // expected tree
        final Node expectedRoot = new Subtraction(new Literal(12), new Literal(2));
        // assertion
        assertEquals(expectedRoot.toString(), actualRoot.toString());
    }

    @Test
    public void testMultiplication() {
        // setup
        final Parser parser = new ArithParser();
        // test input
        final String sourceCode = "12*2";
        // code under test
        final Node actualRoot = parser.parse(sourceCode);
        // expected tree
        final Node expectedRoot = new Multiplication(new Literal(12), new Literal(2));
        // assertion
        assertEquals(expectedRoot.toString(), actualRoot.toString());
    }

    @Test
    public void testDivision() {
        // setup
        final Parser parser = new ArithParser();
        // test input
        final String sourceCode = "12/2";
        // code under test
        final Node actualRoot = parser.parse(sourceCode);
        // expected tree
        final Node expectedRoot = new Division(new Literal(12), new Literal(2));
        // assertion
        assertEquals(expectedRoot.toString(), actualRoot.toString());
    }

    @Test
    public void testParentheses() {
        // setup
        final Parser parser = new ArithParser();
        // test input
        final String sourceCode = "(12)";
        // code under test
        final Node actualRoot = parser.parse(sourceCode);
        // expected tree
        final Node expectedRoot = new Literal(12);
        // assertion
        assertEquals(expectedRoot.toString(), actualRoot.toString());
    }

    @Test
    public void testOpenParentheses() {
        // setup
        final Parser parser = new ArithParser();
        // test input
        final String sourceCode = "-(12+4)";
        // code under test
        final Node actualRoot = parser.parse(sourceCode);
        // expected tree
        final Node expectedRoot = new Negation(new Addition(new Literal(12), new Literal(4)));
        // assertion
        assertEquals(expectedRoot.toString(), actualRoot.toString());
    }

    @Test
    public void testClosedParentheses() {
        // setup
        final Parser parser = new ArithParser();
        // test input
        final String sourceCode = "-(12+4)+4";
        // code under test
        final Node actualRoot = parser.parse(sourceCode);
        // expected tree
        final Node expectedRoot = new Addition(new Negation(new Addition(new Literal(12), new Literal(4))),new Literal(4));
        // assertion
        assertEquals(expectedRoot.toString(), actualRoot.toString());
    }
    
}
