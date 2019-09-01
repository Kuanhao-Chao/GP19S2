package com.anu.calculator.expressionparser;

/**
 * LogNatExp: This class is used to represent the expression of natural logarithm (base e)
 */

public class LogNatExp extends Exp {
	private Exp exp;

	public LogNatExp(Exp exp) {
		this.exp = exp;
	}

	@Override
	public String show() {
		return "log(" + exp.show() + ")";
	}

	@Override
	public double evaluate() {
		return Math.log(exp.evaluate());
	}

}
