package com.anu.calculator.expressionparser;

/**
 * CosExp: This class is used to represent the expression of cosine
 */

public class CosExp extends Exp {
	private Exp exp;

	public CosExp(Exp exp) {
		this.exp = exp;
	}

	@Override
	public String show() {
		return "cos(" + exp.show() + ")";
	}

	@Override
	public double evaluate() {
		return Math.cos(exp.evaluate());
	}

}
