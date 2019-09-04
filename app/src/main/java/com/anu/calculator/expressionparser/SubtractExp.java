package com.anu.calculator.expressionparser;

/**
 * SubtractExp: This class is used to represent the expression of subtraction
 */

public class SubtractExp extends Exp {
	private Exp exp;
	private Exp term;


	public SubtractExp(Exp term, Exp exp) {
		this.term = term;
		this.exp = exp;

	}

	@Override
	public String show() {
		return "(" + term.show() + Scripts.Operators.MINUS.getUnicode() + exp.show() + ")";
	}

	@Override
	public double evaluate() {
		return (term.evaluate() - exp.evaluate());
	}

}
