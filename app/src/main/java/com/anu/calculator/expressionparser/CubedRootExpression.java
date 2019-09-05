package com.anu.calculator.expressionparser;

import com.anu.calculator.Expression;

/**
 * CubedRootExpression: This class is used to represent the expression of cubed root
 *
 * @author: Samuel Brookes (u5380100)
 * @modified: Michael Betterton (u6797866)
 * 	- 09/05/2019: Refactored class to implement Expression interface
 * 	- 09/05/2019: Refactored name from Exp to Expression
 */

public class CubedRootExpression implements Expression {

    private Expression expression;

    CubedRootExpression(Expression expression)
    {
        this.expression = expression;
    }

    @Override
    public String show()
    {
        return Scripts.Operators.CUBE_ROOT.getUnicode() + "(" + expression.show() + ")";
    }

    @Override
    public double evaluate()
    {
        return Math.cbrt(expression.evaluate());
    }

}
