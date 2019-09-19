package com.anu.calculator.expressionparser;

import com.anu.calculator.Expression;
import com.anu.calculator.ParserException;
import com.anu.calculator.exceptions.InfinityException;
import com.anu.calculator.exceptions.MathematicalSyntaxException;

/**
 * CubedRootExpression: This class is used to represent the expression of cubed root
 *
 * @author: Samuel Brookes (u5380100)
 * @modified: Michael Betterton (u6797866)
 * 	- 05/09/2019: Refactored class to implement Expression interface
 * 	- 05/09/2019: Refactored name from Exp to Expression
 */

public class CubedRootExpression implements Expression {
    private static final String TAG = "CUBED_ROOT_EXPRESSION";
    private Expression expression;
    private Integer precision;

    @Override
    public void updatePrecision(Integer precision)
    {
        this.precision = precision;
    }

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
    public double evaluate() throws ParserException
    {
        try
        {
            double evaluation = Math.cbrt(expression.evaluate());

            if(evaluation == Double.POSITIVE_INFINITY) throw new InfinityException(TAG, "Number is too large for little old me");
            if(precision != null) return Double.parseDouble(String.format("%." + precision + "f", evaluation));
            else return evaluation;
        }
        catch(NullPointerException e)
        {
            throw new MathematicalSyntaxException(TAG, "Syntax error");
        }
    }

}
