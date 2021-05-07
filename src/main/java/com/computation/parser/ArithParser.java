package com.computation.parser;

//node imports
import com.computation.ast.Addition;
import com.computation.ast.Division;
import com.computation.ast.Literal;
import com.computation.ast.Multiplication;
import com.computation.ast.Negation;
import com.computation.ast.Node;
import com.computation.ast.Subtraction;
import com.computation.ast.Variable;

import com.computation.lexer.LexicalAnalyzer;
import com.computation.lexer.TokenType;

/**
 * A Parser for our Arith language
 * (a simple language of arithmetic expressions).
 *
 * <code>
 * EXPRESSION   ::= [ "+" | "-" ] TERM { ( "+" | "-" ) TERM }
 * TERM         ::= FACTOR { ( "*" | "/" ) FACTOR }
 * FACTOR       ::= Literal |
 *                  Identifier|
 *                  "(" EXPRESSION ")"
 * </code>
 */
public final class ArithParser implements Parser {

    private LexicalAnalyzer lexer;


    /**
     * Parse a program in the Arith language.
     * @param sourceCode The source code of the program in the Arith language
     * @return an AST of the program
     */
    @Override
    public Node parse(final String sourceCode) {
        this.lexer = new LexicalAnalyzer(sourceCode);
        // fetch first token
        lexer.fetchNextToken();
        // now parse the EXPRESSION
        return parseExpression();
    }
    /*
    public static void main(String[] args) {
        OperandStack op = new OperandStack();
        VariableTable vt = new VariableTable();
        Storage st = new Storage(op,vt);
        Program pr = new Program();
        Parser p = new ArithParser();
        Node res = p.parse("-(-(2+3)+7*7*9)");
        System.out.println(res.toString());
        res.compile(pr);
        int a = pr.execute();
        System.out.println(a);
    }
    */

    /**
     * Parse an expression.
     * This assumes the lexer already points to the first token of this expression.
     *
     * <p>EBNF:
     * <code>
     * EXPRESSION ::= [ "+" | "-" ] TERM { ( "+" | "-" ) TERM }
     * </code>
     *
     * @return a Node representing the expression
     */
    private Node parseExpression() {
        // parses an expression to an AST
        boolean negated = false;
        boolean isAdd;

        //checking the optional [+ | -]
        if (lexer.getCurrentToken().getType() == TokenType.MINUS) {
            negated = true;
            lexer.fetchNextToken();
        } else if (lexer.getCurrentToken().getType() == TokenType.PLUS) {
            lexer.fetchNextToken();
        }

        Node left = parseTerm();
        // negating if expression has a preceding -.
        if (negated) {
            left = new Negation(left);
        }

        // parsing for n number of terms
        while (lexer.getCurrentToken().getType() != TokenType.END_OF_FILE) {

            //checking for + or - opcodes.
            if (lexer.getCurrentToken().getType() == TokenType.PLUS) {
                isAdd = true;
                lexer.fetchNextToken();
            } else if (lexer.getCurrentToken().getType() == TokenType.MINUS) {
                isAdd = false;
                lexer.fetchNextToken();
            } else {
                break;
            }

            //parsing right node.
            final Node right = parseTerm();

            if (isAdd) {
                left = new Addition(left, right);
            } else {
                left = new Subtraction(left, right);
            }

        }
        return left;
    }

    /**
     * Parse a term.
     * This assumes the lexer already points to the first token of this term.
     *
     * <p>EBNF:
     * <code>
     * TERM ::= FACTOR { ( "*" | "/" ) FACTOR }
     * </code>
     *
     * @return a Node representing the term
     */
    private Node parseTerm() {
        Node left = parseFactor();

        lexer.fetchNextToken();
        boolean isMul;

        while (lexer.getCurrentToken().getType() != TokenType.END_OF_FILE) {

            if (lexer.getCurrentToken().getType() == TokenType.STAR) {
                isMul = true;
            }  else if (lexer.getCurrentToken().getType() == TokenType.SLASH) {
                isMul = false;
            }  else {
                break;
            }
            lexer.fetchNextToken();

            final Node right = parseFactor();
            if (isMul) {
                left = new Multiplication(left, right);
            } else {
                left = new Division(left, right);
            }

            lexer.fetchNextToken();

        }
        return left;
    }

    /**
     * Parse a factor.
     * This assumes the lexer already points to the first token of this factor.
     *
     * <p>EBNF:
     * <code>
     * FACTOR ::=
     *          Literal |
     *          Identifier |
     *          "(" EXPRESSION ")"
     * </code>
     *
     * @return a Node representing the factor
     */
    private Node parseFactor() {
        if (lexer.getCurrentToken().getType() == TokenType.LITERAL) {
            return new Literal(Integer.parseInt(lexer.getCurrentToken().getText()));
        } else if (lexer.getCurrentToken().getType() == TokenType.IDENTIFIER) {
            return new Variable(lexer.getCurrentToken().getText());
        } else if (lexer.getCurrentToken().getType() == TokenType.OPEN_PAREN) {
            lexer.fetchNextToken();
            return parseExpression();
        }
        return null;
    }

}
