package com.anu.calculator.expressionparser;

/**
 * MultiplyExp: This class is used to represent the expression of multiplication
 */

public class MultiplyExp extends Exp {
	private Exp term;
	private Exp factor;


	public MultiplyExp(Exp factor, Exp term) {
		this.factor = factor;		
		this.term = term;
	}

	@Override
	public String show() {
		return "(" + factor.show() + Scripts.Operators.MULTIPLY.getUnicode() + term.show() + ")";
	}

	@Override
	public double evaluate() {
		return (factor.evaluate() * term.evaluate());
	}

}