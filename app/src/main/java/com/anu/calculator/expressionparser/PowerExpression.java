package com.anu.calculator.expressionparser;

import com.anu.calculator.Expression;

/**
 * PowerExpression: This class is used to represent the expression of an exponent
 */

public class PowerExpression implements Expression {
	private Expression base;
	private Expression pwr;

	PowerExpression(Expression base, Expression pwr) {
		this.base = base;
		this.pwr = pwr;
	}

	@Override
	public String show() {

		return "(" + base.show() + ")^(" + pwr.show() + ")";
	}

	@Override
	public double evaluate() {
		return Math.pow(base.evaluate(), pwr.evaluate());
	}
}
