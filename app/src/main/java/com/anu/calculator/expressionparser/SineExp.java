package com.anu.calculator.expressionparser;

/**
 * SineExp: This class is used to represent the expression of sine
 */

public class SineExp extends Exp {
	private Exp exp;

	public SineExp(Exp exp) {
		this.exp = exp;
	}

	@Override
	public String show() {
		return "sin(" + exp.show() + ")";
	}

	@Override
	public double evaluate() {
		return Math.sin(exp.evaluate());
	}

}
