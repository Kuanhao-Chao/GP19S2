package com.anu.calculator.functionparser;

import com.anu.calculator.Expression;
import com.anu.calculator.expressionparser.UnknownVariableExpression;

/**
 * EqualityExpression: This class is used to represent equality and variable/function assignment
 *
 * @author: Michael Betterton (u6797866)
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
    public double evaluate() {
        return (expression.evaluate());
    }

}