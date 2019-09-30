package com.anu.calculator.expressions;

import com.anu.calculator.Expression;
import com.anu.calculator.ParserException;
import com.anu.calculator.exceptions.InfinityException;
import com.anu.calculator.exceptions.MathematicalSyntaxException;

/**
 * FactorialExpression: This class is used to represent the expression of a factorial
 *
 * @author: Samuel Brookes (u5380100)
 * @modified: Michael Betterton (u6797866)
 * 	- 05/09/2019: Refactored class to implement Expression interface
 * 	- 05/09/2019: Refactored name from Exp to Expression
 */

public class FactorialExpression implements Expression {

	private static final String TAG = "FACTORIAL_EXPRESSION";
	private Expression expression;
	private Integer precision;

	public FactorialExpression(Expression expression) {
		this.expression = expression;
	}

	@Override
	public String show() {
		return "(" + expression.show() + "!)";
	}

	@Override
	public double evaluate() throws ParserException {
		try
		{
			//evaluate the expression
			double evaluation = factorial(expression.evaluate());

			//if the value of evaluation is too large for a double type, throw an infinity exception
			if(evaluation == Double.POSITIVE_INFINITY) throw new InfinityException(TAG, "Number is too large for little old me.");

			//check if this expression is the root of the parsing tree
			if(precision != null) return Double.parseDouble(String.format("%." + precision + "f", evaluation));
			else return evaluation;
		}
		catch(NullPointerException e)
		{
			throw new MathematicalSyntaxException(TAG, "Syntax error");
		}
	}

	/**
	 * Calculates the factorial of a value.
	 * @author Samuel Brookes (u5380100)
	 * @param value : the value for which the factorial is to be calculated
	 * @return double : factorial of the value
	 */
	private double factorial(double value)
	{
		if(value <= 0) return 0;
		if(value == 1) return 1;
		return value * factorial(value - 1);
	}

	@Override
	public void updatePrecision(Integer precision)
	{
		this.precision = precision;
	}

}
