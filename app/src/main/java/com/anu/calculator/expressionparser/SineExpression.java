package com.anu.calculator.expressionparser;

import com.anu.calculator.Expression;

/**
 * SineExpression: This class is used to represent the expression of sine
 *
 * @author: Samuel Brookes (u5380100)
 * @modified: Michael Betterton (u6797866)
 * 	- 09/05/2019: Refactored class to implement Expression interface
 * 	- 09/05/2019: Refactored name from Exp to Expression
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
	public double evaluate() {
		return Math.sin(Math.toRadians(expression.evaluate()));
	}

}
