package com.anu.calculator.expressionparser;

import com.anu.calculator.Expression;

/**
 * TangentExpression: This class is used to represent the expression of tangent
 */

public class TangentExpression implements Expression {
	private Expression expression;

	TangentExpression(Expression expression) {
		this.expression = expression;
	}

	@Override
	public String show() {
		return "tan(" + expression.show() + ")";
	}

	@Override
	public double evaluate() {
		return Math.tan(Math.toRadians(expression.evaluate()));
	}

}
