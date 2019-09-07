package com.anu.calculator.expressionparser;

import com.anu.calculator.Expression;
import com.anu.calculator.ExpressionParserException;

/**
 * SineExpression: This class is used to represent the expression of sine
 *
 * @author: Samuel Brookes (u5380100)
 * @modified: Michael Betterton (u6797866)
 * 	- 05/09/2019: Refactored class to implement Expression interface
 * 	- 05/09/2019: Refactored name from Exp to Expression
 */

public class SineExpression implements Expression {
	private Expression expression;

	SineExpression(Expression expression) {
		this.expression = expression;
	}

	@Override
	public String show() {
		return "sin(" + expression.show() + ")";
	}

	@Override
	public double evaluate() throws ExpressionParserException {
		return Math.sin(Math.toRadians(expression.evaluate()));
	}

}
