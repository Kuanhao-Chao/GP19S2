package com.anu.calculator.expressionparser;


import com.anu.calculator.Expression;

/**
 * ArcCosineExpression: This class is used to represent the expression of arccosine
 */

public class ArcCosineExpression implements Expression {
	private Expression expression;

	ArcCosineExpression(Expression expression) {
		this.expression = expression;
	}

	@Override
	public String show() {
		return "cos" +
				Scripts.SuperScript.MINUS.getUnicode() +
				Scripts.SuperScript.ONE.getUnicode() +
				"(" + expression.show() + ")";
	}

	@Override
	public double evaluate() {
		return Math.toDegrees(Math.acos(expression.evaluate()));
	}

}
