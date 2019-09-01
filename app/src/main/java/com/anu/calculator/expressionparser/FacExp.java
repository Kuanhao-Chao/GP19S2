package com.anu.calculator.expressionparser;

/**
 * FacExp: This class is used to represent the expression of a factorial
 */

public class FacExp extends Exp {
	private Exp exp;

	public FacExp(Exp exp) {
		this.exp = exp;
	}

	@Override
	public String show() {
		return "!" + exp.show();
	}

	@Override
	public double evaluate() {
		return factorial(exp.evaluate());
	}

	private double factorial(double value)
	{
		if(value <= 0) return -1;
		if(value == 1) return 1;
		return value * factorial(value - 1);
	}

}
