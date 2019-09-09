package com.anu.calculator.functionparser;

import com.anu.calculator.Expression;
import com.anu.calculator.ParserException;
import com.anu.calculator.exceptions.UnassignedVariableException;
import com.anu.calculator.expressionparser.UnknownVariableExpression;

/**
 * EqualityExpression: This class is used to represent equality and variable/function assignment
 *
 * @author: Michael Betterton (u6797866)
 * @modified: Samuel Brookes (u5380100)
 *  - 07/09/2019: added throw clause
 *
 */

public class EqualityExpression implements Expression {

    private UnknownVariableExpression variable;
    private Expression expression;
    private Integer precision;

    @Override
    public void updatePrecision(Integer precision)
    {
        this.precision = precision;
    }

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
        if(precision != null) return Double.parseDouble(String.format("%." + precision + "f", expression.evaluate()));
        else return expression.evaluate();
    }

}