package com.anu.calculator.expressionparser;

/**
 * FactorialExp: This class is used to represent the expression of a factorial
 */

public class FactorialExp extends Exp {
	private Exp exp;

	public FactorialExp(Exp exp) {
		this.exp = exp;
	}

	@Override
	public String show() {
		return "!(" + exp.show() + ")";
	}

	@Override
	public double evaluate() {
		return factorial(exp.evaluate());
	}

	private double factorial(double value)
	{
		if(value <= 0) return 0;
		if(value == 1) return 1;
		return value * factorial(value - 1);
	}

}
