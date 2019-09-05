package com.anu.calculator.expressionparser;

import com.anu.calculator.Expression;

/**
 * FactorialExpression: This class is used to represent the expression of a factorial
 */

public class FactorialExpression implements Expression {
	private Expression expression;

	FactorialExpression(Expression expression) {
		this.expression = expression;
	}

	@Override
	public String show() {
		return "!(" + expression.show() + ")";
	}

	@Override
	public double evaluate() {
		return factorial(expression.evaluate());
	}

	private double factorial(double value)
	{
		if(value <= 0) return 0;
		if(value == 1) return 1;
		return value * factorial(value - 1);
	}

}
