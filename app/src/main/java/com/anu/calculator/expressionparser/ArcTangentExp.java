package com.anu.calculator.expressionparser;

/**
 * ArcTangentExp: This class is used to represent the expression of arctangent
 */

public class ArcTangentExp extends Exp {
	private Exp exp;

	public ArcTangentExp(Exp exp) {
		this.exp = exp;
	}

	@Override
	public String show() {
		return "tan" +
				Scripts.SuperScript.MINUS.getUnicode() +
				Scripts.SuperScript.ONE.getUnicode() +
				"(" + exp.show() + ")";
	}

	@Override
	public double evaluate() {
		return Math.atan(exp.evaluate());
	}

}
