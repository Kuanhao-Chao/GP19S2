package com.anu.calculator.expressionparser;

import com.anu.calculator.Expression;

/**
 * DoubleExpression: This class is used to represent a double literal
 *
 * @author: Samuel Brookes (u5380100)
 * @modified: Michael Betterton (u6797866)
 * 	- 09/05/2019: Refactored class to implement Expression interface
 * 	- 09/05/2019: Refactored name from Exp to Expression
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
