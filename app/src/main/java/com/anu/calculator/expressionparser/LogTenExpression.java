package com.anu.calculator.expressionparser;

import com.anu.calculator.Expression;

/**
 * LogTenExpression: This class is used to represent the expression of log base 10
 *
 * @author: Samuel Brookes (u5380100)
 * @modified: Michael Betterton (u6797866)
 * 	- 09/05/2019: Refactored class to implement Expression interface
 * 	- 09/05/2019: Refactored name from Exp to Expression
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
