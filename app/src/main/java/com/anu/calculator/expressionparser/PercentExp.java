package com.anu.calculator.expressionparser;

/**
 * PercentExp: This class is used to represent the expression of a percentage
 */

public class PercentExp extends Exp {
	private Exp exp;

	public PercentExp(Exp exp) {
		this.exp = exp;
	}

	@Override
	public String show() {

		return "(" + exp.show() + ")%";
	}

	@Override
	public double evaluate() {
		return exp.evaluate()/100;
	}
}
