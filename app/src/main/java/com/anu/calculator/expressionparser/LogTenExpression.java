package com.anu.calculator.expressionparser;

import com.anu.calculator.Expression;

/**
 * LogTenExpression: This class is used to represent the expression of log base 10
 */

public class LogTenExpression implements Expression {
	private Expression expression;

	LogTenExpression(Expression expression) {
		this.expression = expression;
	}

	@Override
	public String show() {
		return "log" + Scripts.SubScript.ONE.getUnicode() +
				Scripts.SubScript.ZERO.getUnicode() + "(" + expression.show() + ")";
	}

	@Override
	public double evaluate() {
		return Math.log10(expression.evaluate());
	}

}
