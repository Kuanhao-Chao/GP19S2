package com.anu.calculator.expressionparser;

import com.anu.calculator.Expression;

/**
 * ArcSineExpression: This class is used to represent the expression of arcsine
 */

public class ArcSineExpression implements Expression {
	private Expression expression;

	ArcSineExpression(Expression expression) {
		this.expression = expression;
	}

	@Override
	public String show() {
		return "sin" +
				Scripts.SuperScript.MINUS.getUnicode() +
				Scripts.SuperScript.ONE.getUnicode() +
				"(" + expression.show() + ")";
	}

	@Override
	public double evaluate() {
		return Math.toDegrees(Math.asin(expression.evaluate()));
	}

}
