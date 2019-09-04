package com.anu.calculator.expressionparser;

/**
 * PowerExp: This class is used to represent the expression of an exponent
 */

public class PowerExp extends Exp {
	private Exp base;
	private Exp pwr;

	public PowerExp(Exp base, Exp pwr) {
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
