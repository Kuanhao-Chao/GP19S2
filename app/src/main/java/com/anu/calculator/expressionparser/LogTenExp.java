package com.anu.calculator.expressionparser;

/**
 * LogTenExp: This class is used to represent the expression of log base 10
 */

public class LogTenExp extends Exp {
	private Exp exp;

	public LogTenExp(Exp exp) {
		this.exp = exp;
	}

	@Override
	public String show() {
		return "log10(" + exp.show() + ")";
	}

	@Override
	public double evaluate() {
		return Math.log10(exp.evaluate());
	}

}
