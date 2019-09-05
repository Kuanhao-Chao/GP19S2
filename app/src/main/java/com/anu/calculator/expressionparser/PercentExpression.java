package com.anu.calculator.expressionparser;

import com.anu.calculator.Expression;

/**
 * PercentExpression: This class is used to represent the expression of a percentage
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
	public double evaluate() {
		return expression.evaluate()/100;
	}
}
