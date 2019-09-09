package com.anu.calculator.expressionparser;

import com.anu.calculator.Expression;
import com.anu.calculator.ParserException;
import com.anu.calculator.exceptions.MathematicalSyntaxException;

/**
 * PowerExpression: This class is used to represent the expression of an exponent
 *
 * @author: Samuel Brookes (u5380100)
 * @modified: Michael Betterton (u6797866)
 * 	- 05/09/2019: Refactored class to implement Expression interface
 * 	- 05/09/2019: Refactored name from Exp to Expression
 */

public class PowerExpression implements Expression {

	private static final String TAG = "POWER_EXPRESSION";
	private Expression base;
	private Expression pwr;
	private Integer precision;

	@Override
	public void updatePrecision(Integer precision)
	{
		this.precision = precision;
	}

	PowerExpression(Expression base, Expression pwr) {
		this.base = base;
		this.pwr = pwr;
	}

	@Override
	public String show() {

		return "(" + base.show() + "^" + pwr.show() + ")";
	}

	@Override
	public double evaluate() throws ParserException {
		try
		{
			double evaluation = Math.pow(base.evaluate(), pwr.evaluate());
			if(precision != null) return Double.parseDouble(String.format("%." + precision + "f", evaluation));
			else return evaluation;
		}
		catch(NullPointerException e)
		{
			throw new MathematicalSyntaxException(TAG, "Syntax error");
		}
	}
}
