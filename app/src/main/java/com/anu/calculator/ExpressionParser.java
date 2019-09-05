package com.anu.calculator;

/**
 * This interface described how a Expression Parser should behave.
 */
public interface ExpressionParser {
    /**
     * Takes a string representation of an expression and returns it as an Expression. Not that the
     * definition of expression is recursive.
     *
     * @param expression The expression represented as a string.
     * @return The string after the tokenizer has been applied and parsed into a Expression.
     */
    Expression parse(String expression);
}
