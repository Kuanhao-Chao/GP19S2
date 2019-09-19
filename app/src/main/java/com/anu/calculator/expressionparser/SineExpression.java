package com.anu.calculator.expressionparser;

import com.anu.calculator.Expression;
import com.anu.calculator.ParserException;
import com.anu.calculator.exceptions.InfinityException;
import com.anu.calculator.exceptions.MathematicalSyntaxException;

/**
 * SineExpression: This class is used to represent the expression of sine
 *
 * @author: Samuel Brookes (u5380100)
 * @modified: Michael Betterton (u6797866)
 * 	- 05/09/2019: Refactored class to implement Expression interface
 * 	- 05/09/2019: Refactored name from Exp to Expression
 */

public class SineExpression implements Expression {

	private final String TAG = "SINE_EXPRESSION";
	private Expression expression;
	private Boolean degrees;
	private Integer precision;

	@Override
	public void updatePrecision(Integer precision)
	{
		this.precision = precision;
	}

	SineExpression(Expression expression, Boolean degrees)
	{
		this.expression = expression;
		this.degrees = degrees;
	}

	@Override
	public String show() {
		return "sin(" + expression.show() +
				((degrees)?Scripts.Operators.DEGREES.getUnicode():
						Scripts.Operators.RADIANS.getUnicode()) + ")";
	}

	@Override
	public double evaluate() throws ParserException {
		try
		{
			double evaluation;
			if(degrees) evaluation = Math.sin(Math.toRadians(expression.evaluate()));
			else evaluation = Math.sin(expression.evaluate());

			if(evaluation == Double.POSITIVE_INFINITY) throw new InfinityException(TAG, "Number is too large for little old me.");
			if(precision != null) return Double.parseDouble(String.format("%." + precision + "f", evaluation));
			else return evaluation;
		}
		catch(NullPointerException e)
		{
			throw new MathematicalSyntaxException(TAG, "Syntax error");
		}
	}

}
