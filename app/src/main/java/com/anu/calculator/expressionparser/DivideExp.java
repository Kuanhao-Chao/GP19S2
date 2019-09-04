package com.anu.calculator.expressionparser;

/**
 * DivideExp: This class is used to represent the expression of division
 */

public class DivideExp extends Exp {
	private Exp factor;	
	private Exp term;

	public DivideExp(Exp factor, Exp term) {
		this.factor = factor;
		this.term = term;		
	}

	@Override
	public String show() {
		return "(" + factor.show() + Scripts.Operators.DIVIDE.getUnicode() + term.show() + ")";
	}

	@Override
	public double evaluate() {
		return (factor.evaluate() / term.evaluate());
	}


}