package com.anu.calculator.expressionparser;

/**
 * MultExp: This class is used to represent the expression of multiplication
 */

public class MultExp extends Exp {
	private Exp term;
	private Exp factor;


	public MultExp(Exp factor, Exp term) {
		this.factor = factor;		
		this.term = term;
	}

	@Override
	public String show() {
		return "(" + factor.show() + " * " + term.show() + ")";
	}

	@Override
	public double evaluate() {
		return (factor.evaluate() * term.evaluate());
	}

}