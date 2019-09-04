package com.anu.calculator.expressionparser;

/**
 * TangentExp: This class is used to represent the expression of tangent
 */

public class TangentExp extends Exp {
	private Exp exp;

	public TangentExp(Exp exp) {
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
