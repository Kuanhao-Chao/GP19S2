package com.anu.calculator.expressionparser;

import com.anu.calculator.Expression;
import com.anu.calculator.ParserException;
import com.anu.calculator.exceptions.UnassignedVariableException;
import com.anu.calculator.expressionparser.UnknownVariableExpression;

/**
 * EqualityExpression: This class is used to represent equality and variable/function assignment
 *
 * @author: Michael Betterton (u6797866)
 * @modified: Samuel Brookes (u5380100)
 * @modified: Howard Chao (u7022787)
 *  - 07/09/2019: added throw clause
 *
 */

public class EqualityExpression implements Expression {

    private UnknownVariableExpression variable;
    private Expression expression;

    EqualityExpression(UnknownVariableExpression variable, Expression expression) {
        this.expression = expression;
        this.variable = variable;
    }

    @Override
    public String show() {
        return variable.show() + "=" + expression.show() + ")";
    }

    @Override
    public double evaluate() throws ParserException {
        return (expression.evaluate());
    }

}