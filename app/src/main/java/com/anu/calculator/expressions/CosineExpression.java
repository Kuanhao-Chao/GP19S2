package com.anu.calculator.expressions;

import com.anu.calculator.Expression;
import com.anu.calculator.ParserException;
import com.anu.calculator.exceptions.InfinityException;
import com.anu.calculator.exceptions.MathematicalSyntaxException;
import com.anu.calculator.utilities.Scripts;

/**
 * CosineExpression: This class is used to represent the expression of cosine
 *
 * @author: Samuel Brookes (u5380100)
 * @modified: Michael Betterton (u6797866)
 * 	- 05/09/2019: Refactored class to implement Expression interface
 * 	- 05/09/2019: Refactored name from Exp to Expression
 */

public class CosineExpression implements Expression {

	private static final String TAG = "COSINE_EXPRESSION";
	private Expression expression;
	private Boolean degrees;
	private Integer precision;

	@Override
	public void updatePrecision(Integer precision)
	{
		this.precision = precision;
	}

	public CosineExpression(Expression expression, Boolean degrees)
	{
		this.expression = expression;
		this.degrees = degrees;
	}

	@Override
	public String show() {
		return "cos(" +
				((degrees)? Scripts.Operators.DEGREES.getUnicode():
						Scripts.Operators.RADIANS.getUnicode()) + ")";
	}

	@Override
	public double evaluate() throws ParserException {
		try
		{
			double evaluation;
			if(degrees) evaluation = Math.cos(Math.toRadians(expression.evaluate()));
			else evaluation = Math.cos(expression.evaluate());

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
