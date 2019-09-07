package com.anu.calculator.expressionparser;

import com.anu.calculator.Expression;
import com.anu.calculator.ParserException;
import com.anu.calculator.exceptions.MathematicalSyntaxException;

/**
 * ArcSineExpression: This class is used to represent the expression of arcsine
 *
 * @author: Samuel Brookes (u5380100)
 * @modified: Michael Betterton (u6797866)
 * 	- 05/09/2019: Refactored class to implement Expression interface
 * 	- 05/09/2019: Refactored name from Exp to Expression
 */

public class ArcSineExpression implements Expression {

	private static final String TAG = "ARCSINE_EXPRESSION";
	private Expression expression;

	ArcSineExpression(Expression expression) {
		this.expression = expression;
	}

	@Override
	public String show() {
		return "sin" +
				Scripts.SuperScript.MINUS.getUnicode() +
				Scripts.SuperScript.ONE.getUnicode() +
				"(" + expression.show() + ")";
	}

	@Override
	public double evaluate()throws ParserException {
		try
		{
			return Math.toDegrees(Math.asin(expression.evaluate()));
		}
		catch(NullPointerException e)
		{
			throw new MathematicalSyntaxException(TAG, "Syntax error");
		}
	}

}
