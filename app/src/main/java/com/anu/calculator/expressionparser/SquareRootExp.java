package com.anu.calculator.expressionparser;

/**
 * SquareRootExp: This class is used to represent the expression of a square root
 */

public class SquareRootExp extends Exp {
	private Exp exp;

	public SquareRootExp(Exp exp) {
		this.exp = exp;
	}

	@Override
	public String show() {
		return Scripts.Operators.SQRT.getUnicode() + "(" + exp.show() + ")";
	}

	@Override
	public double evaluate() {
		return Math.sqrt(exp.evaluate());
	}
}
