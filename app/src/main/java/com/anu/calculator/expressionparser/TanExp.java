package com.anu.calculator.expressionparser;

/**
 * TanExp: This class is used to represent the expression of tangent
 */

public class TanExp extends Exp {
	private Exp exp;

	public TanExp(Exp exp) {
		this.exp = exp;
	}

	@Override
	public String show() {
		return "tan(" + exp.show() + ")";
	}

	@Override
	public double evaluate() {
		return Math.tan(exp.evaluate());
	}

}
