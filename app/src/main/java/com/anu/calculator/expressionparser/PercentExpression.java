package com.anu.calculator.expressionparser;

import com.anu.calculator.Expression;
import com.anu.calculator.ParserException;
import com.anu.calculator.exceptions.MathematicalSyntaxException;

/**
 * PercentExpression: This class is used to represent the expression of a percentage
 *
 * @author: Samuel Brookes (u5380100)
 * @modified: Michael Betterton (u6797866)
 * 	- 05/09/2019: Refactored class to implement Expression interface
 * 	- 05/09/2019: Refactored name from Exp to Expression
 */

public class PercentExpression implements Expression {
	private Expression expression;

	PercentExpression(Expression expression) {
		this.expression = expression;
	}

	@Override
	public String show() {

		return "(" + expression.show() + ")%";
	}

	@Override
	public double evaluate() throws ParserException {
		try
		{
			return expression.evaluate()/100;
		}
		catch(NullPointerException e)
		{
			throw new MathematicalSyntaxException(this.getClass().getName(), "Syntax error");
		}
	}
}
