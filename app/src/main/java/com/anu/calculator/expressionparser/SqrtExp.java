package com.anu.calculator.expressionparser;

/**
 * SqrtExp: This class is used to represent the expression of a square root
 */

public class SqrtExp extends Exp {
	private Exp exp;

	public SqrtExp(Exp exp) {
		this.exp = exp;
	}

	@Override
	public String show() {
		return "sqrt(" + exp.show() + ")";
	}

	@Override
	public double evaluate() {
		return Math.sqrt(exp.evaluate());
	}
}
