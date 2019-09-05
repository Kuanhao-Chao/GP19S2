package com.anu.calculator.expressionparser;

import com.anu.calculator.Expression;

/**
 * LogNaturalExpression: This class is used to represent the expression of natural logarithm (base e)
 */

public class LogNaturalExpression implements Expression {
	private Expression expression;

	LogNaturalExpression(Expression expression) {
		this.expression = expression;
	}

	@Override
	public String show() {
		return "ln(" + expression.show() + ")";
	}

	@Override
	public double evaluate() {
		return Math.log(expression.evaluate());
	}

}
