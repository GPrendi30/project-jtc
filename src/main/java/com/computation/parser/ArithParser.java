package com.computation.parser;

import com.computation.ast.Node;
import com.computation.ast.doublenodes.DoubleLiteral;
import com.computation.ast.doublenodes.DoubleVariable;

import com.computation.ast.function.Function;
import com.computation.ast.function.FunctionList;
import com.computation.ast.intnodes.IntLiteral;

import com.computation.ast.wrappernodes.AdditionWrapper;
import com.computation.ast.wrappernodes.DivisionWrapper;
import com.computation.ast.wrappernodes.MultiplicationWrapper;
import com.computation.ast.wrappernodes.NegationWrapper;
import com.computation.ast.wrappernodes.SubtractionWrapper;

import com.computation.lexer.LexicalAnalyzer;
import com.computation.lexer.TokenType;
import com.computation.program.Program;
import com.computation.program.VariableTable;


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
 * FUNCTION     ::= SIN|COS|SUM (ARGUMENT {, ARGUMENT})
 * ARGUMENT     ::= RANGE | EXPRESSION
 * </code>
 */
public final class ArithParser implements Parser {
    //TODO fix parser
    //TODO add tests
    private LexicalAnalyzer lexer;

    /**
     * Parse a program in the Arith language.
     * @param sourceCode The source code of the program in the Arith language
     * @return an AST of the program
     */
    @Override
    public Node parse(final String sourceCode) throws Exception {
        final String src = sourceCode.replace(" ", "");
        this.lexer = new LexicalAnalyzer(src);
        // fetch first token
        lexer.fetchNextToken();
        // now parse the EXPRESSION
        final Node result = parseExpression();

        if (lexer.getCurrentToken().getType() != TokenType.END_OF_FILE) {
            throw new Exception("Extra parenthesis at "
                    + lexer.getCurrentToken().getStartPosition());
        }
        return result;
    }

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
    private Node parseExpression() throws Exception {
        // parses an expression to an AST

        boolean negated = false;
        boolean isAdd = false;

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
            left = new NegationWrapper(left);
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
                left = new AdditionWrapper(left, right);
            } else {
                left = new SubtractionWrapper(left, right);
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
    private Node parseTerm() throws Exception {
        boolean isMul = false;

        Node left = parseFactor();
        while (lexer.getCurrentToken().getType() != TokenType.END_OF_FILE) {

            if (lexer.getCurrentToken().getType() == TokenType.STAR) {
                isMul = true;
                lexer.fetchNextToken();

            } else if (lexer.getCurrentToken().getType() == TokenType.SLASH) {
                isMul = false;
                lexer.fetchNextToken();

            } else {
                break;
            }


            final Node right = parseFactor();
            if (isMul) {
                left = new MultiplicationWrapper(left, right);
            } else {
                left = new DivisionWrapper(left, right);
            }

        }

        return left;
    }

    //to fix cpd
    /*
    private boolean checkForOp(final TokenType t1, final TokenType t2) throws Exception {
        boolean res;
        if (lexer.getCurrentToken().getType() == t1) {
            res = true;
            lexer.fetchNextToken();
        }  else if (lexer.getCurrentToken().getType() == t2) {
            res = false;
            lexer.fetchNextToken();
        }  else {
            throw new Exception("Was expecting a " + t1 + " or " + t2 + ", got "
                    + lexer.getCurrentToken().getText());
        }

        return res;
    }*/

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
    private Node parseFactor() throws Exception {

        Node res;
        switch (lexer.getCurrentToken().getType()) {
            case DOUBLELITERAL :
                res = new DoubleLiteral(Double.parseDouble(lexer.getCurrentToken().getText()));

                lexer.fetchNextToken();
                break;

            case INTLITERAL:
                res = new IntLiteral(Integer.parseInt(lexer.getCurrentToken().getText()));

                lexer.fetchNextToken();
                break;

            case IDENTIFIER:
                res = new DoubleVariable(lexer.getCurrentToken().getText());

                lexer.fetchNextToken();
                break;

            case OPEN_PAREN:

                lexer.fetchNextToken();

                res = parseExpression();

                if (lexer.getCurrentToken().getType() != TokenType.CLOSED_PAREN) {
                    throw new Exception("MISSING PARENTHESIS at "
                            + lexer.getCurrentToken().getStartPosition());

                }

                lexer.fetchNextToken();
                break;

            case FUNCTION:
                res = parseFunction();
                break;

            default:
                throw new Exception("Illegal Character");
        }

        return res;
    }


    /**
     * Parse a function.
     * This assumes the lexer already points to the first token of this function.
     *
     * <p>EBNF:
     * <code>
     * FUNCTION  ::= SIN|COS|SUM(ARGUMENT {, ARGUMENT})
     * </code>
     * @return a Node representing the function
     */
    private Node parseFunction() throws Exception {
        final Function f = FunctionList.stringToFunction(lexer.getCurrentToken().getText());
        lexer.fetchNextToken();

        if (lexer.getCurrentToken().getType() == TokenType.OPEN_PAREN) {
            lexer.fetchNextToken();

            if (lexer.getCurrentToken().getType() != TokenType.CLOSED_PAREN) {
                f.addArgument(parseArguments());


                if (lexer.getCurrentToken().getType() == TokenType.COMMA) {
                    lexer.fetchNextToken();

                    while (lexer.getCurrentToken().getType() != TokenType.END_OF_FILE) {
                        f.addArgument(parseArguments());
                        //lexer.fetchNextToken();

                        if (lexer.getCurrentToken().getType() == TokenType.COMMA) {
                            lexer.fetchNextToken();
                            continue;
                        } else {
                            break;
                        }
                    }
                }
            }

            if (lexer.getCurrentToken().getType() == TokenType.CLOSED_PAREN) {
                lexer.fetchNextToken();
                return f;

            } else {
                throw new Exception("WAS EXPECTING A CLOSED PAREN got "
                        + lexer.getCurrentToken().getText());
            }
        }
        throw new Exception("WAS EXPECTING AN OPEN PAREN got "
                + lexer.getCurrentToken().getText());
    }



    /**
     * Parse an ARGUMENT.
     * This assumes the lexer already points to the first token of this argument.
     *
     * <p>EBNF:
     * <code>
     * ARGUMENT ::= RANGE | EXPRESSION
     * </code>
     *
     * @return a Node representing the argument
     */
    private Node parseArguments() throws Exception {
        // if (ranges)
        //TODO add ranges
        return parseExpression();
    }


    /**
     * main.
     * @param args args.
     * @throws Exception exp.
     */
    public static void main(final String[] args) throws Exception {
        final Parser p = new ArithParser();
        final Node result = p.parse("-SIN(-3.0)");

        final Program pr = new Program();
        final VariableTable vt = new VariableTable();
        result.compile(pr);
        System.out.println(pr.dexecute(vt));
    }

}
