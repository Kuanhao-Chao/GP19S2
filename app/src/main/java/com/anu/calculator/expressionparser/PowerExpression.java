package com.anu.calculator.expressionparser;

import com.anu.calculator.Expression;

/**
 * PowerExpression: This class is used to represent the expression of an exponent
 *
 * @author: Samuel Brookes (u5380100)
 * @modified: Michael Betterton (u6797866)
 * 	- 09/05/2019: Refactored class to implement Expression interface
 * 	- 09/05/2019: Refactored name from Exp to Expression
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

		return "(" + base.show() + "^" + pwr.show() + ")";
	}

	@Override
	public double evaluate() {
		return Math.pow(base.evaluate(), pwr.evaluate());
	}
}
