package com.anu.calculator.expressionparser;


/**
 * ArcCosineExp: This class is used to represent the expression of arccosine
 */

public class ArcCosineExp extends Exp {
	private Exp exp;

	public ArcCosineExp(Exp exp) {
		this.exp = exp;
	}

	@Override
	public String show() {
		return "cos" +
				Scripts.SuperScript.MINUS.getUnicode() +
				Scripts.SuperScript.ONE.getUnicode() +
				"(" + exp.show() + ")";
	}

	@Override
	public double evaluate() {
		return Math.toDegrees(Math.acos(exp.evaluate()));
	}

}
