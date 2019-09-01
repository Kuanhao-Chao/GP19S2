package com.anu.calculator.expressionparser;

/**
 * SubExp: This class is used to represent the expression of subtraction
 */

public class SubExp extends Exp {
	private Exp exp;
	private Exp term;


	public SubExp(Exp term, Exp exp) {
		this.term = term;
		this.exp = exp;

	}

	@Override
	public String show() {
		return "(" + term.show() + " - " + exp.show() + ")";
	}

	@Override
	public double evaluate() {
		return (term.evaluate() - exp.evaluate());
	}

}
