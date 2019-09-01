package com.anu.calculator.expressionparser;

/**
 * AsineExp: This class is used to represent the expression of arcsine
 */

public class AsineExp extends Exp {
	private Exp exp;

	public AsineExp(Exp exp) {
		this.exp = exp;
	}

	@Override
	public String show() {
		return "sin-1(" + exp.show() + ")";
	}

	@Override
	public double evaluate() {
		return Math.asin(exp.evaluate());
	}

}
