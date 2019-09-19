package com.anu.calculator.expressionparser;

import com.anu.calculator.Expression;
import com.anu.calculator.ParserException;

/**
 * EqualityExpression: This class is used to represent equality and variable/function assignment
 *
 * @author: Michael Betterton (u6797866)
 * @modified: Samuel Brookes (u5380100)
 * @modified: Howard Chao (u7022787)
 *  - 07/09/2019: added throw clause
 *  - 19/09/2019: added isSameVariable() and getExpression()
 *
 */

public class EqualityExpression implements Expression {

    private char variable;
    private Expression expression;
    private Integer precision;

    @Override
    public void updatePrecision(Integer precision)
    {
        this.precision = precision;
    }

    public EqualityExpression(char variable, Expression expression) {
        this.expression = expression;
        this.variable = variable;
    }

    @Override
    public String show() {
        return variable + "=" + expression.show();
    }

    @Override
    public double evaluate() throws ParserException {
        if(precision != null) return Double.parseDouble(String.format("%." + precision + "f", expression.evaluate()));
        else return expression.evaluate();
    }

    /**
     * Checks whether the stored variable is the same as the variable in question
     * @param token
     * @return boolean
     *
     */
    public boolean isSameVariable(String token)
    {
        return this.variable == token.charAt(0);
    }

    /**
     * Getter method for expression
     * @return Expression
     */
    public Expression getExpression()
    {
        return expression;
    }
}