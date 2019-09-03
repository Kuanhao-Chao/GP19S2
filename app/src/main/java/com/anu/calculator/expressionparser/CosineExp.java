package com.anu.calculator.expressionparser;

/**
 * CosineExp: This class is used to represent the expression of cosine
 */

public class CosineExp extends Exp {
	private Exp exp;

	public CosineExp(Exp exp) {
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
