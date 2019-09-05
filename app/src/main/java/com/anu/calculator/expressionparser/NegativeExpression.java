package com.anu.calculator.expressionparser;

import com.anu.calculator.Expression;

/**
 * DoubleExpression: This class is used to represent a double literal
 */

public class NegativeExpression implements Expression {
	private Expression expression;

	NegativeExpression(Expression expression) {
		if(expression == null) System.out.println("expression is null!");
		this.expression = expression;
	}

	@Override
	public String show() {
		return "" + Scripts.Operators.NEGATIVE.getUnicode() + expression.show();
	}

	@Override
	public double evaluate() {
		return -1 * expression.evaluate();
	}
}
