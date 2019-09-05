package com.anu.calculator.expressionparser;

import com.anu.calculator.Expression;

/**
 * DoubleExpression: This class is used to represent a double literal
 */

public class DoubleExpression implements Expression {
	private double value;

	DoubleExpression(double value) {
		this.value = value;
	}

	@Override
	public String show() {
		return "" + value;
	}

	@Override
	public double evaluate() {
		return value;
	}

}
