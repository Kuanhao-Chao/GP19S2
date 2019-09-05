package com.anu.calculator.expressionparser;

import com.anu.calculator.Expression;

/**
 * ArcTangentExpression: This class is used to represent the expression of arctangent
 */

public class ArcTangentExpression implements Expression {
	private Expression expression;

	ArcTangentExpression(Expression expression) {
		this.expression = expression;
	}

	@Override
	public String show() {
		return "tan" +
				Scripts.SuperScript.MINUS.getUnicode() +
				Scripts.SuperScript.ONE.getUnicode() +
				"(" + expression.show() + ")";
	}

	@Override
	public double evaluate() {
		return Math.toDegrees(Math.atan(expression.evaluate()));
	}

}
