package com.anu.calculator.expressionparser;

import com.anu.calculator.Expression;

/**
 * SquareRootExpression: This class is used to represent the expression of a square root
 */

public class SquareRootExpression implements Expression {
	private Expression expression;

	SquareRootExpression(Expression expression) {
		this.expression = expression;
	}

	@Override
	public String show() {
		return Scripts.Operators.SQRT.getUnicode() + "(" + expression.show() + ")";
	}

	@Override
	public double evaluate() {
		return Math.sqrt(expression.evaluate());
	}
}
