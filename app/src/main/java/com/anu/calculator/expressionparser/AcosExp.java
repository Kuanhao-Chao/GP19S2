package com.anu.calculator.expressionparser;

/**
 * AcosExp: This class is used to represent the expression of arccosine
 */

public class AcosExp extends Exp {
	private Exp exp;

	public AcosExp(Exp exp) {
		this.exp = exp;
	}

	@Override
	public String show() {
		return "cos-1(" + exp.show() + ")";
	}

	@Override
	public double evaluate() {
		return Math.acos(exp.evaluate());
	}

}
