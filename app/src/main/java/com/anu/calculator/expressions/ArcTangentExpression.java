package com.anu.calculator.expressions;

import com.anu.calculator.Expression;
import com.anu.calculator.ParserException;
import com.anu.calculator.exceptions.InfinityException;
import com.anu.calculator.exceptions.MathematicalSyntaxException;
import com.anu.calculator.utilities.Scripts;

/**
 * ArcTangentExpression: This class is used to represent the expression of arctangent
 *
 * @author: Samuel Brookes (u5380100)
 * @modified: Michael Betterton (u6797866)
 * 	- 05/09/2019: Refactored class to implement Expression interface
 * 	- 05/09/2019: Refactored name from Exp to Expression
 */

public class ArcTangentExpression implements Expression {

	private static final String TAG = "ARCTANGENT_EXPRESSION";
	private Expression expression;
	private Boolean degrees;
	private Integer precision;

	@Override
	public void updatePrecision(Integer precision)
	{
		this.precision = precision;
	}

	public ArcTangentExpression(Expression expression, Boolean degrees)
	{
		this.expression = expression;
		this.degrees = degrees;
	}

	@Override
	public String show() {
		return "tan" +
				Scripts.SuperScript.MINUS.getUnicode() +
				Scripts.SuperScript.ONE.getUnicode() +
				"(" + expression.show() +
				((degrees)?Scripts.Operators.DEGREES.getUnicode():
						Scripts.Operators.RADIANS.getUnicode()) + ")";
	}

	@Override
	public double evaluate() throws ParserException {
		try
		{
			double evaluation;
			if(degrees) evaluation = Math.toDegrees(Math.atan(expression.evaluate()));
			else evaluation = Math.atan(expression.evaluate());

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
