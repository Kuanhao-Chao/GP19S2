package com.anu.calculator.expressionparser;

import com.anu.calculator.Expression;

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
