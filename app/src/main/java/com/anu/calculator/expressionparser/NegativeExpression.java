package com.anu.calculator.expressionparser;

import com.anu.calculator.Expression;

/**
 * DoubleExpression: This class is used to represent a double literal
 *
 * @author: Samuel Brookes (u5380100)
 * @modified: Michael Betterton (u6797866)
 * 	- 05/09/2019: Refactored class to implement Expression interface
 * 	- 05/09/2019: Refactored name from Exp to Expression
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
