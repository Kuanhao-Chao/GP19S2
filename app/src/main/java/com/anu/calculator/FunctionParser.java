package com.anu.calculator;

import com.anu.calculator.Expression;

import java.util.Stack;

public interface FunctionParser {
    /**
     * This is the only interface between the UI and the Function parser. The UI will pass the latest
     * expression as a String in the Function parameter, it will also pass a history of Expressions
     * in the history parameter.
     *
     * For Example: evaluate("f=2x", [Expression(x=5),Expression(x=10),Expression(29*2)])
     * Returns: 20
     *
     * @param input The input provided the user.
     * @param history A stack of parsed expressions previously returned from the Function Parser.
     * @return The input provided from the user as a parsed expression.
     */
    Expression evaluate(String input, Stack<Expression> history) throws ParserException;
}
