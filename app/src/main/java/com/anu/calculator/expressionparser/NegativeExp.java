package com.anu.calculator.expressionparser;

/**
 * DoubleExp: This class is used to represent a double literal
 */

public class NegativeExp extends Exp {
	private Exp exp;

	public NegativeExp(Exp exp) {
		if(exp == null) System.out.println("exp is null!");
		this.exp = exp;
	}

	@Override
	public String show() {
		return "" + Scripts.Operators.NEGATIVE.getUnicode() + exp.show();
	}

	@Override
	public double evaluate() {
		return -1 * exp.evaluate();
	}
}
