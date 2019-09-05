package com.anu.calculator.expressionparser;

import com.anu.calculator.Expression;

/**
 * PercentExpression: This class is used to represent the expression of a percentage
 *
 * @author: Samuel Brookes (u5380100)
 * @modified: Michael Betterton (u6797866)
 * 	- 09/05/2019: Refactored class to implement Expression interface
 * 	- 09/05/2019: Refactored name from Exp to Expression
 */

public class PercentExpression implements Expression {
	private Expression expression;

	PercentExpression(Expression expression) {
		this.expression = expression;
	}

	@Override
	public String show() {

		return "(" + expression.show() + ")%";
	}

	@Override
	public double evaluate() {
		return expression.evaluate()/100;
	}
}
