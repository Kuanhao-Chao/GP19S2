package com.anu.calculator;

import com.anu.calculator.utilities.History;

/**
 * This interface described how a Expression Parser should behave.
 */
public interface Parser {

    /**
     * Takes a string representation of an expression and returns it as an Expression.
     * Takes a History object and user preferences as parameters.
     *
     * @param expression
     * @param degrees
     * @param precision
     * @param history
     * @return Expression
     * @throws ParserException
     */
    Expression parse(String expression, Boolean degrees, Integer precision, History history) throws ParserException;
}
