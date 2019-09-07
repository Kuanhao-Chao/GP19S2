package com.anu.calculator.expressionparser;

import com.anu.calculator.Expression;
import com.anu.calculator.ParserException;

/**
 * PowerExpression: This class is used to represent the expression of an exponent
 *
 * @author: Samuel Brookes (u5380100)
 * @modified: Michael Betterton (u6797866)
 * 	- 05/09/2019: Refactored class to implement Expression interface
 * 	- 05/09/2019: Refactored name from Exp to Expression
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
	public double evaluate() throws ParserException {
		return Math.pow(base.evaluate(), pwr.evaluate());
	}
}
