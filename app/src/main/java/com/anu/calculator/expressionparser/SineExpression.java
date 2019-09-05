package com.anu.calculator.expressionparser;

import com.anu.calculator.Expression;

/**
 * SineExpression: This class is used to represent the expression of sine
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
