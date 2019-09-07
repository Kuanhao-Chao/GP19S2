package com.anu.calculator.expressionparser;

import com.anu.calculator.Expression;
import com.anu.calculator.ParserException;
import com.anu.calculator.exceptions.MathematicalSyntaxException;

/**
 * CosineExpression: This class is used to represent the expression of cosine
 *
 * @author: Samuel Brookes (u5380100)
 * @modified: Michael Betterton (u6797866)
 * 	- 05/09/2019: Refactored class to implement Expression interface
 * 	- 05/09/2019: Refactored name from Exp to Expression
 */

public class CosineExpression implements Expression {
	private Expression expression;

	CosineExpression(Expression expression) {
		this.expression = expression;
	}

	@Override
	public String show() {
		return "cos(" + expression.show() + ")";
	}

	@Override
	public double evaluate() throws ParserException {
		try
		{
			return Math.cos(Math.toRadians(expression.evaluate()));
		}
		catch(NullPointerException e)
		{
			throw new MathematicalSyntaxException(this.getClass().getName(), "Syntax error");
		}
	}

}
