package com.anu.calculator.functionparser;

import com.anu.calculator.Expression;
import com.anu.calculator.exceptions.UnassignedVariableException;

import java.util.Stack;

/**
 * EqualityExpression: This class is used to represent equality and variable/function assignment
 *
 * @author: Michael Betterton (u6797866)
 * @modified: Samuel Brookes (u5380100)
 *  - 07/09/2019: changed exception to UnassignedVariableException
 *
 */

public class Parser implements com.anu.calculator.FunctionParser {
    @Override
    public Expression evaluate(String input, Stack<Expression> history) throws UnassignedVariableException {
        return null;
    }
}
