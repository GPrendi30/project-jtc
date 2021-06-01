package com.computation.parser;

import com.computation.ast.Node;
import com.computation.ast.NodeException;
import com.computation.ast.Type;
import com.computation.ast.doublenodes.DoubleLiteral;
import com.computation.ast.doublenodes.DoubleVariable;

import com.computation.ast.function.Function;
import com.computation.ast.function.FunctionException;
import com.computation.ast.function.FunctionList;
import com.computation.ast.intnodes.IntLiteral;

import com.computation.ast.intnodes.IntVariable;
import com.computation.ast.range.ArrayNode;
import com.computation.ast.range.Range;
import com.computation.ast.wrappernodes.AdditionWrapper;
import com.computation.ast.wrappernodes.DivisionWrapper;
import com.computation.ast.wrappernodes.MultiplicationWrapper;
import com.computation.ast.wrappernodes.NegationWrapper;
import com.computation.ast.wrappernodes.SubtractionWrapper;

import com.computation.lexer.LexerException;
import com.computation.lexer.LexicalAnalyzer;
import com.computation.lexer.TokenType;
import com.spreadsheetmodel.cell.CellLocation;


/**
 * A Parser for our Arith language
 * (a simple language of arithmetic expressions).
 *
 * <code>
 * EXPRESSION   ::= [ "+" | "-" ] TERM { ( "+" | "-" ) TERM }
 * TERM         ::= FACTOR { ( "*" | "/" ) FACTOR }
 * FACTOR       ::= Literal |
 *                  Identifier|
 *                  "(" EXPRESSION ")" |
 *                  FUNCTION
 * FUNCTION     ::= SIN|COS|SUM (PARAMETER {, PARAMETER})
 * PARAMETER    ::= RANGE | EXPRESSION
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
    public Node parse(final String sourceCode) throws ArithException, LexerException {
        final String src = sourceCode.replace(" ", "");
        this.lexer = new LexicalAnalyzer(src);
        // fetch first token
        lexer.fetchNextToken();
        // now parse the EXPRESSION
        final Node result = parseExpression();

        if (lexer.getCurrentToken().getType() != TokenType.END_OF_FILE) {
            throw new ArithException("Extra parenthesis at "
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
    private Node parseExpression() throws ArithException, LexerException {
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
        if (negated) {
            left = new NegationWrapper(left);
        }

        // parsing for n number of terms
        while (lexer.getCurrentToken().getType() != TokenType.END_OF_FILE) {
            if (lexer.getCurrentToken().getType() == TokenType.PLUS) {
                isAdd = true;
                lexer.fetchNextToken();
            } else if (lexer.getCurrentToken().getType() == TokenType.MINUS) {
                lexer.fetchNextToken();
            } else {
                break;
            }

            //parsing right node.
            final Node right = parseTerm();

            left = isAdd
                ? new AdditionWrapper(left, right)
                : new SubtractionWrapper(left, right);
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
    private Node parseTerm() throws ArithException, LexerException {
        boolean isMul = false;

        Node left = parseFactor();
        while (lexer.getCurrentToken().getType() != TokenType.END_OF_FILE) {

            if (lexer.getCurrentToken().getType() == TokenType.STAR) {
                isMul = true;
                lexer.fetchNextToken();
            } else if (lexer.getCurrentToken().getType() == TokenType.SLASH) {
                lexer.fetchNextToken();
            } else {
                break;
            }

            final Node right = parseFactor();
            left =  isMul
                ? new MultiplicationWrapper(left, right)
                : new DivisionWrapper(left, right);
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
    private Node parseFactor() throws ArithException, LexerException {

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
                    throw new ArithException("MISSING PARENTHESIS at "
                            + lexer.getCurrentToken().getStartPosition());

                }

                lexer.fetchNextToken();
                break;

            case FUNCTION:
                res = parseFunction();
                break;

            default:
                throw new ArithException("Illegal Character");
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
    private Node parseFunction() throws ArithException, LexerException {
        final Function f;
        try {
            f = FunctionList.stringToFunction(lexer.getCurrentToken().getText());
        } catch (FunctionException exception) {
            throw new ArithException("Keyword not found, "
                    + lexer.getCurrentToken().getStartPosition(), exception);
        }
        lexer.fetchNextToken();

        if (lexer.getCurrentToken().getType() == TokenType.OPEN_PAREN) {
            lexer.fetchNextToken();

            if (lexer.getCurrentToken().getType() != TokenType.CLOSED_PAREN) {
                try {
                    f.addParameter(parseParameters());
                } catch (FunctionException exception) {
                    throw new ArithException(exception.getMessage() + " at position "
                            + lexer.getCurrentToken().getStartPosition(), exception);
                }


                if (lexer.getCurrentToken().getType() == TokenType.COMMA) {
                    lexer.fetchNextToken();

                    while (lexer.getCurrentToken().getType() != TokenType.END_OF_FILE) {
                        try {
                            f.addParameter(parseParameters());
                        } catch (FunctionException exception) {
                            throw new ArithException(exception.getMessage(), exception);
                        }
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
                throw new ArithException("WAS EXPECTING A CLOSED PAREN got "
                        + lexer.getCurrentToken().getText());
            }
        }
        throw new ArithException("WAS EXPECTING AN OPEN PAREN got "
                + lexer.getCurrentToken().getText());
    }



    /**
     * Parse an ARGUMENT.
     * This assumes the lexer already points to the first token of this argument.
     *
     * <p>EBNF:
     * <code>
     * PARAMETER ::= RANGE | EXPRESSION
     * </code>
     *
     * @return a Node representing the argument
     */
    private Node parseParameters() throws ArithException, LexerException {
        // if (ranges)
        boolean numericRange = false;
        if (lexer.getCurrentToken().getType() == TokenType.INTLITERAL) {
            numericRange = true;
        }

        Node left = parseExpression();

        if (lexer.getCurrentToken().getType() == TokenType.COLON) {
            lexer.fetchNextToken();
            final Node right = parseExpression();
            final Range range = new Range(left, right);
            left = numericRange
                    ? parseNumberRanges(range)
                    : parseVariableRanges(range);
        }

        return left;
    }

    private Node parseVariableRanges(final Range rangeNode) throws ArithException {
        final int[] startCoordinates = CellLocation.parse(rangeNode.getStart().toString());
        final int[] endCoordinates = CellLocation.parse(rangeNode.getEnd().toString());
        final int startX = startCoordinates[0];
        final int startY = startCoordinates[1];
        final int endX = endCoordinates[0];
        final int endY = endCoordinates[1];
        final ArrayNode arrayValues = new ArrayNode(Type.INT);

        try {
            for (int i = startY; i <= endY; i++) {
                for (int j = startX; j <= endX; j++) {
                    final CellLocation cl = new CellLocation(j, i);
                    arrayValues.append(new IntVariable(cl.toString()));
                }
            }
        } catch (NodeException exception) {
            throw new ArithException(exception.getMessage(), exception);
        }

        return arrayValues;
    }

    private Node parseNumberRanges(final Range rangeNode) throws ArithException {
        final int startIndex = Integer.parseInt(rangeNode.getStart().toString());
        final int endIndex = Integer.parseInt(rangeNode.getEnd().toString());
        final ArrayNode arrayValues = new ArrayNode(Type.INT);

        try {
            for (int i = startIndex; i <= endIndex; i++) {
                final Node tempNode = new IntLiteral(i);
                arrayValues.append(tempNode);
            }
        } catch (NodeException exception) {
            throw new ArithException(exception.getMessage(), exception);
        }

        return arrayValues;
    }



}
