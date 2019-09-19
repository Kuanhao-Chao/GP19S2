package com.anu.calculator;

import java.util.Stack;

/**
 * This interface described how a Expression Parser should behave.
 */
public interface ExpressionParser {
    /**
     * Takes a string representation of an expression and returns it as an Expression. Note that the
     * definition of expression is recursive.
     *
     * @param expression The expression represented as a string.
     * @return The string after the tokenizer has been applied and parsed into a Expression.
     */
    Expression parse(String expression) throws ParserException;

    /**
     * Takes a string representation of an expression and returns it as an Expression. Note that the
     * definition of expression is recursive.
     *
     * @param expression The expression represented as a string.
     * @param degrees Bool if degrees should be used for the calculation
     * @param precision The precision to use for the calculation as an int.
     * @return The string after the tokenizer has been applied and parsed into a Expression.
     */
    Expression parse(String expression, Boolean degrees, Integer precision) throws ParserException;

    /**
     * The parse method for parsing functions. Takes a history Stack as a parameter to search
     * for values that have been previously assigned to variables
     * @param expression
     * @param degrees
     * @param precision
     * @param history
     * @return Expression
     * @throws ParserException
     */
    Expression parse(String expression, Boolean degrees, Integer precision, Stack<Expression> history) throws ParserException;
}
