package com.anu.calculator.expressionparser;

/**
 * AtanExp: This class is used to represent the expression of arctangent
 */

public class AtanExp extends Exp {
	private Exp exp;

	public AtanExp(Exp exp) {
		this.exp = exp;
	}

	@Override
	public String show() {
		return "tan-1(" + exp.show() + ")";
	}

	@Override
	public double evaluate() {
		return Math.atan(exp.evaluate());
	}

}
