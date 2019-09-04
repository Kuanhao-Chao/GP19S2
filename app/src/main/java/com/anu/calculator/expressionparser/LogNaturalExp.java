package com.anu.calculator.expressionparser;

/**
 * LogNaturalExp: This class is used to represent the expression of natural logarithm (base e)
 */

public class LogNaturalExp extends Exp {
	private Exp exp;

	public LogNaturalExp(Exp exp) {
		this.exp = exp;
	}

	@Override
	public String show() {
		return "ln(" + exp.show() + ")";
	}

	@Override
	public double evaluate() {
		return Math.log(exp.evaluate());
	}

}
