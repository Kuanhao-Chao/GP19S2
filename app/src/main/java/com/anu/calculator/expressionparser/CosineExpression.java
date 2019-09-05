package com.anu.calculator.expressionparser;

import com.anu.calculator.Expression;

/**
 * CosineExpression: This class is used to represent the expression of cosine
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
	public double evaluate() {
		return Math.cos(Math.toRadians(expression.evaluate()));
	}

}
