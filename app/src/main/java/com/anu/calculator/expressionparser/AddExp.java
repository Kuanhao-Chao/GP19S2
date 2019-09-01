package com.anu.calculator.expressionparser;

/**
 * AddExp: This class is used to represent the expression of addition
 */

public class AddExp extends Exp {
	private Exp exp;
	private Exp term;

	public AddExp(Exp term, Exp exp) {
		this.term = term;
		this.exp = exp;
	}

	@Override
	public String show() {
		return "(" + term.show() + " + " + exp.show() + ")";
	}

	@Override
	public double evaluate() {
		return (term.evaluate() + exp.evaluate());
	}

}